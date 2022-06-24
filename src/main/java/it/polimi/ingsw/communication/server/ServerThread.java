package it.polimi.ingsw.communication.server;

import it.polimi.ingsw.communication.common.*;
import it.polimi.ingsw.communication.common.messages.*;
import it.polimi.ingsw.controller.ComplexLobby;
import it.polimi.ingsw.controller.GameManager;
import it.polimi.ingsw.model.MovedStudent;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.board.GameComponents;

import java.util.Comparator;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ServerThread extends Thread{
    private static int counter = 0;
    private int id = ++counter;

    private final GameManager gameManager;
    private String username;

    private final Socket clientSocket;
    private BufferedReader input;
    private PrintWriter output;
    private ObjectToJSON sendMessage;
    private JSONtoObject receiveMessage;
    private ComplexLobby currentCL;

    private PingPongThread pingPongThread;

    private Object preMageLock;
    private Object preCardLock;

    private Object afterMageLock;
    private Object afterCardLock;

    public ServerThread(Socket clientSocket, GameManager gameManager) throws IOException{
        this.clientSocket = clientSocket;
        this.gameManager = gameManager;
        sendMessage = new ObjectToJSON(clientSocket);
        receiveMessage = new JSONtoObject(clientSocket);
        OutputStreamWriter outputStreamWriter = null;

        //creating the objects for input and output
        input = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
        outputStreamWriter = new OutputStreamWriter(this.clientSocket.getOutputStream());
        output = new PrintWriter(new BufferedWriter(outputStreamWriter), true);
        //the thread is started
        start();
        System.out.println("ServerThread " + this.id + " starded");

    }

    public PingPongThread getPingPongThread() {
        return pingPongThread;
    }

    @Override
    public void run() {
        JSONtoObject receiveMessage = new JSONtoObject(clientSocket);
        ObjectToJSON sendMessage = new ObjectToJSON(clientSocket);
        pingPongThread = new PingPongThread(clientSocket, "server", this);
        //receivePingSendPong();
        login();
        if(currentCL == null || clientSocket.isClosed()){
            interrupt();
            return;
        } else {

            while(!isMyTurn()){
                if(clientSocket.isClosed()){
                    System.out.println(username + " socket is closed 1.\r");
                    interrupt();
                    break;
                }
            }

            chooseMage();

            while (!isMyTurn()){
                if(clientSocket.isClosed()){
                    System.out.println(username + " socket is closed 2.\r");
                    interrupt();
                    break;
                }
            }

            sendModel();

            playCard();

            while (!isMyTurn()){
                if(clientSocket.isClosed()){
                    System.out.println(username + " socket is closed 3.\r");
                    interrupt();
                    break;
                }
            }

            boolean endGame = false;
            int i = 0;
            while (!endGame) {
                while (isMyTurn()){

                    if(clientSocket.isClosed()){
                        System.out.println(username + " socket is closed.\r");
                        endGame = true;
                        interrupt();
                        break;
                    }

                    System.out.println("it is " + username + " turn");
                    sendMessage.sendTurnMessage();
                    MessageInterface message = receiveMessage.receiveMessage();
                    if(message == null){
                        return;
                    }
                    MessageType messageCode = null;
                    try {
                         messageCode = message.getCode();
                        System.out.println("received " +  messageCode.toString());
                    } catch (NullPointerException e){
                        System.out.println("Connection error.");
                        return;
                    }

                    switch (messageCode){
                        case PINGPONG:
                            sendMessage.sendPingPongMessage(new PingPongMessage("pong"));
                            break;
                        case MOTHERNATURE:
                            moveMotherNature();
                            sendModel();
                            Player winner = currentCL.getGame().winCondition();
                            if( winner != null && !endGame){
                                endGame = true;
                                currentCL.endGame(winner);
                            }
                            break;
                        case CLOUDCARD:

                            selectCloudCard();
                            if (!currentCL.getActivePlayer().equals(currentCL.getPlayerOrder().get(currentCL.getPlayerOrder().size()-1))){
                                currentCL.changeActivePlayer();
                            } else {
                                currentCL.setActivePlayer(currentCL.getPlayerOrder().get(0));
                                System.out.println("ish should be " + currentCL.getPlayerOrder().get(0).getID_player() + " turn");
                                currentCL.getGame().refillCloudCards();
                            }

                            break;

                        case CARD:
                            playCardInGame();
                            break;
                        case MODEL:
                            sendModelInGame();
                            break;
                        case STUDENT:
                            moveStudent();
                            sendModel();
                            break;
                        case CHARACTERCHOICE:
                            playCharacter(message);
                            break;
                    }
                    //methods call
                    //check if a player has won after a turn
                    if(currentCL.getGame() != null && !endGame){
                        Player winner = currentCL.getGame().winCondition();
                        if( winner != null){
                            endGame = true;
                            currentCL.endGame(winner);
                        }
                    }
                }
            }
        }
    }
    /**
     * receives the username and preferences for the game that a player wants
     */
    private void login(){

        LoginMessage loginMessage = null;

        try{
            loginMessage = (LoginMessage) receiveMessage.receiveMessage();
        }catch (ClassCastException e){
            System.out.println("Connection error.\r");
        }
        if(loginMessage == null){
            return;
        }

        username = loginMessage.getUsername();
        int numOfPlayers = loginMessage.getNumOfPlayers();
        boolean isPro = loginMessage.isPro();

        if(gameManager.loginSocket(username, numOfPlayers, isPro, clientSocket)){
            currentCL = gameManager.getPlayerComplexLobby(username);
            preMageLock = currentCL.getPreMageLock();
            preCardLock = currentCL.getPreCardLock();
            afterMageLock = currentCL.getAfterMageLock();
            afterCardLock = currentCL.getAfterCardLock();

            sendMessage.sendNoError();
            sendMessage.sendLobbiesMessage(new LobbiesMessage(gameManager.getPlayerComplexLobby(username).getID()));
        } else {
            sendMessage.sendLoginError();
            login();
        }
    }

    /**
     * selects the mage that a player wants
     */
    private void chooseMage(){
        boolean ok = false;
        while(!ok){
            MessageType messageCode = receiveMessage.receiveMessage().getCode();
            if(messageCode == MessageType.MAGE){
                ok = currentCL.selectMage(clientSocket, currentCL.getPlayerByID(username), receiveMessage, sendMessage);
            }
        }
    }

    /**
     * plays an assistant card
     */
    private void playCard(){
        boolean ok = false;
        while (!ok){
            MessageInterface message = receiveMessage.receiveMessage();
            if(message==null){
                return;
            }
            MessageType messageType = message.getCode();

            if(messageType == MessageType.CARD){
                ok = currentCL.playCard(sendMessage, receiveMessage);
            }
        }
    }

    private void playCardInGame(){

        if(!currentCL.playCard(sendMessage, receiveMessage)){
            playCard();
        }
    }

    /**
     * sends the table with the game components
     */
    private void sendModel(){
        ModelMessage message = null;
        try{
            message = (ModelMessage) receiveMessage.receiveMessage();
        } catch(ClassCastException e){
            interrupt();
            return;
        }
        if(message.getCode() == MessageType.MODEL){
            sendMessage.sendModelMessage(currentCL.sendModel(username));
        }
    }

    private void sendModelInGame(){
        sendMessage.sendModelMessage(currentCL.sendModel(username));
    }

    /**
     * moves the students
     */
    private void moveStudent(){
        MoveStudentMessage message = null;
        try{
            message = (MoveStudentMessage) receiveMessage.receiveMessage();
        } catch(ClassCastException e){
            interrupt();
            return;
        }

        ArrayList<MovedStudent> students = new ArrayList<>();

        students.add(new MovedStudent( message.getStudent1Entrance() - 1,  message.getStudent1WhereToPut(), message.getIndexIslandIf1ToIsland()));
        students.add(new MovedStudent( message.getStudent2Entrance() - 1 ,  message.getStudent2WhereToPut(), message.getIndexIslandIf2ToIsland()));
        students.add(new MovedStudent( message.getStudent3Entrance() - 1 ,  message.getStudent3WhereToPut(), message.getIndexIslandIf3ToIsland()));
        if(currentCL.getNumPlayers() == 3){
            students.add(new MovedStudent( message.getStudent4Entrance() - 1 ,  message.getStudent4WhereToPut(), message.getIndexIslandIf4ToIsland()));
        }

        ArrayList<MovedStudent> orderedStudents = students.stream()
                .sorted(Comparator.comparingInt(MovedStudent::getIndex).reversed()).
                collect(Collectors.toCollection(ArrayList<MovedStudent> :: new));

        currentCL.moveStudents(orderedStudents);
    }

    /**
     * moves motherNature
     */
    private void moveMotherNature(){
        MoveMotherNatureMessage message = null;
        try{
            message = (MoveMotherNatureMessage) receiveMessage.receiveMessage();
        } catch (ClassCastException e){
            interrupt();
            return;
        }
        currentCL.moveMotherNature(message.getMoves());
    }

    /**
     * selects a cloud card
     */
    private void selectCloudCard(){
        CloudCardChoiceMessage message = null;
        try{
            message = (CloudCardChoiceMessage) receiveMessage.receiveMessage();
        } catch (ClassCastException e){
            interrupt();
            return;
        }
        currentCL.selectCloudCard(message.getCloud());
    }

    /**
     * plays a Character
     * @param message
     */
    private void playCharacter(MessageInterface message){
        UseCharacterMessage characterMessage = (UseCharacterMessage) message;
        currentCL.playCharacter(characterMessage);
        System.out.println("sending turn message");
    }

    /**
     * check if is the turn of the player
     * @return boolean
     */
    private boolean isMyTurn(){
        try{
            if(currentCL.getActivePlayer() == null){
                return  false;
            }
            else {
                return currentCL.getActivePlayer().getID_player().equals(username);
            }
        } catch (NullPointerException e){
            interrupt();
            return false;
        }

    }

    public void closeConnection(){
        currentCL.closeConnection();
    }
}
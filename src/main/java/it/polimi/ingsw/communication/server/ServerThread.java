package it.polimi.ingsw.communication.server;

import it.polimi.ingsw.communication.common.*;
import it.polimi.ingsw.communication.common.messages.*;
import it.polimi.ingsw.controller.ComplexLobby;
import it.polimi.ingsw.controller.GameManager;
import it.polimi.ingsw.model.MovedStudent;
import it.polimi.ingsw.model.Player;

import java.util.Comparator;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ServerThread extends Thread{
    private static int counter = 0;

    private final GameManager gameManager;
    private String username;

    private final Socket clientSocket;
    private final ObjectToJSON sendMessage;
    private final JSONtoObject receiveMessage;
    private ComplexLobby currentCL;

    public ServerThread(Socket clientSocket, GameManager gameManager) throws IOException{
        this.clientSocket = clientSocket;
        this.gameManager = gameManager;
        sendMessage = new ObjectToJSON(clientSocket);
        receiveMessage = new JSONtoObject(clientSocket);
        //the thread is started
        start();
        int id = ++counter;
        System.out.println("ServerThread " + id + " starded");

    }

    @Override
    public void run() {
        JSONtoObject receiveMessage = new JSONtoObject(clientSocket);
        ObjectToJSON sendMessage = new ObjectToJSON(clientSocket);
        new PingPongThread(clientSocket, "server", this);
        login();
        if(currentCL == null || clientSocket.isClosed()){
            interrupt();
        } else {

            while(!isMyTurn()){
                if(clientSocket.isClosed()){
                    System.out.println(username + " socket is closed 1.\r");
                    interrupt();
                }
            }

            synchronized (this){
                chooseMage();

            }

            while (!isMyTurn()){
                if(clientSocket.isClosed()){
                    System.out.println(username + " socket is closed 2.\r");
                    interrupt();
                }
            }

            synchronized (this){
                sendModel();

            }

            synchronized (this){

                playCard();
            }


            while (!isMyTurn()){
                if(clientSocket.isClosed()){
                    System.out.println(username + " socket is closed 3.\r");
                    interrupt();
                    break;
                }
            }

            boolean endGame = false;
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
                    System.out.println("received: " + message.getCode());
                    MessageType messageCode = message.getCode();
                    switch (messageCode) {
                        case PINGPONG -> sendMessage.sendPingPongMessage(new PingPongMessage("pong"));
                        case MOTHERNATURE -> {
                            moveMotherNature();
                            sendModel();
                            Player winner = currentCL.getGame().winCondition();
                            if (winner != null && !endGame) {
                                endGame = true;
                                currentCL.endGame(winner);
                            }
                        }
                        case CLOUDCARD -> {
                            selectCloudCard();
                            if (!currentCL.getActivePlayer().equals(currentCL.getPlayerOrder().get(currentCL.getPlayerOrder().size() - 1))) {
                                currentCL.changeActivePlayer();
                            } else {
                                currentCL.setActivePlayer(currentCL.getPlayerOrder().get(0));
                                System.out.println("ish should be " + currentCL.getPlayerOrder().get(0).getID_player() + " turn");
                                currentCL.getGame().refillCloudCards();
                            }
                        }
                        case CARD -> playCardInGame();
                        case MODEL -> sendModelInGame();
                        case STUDENT -> {
                            synchronized (this){
                                System.out.println("preomove");
                                moveStudent();
                                System.out.println("sending model");
                                sendModel();
                                System.out.println("aftermodel");
                            }
                        }
                        case CHARACTERCHOICE -> playCharacter(message);
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
            sendMessage.sendNoError();
            sendMessage.sendLobbiesMessage(new LobbiesMessage(gameManager.getPlayerComplexLobby(username).getID()));
        } else {
            sendMessage.sendLoginError();
            login();
        }
    }

    private void chooseMage(){
        boolean ok = false;
        while(!ok){
            MessageType messageCode = receiveMessage.receiveMessage().getCode();
            if(messageCode == MessageType.MAGE){
                ok = currentCL.selectMage(clientSocket, currentCL.getPlayerByID(username), receiveMessage, sendMessage);
            }
        }
    }

    private void playCard(){
        boolean ok = false;
        while (!ok){
            MessageType messageCode = receiveMessage.receiveMessage().getCode();

            if(messageCode == MessageType.CARD){
                ok = currentCL.playCard(sendMessage, receiveMessage);
            }
        }
    }

    private void playCardInGame(){

        if(!currentCL.playCard(sendMessage, receiveMessage)){
            playCard();
        }
    }

    private void sendModel(){
        ModelMessage message;
        try{
            message = (ModelMessage) receiveMessage.receiveMessage();
        } catch(ClassCastException e){
            interrupt();
            return;
        }

        System.out.println(message.getCode());

        if(message.getCode() == MessageType.MODEL){

            sendMessage.sendModelMessage(currentCL.sendModel(username));
            System.out.println("sent");
        }
    }

    private void sendModelInGame(){
        sendMessage.sendModelMessage(currentCL.sendModel(username));
    }

    private void moveStudent(){
        MoveStudentMessage message;
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

    private void moveMotherNature(){
        MoveMotherNatureMessage message;
        try{
            message = (MoveMotherNatureMessage) receiveMessage.receiveMessage();
        } catch (ClassCastException e){
            interrupt();
            return;
        }
        currentCL.moveMotherNature(message.getMoves());
    }

    private void selectCloudCard(){
        CloudCardChoiceMessage message;
        try{
            message = (CloudCardChoiceMessage) receiveMessage.receiveMessage();
        } catch (ClassCastException e){
            interrupt();
            return;
        }
        currentCL.selectCloudCard(message.getCloud());
    }

    private void playCharacter(MessageInterface message){
        UseCharacterMessage characterMessage = (UseCharacterMessage) message;
        currentCL.playCharacter(characterMessage);
    }

    private boolean isMyTurn(){

        if(currentCL.getActivePlayer() == null){
            return  false;
        }
        else {
            return currentCL.getActivePlayer().getID_player().equals(username);
        }
    }

    public void closeConnection(){
        currentCL.closeConnection();
    }
}
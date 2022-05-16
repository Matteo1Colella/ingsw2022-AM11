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

    @Override
    public void run() {
        JSONtoObject receiveMessage = new JSONtoObject(clientSocket);
        ObjectToJSON sendMessage = new ObjectToJSON(clientSocket);
        new PingPongThread(clientSocket, "server", this);
        //receivePingSendPong();
        login();
        if(currentCL == null){
            interrupt();
        } else {

            synchronized (preMageLock){
                try{
                    preMageLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            chooseMage();

            synchronized (preMageLock){
                preMageLock.notify();
            }

            if(currentCL.getPlayerByID(username).equals(currentCL.getPlayers().get(currentCL.getPlayers().size() - 1))){
                synchronized (afterMageLock){
                    afterMageLock.notify();
                }
            }

            synchronized (afterMageLock){
                try{
                    afterMageLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            sendModel();

            ArrayList<Player> preorder = new ArrayList<>(currentCL.getPlayerOrder());
            Player preActive = currentCL.getActivePlayer();

            playCard();

            if(preActive.equals(preorder.get(preorder.size()-1))){
                synchronized (afterCardLock){
                    afterCardLock.notify();
                }
            }

            while (!isMyTurn()) {
                synchronized (afterCardLock){
                    afterCardLock.notify();
                }
            }

            System.out.println(username + " in post card.");

            synchronized (afterCardLock){
                try{
                    afterCardLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            System.out.println(username + " in pre turn.");
            boolean endGame = false;
            while (!endGame) {

                while (isMyTurn()){
                    System.out.println("it is " + username + " turn");
                    sendMessage.sendTurnMessage();
                    MessageType messageCode = receiveMessage.receiveMessage().getCode();
                    System.out.println("received " + messageCode.toString());
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
                            System.out.println(currentCL.getPlayerOrder().size());
                            if (!currentCL.getActivePlayer().equals(currentCL.getPlayerOrder().get(currentCL.getPlayerOrder().size()-1))){
                                synchronized (afterCardLock){
                                    afterCardLock.notifyAll();
                                }
                                currentCL.changeActivePlayer();
                                /*
                                synchronized (preCardLock){
                                    try{
                                        preCardLock.wait();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                 */
                            } else {
                                //synchronized (preCardLock){
                                    //preCardLock.notifyAll();
                                    currentCL.setActivePlayer(currentCL.getPlayerOrder().get(0));
                                    currentCL.getGame().refillCloudCards();
                                    /*
                                    try {
                                        preCardLock.wait();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                     */
                                //}
                            }

                            break;

                        case CARD:

                            //sendModel();

                            System.out.println("chosing card");

                            preorder = new ArrayList<>(currentCL.getPlayerOrder());
                            preActive = currentCL.getActivePlayer();

                            playCardInGame();

                            if(!preActive.equals(preorder.get(preorder.size()-1))){
                                //currentCL.changeActivePlayer();
                                synchronized (preCardLock){
                                    preCardLock.notifyAll();
                                }
                            } else{
                                synchronized (afterCardLock){
                                   // currentCL.setActivePlayer(currentCL.getPlayerOrder().get(0));
                                    afterCardLock.notifyAll();
                                }
                            }

                            synchronized (afterCardLock){
                                try{
                                    afterCardLock.wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }


                            break;
                        case MODEL:
                            sendModelInGame();
                            break;
                        case STUDENT:
                            moveStudent();
                            sendModel();
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
                synchronized (afterCardLock){
                    System.out.println("not " + username + "turn");
                    afterCardLock.notifyAll();
                }

            }
        }
    }

    /*
    private MessageInterface receiveMessageTimeOut(JSONtoObject receiveMessage){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        final MessageInterface[] message = {null};

        Future<String> future = executor.submit(new Callable() {

            public String call() throws Exception {
                message[0] = receiveMessage.receiveMessage();
                return "OK";
            }
        });
        try {
            System.out.println(future.get(3000, TimeUnit.SECONDS)); //timeout is in 2 seconds
        } catch (TimeoutException | InterruptedException | ExecutionException e) {
            System.err.println("Timeout");
            currentCL.closeConnection();
        }
        executor.shutdownNow();
        return message[0];
    }
    */

    private void receivePingSendPong(){
        receiveMessage.receiveMessage();
        sendMessage.sendPingPongMessage(new PingPongMessage("pong"));
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
        synchronized (afterMageLock){
            afterMageLock.notify();
        }
    }

    private void playCardInGame(){

        System.out.println(username + "chosing card");
        if(!currentCL.playCard(sendMessage, receiveMessage)){
            playCard();
        }
        System.out.println(username + "has chosen card");

        synchronized (preCardLock){
            preCardLock.notify();
        }
    }

    private void sendModel(){
        if(receiveMessage.receiveMessage().getCode() == MessageType.MODEL){
            sendMessage.sendModelMessage(currentCL.sendModel(username));
        }
    }

    private void sendModelInGame(){
            sendMessage.sendModelMessage(currentCL.sendModel(username));
    }

    private void moveStudent(){
        MoveStudentMessage message = (MoveStudentMessage) receiveMessage.receiveMessage();
        ArrayList<MovedStudent> students = new ArrayList<>();

        students.add(new MovedStudent( message.getStudent1Entrance() - 1,  message.getStudent1WhereToPut(), message.getIndexIslandIf1ToIsland()));
        students.add(new MovedStudent( message.getStudent2Entrance() - 1 ,  message.getStudent2WhereToPut(), message.getIndexIslandIf2ToIsland()));
        students.add(new MovedStudent( message.getStudent3Entrance() - 1 ,  message.getStudent3WhereToPut(), message.getIndexIslandIf3ToIsland()));

        ArrayList<MovedStudent> orderedStudents = students.stream()
                .sorted(Comparator.comparingInt(MovedStudent::getIndex).reversed()).collect(Collectors.toCollection(ArrayList<MovedStudent> :: new));

        currentCL.moveStudents(orderedStudents);
    }

    private void moveMotherNature(){
        MoveMotherNatureMessage message = (MoveMotherNatureMessage) receiveMessage.receiveMessage();
        currentCL.moveMotherNature(message.getMoves());
    }

    private void selectCloudCard(){
        CloudCardChoiceMessage message = (CloudCardChoiceMessage) receiveMessage.receiveMessage();
        currentCL.selectCloudCard(message.getCloud());
    }

    private boolean isMyTurn(){
        if(currentCL.getActivePlayer() == null){
            return  false;
        } else {
            return currentCL.getActivePlayer().getID_player().equals(username);
        }
    }

    public void closeConnection(){
        currentCL.closeConnection();
    }
}
package it.polimi.ingsw.communication.server;

import com.google.gson.Gson;
import it.polimi.ingsw.communication.common.*;
import it.polimi.ingsw.communication.common.errors.ErrorMessage;
import it.polimi.ingsw.communication.common.messages.*;
import it.polimi.ingsw.controller.ComplexLobby;
import it.polimi.ingsw.controller.GameManager;
import it.polimi.ingsw.model.Player;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.*;

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

    private Object mageLock;
    private Object cardLock;

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

            synchronized (mageLock){
                try{
                    mageLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            chooseMage();

            synchronized (mageLock){
                mageLock.notifyAll();
            }

            boolean endGame = false;

            synchronized (cardLock){
                try{
                    cardLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            playCard();

            synchronized (cardLock){
                try{
                    cardLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            while (!endGame) {
                if(!isMyTurn()){
                    synchronized (cardLock){
                        try{
                            cardLock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    while (isMyTurn()){
                        sendMessage.sendTurnMessage();
                        MessageType messageCode = receiveMessage.receiveMessage().getCode();
                        switch (messageCode){
                            case PINGPONG:
                                sendMessage.sendPingPongMessage(new PingPongMessage("pong"));
                                break;
                            case MOTHERNATURE:
                                //methods call
                                if(currentCL.getGame().winCondition() != null){
                                    endGame = true;
                                    currentCL.endGame(currentCL.getGame().winCondition());
                                }
                            case CLOUDCARD:
                                currentCL.changeActivePlayer();
                                synchronized (clientSocket){
                                    try{
                                        clientSocket.wait();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                break;
                        }
                        //methods call
                        //check if a player has won after a turn
                        if(currentCL.getGame() != null){
                            if(currentCL.getGame().winCondition() != null){
                                endGame = true;
                                currentCL.endGame(currentCL.getGame().winCondition());
                            }
                        }
                    }
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
            mageLock = currentCL.getMageLock();
            cardLock = currentCL.getCardLock();
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
        while(!ok){
            MessageType messageCode = receiveMessage.receiveMessage().getCode();
            if(messageCode == MessageType.CARD){
                ok = currentCL.playCard(sendMessage, receiveMessage);
            }
        }
    }

    public boolean isMyTurn(){
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
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

    private Object lock;

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

        receivePingSendPong();
        login();
        new PingPongThread(clientSocket, currentCL);

        synchronized (currentCL){
            try{
                currentCL.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        boolean endOfTurn = false, endGame = false;

        while (!endGame) {
            while (!endOfTurn){

                MessageType messageCode = receiveMessage.receiveMessage().getCode();
                switch (messageCode){
                    case PINGPONG:
                        sendMessage.sendPingPongMessage(new PingPongMessage("pong"));
                        break;
                    case MAGE:
                        boolean ok = currentCL.selectMage(clientSocket, currentCL.getPlayerByID(username), receiveMessage, sendMessage);

                        synchronized (currentCL) {
                            currentCL.notifyAll();
                        }
                        if(ok){
                            synchronized (lock){
                                try{
                                    lock.wait();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                        break;
                    case MOTHERNATURE:
                        //methods call
                        if(currentCL.getGame().winCondition() != null){
                            endGame = true;
                            currentCL.endGame(currentCL.getGame().winCondition());
                        }
                    case CLOUDCARD:
                        endOfTurn = true;
                        synchronized (lock){
                            try{
                                lock.wait();
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

    private void receivePingSendPong(){
        receiveMessage.receiveMessage();
        sendMessage.sendPingPongMessage(new PingPongMessage("pong"));
    }

    private void login(){
        LoginMessage loginMessage = (LoginMessage) receiveMessage.receiveMessage();

        username = loginMessage.getUsername();
        int numOfPlayers = loginMessage.getNumOfPlayers();
        boolean isPro = loginMessage.isPro();

        if(gameManager.loginSocket(username, numOfPlayers, isPro, clientSocket)){
            currentCL = gameManager.getPlayerComplexLobby(username);
            lock = currentCL.getLock();
            sendMessage.sendNoError();
            sendMessage.sendLobbiesMessage(new LobbiesMessage(gameManager.getPlayerComplexLobby(username).getID()));
        } else {
            sendMessage.sendLoginError();
            login();
        }
    }

    public synchronized int getCounter(){
        return counter;
    }

}
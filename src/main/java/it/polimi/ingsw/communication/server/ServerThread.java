package it.polimi.ingsw.communication.server;

import it.polimi.ingsw.communication.common.JSONtoObject;
import it.polimi.ingsw.communication.common.ObjectToJSON;
import it.polimi.ingsw.communication.common.messages.LobbiesMessage;
import it.polimi.ingsw.communication.common.messages.LoginMessage;
import it.polimi.ingsw.communication.common.messages.MageMessage;
import it.polimi.ingsw.communication.common.messages.PingPongMessage;
import it.polimi.ingsw.controller.GameManager;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread{
    private static int counter = 0;
    private int id = ++counter;

    private GameManager gameManager;

    private Socket clientSocket;
    private BufferedReader input;
    private PrintWriter output;
    private ObjectToJSON sendMessage;
    private JSONtoObject receiveMessage;

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
        receivePingSendPong();
        login();
    }

    private void receivePingSendPong(){
       receiveMessage.receiveMessage();
       sendMessage.sendPingPongMessage(new PingPongMessage("pong"));
    }

    private void login(){
        LoginMessage loginMessage = (LoginMessage) receiveMessage.receiveMessage();

        String username = loginMessage.getUsername();
        int numOfPlayers = loginMessage.getNumOfPlayers();
        boolean isPro = loginMessage.isPro();

        if(gameManager.loginSocket(username, numOfPlayers, isPro, clientSocket)){
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

    public Socket getClientSocket() {
        return clientSocket;
    }

    private synchronized void decrementCounter(){
        counter--;
        System.out.println("Counter:" + counter);
        id--;
    }
}

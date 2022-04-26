package it.polimi.ingsw.comunication.server;

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

    public ServerThread(Socket clientSocket, GameManager gameManager) throws IOException{
        this.clientSocket = clientSocket;
        this.gameManager = gameManager;
        OutputStreamWriter outputStreamWriter = null;

        //creating the objects for input and output
        this.input = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
        outputStreamWriter = new OutputStreamWriter(this.clientSocket.getOutputStream());
        output = new PrintWriter(new BufferedWriter(outputStreamWriter), true);
        //the thread is started
        start();
        System.out.println("ServerThread " + this.id + " starded");
    }

    @Override
    public void run() {
        login();
    }

    private void login(){
        String inputString = null;
        String ID = null;
        int numPlayers = 0;
        boolean gameType = false;
        try{
            int i = 0;
            String outPutString;
            outPutString = "Enter ID:";
            output.println(outPutString);
            inputString = this.input.readLine();
            while (true){
                if(inputString.equals("exit")){
                    System.out.println("adios");
                    break;
                } else if(i == 0){
                    ID = inputString;
                    System.out.println("ID: " + ID);
                    outPutString = "You entered: " + ID;
                    output.println(outPutString);
                    i++;
                    outPutString = "Enter the number of players:";
                    output.println(outPutString);
                } else if(i == 1){
                    inputString = this.input.readLine();
                    numPlayers = Integer.parseInt(inputString);
                    System.out.println("numPlayers: " + numPlayers);
                    outPutString = "You entered: " + numPlayers;
                    output.println(outPutString);
                    i++;
                    outPutString = "Enter the game type:";
                    output.println(outPutString);
                } else if (i == 2){
                    inputString = this.input.readLine();
                    gameType = Boolean.parseBoolean(inputString);
                    System.out.println("gameType: " + gameType);
                    outPutString = "You entered: " + gameType;
                    output.println(outPutString);
                    if(gameManager.loginSocket(ID, numPlayers, gameType, getClientSocket())){
                        System.out.println("Login done.");
                        i++;
                    } else {
                        outPutString = "Something gone wrong. Please retry the login.";
                        output.println(outPutString);
                        login();
                    }
                }
            }
            System.out.println("ServerThread " + this.id + " closing...");
            this.decrementCounter();
        }catch(IOException e){
            try{
                this.clientSocket.close();
            } catch(IOException e1){}
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

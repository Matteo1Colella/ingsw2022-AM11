package it.polimi.ingsw.communication.server;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import com.google.gson.*;
import it.polimi.ingsw.controller.GameManager;


public class ServerMain {
    private String hostName;
    private Integer portNumber;
    private int maxRequests;
    private GameManager gameManager;

    public ServerMain(){
        this.gameManager = new GameManager();
    }

    public static void main(String[] args){
        ServerMain serverMain = new ServerMain();
        ServerSocket myServerSocket = null;
        Socket clientSocket = null;

        //read the parameters from args or .json file
        if(args.length == 3){
            serverMain.setHostName(args[0]);
            serverMain.setPortNumber(Integer.parseInt(args[1]));
            serverMain.setMaxRequests(Integer.parseInt(args[2]));
        } else {
            serverMain.readParameters();
        }

        try{
            //creating the server socket.
            myServerSocket = new ServerSocket(serverMain.getPortNumber());
            System.out.println("Listening on port " + serverMain.getPortNumber());

            while(true){
                clientSocket = myServerSocket.accept();
                System.out.println("Starting connection with client: " + clientSocket);
                try{
                    //when a client tries to connect, the server create a new thread to deal with the client
                    ServerThread serverThread =  new ServerThread(clientSocket, serverMain.getGameManager());
                    if(serverThread.getCounter() > serverMain.getMaxRequests()) {
                        System.out.println("To many clients.");
                        throw new IOException();
                    }

                }catch (IOException e){
                    clientSocket.close();
                }
            }
        } catch(IOException e){
            System.out.println("Connection error");
            System.exit(1);
        }
        System.out.println("Closing multi thread server...");
        try{
            myServerSocket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    //read the socket parameters from the file "config.json"
    public Integer getPortNumber() {
        return portNumber;
    }

    public void readParameters(){
        try {
            Gson gson = new Gson();
            //create a reader
            Reader reader = Files.newBufferedReader(Paths.get("src/main/java/it/polimi/ingsw/comunication/server/config.json"));
            //convert JSON file to map
            Map<?, ?> map = gson.fromJson(reader, Map.class);
            //print map entries
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                System.out.println(entry.getKey() + "=" + entry.getValue());
                if(entry.getKey().equals("server")){
                    hostName = entry.getValue().toString();
                } else if (entry.getKey().equals("port")){
                    portNumber = (int) Float.parseFloat(entry.getValue().toString());
                } else if (entry.getKey().equals("max_client")){
                    maxRequests = (int) Float.parseFloat(entry.getValue().toString());
                }
            }
            // close reader
            reader.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getHostName() {
        return hostName;
    }

    public int getMaxRequests() {
        return maxRequests;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public void setPortNumber(Integer portNumber) {
        this.portNumber = portNumber;
    }

    public void setMaxRequests(int maxRequests){
        this.maxRequests = maxRequests;
    }
}

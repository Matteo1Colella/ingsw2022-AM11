package it.polimi.ingsw.communication.client;

import com.google.gson.Gson;
import it.polimi.ingsw.communication.common.JSONtoObject;
import it.polimi.ingsw.communication.common.MessageInterface;
import it.polimi.ingsw.communication.common.MessageType;
import it.polimi.ingsw.communication.common.ObjectToJSON;
import it.polimi.ingsw.communication.common.messages.*;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class ClientMain {
    private int port;

    private Socket clientSocket;
    private final ObjectToJSON sendMessage;
    private final JSONtoObject receiveMessage;

    public ObjectToJSON getSendMessage() {
        return sendMessage;
    }

    public JSONtoObject getReceiveMessage() {
        return receiveMessage;
    }

    public ClientMain() {
        try {
            readParameters();
            createConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sendMessage = new ObjectToJSON(clientSocket);
        receiveMessage = new JSONtoObject(clientSocket);
    }

    public static void main(String[] args) {
        ClientMain clientMain = new ClientMain();

        clientMain.pingPong();
        clientMain.login();

        boolean choice = false;
        while (!choice) {
            choice = clientMain.choseMage();
        }
        synchronized (clientMain){
            try{
                clientMain.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void readParameters() throws IOException {

        Gson gson = new Gson();
        //create a reader
        Reader reader = Files.newBufferedReader(Paths.get("src/main/java/it/polimi/ingsw/communication/client/configClient.json"));
        //convert JSON file to map
        Map<?, ?> map = gson.fromJson(reader, Map.class);
        //print map entries
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (entry.getKey().equals("port")) {
                port = (int) Float.parseFloat(entry.getValue().toString());
            }
            // close reader
            reader.close();
        }
    }

    private void createConnection() throws IOException {
        //connect to the server on the local host: to be modified.
        // socket parameters
        InetAddress host = InetAddress.getLocalHost();
        clientSocket = new Socket(host, port);
    }

    public void pingPong(){
        sendMessage.sendPingPongMessage(new PingPongMessage("ping"));
        receiveMessage.receiveMessage();
    }

    public void login(){
        String username = null;
        int numOfPlayers = 0;
        boolean isPro = false;
        boolean ok = false;

        while (!ok){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Insert username:\r");
            username = scanner.nextLine().toLowerCase(Locale.ROOT);
            System.out.println("Insert the number of players: \r");
            numOfPlayers = scanner.nextInt();
            System.out.println("Select game mode (0 = not pro, 1 = pro):\r");
            isPro = Boolean.parseBoolean(scanner.next());

            if(numOfPlayers >= 2 && numOfPlayers <= 4){
                ok = true;
            }
        }

        sendMessage.sendLoginMessage(new LoginMessage(username.replaceAll("\\s+",""), numOfPlayers, isPro));

        MessageInterface message = receiveMessage.receiveMessage();
        if(message.getCode() == MessageType.LOGINERROR){
            System.out.println("Something gone wrong, please retry.\r");
            login();
        } else if(message.getCode() == MessageType.NOERROR) {
            LobbiesMessage lobbiesMessage = (LobbiesMessage) receiveMessage.receiveMessage();
            System.out.println("You are in the lobby " + lobbiesMessage.getIdLobby());
        }
    }

    public boolean choseMage() {
        boolean ok = false;

        sendMessage.sendMageMessage(new MageMessage());
        MageMessage mageMessage = (MageMessage) receiveMessage.receiveMessage();

        for (int i = 0; i < mageMessage.getAviableMage().length; i++) {
            System.out.println("MAGE " + mageMessage.getAviableMage()[i] + "\n");
        }
        System.out.println("Select mage:\n");
        int mage = 0;
        while (!ok) {
            Scanner scanner = new Scanner(System.in);
            mage = scanner.nextInt();
            if (mage == 1 || mage == 2 || mage == 3 || mage == 4) {
                ok = true;
            }
        }
        sendMessage.sendMageMessage(new MageMessage(mage));

        MessageInterface receivedMessage = receiveMessage.receiveMessage();
        if (receivedMessage.getCode() == MessageType.NOERROR){
            System.out.println("Correct selection.\r");
            return true;
        } else if (receivedMessage.getCode() == MessageType.MAGEERROR){
            return false;
        }
        return false;
    }
}


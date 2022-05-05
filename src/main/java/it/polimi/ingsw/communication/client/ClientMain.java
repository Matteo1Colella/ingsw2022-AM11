package it.polimi.ingsw.communication.client;

import com.google.gson.Gson;
import it.polimi.ingsw.communication.common.*;
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
    private final Object lock;

    public ClientMain() {
        lock = new Object();
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
        //playCard
        choice = false;
        while (!choice) {
            choice = clientMain.playAssistantCard();
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
        clientSocket.setSoTimeout(30000);
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
        int mage = 0;
        while (!ok) {
            System.out.println("Select mage:\n");
            Scanner scanner = new Scanner(System.in);
            mage = scanner.nextInt();
            if (mage == 1 || mage == 2 || mage == 3 || mage == 4) {
                ok = true;
            } else {
                System.out.println("Invalid choice.\r");
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

    public Socket getClientSocket() {
        return clientSocket;
    }

    public boolean playAssistantCard(){

        boolean ok = false;

        //ask the list of cards already played on the table
        sendMessage.sendAssistantCardsMessage(new AssistantCardsMessage());
        AssistantCardsMessage assistantCardsMessage = (AssistantCardsMessage) receiveMessage.receiveMessage();

        System.out.println("List of played cards on table: ");
        for (int j = 0; j < assistantCardsMessage.getChosenCard().size(); j++) {
            System.out.println("CARD NAME: " + assistantCardsMessage.getChosenCard().get(j).getName() + "\n");
            System.out.println("Influence: -> " +  assistantCardsMessage.getChosenCard().get(j).getInfluence() + "\n");
            System.out.println("Steps: -> " +  assistantCardsMessage.getChosenCard().get(j).getSteps() + "\n");
        }

        //list of my cards:

        System.out.println("");
        System.out.println("My cards:");
        for (int i = 0; i < assistantCardsMessage.getDeck().size(); i++) {
            System.out.println("");
            System.out.println("(Enter -> " + i + "for: )");
            System.out.println("NAME: " + assistantCardsMessage.getDeck().get(i).getName() + "\n");
            System.out.println("Influence: -> " +  assistantCardsMessage.getDeck().get(i).getInfluence() + "\n");
            System.out.println("Steps: -> " +  assistantCardsMessage.getDeck().get(i).getSteps() + "\n");
        }
        System.out.println("Select card:\n");
        int card = -1;
        while (!ok) {
            Scanner scanner = new Scanner(System.in);
            card = scanner.nextInt();
            if (card == 0 || card == 1 || card == 2 || card == 3 || card == 4 || card == 5 ||card == 6 ||card == 7 ||card == 8 ||card == 9 ) {
                ok = true;
            }
        }

        sendMessage.sendPlayCardMessage(new PlayCardMessage(card));
        MessageInterface receivedMessage = receiveMessage.receiveMessage();

        if (receivedMessage.getCode() == MessageType.NOERROR){
            System.out.println("Correct selection.\r");
            return true;
        } else if (receivedMessage.getCode() == MessageType.CARDERROR){
            return false;
        }
        return false;
    }




}


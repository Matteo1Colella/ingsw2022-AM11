package it.polimi.ingsw.communication.client;

import com.google.gson.Gson;
import it.polimi.ingsw.communication.common.*;
import it.polimi.ingsw.communication.common.errors.ErrorMessage;
import it.polimi.ingsw.communication.common.messages.*;
import it.polimi.ingsw.model.board.CloudCard;
import it.polimi.ingsw.model.board.DiningRoom;
import it.polimi.ingsw.model.board.IslandCard;
import it.polimi.ingsw.model.cards.CharacterCard;
import it.polimi.ingsw.model.pieces.Student;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class ClientMain extends Thread {
    private int port;

    private Socket clientSocket;
    private final ObjectToJSON sendMessage;
    private final JSONtoObject receiveMessage;
    private final Object lock;

    public ObjectToJSON getSendMessage() {
        return sendMessage;
    }

    public JSONtoObject getReceiveMessage() {
        return receiveMessage;
    }

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
        new PingPongThread(clientMain.getClientSocket(), "client");
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

        while (true) {
            if (clientMain.receiveMessage().getCode() == MessageType.TURN) {
            }
            if ((clientMain.receiveMessage().getCode() == MessageType.MODEL)) {
                clientMain.showModel();
            }
            if ((clientMain.receiveMessage().getCode() == MessageType.STUDENT)) {
                clientMain.moveStudents();
            }
        }

        /*
        try{
            clientMain.getClientSocket().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

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
        clientSocket.setSoTimeout(300000);
    }

    public void pingPong() {
        sendMessage.sendPingPongMessage(new PingPongMessage("ping"));
        receiveMessage();
    }

    public void login() {
        String username = null;
        int numOfPlayers = 0;
        boolean isPro = false;
        boolean ok = false;

        while (!ok) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Insert username:\r");
            username = scanner.nextLine().toLowerCase(Locale.ROOT);
            System.out.println("Insert the number of players: \r");
            numOfPlayers = scanner.nextInt();
            System.out.println("Select game mode (0 = not pro, 1 = pro):\r");
            isPro = Boolean.parseBoolean(scanner.next());

            if (numOfPlayers >= 2 && numOfPlayers <= 4) {
                ok = true;
            }
        }

        sendMessage.sendLoginMessage(new LoginMessage(username.replaceAll("\\s+", ""), numOfPlayers, isPro));


        MessageInterface message = receiveMessage();
        if(message.getCode() == MessageType.LOGINERROR){
            System.out.println("Something gone wrong, please retry.\r");
            login();
        } else if(message.getCode() == MessageType.NOERROR) {
            LobbiesMessage lobbiesMessage = (LobbiesMessage) receiveMessage();
            System.out.println("You are in the lobby " + lobbiesMessage.getIdLobby());
        }
    }

    public boolean choseMage() {
        boolean ok = false;
        sendMessage.sendMageMessage(new MageMessage());
        MageMessage mageMessage = (MageMessage) receiveMessage();

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


        MessageInterface receivedMessage = receiveMessage();
        if (receivedMessage.getCode() == MessageType.NOERROR){
            System.out.println("Correct selection.\r");
            return true;
        } else if (receivedMessage.getCode() == MessageType.MAGEERROR) {
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
        AssistantCardsMessage assistantCardsMessage = (AssistantCardsMessage) receiveMessage();

        System.out.println("List of played cards on table: ");
        for (int j = 0; j < assistantCardsMessage.getChosenCard().size(); j++) {
            System.out.println("CARD NAME: " + assistantCardsMessage.getChosenCard().get(j).getName() + "\n");
            System.out.println("Influence: -> " + assistantCardsMessage.getChosenCard().get(j).getInfluence() + "\n");
            System.out.println("Steps: -> " + assistantCardsMessage.getChosenCard().get(j).getSteps() + "\n");
        }

        //list of my cards:

        System.out.println("");
        System.out.println("My cards:");
        for (int i = 0; i < assistantCardsMessage.getDeck().size(); i++) {
            System.out.println("");
            System.out.println("(Enter -> " + i + "for: )");
            System.out.println("NAME: " + assistantCardsMessage.getDeck().get(i).getName() + "\n");
            System.out.println("Influence: -> " + assistantCardsMessage.getDeck().get(i).getInfluence() + "\n");
            System.out.println("Steps: -> " + assistantCardsMessage.getDeck().get(i).getSteps() + "\n");
        }
        System.out.println("Select card:\n");
        int card = -1;
        while (!ok) {
            Scanner scanner = new Scanner(System.in);
            card = scanner.nextInt();
            if (card == 0 || card == 1 || card == 2 || card == 3 || card == 4 || card == 5 || card == 6 || card == 7 || card == 8 || card == 9) {
                ok = true;
            }
        }

        sendMessage.sendPlayCardMessage(new PlayCardMessage(card));
        MessageInterface receivedMessage = receiveMessage();

        if (receivedMessage.getCode() == MessageType.NOERROR) {
            System.out.println("Correct selection.\r");
            return true;
        } else if (receivedMessage.getCode() == MessageType.CARDERROR) {
            return false;
        }
        return false;
    }


    public Object getLock() {
        return lock;
    }

    public MessageInterface receiveMessage() {
        return receiveMessage.receiveMessageClient();
    }

    public void showModel() {

        //ask the list of GameComponents (all the model)
        sendMessage.sendModelMessage(new ModelMessage()); //model request
        ModelMessage modelMessage = (ModelMessage) receiveMessage();

        //printing model..

        //if pro{
        if (modelMessage.getCoinOwned() >= 0) {
            System.out.println("CHARACTER CARDS:");
            for (CharacterCard characterCard : modelMessage.getCharacterCards()) {
                System.out.println(characterCard.getNum());
            }
            System.out.println("");
            System.out.println("YOUR COINS:");
            System.out.println(modelMessage.getCoinOwned());
            System.out.println("");
        }
        //}

        int i = 0;
        System.out.println("ARCHIPELAGO: ");
        System.out.println("");
        for (IslandCard islandCard : modelMessage.getArchipelago()) {
            if (islandCard.getMotherNature()) {
                System.out.println("MOTHERNATURE");
            }
            if (islandCard.getTower() != null) {
                System.out.println("ISLAND: " + i + "\tTower : " + islandCard.getTower().getColor().toString());
            } else {
                System.out.println("ISLAND: " + i + "\tTower : no tower");
            }
            System.out.println("Students:");
            for (int j = 0; j < islandCard.getStudents().size(); j++) {
                System.out.println("Student " + j + " color : " + islandCard.getStudents().get(j));
            }
            System.out.println("Merged with: ");
            for (int j = 0; j < islandCard.getMergedWith().size(); j++) {
                IslandCard tempIslandCard = islandCard.getMergedWith().get(j);
                for (int k = 0; k < tempIslandCard.getStudents().size(); k++) {
                    System.out.println("Student " + k + " color : " + tempIslandCard.getStudents().get(k));
                }
            }
            i++;
            System.out.println("");
        }
        System.out.println("MY SCHOOLBOARD:");
        System.out.println("");
        System.out.println("ENTRANCE:");
        i = 0;
        for (Student student : modelMessage.getSchoolBoard().getEntrance().getStudents()) {
            System.out.println("student " + i + ":");
            System.out.println(student.getColor());
            i++;
        }
        System.out.println("");
        System.out.println("DINING ROOMS:");
        System.out.println("");
        i = 0;
        for (DiningRoom diningRoom : modelMessage.getSchoolBoard().getDiningRooms()) {
            System.out.println("Color: " + modelMessage.getSchoolBoard().getDiningRooms().get(i).getColor());
            System.out.println("Number of Students: " + modelMessage.getSchoolBoard().getDiningRooms().get(i).getStudents().size());
            System.out.println("Professor: " + modelMessage.getSchoolBoard().getDiningRooms().get(i).IsProfessor());
            i++;
            System.out.println("");
        }
        if (modelMessage.getSchoolBoard().getTowers().get(0).getColor() != null)
            System.out.println("TOWER color: " + modelMessage.getSchoolBoard().getTowers().get(0).getColor());

        System.out.println("Remaining Towers: " + modelMessage.getSchoolBoard().getTowers().size());
        i = 0;
        System.out.println("");
        System.out.println("CLOUDS: ");
        for (CloudCard card : modelMessage.getCloudCardList()) {
            System.out.println("cloud " + i + ":");
            System.out.println(card.getStudents());
            i++;
        }
    }


    public boolean moveStudents() {

        //The user already has the schoolBoard (sent with ModelMessage)
        //student choice
        int student1Entrance = -1;
        int student1WhereToPut = -1;
        int indexIslandIf1ToIsland = -1;
        int student2Entrance = -1;
        int student2WhereToPut = -1;
        int indexIslandIf2ToIsland = -1;
        int student3Entrance = -1;
        int student3WhereToPut = -1;
        int indexIslandIf3ToIsland = -1;
        int i = 0;
        while (i!=3){
            System.out.println("SELECT a student from the entrance:\n");
            Scanner scanner1 = new Scanner(System.in);
            System.out.println("ENTER:\n");
            System.out.println("0 -> to move student " + scanner1 + " to your SCHOOLBOARD");
            System.out.println("1 -> to move student " + scanner1 + " to an ISLAND");
            Scanner scanner2 = new Scanner(System.in);
            if(i==0){
                student1Entrance = scanner1.nextInt();
                student1WhereToPut = scanner2.nextInt();
                if(student1WhereToPut == 1){
                    System.out.println("WHAT ISLAND? (enter the Island number)");
                    Scanner scanner3 = new Scanner(System.in);
                    indexIslandIf1ToIsland = scanner3.nextInt();
                }
            }
            if(i==1){
                student2Entrance = scanner1.nextInt();
                student2WhereToPut = scanner2.nextInt();
                if(student2WhereToPut == 1){
                    System.out.println("WHAT ISLAND? (enter the Island number)");
                    Scanner scanner3 = new Scanner(System.in);
                    indexIslandIf2ToIsland = scanner3.nextInt();
                }
            }
            if(i==2){
                student3Entrance = scanner1.nextInt();
                student3WhereToPut = scanner2.nextInt();
                if(student3WhereToPut == 1){
                    System.out.println("WHAT ISLAND? (enter the Island number)");
                    Scanner scanner3 = new Scanner(System.in);
                    indexIslandIf3ToIsland = scanner3.nextInt();
                }
            }
            i++;
        }

        sendMessage.sendMoveStudentsMessage(new MoveStudentMessage(student1Entrance,student1WhereToPut,indexIslandIf1ToIsland,student2Entrance,student2WhereToPut,indexIslandIf2ToIsland,student3Entrance,student3WhereToPut,indexIslandIf3ToIsland));


        MessageInterface receivedMessage = receiveMessage.receiveMessage();
        if (receivedMessage.getCode() == MessageType.NOERROR) {
            System.out.println("Correct selection.\r");
            return true;
        } else if (receivedMessage.getCode() == MessageType.MOVESTUDENTERROR) {
            return false;
        }
        return false;

    }


}
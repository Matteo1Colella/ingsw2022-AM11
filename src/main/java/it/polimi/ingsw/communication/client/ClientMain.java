package it.polimi.ingsw.communication.client;

import com.google.gson.Gson;
import it.polimi.ingsw.communication.common.*;
import it.polimi.ingsw.communication.common.messages.*;
import it.polimi.ingsw.model.board.CloudCard;
import it.polimi.ingsw.model.board.DiningRoom;
import it.polimi.ingsw.model.board.IslandCard;
import it.polimi.ingsw.model.cards.CharacterCard;
import it.polimi.ingsw.model.pieces.Student;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.*;

public class ClientMain extends Thread {
    private int port;
    private static int[] cloudName = new int[4];
    private Socket clientSocket;
    private ObjectToJSON sendMessage;
    private JSONtoObject receiveMessage;
    private int selectedCard;
    private int gameSize;
    private boolean gameType;
    private ModelMessage model;
    private CharacterHandlerClient characterHandler;

    public ObjectToJSON getSendMessage() {
        return sendMessage;
    }

    public JSONtoObject getReceiveMessage() {
        return receiveMessage;
    }

    public static void main(String[] args) {
        System.out.println("                                                                                                                                                                                                       \n" +
                "                                                                                                                                                                                                        \n" +
                "                                                                                                                                                                            ,&@@ *#                     \n" +
                "                                                                                                                                                                         @@@@@ (.                       \n" +
                "        ,%%%%%%%%%%%%%%%%%%%%,         ,(&@@@@@#*          #%%%%%%%%.            ########(            .#########    (#######,########################%%%%%%%%%           *@@,.,      (&@@@@@#*    *     \n" +
                "           #@@@. **//, ,@@@% #    *@@@@@.*##%#. @@@@#         @@@@ &              .@@@@@ &               @@@@@ &      .@@& &. @@@/    .@@@% .. .@@@.%  &@@@@ %          ,@& @     @@@* (%#%# @@@@,/     \n" +
                "           (@@@.,        (/*        @@@@.%       *@@@@ ,      @@@@ .             .@@#@@@@.               @&@@@@ (      @@ #    @ &    .@@@%*     % &     @@@@% %       @@./      @@@ /        .@.&      \n" +
                "           (@@@.,                   @@@@.%       /@@@@ /      @@@@ .             @@ /%@@@&               @& &@@@# .    @@ (           .@@@%*              ,@@@@ (.   *@& #       @@@@ &                 \n" +
                "           #@@@.,    &@ &           @@@@.%      @@@@. %       @@@@ .         @.,@@ %  %@@@@.#@,#         @& .,@@@@ #   @@ (           .@@@%*                &@@@@ @ @@ %         *@@@@@@@@/             \n" +
                "        @@(%@@@###((#@@ @           @@@@%@@@@@@@.  @          @@@@ .        @@@@@ &    @@@@@@@@*%        @& .  @@@@ &  @@ (           .@@@%*                  @@@@@@% *           % &@@@@@@@@@@@@       \n" +
                "        (  #@@@.,     . @        %%*@@@@. .,&*@@...           @@@@ .          @@/@@ ( &@@@@@(#           @& .   @@@@,/ @@ (           .@@@%*                   @@@@ %                 (@%,  *@@@@@@ #   \n" +
                "           #@@@.,                   @@@@.%    ,@@( *          @@@@ .         @@ % *@@@@ #@@@@/#          @@ .    /@@@#.@@ (           .@@@%*                   @@@@ %             .           (.@@@@ .  \n" +
                "           #@@@.,         @ %       &@@@.%     #@@@ #         @@@@ .        @@**    & #   @@@@*#         @@ ,      @@@@@@ (           .@@@%*                   @@@@ %            /@**           (@@( .  \n" +
                "           #@@@.,       @@@@, *     &@@@.%      @@@@@ %       @@@@ .      (@@@/(           @@@@.%       /@@.#       @@@@@ (           .@@@%*                   @@@@ %           %@@@@.#        (@@,,,   \n" +
                "        *@@@@@@@@@@@@@@@@@@@@@/ ( &@@@@@@@,/    ,@@@@@@..# /@@@@@@@@@  (@@@@@@@@/.       @@@@@@@@@(  .&@@@@@@&,      %@@@ (         (@@@@@@@(%              .@@@@@@@@&&        ,    .*%@@@@@@@@, %,     \n" +
                "                                                 @@* .&,                                                                                                                                .,*/,.          \n" +
                "                                                  .                                                                                                                                                     \n");
       
        ClientMain clientMain = new ClientMain();
        try {
            if(!clientMain.askParameters()){
                clientMain.readParameters();
                clientMain.createConnection();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        new PingPongThread(clientMain.getClientSocket(), "client");
        //clientMain.pingPong();
        clientMain.login();


        boolean choice = false;
        while (!choice) {
            choice = clientMain.choseMage();
        }

        clientMain.showModel();

        //playCard
        choice = false;
        while (!choice) {
            choice = clientMain.playAssistantCard();
        }

        for (int k =0; k < 4; k++)
            cloudName[k]=k;
        while (true){
            MessageInterface receivedMessage = clientMain.receiveMessage();
            MessageType message = receivedMessage.getCode();


            if(clientMain.gameType == false){
                switch (message){
                    case TURN:
                        clientMain.moveStudents();
                        clientMain.moveMotherNature();
                        clientMain.selectCloudCard();
                        choice = false;
                        message = clientMain.receiveMessage().getCode();
                        clientMain.showModel();
                        message = clientMain.receiveMessage().getCode();

                        while (!choice) {
                            choice = clientMain.playAssistantCard();
                        }
                        break;
                    case WIN:
                        System.out.println("");
                        System.out.println("----GAME-OVER----");
                        System.out.println(receivedMessage.getMessage());
                        System.out.println("-----------------");
                        return;
                    case MODEL:
                        clientMain.setModel((ModelMessage) receivedMessage);
                        break;
                }
            } else {
                switch (message){
                    case TURN:
                        clientMain.askCharacter();
                        clientMain.moveStudents();
                        clientMain.askCharacter();
                        clientMain.moveMotherNature();
                        clientMain.askCharacter();
                        clientMain.selectCloudCard();
                        message = clientMain.receiveMessage().getCode();
                        clientMain.showModel();
                        message = clientMain.receiveMessage().getCode();

                        choice = false;
                        message = clientMain.receiveMessage().getCode();
                        clientMain.showModel();
                        message = clientMain.receiveMessage().getCode();
                        while (!choice) {
                            choice = clientMain.playAssistantCard();
                        }
                        break;
                    case WIN:
                        System.out.println("");
                        System.out.println("----GAME-OVER----");
                        System.out.println(receivedMessage.getMessage());
                        System.out.println("-----------------");
                        return;
                    case MODEL:
                        clientMain.setModel((ModelMessage) receivedMessage);
                        break;
                }

            }

        }
    }

    private boolean askParameters() throws IOException{
        String input = "";
        while(!Objects.equals(input, "no") && !Objects.equals(input, "yes"))
        {
            System.out.println("Do you want to set the server's IP and port?");
            Scanner charscanner = new Scanner(System.in);
            input = charscanner.nextLine();
        }
        if(input.equals("yes")){
            String IP = null;
            boolean ok = false;
            System.out.println("Insert IP:");
            Scanner scanner = new Scanner(System.in);
            IP = scanner.nextLine();
            System.out.println("Insert port:");
            port = scanner.nextInt();
            InetAddress host = InetAddress.getByName(IP);
            clientSocket = new Socket(host, port);
            clientSocket.setSoTimeout(100000);
            setReceiveMessage(new JSONtoObject(clientSocket));
            setSendMessage(new ObjectToJSON(clientSocket));
            return true;

        } else if(input.equals("no")){
            return false;
        }
        return false;
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
        /*
        String ip = "192.168.4.192";
        InetAddress host = InetAddress.getByName(ip);
         */
        InetAddress host = InetAddress.getLocalHost();
        clientSocket = new Socket(host, port);
        clientSocket.setSoTimeout(100000);
        setReceiveMessage(new JSONtoObject(clientSocket));
        setSendMessage(new ObjectToJSON(clientSocket));
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void pingPong() {
        sendMessage.sendPingPongMessage(new PingPongMessage("ping"));
        receiveMessage();
    }

    public void login() {
        String username = null;
        int numOfPlayers = 0;

        int isPro = 0;
        int gameMode = 0;

        boolean ok = false;

        while (!ok) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Insert username:\r");
            username = scanner.nextLine().toLowerCase(Locale.ROOT);
            System.out.println("Insert the number of players: \r");
            try {
                numOfPlayers = scanner.nextInt();
            }catch (InputMismatchException e){
                scanner.nextLine();
                System.out.println("Please retry...");
            }
            while (numOfPlayers < 2 || numOfPlayers > 4){
                System.out.println("You can select only 2,3,4 players... ");
                try {
                    numOfPlayers = scanner.nextInt();
                }catch (InputMismatchException e){
                    scanner.nextLine();
                    System.out.println("Please retry...");
                }
            }

            System.out.println("Select game mode (0 = not pro, 1 = pro):\r");

            isPro = scanner.nextInt();

            if(isPro == 0 || isPro == 1) {
                ok = true;
                gameSize = numOfPlayers;
                if (isPro == 0) {
                    gameType = false;
                } else if (isPro == 1) {
                    gameType = true;
                }
            }
        }

        sendMessage.sendLoginMessage(new LoginMessage(username.replaceAll("\\s+", ""), numOfPlayers, gameType));


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
        System.out.println("");
        System.out.println(" _____________________________\n" +
                "|                             |\n" +
                "|                             |\n" +
                "|   The game is starting...   |\n" +
                "|                             |\n" +
                "|_____________________________|\n");
        boolean ok = false;
        sendMessage.sendMageMessage(new MageMessage());
        MageMessage mageMessage = (MageMessage) receiveMessage();

        System.out.println("");
        for (int i = 0; i < mageMessage.getAviableMage().length; i++) {
            System.out.println("MAGE " + mageMessage.getAviableMage()[i] + "\n");
        }
        int mage = 0;
        while (!ok) {
            System.out.println("Select mage:\n");
            Scanner scanner = new Scanner(System.in);
            try {
                mage = scanner.nextInt();
            }catch (InputMismatchException e){
                scanner.nextLine();
                System.out.println("Please retry...");
            }
            while (mage < 1 || mage > 4){
                System.out.println("You can select only this MAGES: 1,2,3,4");
                try {
                    mage = scanner.nextInt();
                }catch (InputMismatchException e){
                    scanner.nextLine();
                    System.out.println("Please retry...");
                }
            }
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
            System.out.println(" ________________________________________\n" +
                    "|                                        |\n" +
                    "|                                        |\n" +
                    "|   Waiting for the opponent's move...   |\n" +
                    "|                                        |\n" +
                    "|________________________________________|");
            return true;
        } else if (receivedMessage.getCode() == MessageType.MAGEERROR) {
            return false;
        }
        return false;

    }

    public boolean playAssistantCard(){
        boolean ok = false;
        for (int k =0; k < 4; k++)
            cloudName[k]=k;

        //ask the list of cards already played on the table
        sendMessage.sendAssistantCardsMessage(new AssistantCardsMessage());
        AssistantCardsMessage assistantCardsMessage = (AssistantCardsMessage) receiveMessage();

        System.out.println("List of played cards on table: ");
        for (int j = 0; j < assistantCardsMessage.getChosenCard().size(); j++) {
            System.out.println("CARD NAME: " + assistantCardsMessage.getChosenCard().get(j).getName());
            System.out.println("Influence: -> " + assistantCardsMessage.getChosenCard().get(j).getInfluence());
            System.out.println("Steps: -> " + assistantCardsMessage.getChosenCard().get(j).getSteps());
        }

        //list of my cards:

        System.out.println("");
        System.out.println("My cards:");
        for (int i = 0; i < assistantCardsMessage.getDeck().size(); i++) {
            if(!assistantCardsMessage.getDeck().get(i).isUsed()){
                System.out.println("");
                System.out.println("(Enter -> " + (assistantCardsMessage.getDeck().get(i).getInfluence()) + " for: )");
                System.out.println("NAME: " + assistantCardsMessage.getDeck().get(i).getName());
                System.out.println("Influence: -> " + assistantCardsMessage.getDeck().get(i).getInfluence());
                System.out.println("Steps: -> " + assistantCardsMessage.getDeck().get(i).getSteps());
            }
        }
        System.out.println("Select card:\n");
        int card = -1;
        while (!ok) {
            Scanner scanner = new Scanner(System.in);
            try {
                card = scanner.nextInt();
            }catch (InputMismatchException e){
                scanner.nextLine();
                System.out.println("Please retry...");
            }
            while (card < 1 || card > 10){
                System.out.println("Please enter a valid number card: ");
                try {
                    card = scanner.nextInt();
                }catch (InputMismatchException e){
                    scanner.nextLine();
                    System.out.println("Please retry...");
                }
            }
            ok = true;
        }

        selectedCard = card;

        sendMessage.sendAssistantCardsMessage(new AssistantCardsMessage(card));
        MessageInterface receivedMessage = receiveMessage();

        if (receivedMessage.getCode() == MessageType.NOERROR) {
            System.out.println("Correct selection.\r");
            System.out.println(" ________________________________________\n" +
                    "|                                        |\n" +
                    "|                                        |\n" +
                    "|   Waiting for the opponent's move...   |\n" +
                    "|                                        |\n" +
                    "|________________________________________|");
            return true;
        } else if (receivedMessage.getCode() == MessageType.CARDERROR) {
            return false;
        }
        return false;
    }

    public void moveStudents() {

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
        int student4Entrance = -1;
        int student4WhereToPut = -1;
        int indexIslandIf4ToIsland = -1;
        int i = 0;
        int student = -1;
        sendMessage.sendMoveStudentsMessage(new MoveStudentMessage());
        int requests = -1;
        if(gameSize == 2 || gameSize == 4){
            requests = 3;
        } else if(gameSize == 3){
            requests = 4;
        }
        while (i < requests){
            student = -1;
            while (student < 1 || student > 7) {
                System.out.println("SELECT a student from the entrance:\n");
                Scanner scanner1 = new Scanner(System.in);
                try {
                    student = scanner1.nextInt();
                }catch (InputMismatchException e){
                    scanner1.nextLine();
                    System.out.println("Please retry...");
                }
            }
            System.out.println("ENTER:\n");
            System.out.println("0 -> to move student " + student + " to your SCHOOLBOARD");
            System.out.println("1 -> to move student " + student + " to an ISLAND");
            Scanner scanner2 = new Scanner(System.in);
            int pose = -1;
            try {
                pose = scanner2.nextInt();
            }catch (InputMismatchException e){
                scanner2.nextLine();
                System.out.println("Please retry...");
            }
            while (pose < 0 || pose > 1){
                System.out.println("Please enter 0 (SCHOOLBOARD) or 1 (ISLAND)");
                try {
                    pose = scanner2.nextInt();
                }catch (InputMismatchException e){
                    scanner2.nextLine();
                    System.out.println("Please retry...");
                }
            }
            if(i==0){
                student1Entrance = student;
                student1WhereToPut = pose;
                if(student1WhereToPut == 1){
                    while(indexIslandIf1ToIsland < 0 || indexIslandIf1ToIsland > model.getArchipelago().size() - 1) {
                        System.out.println("WHAT ISLAND? (enter the Island number)");
                        Scanner scanner3 = new Scanner(System.in);
                        try {
                            indexIslandIf1ToIsland = scanner3.nextInt();
                        }catch (InputMismatchException e){
                            scanner3.nextLine();
                            System.out.println("Please retry...");
                        }
                        while (indexIslandIf1ToIsland < 0 || indexIslandIf1ToIsland > 10){
                            System.out.println("Please enter a valid number");
                            try {
                                indexIslandIf1ToIsland = scanner3.nextInt();
                            }catch (InputMismatchException e){
                                scanner3.nextLine();
                                System.out.println("Please retry...");
                            }
                        }
                    }
                }
            }
            if(i==1){
                student2Entrance = student;
                student2WhereToPut = pose;
                if(student2WhereToPut == 1) {
                    while (indexIslandIf2ToIsland < 0 || indexIslandIf2ToIsland > model.getArchipelago().size() - 1) {
                        System.out.println("WHAT ISLAND? (enter the Island number)");
                        Scanner scanner3 = new Scanner(System.in);
                        try {
                            indexIslandIf2ToIsland = scanner3.nextInt();
                        }catch (InputMismatchException e){
                            scanner3.nextLine();
                            System.out.println("Please retry...");
                        }
                        while (indexIslandIf2ToIsland < 0 || indexIslandIf2ToIsland > 10){
                            System.out.println("Please enter a valid number");
                            try {
                                indexIslandIf2ToIsland = scanner3.nextInt();
                            }catch (InputMismatchException e){
                                scanner3.nextLine();
                                System.out.println("Please retry...");
                            }
                        }
                    }
                }
            }
            if(i==2) {
                student3Entrance = student;
                student3WhereToPut = pose;
                if (student3WhereToPut == 1) {
                    while (indexIslandIf3ToIsland < 0 || indexIslandIf3ToIsland > model.getArchipelago().size() - 1) {
                        System.out.println("WHAT ISLAND? (enter the Island number)");
                        Scanner scanner3 = new Scanner(System.in);
                        try {
                            indexIslandIf3ToIsland = scanner3.nextInt();
                        }catch (InputMismatchException e){
                            scanner3.nextLine();
                            System.out.println("Please retry...");
                        }
                        while (indexIslandIf3ToIsland < 0 || indexIslandIf3ToIsland > 10){
                            System.out.println("Please enter a valid number");
                            try {
                                indexIslandIf3ToIsland = scanner3.nextInt();
                            }catch (InputMismatchException e){
                                scanner3.nextLine();
                                System.out.println("Please retry...");
                            }
                        }
                    }
                }
            }
            if(i==3 && gameSize == 3) {
                student4Entrance = student;
                student4WhereToPut = pose;
                if (student4WhereToPut == 1) {
                    while (indexIslandIf4ToIsland < 0 || indexIslandIf4ToIsland > model.getArchipelago().size() - 1) {
                        System.out.println("WHAT ISLAND? (enter the Island number)");
                        Scanner scanner3 = new Scanner(System.in);
                        try {
                            indexIslandIf4ToIsland = scanner3.nextInt();
                        }catch (InputMismatchException e){
                            scanner3.nextLine();
                            System.out.println("Please retry...");
                        }
                        while (indexIslandIf4ToIsland < 0 || indexIslandIf4ToIsland > 10){
                            System.out.println("Please enter a valid number");
                            try {
                                indexIslandIf4ToIsland = scanner3.nextInt();
                            }catch (InputMismatchException e){
                                scanner3.nextLine();
                                System.out.println("Please retry...");
                            }
                        }
                    }
                }
            }
            i++;
        }

        sendMessage.sendMoveStudentsMessage(new MoveStudentMessage(student1Entrance,student1WhereToPut,indexIslandIf1ToIsland,
                student2Entrance,student2WhereToPut,indexIslandIf2ToIsland,
                student3Entrance,student3WhereToPut,indexIslandIf3ToIsland,
                student4Entrance,student4WhereToPut,indexIslandIf4ToIsland));

        showModel();

        MessageInterface receivedMessage = receiveMessage.receiveMessage();
        if (receivedMessage.getCode() == MessageType.NOERROR) {
            System.out.println("Correct selection.\r");
        } else if (receivedMessage.getCode() == MessageType.MOVESTUDENTERROR) {
        }

    }

    public void showModel() {

        //ask the list of GameComponents (all the model)
        sendMessage.sendModelMessage(new ModelMessage());
        ModelMessage modelMessage = (ModelMessage) receiveMessage();
        this.model = modelMessage;
        if(gameType == true && characterHandler == null){
            characterHandler = new CharacterHandlerClient(modelMessage, clientSocket);
        }
        if(gameType == true){
            characterHandler.setCoinsOwned(modelMessage.getCoinOwned());
            characterHandler.setModel(modelMessage);
        }
        //printing model..

        if (gameType == true) {
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
        }

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
        System.out.println("ENTRANCE:" );
        i = 0;
        for (Student student : modelMessage.getSchoolBoard().getEntrance().getStudents()) {
            System.out.println("student " + (i + 1) + ":");
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
        if (!modelMessage.getSchoolBoard().getTowers().isEmpty() && modelMessage.getSchoolBoard().getTowers().get(0).getColor() != null)
            System.out.println("TOWER color: " + modelMessage.getSchoolBoard().getTowers().get(0).getColor());

        System.out.println("Remaining Towers: " + modelMessage.getSchoolBoard().getTowers().size());
        i = 0;

        System.out.println("");
        System.out.println("CLOUDS: ");
        for (CloudCard card : modelMessage.getCloudCardList()) {
            System.out.println("cloud " + i + ":");
            System.out.println(card.getStudents());
            if (card.getStudents().size() == 0){
                cloudName[i] = -1; //cloud empty
            }
            i++;
        }
    }

    public MessageInterface receiveMessage() {
        MessageInterface message = receiveMessage.receiveMessageClient();
        if(message.getCode() == MessageType.PINGPONG){
            return receiveMessage();
        } else if(message.getCode() == MessageType.WIN){
            System.out.println(message.getMessage());
            System.exit(0);
        } else if(message.getCode() == MessageType.ERROR){
            System.out.println("Connection error.\r");
            System.exit(0);
        } else {
            return message;
        }
       return null;
    }

    public boolean moveMotherNature(){

        Scanner scanner;
        int numberSelectedSteps=0;
        sendMessage.sendMoveMotherNatureMessage(new MoveMotherNatureMessage());
        System.out.println("");
        System.out.println("How many steps you want MOTHERNATURE do?");
        switch (selectedCard){
            case 1,2:
                System.out.println("(You can Select only 1 step!)");
                scanner = new Scanner(System.in);
                try {
                    numberSelectedSteps = scanner.nextInt();
                }catch (InputMismatchException e){
                    scanner.nextLine();
                    System.out.println("Please retry...");
                }
                while (numberSelectedSteps != 1){
                    System.out.println("(You can Select only 1 step!!)");
                    try {
                        numberSelectedSteps = scanner.nextInt();
                    }catch (InputMismatchException e){
                        scanner.nextLine();
                        System.out.println("Please retry...");
                    }
                }
                break;
            case 3,4:
                System.out.println("(You can Select between 1 or 2 steps!)");
                scanner = new Scanner(System.in);
                try {
                    numberSelectedSteps = scanner.nextInt();
                }catch (InputMismatchException e){
                    scanner.nextLine();
                    System.out.println("Please retry...");
                }
                while (numberSelectedSteps < 1 || numberSelectedSteps > 2){
                    System.out.println("(You can Select only 1 or 2 steps!!)");
                    try {
                        numberSelectedSteps = scanner.nextInt();
                    }catch (InputMismatchException e){
                        scanner.nextLine();
                        System.out.println("Please retry...");
                    }
                }
                break;
            case 5,6:
                System.out.println("(You can Select from 1 to 3 steps!)");
                scanner = new Scanner(System.in);
                try {
                    numberSelectedSteps = scanner.nextInt();
                }catch (InputMismatchException e){
                    scanner.nextLine();
                    System.out.println("Please retry...");
                }
                while (numberSelectedSteps < 1 || numberSelectedSteps > 3){
                    System.out.println("(You can Select only 1,2 or 3 steps!!)");
                    try {
                        numberSelectedSteps = scanner.nextInt();
                    }catch (InputMismatchException e){
                        scanner.nextLine();
                        System.out.println("Please retry...");
                    }
                }
                break;
            case 7,8:
                System.out.println("(You can Select from 1 to 4 steps!)");
                scanner = new Scanner(System.in);
                try {
                    numberSelectedSteps = scanner.nextInt();
                }catch (InputMismatchException e){
                    scanner.nextLine();
                    System.out.println("Please retry...");
                }
                while (numberSelectedSteps < 1 || numberSelectedSteps > 4){
                    System.out.println("(You can Select only 1,2,3 or 4 steps!!)");
                    try {
                        numberSelectedSteps = scanner.nextInt();
                    }catch (InputMismatchException e){
                        scanner.nextLine();
                        System.out.println("Please retry...");
                    }
                }
                break;
            case 9,10:
                System.out.println("(You can Select from 1 to 5 steps!)");
                scanner = new Scanner(System.in);
                try {
                    numberSelectedSteps = scanner.nextInt();
                }catch (InputMismatchException e){
                    scanner.nextLine();
                    System.out.println("Please retry...");
                }
                while (numberSelectedSteps < 1 || numberSelectedSteps > 5){
                    System.out.println("(You can Select only 1,2,3,4 or 5 steps!!)");
                    try {
                        numberSelectedSteps = scanner.nextInt();
                    }catch (InputMismatchException e){
                        scanner.nextLine();
                        System.out.println("Please retry...");
                    }
                }
                break;
            default:
                System.out.println("(ERROR: you have to play an Assistant Card first or choose a correct number of Steps!)");
                break;
        }


        sendMessage.sendMoveMotherNatureMessage(new MoveMotherNatureMessage(numberSelectedSteps));

        showModel();

        MessageInterface receivedMessage = receiveMessage();
        selectedCard = -1;
        if (receivedMessage.getCode() == MessageType.NOERROR) {
            System.out.println("Correct selection.\r");
            return true;
        } else if (receivedMessage.getCode() == MessageType.MOVEMOTHERNATUREERROR) {
            return false;
        }
        return false;
    }

    public void selectCloudCard(){
        sendMessage.sendCloudCardMessage(new CloudCardChoiceMessage());
        Scanner scanner = new Scanner(System.in);

        int choice = -1;
        boolean ok = false;
        if (gameSize == 2){
            System.out.println("Select 0 or 1 to choose the cloud card.\r");
            while (!ok){
                try {
                    choice = scanner.nextInt();
                }catch (InputMismatchException e){
                    scanner.nextLine();
                    System.out.println("Please retry...");
                }
                if(choice < 0 || choice > 1 || cloudName[choice]==-1){
                    System.out.println("Please chose a valid Cloud Card!.\r");
                    ok = false;
                } else {
                    cloudName[choice]=-1;
                    ok = true;
                    System.out.println("Please wait the opponent's move...");
                }
            }
        }
        if (gameSize == 3){
            System.out.println("Select 0, 1 or 2 to choose the cloud card.\r");
            while (!ok){
                try {
                    choice = scanner.nextInt();
                }catch (InputMismatchException e){
                    scanner.nextLine();
                    System.out.println("Please retry...");
                }
                if(choice < 0 || choice > 2 || cloudName[choice]==-1){
                    System.out.println("Please chose a valid Cloud Card!\r");
                    ok = false;
                } else {
                    cloudName[choice]=-1;
                    ok = true;
                    System.out.println("Please wait the opponent's move...");
                }
            }
        }



        sendMessage.sendCloudCardMessage(new CloudCardChoiceMessage(choice));
    }

    public void setModel(ModelMessage model){
        this.model = model;

    }

    /*
    public void showModel1() {

        //ask the list of GameComponents (all the model)
        sendMessage.sendModelMessage(new ModelMessage());
        ModelMessage modelMessage = (ModelMessage) receiveMessage();
        this.model = modelMessage;
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
        System.out.println("ENTRANCE:" +"\t"+"\t"+"\t"+"\t"+"\t"+ "DINING ROOMS: " +"\t"+"\t"+"\t"+"\t"+"\t"+"Remaining Towers: " + modelMessage.getSchoolBoard().getTowers().size());
        i = 0;
        for (Student student : modelMessage.getSchoolBoard().getEntrance().getStudents()) {
            System.out.println("student " + (i + 1) + ":" );
            System.out.println(student.getColor());
            if(i==0){
                for (DiningRoom diningRoom : modelMessage.getSchoolBoard().getDiningRooms()) {
                    System.out.println("\t"+"\t"+"\t"+"\t"+"\t"+"\t" + "Color: " + modelMessage.getSchoolBoard().getDiningRooms().get(i).getColor());
                    System.out.println("\t"+"\t"+"\t"+"\t"+"\t"+"\t" +"Number of Students: " + modelMessage.getSchoolBoard().getDiningRooms().get(i).getStudents().size());
                    System.out.println("\t"+"\t"+"\t"+"\t"+"\t"+"\t" +"Professor: " + modelMessage.getSchoolBoard().getDiningRooms().get(i).IsProfessor());
                    i++;
                    System.out.println("");
                }
                i++;
            }
            i--;
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
        if (!modelMessage.getSchoolBoard().getTowers().isEmpty() && modelMessage.getSchoolBoard().getTowers().get(0).getColor() != null)
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


     */


    public void askCharacter(){
        characterHandler.askCharacter();
    }

    public void setSendMessage(ObjectToJSON sendMessage) {
        this.sendMessage = sendMessage;
    }

    public void setReceiveMessage(JSONtoObject receiveMessage) {
        this.receiveMessage = receiveMessage;
    }

}
package it.polimi.ingsw.communication.client;

import com.google.gson.Gson;
import it.polimi.ingsw.communication.common.*;
import it.polimi.ingsw.communication.common.messages.*;
import it.polimi.ingsw.model.board.CloudCard;
import it.polimi.ingsw.model.board.DiningRoom;
import it.polimi.ingsw.model.board.IslandCard;
import it.polimi.ingsw.model.cards.CharacterCard;
import it.polimi.ingsw.model.colors.ColorStudent;
import it.polimi.ingsw.model.colors.ColorTower;
import it.polimi.ingsw.model.pieces.Student;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class ClientMain extends Thread {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    private int port;
    private static int[] cloudName = new int[4];
    private Socket clientSocket;
    private ObjectToJSON sendMessage;
    private JSONtoObject receiveMessage;
    private int selectedCard;
    private int gameSize;
    private ModelMessage model;
    private boolean gameType;

    private CharacterHandlerClient characterHandler;

    public ObjectToJSON getSendMessage() {
        return sendMessage;
    }

    public JSONtoObject getReceiveMessage() {
        return receiveMessage;
    }

    public ModelMessage getModel() {
        return model;
    }

    public static void main(String[] args) {
        ClientMain clientMain = new ClientMain();
        System.out.println(ANSI_PURPLE +"                                                                                                                                                                                                       \n" +
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
                "                                                  .                                                                                                                                                     \n" + ANSI_RESET);

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
        System.out.println("Waiting for my turn...");
        while (true){
            MessageInterface receivedMessage = clientMain.receiveMessage();
            MessageType message = receivedMessage.getCode();
            System.out.println("start turn " + message);

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
                        System.out.println(ANSI_PURPLE+receivedMessage.getMessage()+ANSI_RESET);
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
                        System.out.println(ANSI_PURPLE+receivedMessage.getMessage()+ANSI_RESET);
                        System.out.println("-----------------");
                        return;
                    case MODEL:
                        clientMain.setModel((ModelMessage) receivedMessage);
                        break;
                }

            }
        }
    }


    public boolean isGameType() {
        return gameType;
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
            // clientSocket.setSoTimeout(100000);
            setReceiveMessage(new JSONtoObject(clientSocket));
            setSendMessage(new ObjectToJSON(clientSocket));
            return true;

        } else if(input.equals("no")){
            return false;
        }
        return false;
    }

    public void readParameters() throws IOException {
        Gson gson = new Gson();
        //create a reader
        Reader reader = Files.newBufferedReader(Paths.get("/Users/matteocolella/Desktop/Politecnico/ingsw2022-AM11/src/main/resources/configs/configClient.json"));
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

    public void createConnection() throws IOException {
        //connect to the server on the local host: to be modified.
        // socket parameters
        /*
        String ip = "192.168.4.192";
        InetAddress host = InetAddress.getByName(ip);
         */
        InetAddress host = InetAddress.getLocalHost();
        clientSocket = new Socket(host, port);
        // clientSocket.setSoTimeout(100000);
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
        System.out.println(ANSI_PURPLE+" _____________________________\n" +
                "|                             |\n" +
                "|                             |\n" +
                "|   The game is starting...   |\n" +
                "|                             |\n" +
                "|_____________________________|\n"+ANSI_RESET);
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
            System.out.println(ANSI_PURPLE+" ________________________________________\n" +
                    "|                                        |\n" +
                    "|                                        |\n" +
                    "|   Waiting for the opponent's move...   |\n" +
                    "|                                        |\n" +
                    "|________________________________________|"+ANSI_RESET);



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

        System.out.println(ANSI_YELLOW+"List of played cards on table: "+ANSI_RESET);
        for (int j = 0; j < assistantCardsMessage.getChosenCard().size(); j++) {
            System.out.println(ANSI_YELLOW+"___________________________________"+ANSI_RESET);
            System.out.println(ANSI_YELLOW+"|"+ANSI_RESET+"CARD NAME: " + assistantCardsMessage.getChosenCard().get(j).getName());
            System.out.println(ANSI_YELLOW+"|"+ANSI_RESET+"Influence: -> " + assistantCardsMessage.getChosenCard().get(j).getInfluence());
            System.out.println(ANSI_YELLOW+"|"+ANSI_RESET+"Steps: -> " + assistantCardsMessage.getChosenCard().get(j).getSteps());

        }

        //list of my cards:

        System.out.println("");
        System.out.println(ANSI_YELLOW+"--> "+ANSI_RESET+ANSI_YELLOW_BACKGROUND+"My cards:"+ANSI_RESET);
        for (int i = 0; i < assistantCardsMessage.getDeck().size(); i++) {
            if(!assistantCardsMessage.getDeck().get(i).isUsed()){
                System.out.println(ANSI_YELLOW+"___________________________________"+ANSI_RESET);
                System.out.println(ANSI_YELLOW+"|"+ANSI_RESET+"(Enter -> " + (assistantCardsMessage.getDeck().get(i).getInfluence()) + " for: )");
                System.out.println(ANSI_YELLOW+"|"+ANSI_RESET+"NAME: " + assistantCardsMessage.getDeck().get(i).getName());
                System.out.println(ANSI_YELLOW+"|"+ANSI_RESET+"Influence: -> " + assistantCardsMessage.getDeck().get(i).getInfluence());
                System.out.println(ANSI_YELLOW+"|"+ANSI_RESET+"Steps: -> " + assistantCardsMessage.getDeck().get(i).getSteps());

            }
        }
        System.out.println("");
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
            System.out.println(ANSI_PURPLE+" ________________________________________\n" +
                    "|                                        |\n" +
                    "|                                        |\n" +
                    "|   Waiting for the opponent's move...   |\n" +
                    "|                                        |\n" +
                    "|________________________________________|"+ANSI_RESET);
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
        int alreadyChosenStudent1 = -55;
        int alreadyChosenStudent2 = -55;
        int alreadyChosenStudent3 = -55;
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
            if(gameSize == 2 || gameSize == 4){
                while (student < 1 || student > 7 ) {
                    if (student != alreadyChosenStudent1 && student != alreadyChosenStudent2){
                        System.out.println("SELECT a student from the entrance:\n");
                        Scanner scanner1 = new Scanner(System.in);
                        try {
                            student = scanner1.nextInt();
                        } catch (InputMismatchException e) {
                            scanner1.nextLine();
                            System.out.println("Please retry...");
                        }
                    }while(student == alreadyChosenStudent1 || student == alreadyChosenStudent2){
                        System.out.println("Please select a never chosen student on the Entrance!");
                        Scanner scanner1 = new Scanner(System.in);
                        try {
                            student = scanner1.nextInt();
                        } catch (InputMismatchException e) {
                            scanner1.nextLine();
                            System.out.println("Please retry...");
                        }
                    }
                }
            } else if(gameSize == 3){
                while (student < 1 || student > 9) {
                    if (student != alreadyChosenStudent1 && student != alreadyChosenStudent2 && student != alreadyChosenStudent3){
                        System.out.println("SELECT a student from the entrance:\n");
                        Scanner scanner1 = new Scanner(System.in);
                        try {
                            student = scanner1.nextInt();
                        } catch (InputMismatchException e) {
                            scanner1.nextLine();
                            System.out.println("Please retry...");
                        }
                    }while(student == alreadyChosenStudent1 || student == alreadyChosenStudent2 || student == alreadyChosenStudent3){
                        System.out.println("Please select a never chosen student on the Entrance!");
                        System.out.println("SELECT a student from the entrance:\n");
                        Scanner scanner1 = new Scanner(System.in);
                        try {
                            student = scanner1.nextInt();
                        } catch (InputMismatchException e) {
                            scanner1.nextLine();
                            System.out.println("Please retry...");
                        }
                    }
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
                alreadyChosenStudent1=student;
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
                alreadyChosenStudent2=student;
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
                alreadyChosenStudent3 = student;
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
        if (receivedMessage.getCode() == MessageType.TURN) {
            System.out.println("Correct selection.\r");
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
            System.out.println("");
            if (modelMessage.getCoinOwned() >= 0) {
                System.out.println(ANSI_PURPLE+"--> "+ANSI_RESET + ANSI_PURPLE_BACKGROUND+ "CHARACTER CARDS:"+ANSI_RESET);
                for (CharacterCard characterCard : modelMessage.getCharacterCards()) {
                    System.out.println(ANSI_PURPLE+characterCard.getNum()+ANSI_RESET);
                    if (characterCard.getNum()==1)
                        System.out.println("1 COIN EFFECT: Take a student from this card and place it on an island");
                    if (characterCard.getNum()==2)
                        System.out.println("2 COINS EFFECT: If you have the same number of professors as another player, you control them");
                    if (characterCard.getNum()==3)
                        System.out.println("3 COINS EFFECT: Choose an island where you can calculate dominance and place a tower if possible");
                    if (characterCard.getNum()==4)
                        System.out.println("1 COIN EFFECT: +2 extra steps of Mother Nature");
                    if (characterCard.getNum()==5)
                        System.out.println("2 COINS EFFECT: Put a No Entry Tile on an Island");
                    if (characterCard.getNum()==6)
                        System.out.println("3 COINS EFFECT: Do not calculate the towers on an island during the domination check");
                    if (characterCard.getNum()==7)
                        System.out.println("1 COIN EFFECT: Replace 3 students with those in your entrance");
                    if (characterCard.getNum()==8)
                        System.out.println("2 COINS EFFECT: +2 points of your influence in your turn");
                    if (characterCard.getNum()==9)
                        System.out.println("3 COINS EFFECT: Choose a color that provides no influence in your turn");
                    if (characterCard.getNum()==10)
                        System.out.println("1 COIN EFFECT: Exchange 2 students present in your Entrance and in your Dining Room");
                    if (characterCard.getNum()==11)
                        System.out.println("2 COINS EFFECT: Draw a student to put in your Dining Room");
                    if (characterCard.getNum()==12)
                        System.out.println("3 COINS EFFECT: Put 3 students of the color of your choice back into the bag from all Dining Rooms of all the players");
                }
                System.out.println("");
                System.out.println(ANSI_YELLOW+"--> "+ANSI_RESET +ANSI_YELLOW_BACKGROUND+ "YOUR COINS:" +ANSI_RESET);
                System.out.println(ANSI_YELLOW+ modelMessage.getCoinOwned()+ANSI_RESET);
                System.out.println("");
            }
        }

        int i = 0;
        System.out.println("");
        System.out.println(ANSI_GREEN+" ________________________________________\n" +
                "|             ARCHIPELAGO                |\n" +
                "|________________________________________|"+ANSI_RESET);
        System.out.println("");
        for (IslandCard islandCard : modelMessage.getArchipelago()) {
            if (islandCard.getMotherNature()) {
                System.out.println(ANSI_BLACK+ANSI_WHITE_BACKGROUND+"MOTHERNATURE"+ANSI_RESET+ANSI_RESET);
            }if (i < 10) {
                if(islandCard.getTower()!=null) {
                    if (islandCard.getTower().getColor() == ColorTower.BLACK) {

                        System.out.println(ANSI_GREEN + "" + ANSI_RESET +
                                ANSI_GREEN + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" + ANSI_RESET +
                                ANSI_GREEN + "@@@@                        @@@@\n" + ANSI_RESET +
                                ANSI_GREEN + "@@@          ISLAND          @@@\n" + ANSI_RESET +
                                ANSI_GREEN + "@@             " + i + "              @@\n" + ANSI_RESET +
                                ANSI_GREEN + "@                              @\n" + ANSI_RESET +
                                ANSI_GREEN + "@@           Tower:           @@\n" + ANSI_RESET +
                                ANSI_GREEN + "@@@          BLACK           @@@\n" + ANSI_RESET +
                                ANSI_GREEN + "@@@@                        @@@@\n" + ANSI_RESET +
                                ANSI_GREEN + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + ANSI_RESET);

                    }
                    if (islandCard.getTower().getColor() == ColorTower.WHITE) {

                        System.out.println(ANSI_GREEN + "" + ANSI_RESET +
                                ANSI_GREEN + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" + ANSI_RESET +
                                ANSI_GREEN + "@@@@                        @@@@\n" + ANSI_RESET +
                                ANSI_GREEN + "@@@          ISLAND          @@@\n" + ANSI_RESET +
                                ANSI_GREEN + "@@             " + i + "              @@\n" + ANSI_RESET +
                                ANSI_GREEN + "@                              @\n" + ANSI_RESET +
                                ANSI_GREEN + "@@           Tower:           @@\n" + ANSI_RESET +
                                ANSI_GREEN + "@@@          WHITE           @@@\n" + ANSI_RESET +
                                ANSI_GREEN + "@@@@                        @@@@\n" + ANSI_RESET +
                                ANSI_GREEN + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + ANSI_RESET);

                    }
                    if (islandCard.getTower().getColor() == ColorTower.GREY) {

                        System.out.println(ANSI_GREEN + "" + ANSI_RESET +
                                ANSI_GREEN + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" + ANSI_RESET +
                                ANSI_GREEN + "@@@@                        @@@@\n" + ANSI_RESET +
                                ANSI_GREEN + "@@@          ISLAND          @@@\n" + ANSI_RESET +
                                ANSI_GREEN + "@@             " + i + "              @@\n" + ANSI_RESET +
                                ANSI_GREEN + "@                              @\n" + ANSI_RESET +
                                ANSI_GREEN + "@@           Tower:           @@\n" + ANSI_RESET +
                                ANSI_GREEN + "@@@           GREY           @@@\n" + ANSI_RESET +
                                ANSI_GREEN + "@@@@                        @@@@\n" + ANSI_RESET +
                                ANSI_GREEN + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + ANSI_RESET);

                    }
                }
                if(islandCard.getTower() == null){
                    System.out.println(ANSI_GREEN + "" + ANSI_RESET +
                            ANSI_GREEN + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" + ANSI_RESET +
                            ANSI_GREEN + "@@@@                        @@@@\n" + ANSI_RESET +
                            ANSI_GREEN + "@@@          ISLAND          @@@\n" + ANSI_RESET +
                            ANSI_GREEN + "@@             " + i + "              @@\n" + ANSI_RESET +
                            ANSI_GREEN + "@                              @\n" + ANSI_RESET +
                            ANSI_GREEN + "@@      There isn't any       @@\n" + ANSI_RESET +
                            ANSI_GREEN + "@@@          tower           @@@\n" + ANSI_RESET +
                            ANSI_GREEN + "@@@@                        @@@@\n" + ANSI_RESET +
                            ANSI_GREEN + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + ANSI_RESET);

                }
            }else{
                if(islandCard.getTower()!=null) {
                    if (islandCard.getTower().getColor() == ColorTower.BLACK) {

                        System.out.println(ANSI_GREEN + "" + ANSI_RESET +
                                ANSI_GREEN + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" + ANSI_RESET +
                                ANSI_GREEN + "@@@@                        @@@@\n" + ANSI_RESET +
                                ANSI_GREEN + "@@@          ISLAND          @@@\n" + ANSI_RESET +
                                ANSI_GREEN + "@@             " + i + "             @@\n" + ANSI_RESET +
                                ANSI_GREEN + "@                              @\n" + ANSI_RESET +
                                ANSI_GREEN + "@@           Tower:           @@\n" + ANSI_RESET +
                                ANSI_GREEN + "@@@          BLACK           @@@\n" + ANSI_RESET +
                                ANSI_GREEN + "@@@@                        @@@@\n" + ANSI_RESET +
                                ANSI_GREEN + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + ANSI_RESET);

                    }
                    if (islandCard.getTower().getColor() == ColorTower.WHITE) {

                        System.out.println(ANSI_GREEN + "" + ANSI_RESET +
                                ANSI_GREEN + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" + ANSI_RESET +
                                ANSI_GREEN + "@@@@                        @@@@\n" + ANSI_RESET +
                                ANSI_GREEN + "@@@          ISLAND          @@@\n" + ANSI_RESET +
                                ANSI_GREEN + "@@             " + i + "             @@\n" + ANSI_RESET +
                                ANSI_GREEN + "@                              @\n" + ANSI_RESET +
                                ANSI_GREEN + "@@           Tower:           @@\n" + ANSI_RESET +
                                ANSI_GREEN + "@@@          WHITE           @@@\n" + ANSI_RESET +
                                ANSI_GREEN + "@@@@                        @@@@\n" + ANSI_RESET +
                                ANSI_GREEN + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + ANSI_RESET);

                    }
                    if (islandCard.getTower().getColor() == ColorTower.GREY) {

                        System.out.println(ANSI_GREEN + "" + ANSI_RESET +
                                ANSI_GREEN + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" + ANSI_RESET +
                                ANSI_GREEN + "@@@@                        @@@@\n" + ANSI_RESET +
                                ANSI_GREEN + "@@@          ISLAND          @@@\n" + ANSI_RESET +
                                ANSI_GREEN + "@@             " + i + "             @@\n" + ANSI_RESET +
                                ANSI_GREEN + "@                              @\n" + ANSI_RESET +
                                ANSI_GREEN + "@@           Tower:           @@\n" + ANSI_RESET +
                                ANSI_GREEN + "@@@           GREY           @@@\n" + ANSI_RESET +
                                ANSI_GREEN + "@@@@                        @@@@\n" + ANSI_RESET +
                                ANSI_GREEN + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + ANSI_RESET);

                    }
                }
                if(islandCard.getTower() == null){
                    System.out.println(ANSI_GREEN + "" + ANSI_RESET +
                            ANSI_GREEN + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" + ANSI_RESET +
                            ANSI_GREEN + "@@@@                        @@@@\n" + ANSI_RESET +
                            ANSI_GREEN + "@@@          ISLAND          @@@\n" + ANSI_RESET +
                            ANSI_GREEN + "@@             " + i + "             @@\n" + ANSI_RESET +
                            ANSI_GREEN + "@                              @\n" + ANSI_RESET +
                            ANSI_GREEN + "@@      There isn't any       @@\n" + ANSI_RESET +
                            ANSI_GREEN + "@@@          tower           @@@\n" + ANSI_RESET +
                            ANSI_GREEN + "@@@@                        @@@@\n" + ANSI_RESET +
                            ANSI_GREEN + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" + ANSI_RESET);

                }
            }
            System.out.println("Students:");
            for (int j = 0; j < islandCard.getStudents().size(); j++) {
                if(islandCard.getStudents().get(j).getColor().equals(ColorStudent.PINK))
                    System.out.println("Student " + j + " color : " +ANSI_PURPLE+ islandCard.getStudents().get(j)+ANSI_RESET);
                if(islandCard.getStudents().get(j).getColor().equals(ColorStudent.RED))
                    System.out.println("Student " + j + " color : " +ANSI_RED+ islandCard.getStudents().get(j)+ANSI_RESET);
                if(islandCard.getStudents().get(j).getColor().equals(ColorStudent.GREEN))
                    System.out.println("Student " + j + " color : " +ANSI_GREEN+ islandCard.getStudents().get(j)+ANSI_RESET);
                if(islandCard.getStudents().get(j).getColor().equals(ColorStudent.BLUE))
                    System.out.println("Student " + j + " color : " +ANSI_BLUE+ islandCard.getStudents().get(j)+ANSI_RESET);
                if(islandCard.getStudents().get(j).getColor().equals(ColorStudent.YELLOW))
                    System.out.println("Student " + j + " color : " +ANSI_YELLOW+ islandCard.getStudents().get(j)+ANSI_RESET);
            }
            System.out.println("Merged with: ");
            for (int j = 0; j < islandCard.getMergedWith().size(); j++) {
                IslandCard tempIslandCard = islandCard.getMergedWith().get(j);
                for (int k = 0; k < tempIslandCard.getStudents().size(); k++) {
                    if(tempIslandCard.getStudents().get(k).getColor().equals(ColorStudent.PINK))
                        System.out.println("Student " + k + " color : " +ANSI_PURPLE+ tempIslandCard.getStudents().get(k)+ANSI_RESET);
                    if(tempIslandCard.getStudents().get(k).getColor().equals(ColorStudent.RED))
                        System.out.println("Student " + k + " color : " +ANSI_RED+ tempIslandCard.getStudents().get(k)+ANSI_RESET);
                    if(tempIslandCard.getStudents().get(k).getColor().equals(ColorStudent.GREEN))
                        System.out.println("Student " + k + " color : " +ANSI_GREEN+ tempIslandCard.getStudents().get(k)+ANSI_RESET);
                    if(tempIslandCard.getStudents().get(k).getColor().equals(ColorStudent.BLUE))
                        System.out.println("Student " + k + " color : " +ANSI_BLUE+ tempIslandCard.getStudents().get(k)+ANSI_RESET);
                    if(tempIslandCard.getStudents().get(k).getColor().equals(ColorStudent.YELLOW))
                        System.out.println("Student " + k + " color : " +ANSI_YELLOW+ tempIslandCard.getStudents().get(k)+ANSI_RESET);
                }
            }
            i++;
            System.out.println("");
        }
        System.out.println(ANSI_PURPLE+" ________________________________________\n" +
                "|             MY SCHOOLBOARD             |\n" +
                "|________________________________________|"+ANSI_RESET);
        System.out.println("");
        System.out.println(ANSI_PURPLE+"ENTRANCE:" +ANSI_RESET);
        System.out.println(ANSI_PURPLE+"________________________________________" +ANSI_RESET);
        i = 0;
        for (Student student : modelMessage.getSchoolBoard().getEntrance().getStudents()) {
            System.out.println(ANSI_PURPLE+"|" +ANSI_RESET+"student " + (i + 1) + ":");
            if(student.getColor().equals(ColorStudent.RED))
                System.out.println(ANSI_PURPLE+"|" +ANSI_RESET+ ANSI_RED+ student.getColor()+ANSI_RESET);
            if(student.getColor().equals(ColorStudent.PINK))
                System.out.println(ANSI_PURPLE+"|" +ANSI_RESET+ ANSI_PURPLE+ student.getColor()+ANSI_RESET);
            if(student.getColor().equals(ColorStudent.BLUE))
                System.out.println(ANSI_PURPLE+"|" +ANSI_RESET+ ANSI_BLUE+ student.getColor()+ANSI_RESET);
            if(student.getColor().equals(ColorStudent.YELLOW))
                System.out.println(ANSI_PURPLE+"|" +ANSI_RESET+ ANSI_YELLOW+ student.getColor()+ANSI_RESET);
            if(student.getColor().equals(ColorStudent.GREEN))
                System.out.println(ANSI_PURPLE+"|" +ANSI_RESET+ ANSI_GREEN+ student.getColor()+ANSI_RESET);
            i++;
        }
        System.out.println(ANSI_PURPLE+"________________________________________" +ANSI_RESET);
        System.out.println("");
        System.out.println(ANSI_PURPLE+"DINING ROOMS:"+ANSI_RESET);
        i = 0;
        for (DiningRoom diningRoom : modelMessage.getSchoolBoard().getDiningRooms()) {
            if(diningRoom.getColor().equals(ColorStudent.RED)){
                System.out.println(ANSI_RED+"________________________________________" +ANSI_RESET);
                System.out.println(ANSI_RED+"|"+ANSI_RESET+"Color: "+ANSI_RED + modelMessage.getSchoolBoard().getDiningRooms().get(i).getColor()+ANSI_RESET);
                System.out.println(ANSI_RED+"|"+ANSI_RESET+"Number of Students: " + modelMessage.getSchoolBoard().getDiningRooms().get(i).getStudents().size());
                System.out.println(ANSI_RED+"|"+ANSI_RESET+"Professor: " + modelMessage.getSchoolBoard().getDiningRooms().get(i).IsProfessor());
                i++;
                System.out.println("");
            }
            if(diningRoom.getColor().equals(ColorStudent.GREEN)){
                System.out.println(ANSI_GREEN+"________________________________________" +ANSI_RESET);
                System.out.println(ANSI_GREEN+"|"+ANSI_RESET+"Color: "+ANSI_GREEN + modelMessage.getSchoolBoard().getDiningRooms().get(i).getColor()+ANSI_RESET);
                System.out.println(ANSI_GREEN+"|"+ANSI_RESET+"Number of Students: " + modelMessage.getSchoolBoard().getDiningRooms().get(i).getStudents().size());
                System.out.println(ANSI_GREEN+"|"+ANSI_RESET+"Professor: " + modelMessage.getSchoolBoard().getDiningRooms().get(i).IsProfessor());
                i++;
                System.out.println("");
            }
            if(diningRoom.getColor().equals(ColorStudent.BLUE)){
                System.out.println(ANSI_BLUE+"________________________________________" +ANSI_RESET);
                System.out.println(ANSI_BLUE+"|"+ANSI_RESET+"Color: " +ANSI_BLUE+ modelMessage.getSchoolBoard().getDiningRooms().get(i).getColor()+ANSI_RESET);
                System.out.println(ANSI_BLUE+"|"+ANSI_RESET+"Number of Students: " + modelMessage.getSchoolBoard().getDiningRooms().get(i).getStudents().size());
                System.out.println(ANSI_BLUE+"|"+ANSI_RESET+"Professor: " + modelMessage.getSchoolBoard().getDiningRooms().get(i).IsProfessor());
                i++;
                System.out.println("");
            }
            if(diningRoom.getColor().equals(ColorStudent.PINK)){
                System.out.println(ANSI_PURPLE+"________________________________________" +ANSI_RESET);
                System.out.println(ANSI_PURPLE+"|"+ANSI_RESET+"Color: "+ANSI_PURPLE + modelMessage.getSchoolBoard().getDiningRooms().get(i).getColor()+ANSI_RESET);
                System.out.println(ANSI_PURPLE+"|"+ANSI_RESET+"Number of Students: " + modelMessage.getSchoolBoard().getDiningRooms().get(i).getStudents().size());
                System.out.println(ANSI_PURPLE+"|"+ANSI_RESET+"Professor: " + modelMessage.getSchoolBoard().getDiningRooms().get(i).IsProfessor());
                i++;
                System.out.println("");
            }
            if(diningRoom.getColor().equals(ColorStudent.YELLOW)){
                System.out.println(ANSI_YELLOW+"________________________________________" +ANSI_RESET);
                System.out.println(ANSI_YELLOW+"|"+ANSI_RESET+"Color: " + ANSI_YELLOW+modelMessage.getSchoolBoard().getDiningRooms().get(i).getColor()+ANSI_RESET);
                System.out.println(ANSI_YELLOW+"|"+ANSI_RESET+"Number of Students: " + modelMessage.getSchoolBoard().getDiningRooms().get(i).getStudents().size());
                System.out.println(ANSI_YELLOW+"|"+ANSI_RESET+"Professor: " + modelMessage.getSchoolBoard().getDiningRooms().get(i).IsProfessor());
                i++;
                System.out.println("");
            }
        }
        if (!modelMessage.getSchoolBoard().getTowers().isEmpty() && modelMessage.getSchoolBoard().getTowers().get(0).getColor() != null)
            System.out.println(ANSI_PURPLE+"TOWER color: " +ANSI_RESET+ modelMessage.getSchoolBoard().getTowers().get(0).getColor());

        System.out.println(ANSI_PURPLE+"Remaining Towers: "+ANSI_RESET + modelMessage.getSchoolBoard().getTowers().size());
        i = 0;

        System.out.println("");
        System.out.println(ANSI_CYAN+" ________________________________________\n" +
                "|                 CLOUDS                 |\n" +
                "|________________________________________|"+ANSI_RESET);
        System.out.println("");
        for (CloudCard card : modelMessage.getCloudCardList()) {
            System.out.println(ANSI_CYAN+
                    "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                    "@@@@@@@@@@BY7~^~~7JP&@@@@@@@@@@@@@@\n" +
                    "@@@@@@@&&?          .!7~^^^!?P&@@@@\n" +
                    "@@@#Y!^::                     :P@@@\n" +
                    "@@P.                           ^@@@\n" +
                    "@@5          CLOUD "+i+"          .G@@@" + "    ->  "+card.getStudents()+ "\n"+
                    "@@@#J:                        .7G@@\n" +
                    "@@@G:                            P@\n" +
                    "@@@G.                           :G@\n" +
                    "@@@@&57~^~7^               ~?JYG&@@\n" +
                    "@@@@@@@@@@@&5!:.       .^?G@@@@@@@@\n" +
                    "@@@@@@@@@@@@@@&#GP55PPB#@@@@@@@@@@@"+ANSI_RESET);
            if (card.getStudents().size() == 0){
                cloudName[i] = -1; //cloud empty
            }
            i++;
        }
        System.out.println("");
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
        if (receivedMessage.getCode() == MessageType.TURN) {
            System.out.println("Correct selection.\r");
            return true;
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



                    System.out.println(ANSI_PURPLE+" ________________________________________\n" +
                            "|   Please wait the opponent's move...   |\n" +
                            "|________________________________________|"+ANSI_RESET);


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
                    System.out.println(ANSI_PURPLE+" ________________________________________\n" +
                            "|   Please wait the opponent's move...   |\n" +
                            "|________________________________________|"+ANSI_RESET);
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
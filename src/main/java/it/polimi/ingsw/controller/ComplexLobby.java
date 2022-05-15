package it.polimi.ingsw.controller;

import it.polimi.ingsw.communication.common.*;
import it.polimi.ingsw.communication.common.messages.AssistantCardsMessage;
import it.polimi.ingsw.communication.common.messages.MageMessage;
import it.polimi.ingsw.communication.common.messages.ModelMessage;
import it.polimi.ingsw.communication.common.messages.WinMessage;
import it.polimi.ingsw.model.Mage;
import it.polimi.ingsw.model.MovedStudent;
import it.polimi.ingsw.model.board.*;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.cards.AssistantDeck;
import it.polimi.ingsw.model.cards.Card;
import it.polimi.ingsw.model.pieces.Student;

import java.io.IOException;
import java.net.Socket;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ComplexLobby{
    private Game game;
    private final boolean gameType;
    private final int numPlayers;
    private final int ID;
    private Player activePlayer;
    private ArrayList<Player> players;
    private boolean ready;
    private CoinReserve coinReserve;
    private DeckManager dm;
    private final ArrayList<Card> chosenCards;
    private ArrayList<Player> playerOrder;
    private final HashMap<Player, Socket> clientSocketsMap;
    private int roundCounter;
    private boolean cornerCase;
    private static Object preMageLock;
    private static Object afterMageLock;
    private static Object preCardLock;
    private static Object afterCardLock;

    private CloseConnectionThread closeConnectionThread;


    // Start of Getters, Setters, Constructor
    public ComplexLobby(int numplayers, boolean gametype, int ID, ArrayList<Player> Players) {
        this.chosenCards = new ArrayList<>();
        this.gameType = gametype;
        this.numPlayers = numplayers;
        this.ID = ID;
        this.players = Players;
        this.dm = new DeckManager();
        this.playerOrder = new ArrayList<>();
        this.clientSocketsMap = new HashMap<>();
        this.roundCounter=0;
        this.cornerCase=false;

        preMageLock = new Object();
        preCardLock = new Object();
        afterMageLock = new Object();
        afterCardLock = new Object();

    }

    public Object getPreMageLock() {
        return preMageLock;
    }

    public Object getPreCardLock(){
        return preCardLock;
    }

    public Object getAfterMageLock() {
        return afterMageLock;
    }

    public Object getAfterCardLock() {
        return afterCardLock;
    }

    public int getRoundCounter() {
        return roundCounter;
    }

    public ArrayList<Player> getPlayerOrder() {
        return playerOrder;
    }

    public void setPlayerOrder(ArrayList<Player> playerOrder) {
        this.playerOrder = playerOrder;
    }

    public ArrayList<Card> getChosenCards() {
        return chosenCards;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
    }

    public DeckManager getDm() {
        return dm;
    }

    public boolean isGameType() {
        return gameType;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public Game getGame() {
        return game;
    }

    public int getID() {

        return ID;
    }

    public ArrayList<Player> getPlayers() {

        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public HashMap<Player, Socket> getClientSocketsMap() {
        return clientSocketsMap;
    }

    // End of Getters, Setters, Constructor

    // adds a player to the lobby, if it fills up the game starts
    public synchronized void AddPlayer(String ID){
        Player newPlayer = new Player(players.size(), ID);
        newPlayer.setGameID(this.ID);
        this.players.add(newPlayer);

        if (players.size() == this.numPlayers) {
            this.setReady(true);
        }
    }

    public void addClientSocket(Socket socket, String ID){
        for(Player player : this.players){
            System.out.println("Player: " + player.getID_player());
            if(ID.equals(player.getID_player())){
                this.clientSocketsMap.put(player, socket);
                System.out.println("Player's socket " + ID + " added.");
                break;
            }
        }
        if (players.size() == this.numPlayers) {
            this.setReady(true);
            synchronized (preMageLock){
                closeConnectionThread = new CloseConnectionThread(clientSocketsMap);
                preMageLock.notifyAll();
            }
        }
    }

    //creates the game related to this lobby
    public void createGame(int NumPlayers, int ID, boolean GameType) {
        System.out.println("All set! Starting Game...");
        System.out.println("");
        this.game = new Game(GameType, ID, NumPlayers);
        this.game.setComplexLobby(this);
        for(Player temp : this.players){
            temp.setPlayerGame(this.game);
        }
        this.game.generateBoard();
    }

    //adds the Card to the Array of chosen cards
    //in a turn this method is called a (int)numPlayers times
    public boolean checkIfPlayable(Card chosen) {

        if(chosen == null ){ //removed "|| this.activePlayer.getDeck().getCards().get(chosen.getInfluence() - 1).isUsed()"
            return false;
        }
        //necessary because, in the new round, a Player can play the card played by the last player at the previous round


        if (this.chosenCards.size() == this.numPlayers && clientSocketsMap.isEmpty()){
            this.chosenCards.clear(); //clear the array if already full
        }

        for (Card temp : this.chosenCards){

            if (temp.getName().equals(chosen.getName())) {
                System.out.println("ERROR: You can't play this card in this round because someone has already played that");
                return false;
            }
        }

        if(roundCounter >= 9){
            return true;
        }

        //chosenCards is a private attribute of game, it has the same size as numOfPlayers, at the end of a round becomes empty
        this.chosenCards.add(chosen);
        return true;
    }

    //turn manager
    public void modifyPlayerTurn(){

        //corner case
        roundCounter++;
        if((this.numPlayers==3 && roundCounter>=7 && cornerCase) || (this.numPlayers==4 && roundCounter>=6 && cornerCase)){
            setActivePlayer(this.players.get(0));
            System.out.println("CORNER CASE: Player list is the same of the previous round! ");
            for(int i = 0; i<this.numPlayers; i++)
                System.out.println(this.players.get(i).getID_player());
            System.out.println("");
            this.cornerCase=false;
            return;
        }


        System.out.println("Player list in the previous round: ");
        for(int i = 0; i<this.numPlayers; i++){
            System.out.println(this.players.get(i).getID_player());
        }


        if(this.chosenCards.size()==this.numPlayers){

            HashMap<Card, Player> h = new HashMap<Card, Player>();
            HashMap<Card, Player> k = new HashMap<>();

            for(int i = 0; i<this.numPlayers; i++){
                h.put(this.chosenCards.get(i), this.getPlayers().get(i));
            }

            Collections.sort(this.chosenCards,new OrderComparator());



            ArrayList<Player> tempList = new ArrayList<>();
            for(int i= 0; i<this.numPlayers; i++) {

                Player temp = h.get(this.chosenCards.get(i));
                tempList.add(temp);
            }

            for(int i= 0; i<this.numPlayers; i++){
                k.put(this.chosenCards.get(i), tempList.get(i));
            }


            h.clear();

            for(int i = 0; i<this.numPlayers; i++){
                h.put(this.chosenCards.get(i), this.getPlayers().get(i));
            }

            this.setPlayerOrder(tempList);


            this.setPlayers(tempList);

            System.out.println("");
            System.out.println("Player list in the next round: ");

            for(int i= 0; i<this.numPlayers; i++){
                System.out.println(this.getPlayerOrder().get(i).getID_player());
            }

            System.out.println("");

        }
        else{
            System.out.println("ERROR: not all the players have played their Assistant card! \n");
        }

        setActivePlayer(this.players.get(0));
    }

    public void changeActivePlayer(){

        int index = 0;
        for (int i = 0; i<this.numPlayers; i++ ){
            if (this.playerOrder.get(i)==this.getActivePlayer())
                index = i;
        }

        if((index+1)<this.numPlayers) {
            setActivePlayer(this.playerOrder.get(index + 1));
        }

    }

    // A player (IDPlayer) from a lobby (ID) requests a deck with a specified mage (mage). if free, it sets player's deck,
    // if busy, it returns false
    public boolean deckRequest(Mage mage, String IDplayer){
        //looks for lobby (ID)
        ComplexLobby room = this;

        System.out.println("searching for mage " + mage);
        AssistantDeck d = room.getDm().generateDeck(mage);

        // if mage is not avaible returns false
        if (d == null){
            System.out.println("Mage Already Chosen, here are remaining Mages");
            room.getDm().getAssistantDecks().stream().filter(AssistantDeck::isFree).map(AssistantDeck::getMage).forEach(System.out::println);
            System.out.println("");
            return false;
        }
        System.out.println("found deck with mage " + d.getMage());

        // if mage is avaible it returns true and sets the deck
        Player p0 = null;
        for (Player p : room.getPlayers()){
            if (p.getID_player().equals(IDplayer)){
                p0 = p;
            }
        }
        System.out.println("Added deck " + d.getMage() + " to " + IDplayer);
        System.out.println("Here are remaining Mages");
        room.getDm().getAssistantDecks().stream().filter(AssistantDeck::isFree).map(AssistantDeck::getMage).forEach(System.out::println);
        System.out.println("");
        p0.setDeck(d);

        int usages = 0;
        for(int i = 0; i < room.getDm().getAssistantDecks().size(); i++){
            if(!room.getDm().getAssistantDecks().get(i).isFree()){
                usages++;
            }
        }

        if (usages == room.getNumPlayers()){
            room.createGame(room.getNumPlayers(), room.getID(), room.isGameType());
        }
        return true;
    }

    public boolean deckRequest(Mage mage, String IDplayer, Socket clientSocket){
        //looks for lobby (ID)
        ComplexLobby room = this;

        //System.out.println("searching for mage " + mage);
        AssistantDeck d = room.getDm().generateDeck(mage);

        // if mage is not avaible returns false
        if (d == null){
            /*
            try{
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(clientSocket.getOutputStream());
                PrintWriter output = new PrintWriter(new BufferedWriter(outputStreamWriter), true);
                String outputString = "Mage already chosen, here are remaining Mages";
                output.println(outputString);
                for(int i = 0; i < room.getDm().getAssistantDecks().size(); i++){
                    if( room.getDm().getAssistantDecks().get(i).isFree()){
                        outputString = room.getDm().getAssistantDecks().get(i).getMage().toString();
                        output.println(outputString);
                    }
                }

                Player courrentPlayer = null;
                for (Player p : room.getPlayers()){
                    if (p.getID_player().equals(IDplayer)){
                        courrentPlayer = p;
                    }
                }
                selectMage(courrentPlayer);

            } catch (IOException e){
                e.printStackTrace();
            }
             */
            return false;
        }
        System.out.println("found deck with mage " + d.getMage());

        // if mage is avaible it returns true and sets the deck
        Player p0 = null;
        for (Player p : room.getPlayers()){
            if (p.getID_player().equals(IDplayer)){
                p0 = p;
            }
        }
        System.out.println("Added deck " + d.getMage() + " to " + IDplayer);

        /*
        try{
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(clientSocket.getOutputStream());
            PrintWriter output = new PrintWriter(new BufferedWriter(outputStreamWriter), true);
            String outputString = "Here are remaining Mages";
            output.println(outputString);
            for(int i = 0; i < room.getDm().getAssistantDecks().size(); i++){
                if( room.getDm().getAssistantDecks().get(i).isFree()){
                    outputString = room.getDm().getAssistantDecks().get(i).getMage().toString();
                    output.println(outputString);
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
         */

        p0.setDeck(d);

        int usages = 0;
        for(int i = 0; i < room.getDm().getAssistantDecks().size(); i++){
            if(!room.getDm().getAssistantDecks().get(i).isFree()){
                usages++;
            }
        }

        if (usages == room.getNumPlayers()){
            room.createGame(room.getNumPlayers(), room.getID(), room.isGameType());
            game.startGameWithRandomPlayer();
            synchronized (preCardLock){
                preCardLock.notifyAll();
            }
        }
        return true;
    }

    public synchronized boolean selectMage(Socket clientSocket, Player player, JSONtoObject receiveMessage, ObjectToJSON sendMessage){
        int i = 0;
        ArrayList<AssistantDeck> assistantDecks = getDm().getAssistantDecks();

        for(AssistantDeck assistantDeck : assistantDecks){
            if(assistantDeck.isFree()){
                i++;
            }
        }
        int[] aviableMages = new int[i];
        i = 0;
        for(AssistantDeck assistantDeck : assistantDecks){
            if(assistantDeck.isFree()){
                switch (assistantDeck.getMage()){
                    case MAGE1:
                        aviableMages[i] = 1;
                        break;
                    case MAGE2:
                        aviableMages[i] = 2;
                        break;
                    case MAGE3:
                        aviableMages[i] = 3;
                        break;
                    case MAGE4:
                        aviableMages[i] = 4;
                }
                i++;
            }
        }

        sendMessage.sendMageMessage(new MageMessage(aviableMages));
        MageMessage mageMessage = null;

        try{
            mageMessage = (MageMessage) receiveMessage.receiveMessage();
        }catch (ClassCastException e){
            System.out.println("Connection error.\r");
        }
        if(mageMessage == null){
            return false;
        }

        int choice = mageMessage.getMageSelection();
        if(choice == 1){
            if(deckRequest(Mage.MAGE1, player.getID_player(), clientSocket)){
                sendMessage.sendNoError();
                //this.notifyAll();
                return true;
            } else {
                sendMessage.sendMageError();
                return false;
            }
        } else if(choice == 2){
            if(deckRequest(Mage.MAGE2, player.getID_player(), clientSocket)){
                sendMessage.sendNoError();
                //this.notifyAll();
                return true;
            } else {
                sendMessage.sendMageError();
                return false;
            }
        } else if(choice == 3){
            if(deckRequest(Mage.MAGE3, player.getID_player(), clientSocket)){
                sendMessage.sendNoError();
                //this.notifyAll();
                return true;
            } else {
                sendMessage.sendMageError();
                return false;
            }
        } else if(choice == 4){
            if(deckRequest(Mage.MAGE4, player.getID_player(), clientSocket)){
                sendMessage.sendNoError();
                //this.notifyAll();
                return true;
            } else {
                sendMessage.sendMageError();
                return false;
            }
        }
        return false;
    }

    public synchronized boolean playCard(ObjectToJSON sendMessage,  JSONtoObject receiveMessage){

        if (this.chosenCards.size() == this.numPlayers){
            this.chosenCards.clear(); //clear the array if already full
        }

        AssistantDeck assistantDeck = this.activePlayer.getDeck();
        // ArrayList<Card> deck = (ArrayList<Card>) assistantDeck.getCards().stream().filter(card -> !card.isUsed()).collect(Collectors.toList());

        sendMessage.sendAssistantCardsMessage(new AssistantCardsMessage(assistantDeck.getCards(), chosenCards));

        AssistantCardsMessage cardMessage = null;
        try{
            cardMessage = (AssistantCardsMessage) receiveMessage.receiveMessage();
        }catch (ClassCastException e){
            System.out.println("Connection error.\r");
        }
        if(cardMessage == null){
            return false;
        }
        System.out.println(cardMessage.getPlayedCard());
        if(!checkIfPlayable(assistantDeck.getCards().get(cardMessage.getPlayedCard() - 1))){
            sendMessage.sendCardError();
            return false;
        } else {
            if(activePlayer.playCard(cardMessage.getPlayedCard()) == null){
                sendMessage.sendCardError();
                return false;
            }
            sendMessage.sendNoError();

            if(chosenCards.size() == numPlayers){
                modifyPlayerTurn();
                //changeActivePlayer();
                /*
                synchronized (afterCardLock){
                    afterCardLock.notifyAll();
                }
                 */
            } else {
                changeActivePlayer();
            }

            return true;
        }
    }


    public synchronized void moveStudents(ArrayList<MovedStudent> orderedStudents){
        SchoolBoard schoolBoard = activePlayer.getSchoolBoard();
        for(MovedStudent movedStudent : orderedStudents){
            if(movedStudent.getPose() == 0){
                Student student = schoolBoard.getEntrance().getStudents().get(movedStudent.getIndex());
                schoolBoard.moveStudent(student);
            } else {
                Student student = schoolBoard.getEntrance().getStudents().get(movedStudent.getIndex());
                IslandCard islandCard = game.getGameComponents().getArchipelago().get(movedStudent.getIsland());
                schoolBoard.moveStudent(student,islandCard);
            }
        }
        game.colorDominance();
    }

    public synchronized void moveMotherNature(int moves){
        game.moveMotherNature(moves, game.getGameComponents().getMotherNature(), game.getGameComponents().getArchipelago());
        game.islandDominance();
        game.mergeIsland();
    }

    public void selectCloudCard(int cloudCard){
        CloudCard cloudCardChosen = game.getGameComponents().getCloudCards().get(cloudCard);
        activePlayer.getSchoolBoard().addStudetsToEntrance(cloudCardChosen.drawStudents());
        if(activePlayer.equals(playerOrder.get(numPlayers - 1))){
            System.out.println("it should be " + playerOrder.get(0).getID_player() + " turn.");
            /*
            synchronized (preMageLock){
                preMageLock.notifyAll();
            }
             */
        }
    }

    public synchronized ModelMessage sendModel(){
        GameComponents gameComponents = getGame().getGameComponents();
        //if game type is pro
        ModelMessage modelMessage = null;
        if(isGameType()){
            modelMessage = new ModelMessage(gameComponents.getArchipelago(), gameComponents.getCloudCards(),
                    activePlayer.getSchoolBoard(), gameComponents.getSpecialDeck().getCards(),
                    activePlayer.getCoinOwned());
        } else {
            modelMessage = new ModelMessage(gameComponents.getArchipelago(), gameComponents.getCloudCards(),
                    activePlayer.getSchoolBoard());
        }

        return modelMessage;
    }

    public synchronized void endGame(Player winner){
        for(Player player : players){

            Socket clientSocket = clientSocketsMap.get(player);

            if (clientSocket != null) {
                ObjectToJSON sendMessage = new ObjectToJSON(clientSocket);
                //send win message

                sendMessage.sendWinMessage(new WinMessage(winner.getID_player()));

                try {
                    clientSocket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public synchronized void closeConnection(){
        closeConnectionThread.start();
    }

    public Player getPlayerByID(String ID){
        for(Player player : players){
            if(ID.equals(player.getID_player())){
                return player;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Lobby " + ID;
    }

}


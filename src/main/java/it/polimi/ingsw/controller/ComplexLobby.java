package it.polimi.ingsw.controller;

import it.polimi.ingsw.communication.common.*;
import it.polimi.ingsw.communication.common.messages.*;
import it.polimi.ingsw.model.Mage;
import it.polimi.ingsw.model.MovedStudent;
import it.polimi.ingsw.model.board.*;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.cards.AssistantDeck;
import it.polimi.ingsw.model.cards.Card;
import it.polimi.ingsw.model.cards.CharacterCard;
import it.polimi.ingsw.model.cards.characters.*;
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
    private ArrayList<Player> playersInitialList;
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
        this.playersInitialList = Players;
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

    public ArrayList<Player> getPlayersInitialList() {return playersInitialList; }

    public ArrayList<Player> getPlayerOrder() {
        return playerOrder;
    }

    public void setPlayerOrder(ArrayList<Player> playerOrder) {
        this.playerOrder = playerOrder;
    }

    public ArrayList<Card> getChosenCards() {
        return chosenCards;
    }

    public synchronized Player getActivePlayer() {
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

    public  int getNumPlayers() {
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

    // adds a player to the lobby, if it fills up the game starts
    public synchronized void AddPlayer(String ID){
        Player newPlayer = new Player(players.size(), ID);
        newPlayer.setGameID(this.ID);
        this.players.add(newPlayer);

        if (players.size() == this.numPlayers) {
            this.setReady(true);
        }
    }

    public synchronized void addClientSocket(Socket socket, String ID){
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
            closeConnectionThread = new CloseConnectionThread(clientSocketsMap);
        }
    }

    //creates the game related to this lobby
    public synchronized void createGame(int NumPlayers, int ID, boolean GameType) {
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
    public synchronized boolean checkIfPlayable(Card chosen) {

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
    public synchronized void modifyPlayerTurn(){

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
    public synchronized boolean deckRequest(Mage mage, String IDplayer){
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

    public synchronized boolean deckRequest(Mage mage, String IDplayer, Socket clientSocket){
        //looks for lobby (ID)
        ComplexLobby room = this;

        //System.out.println("searching for mage " + mage);
        AssistantDeck d = room.getDm().generateDeck(mage);

        // if mage is not avaible returns false
        if (d == null){
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

        p0.setDeck(d);

        int usages = 0;
        for(int i = 0; i < room.getDm().getAssistantDecks().size(); i++){
            if(!room.getDm().getAssistantDecks().get(i).isFree()){
                usages++;
            }
        }
        if(!activePlayer.equals(playerOrder.get(numPlayers - 1))){
            changeActivePlayer();
        }

        if (usages == room.getNumPlayers()){
            room.createGame(room.getNumPlayers(), room.getID(), room.isGameType());
            game.startGameWithRandomPlayer();
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
            return false;
        }

        int choice = mageMessage.getMageSelection();
        if(choice == 1){
            if(deckRequest(Mage.MAGE1, player.getID_player(), clientSocket)){
                sendMessage.sendNoError();
                return true;
            } else {
                sendMessage.sendMageError();
                return false;
            }
        } else if(choice == 2){
            if(deckRequest(Mage.MAGE2, player.getID_player(), clientSocket)){
                sendMessage.sendNoError();
                return true;
            } else {
                sendMessage.sendMageError();
                return false;
            }
        } else if(choice == 3){
            if(deckRequest(Mage.MAGE3, player.getID_player(), clientSocket)){
                sendMessage.sendNoError();
                return true;
            } else {
                sendMessage.sendMageError();
                return false;
            }
        } else if(choice == 4){
            if(deckRequest(Mage.MAGE4, player.getID_player(), clientSocket)){
                sendMessage.sendNoError();
                return true;
            } else {
                sendMessage.sendMageError();
                return false;
            }
        }
        return false;
    }

    public synchronized boolean playCard(ObjectToJSON sendMessage,  JSONtoObject receiveMessage) {

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
            return false;
        }

        System.out.println(cardMessage.getPlayedCard());
        if(!checkIfPlayable(assistantDeck.getCards().get(cardMessage.getPlayedCard() - 1))){
            sendMessage.sendCardError();
            return false;
        } else {
            if(activePlayer.playCard(cardMessage.getPlayedCard()) == null){
                chosenCards.remove(chosenCards.size() - 1);
                sendMessage.sendCardError();
                return false;
            }
            sendMessage.sendNoError();

            System.out.println("choosen card: " + chosenCards.size());
            if(chosenCards.size() == numPlayers){
                modifyPlayerTurn();
            } else {
                System.out.println("changing player");
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
        if(gameType) {
            game.coinGiver();
        }
    }

    public synchronized void moveMotherNature(int moves){
        game.moveMotherNature(moves, game.getGameComponents().getMotherNature(), game.getGameComponents().getArchipelago());
        game.islandDominance();
        game.mergeIsland();
    }

    public synchronized void selectCloudCard(int cloudCard){
        CloudCard cloudCardChosen = game.getGameComponents().getCloudCards().get(cloudCard);
        activePlayer.getSchoolBoard().addStudetsToEntrance(cloudCardChosen.drawStudents());
    }

    public synchronized  void playCharacter(UseCharacterMessage characterMessage){
        int code = characterMessage.getId();
        int choice = characterMessage.getChoice();
        ArrayList<CharacterCard> characterCards = game.getGameComponents().getSpecialDeck().getCards();
        ArrayList<IslandCard> archipelago =  game.getGameComponents().getArchipelago();
        System.out.println("Character's code: " + code);
        System.out.println("Character's choice: " + choice);
        switch (code){
            case 1:
                Character1 card = (Character1) characterCards.get(choice);
                card.effect(activePlayer,archipelago.get(characterMessage.getIslandCharacter()), characterMessage.getStudentCharacter());
                break;
            case 2:
                Character2 card2 = (Character2) characterCards.get(choice);
                card2.effect(activePlayer);
                break;
            case 3:
                Character3 card3 = (Character3) characterCards.get(choice);
                card3.effect(activePlayer,  archipelago.get(characterMessage.getIslandCharacter()));
                break;
            case 4:
                Character4 card4 = (Character4) characterCards.get(choice);
                card4.effect(activePlayer);
                break;
            case 5:
                Character5 card5 = (Character5) characterCards.get(choice);
                card5.effect(activePlayer, archipelago.get(characterMessage.getIslandCharacter()));
                break;
            case 6:
                Character6 card6 = (Character6) characterCards.get(choice);
                card6.effect(activePlayer);
                break;
            case 7:
                Character7 card7 = (Character7) characterCards.get(choice);
                card7.effect(activePlayer, characterMessage.getStudentsFromEntranceCharacter(), characterMessage.getStudentsOnCardCharacter());
                break;
            case 8:
                Character8 card8 = (Character8) characterCards.get(choice);
                card8.effect(activePlayer);
                break;
            case 9:
                Character9 card9 = (Character9) characterCards.get(choice);
                card9.effect(activePlayer, characterMessage.getColorStudentCharacter());
                break;
            case 10:
                Character10 card10 = (Character10) characterCards.get(choice);
                ArrayList<Student> students = new ArrayList<>();
                for(int i = 0; i < 2; i++){
                    students.add(activePlayer.getSchoolBoard().getStudents().get(characterMessage.getStudentsFromDinignRoomCharacter()[i]));
                }
                card10.effect(activePlayer, students, characterMessage.getStudentsFromEntranceCharacter());
                break;
            case 11:
                Character11 card11 = (Character11) characterCards.get(choice);
                card11.effect(activePlayer, characterMessage.getStudentCharacter());
                break;
            case 12:
                Character12 card12 = (Character12) characterCards.get(choice);
                card12.effect(activePlayer, characterMessage.getColorStudentCharacter());
                break;
        }
    }

    public synchronized ModelMessage sendModel(String username){
        GameComponents gameComponents = getGame().getGameComponents();
        //if game type is pro
        ModelMessage modelMessage = null;
        if(isGameType()){
            modelMessage = new ModelMessage(gameComponents.getArchipelago(), gameComponents.getCloudCards(),
                    this.getPlayerByID(username).getSchoolBoard(), gameComponents.getSpecialDeck().getCards(),
                    this.getPlayerByID(username).getCoinOwned());
        } else {
            modelMessage = new ModelMessage(gameComponents.getArchipelago(), gameComponents.getCloudCards(),
                    this.getPlayerByID(username).getSchoolBoard());
        }

        return modelMessage;
    }

    public synchronized void endGame(Player winner){
        for(Socket clientSocket : clientSocketsMap.values()){

            if (clientSocket != null) {
                ObjectToJSON sendMessage = new ObjectToJSON(clientSocket);
                //send win message

                sendMessage.sendWinMessage(new WinMessage(winner.getID_player()));

                playerOrder.clear();
                //activePlayer = null;

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
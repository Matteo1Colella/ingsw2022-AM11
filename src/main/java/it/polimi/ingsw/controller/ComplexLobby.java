package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.board.CoinReserve;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.cards.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ComplexLobby {
    private it.polimi.ingsw.model.Game game;
    private boolean gameType;
    private int numPlayers;
    private int ID;
    private Player activePlayer;
    private ArrayList<Player> players;
    private boolean ready;
    private CoinReserve coinReserve;
    private DeckManager dm;
    private ArrayList<Card> chosenCards;
    private ArrayList<Player> playerOrder;

    // Start of Getters, Setters, Constructor
    public ComplexLobby(int numplayers, boolean gametype, int ID, ArrayList<Player> Players) {
        this.chosenCards = new ArrayList<>();
        this.gameType = gametype;
        this.numPlayers = numplayers;
        this.ID = ID;
        this.players = Players;
        this.dm = new DeckManager();
        this.playerOrder = new ArrayList<>();
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

    public void setGame(Game game) {
        this.game = game;
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
    // End of Getters, Setters, Constructor

    // adds a player to the lobby, if it fills up the game starts
    public void AddPlayer(String ID){
        Player New = new Player(players.size(), ID);
        New.setGameID(this.ID);
        this.players.add(New);
        if (players.size() == this.numPlayers) {
            this.setReady(true);
        };
    }
    //creates the game related to this lobby
    public void CreateGame(int NumPlayers, int ID, boolean GameType) {
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


        //necessary because, in the new round, a Player can play the card played by the last player at the previous round
        if (this.chosenCards.size() == this.numPlayers)
            this.chosenCards.clear(); //clear the array if already full

        for (Card temp : this.chosenCards){
            if(chosen == null){
                return false;
            }
            if (temp.getName().equals(chosen.getName())) {
                System.out.println("ERROR: You can't play this card in this round because someone has already played that");
                return false;
            }
        }

        //chosenCards is a private attribute of game, it has the same size as numOfPlayers, at the end of a round becomes empty
        this.chosenCards.add(chosen);
        return true;
    }

    //turn manager
    public void modifyPlayerTurn(){ //gestire caso ultimo turno

        System.out.println("Player list in the previous round: ");
        for(int i = 0; i<this.numPlayers; i++)
            System.out.println(this.players.get(i).getID_player());



        if(this.chosenCards.size()==this.numPlayers){

            HashMap<Card, Player> h = new HashMap<Card, Player>();
            HashMap<Card, Player> k = new HashMap<>();

            for(int i = 0; i<this.numPlayers; i++)
                h.put(this.chosenCards.get(i), this.getPlayers().get(i));

            Collections.sort(this.chosenCards,new OrderComparator());



            ArrayList<Player> tempList = new ArrayList<>();
            for(int i= 0; i<this.numPlayers; i++) {

                Player temp = h.get(this.chosenCards.get(i));
                tempList.add(temp);
            }

            for(int i= 0; i<this.numPlayers; i++)
                k.put(this.chosenCards.get(i), tempList.get(i));

            h.clear();

            for(int i = 0; i<this.numPlayers; i++)
                h.put(this.chosenCards.get(i), this.getPlayers().get(i));

            this.setPlayerOrder(tempList);


            this.setPlayers(tempList);

            System.out.println("");
            System.out.println("Player list in the next round: ");

            for(int i= 0; i<this.numPlayers; i++)
                System.out.println(this.getPlayerOrder().get(i).getID_player());


            }
        else
            System.out.println("ERROR: not all the players have played their Assistant card! \n");

        setActivePlayer(this.players.get(0));
    }

    public void changeActivePlayer(){

        int index = 0;
        for (int i = 0; i<this.numPlayers; i++ ){
            if (this.players.get(i)==this.getActivePlayer())
                index = i;
        }

        if((index+1)<this.numPlayers)
            setActivePlayer(this.players.get(index+1));
       }

}

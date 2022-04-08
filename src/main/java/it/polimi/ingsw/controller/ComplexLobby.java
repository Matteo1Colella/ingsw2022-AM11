package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.board.CoinReserve;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.cards.Card;

import java.util.ArrayList;
import java.util.Collection;

public class ComplexLobby {
    private it.polimi.ingsw.model.Game Game;
    private boolean GameType;
    private int NumPlayers;
    private int ID;
    private Player activePlayer;
    private ArrayList<Player> players;
    private boolean ready;
    private CoinReserve coinReserve;
    private DeckManager DM;
    private ArrayList<Card> chosenCards;

    // Start of Getters, Setters, Constructor
    public ComplexLobby(int numplayers, boolean gametype, int ID, ArrayList<Player> Players) {
        this.chosenCards = new ArrayList<>();
        this.GameType = gametype;
        this.NumPlayers = numplayers;
        this.ID = ID;
        this.players = Players;
        this.DM = new DeckManager();
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

    public DeckManager getDM() {
        return DM;
    }

    public void setDM(DeckManager DM) {
        this.DM = DM;
    }

    public boolean isGameType() {
        return GameType;
    }

    public void setGameType(boolean gameType) {
        GameType = gameType;
    }

    public int getNumPlayers() {
        return NumPlayers;
    }

    public void setNumPlayers(int numPlayers) {
        NumPlayers = numPlayers;
    }

    public Game getGame() {
        return Game;
    }

    public void setGame(Game game) {
        Game = game;
    }

    public int getID() {

        return ID;
    }

    public void setID(int ID) {

        this.ID = ID;
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
        if (players.size() == this.NumPlayers) {
            this.setReady(true);
        };
    }
    //creates the game related to this lobby
    public void CreateGame(int NumPlayers, int ID, boolean GameType) {
        System.out.println("All set! Starting Game...");
        System.out.println("");
        this.Game = new Game(GameType, ID, NumPlayers);
        this.Game.setComplexLobby(this);
        for(Player temp : this.players){
            temp.setPlayerGame(this.Game);
        }
    }

    //adds the Card to the Array of chosen cards
    public void addChosenCard(Card chosen) {

        //necessary because, in the new round, a Player can play the card played by the last player at the previous round
        if (this.chosenCards.size() == this.NumPlayers)
            this.chosenCards.clear(); //clear the array if already full

        for (Card temp : this.chosenCards)
            if (temp.getName().equals(chosen.getName())) {
                System.out.println("ERROR: You can't play this card in this round because someone has already played that");
                return;
            }
        //chosenCards is a private attribute of game, it has the same size as numOfPlayers, at the end of a round becomes empty
        this.chosenCards.add(chosen);
    }
}

package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Collection;

public class PlayersManager {
private Game Game;
private boolean GameType;
private int NumPlayers;
private int ID;
private ArrayList<Player> Players;
private boolean ready;
    // Start of Getters, Setters, Constructor
    public PlayersManager(int numplayers, boolean gametype, int ID, ArrayList<Player> Players) {

        this.GameType = gametype;
        this.NumPlayers = numplayers;
        this.ID = ID;
        this.Players = Players;

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

    public Game getGame(int Numplayers, boolean Gametype) {
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

    public Collection<Player> getPlayers() {

        return Players;
    }

    public void setPlayers(ArrayList<Player> players) {
        Players = players;
    }
    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }
    // End of Getters, Setters, Constructor

    // checks no duplicates players inside the lobby
    public boolean IsIn(String ID){

        for (Player Temp : Players){

            if (Temp.getID_player().toUpperCase().equals(ID.toUpperCase())) {
                return true;
            }
        }

        return false;
    }

    // adds a player to the lobby, if it fills up the game starts
    public void AddPlayer(String ID){
        Player New = new Player(Players.size(), ID);
        this.Players.add(New);
        if (Players.size() == this.NumPlayers) {
            CreateGame(this.NumPlayers, this.ID, this.GameType);
            this.setReady(true);

        };
    }
    //creates the game related to this lobby
    public void CreateGame(int NumPlayers, int ID, boolean GameType) {
        this.Game = new Game(GameType, ID);
    }


}

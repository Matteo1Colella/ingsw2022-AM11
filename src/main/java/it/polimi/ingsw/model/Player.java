package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Set;

public class Player {

    private int PlayerNum;
    private String ID_player;
    private AssistantDeck Deck;
    private boolean Status;
    private SchoolBoard schoolBoard;
    private ArrayList<Coin> Coins;
    private ArrayList<Tower> Towers;
    private Game PlayerGame;

    // Start of Getters, Setters, Constructor

    public Player(int playerNum, String ID_player) {
        PlayerNum = playerNum;
        this.ID_player = ID_player;
    }

    public int getPlayerNum() {
        return PlayerNum;
    }

    public void setPlayerNum(int playerNum) {
        PlayerNum = playerNum;
    }

    public String getID_player() {
        return ID_player;
    }

    public void setID_player(String ID_player) {
        this.ID_player = ID_player;
    }

    public AssistantDeck getDeck() {
        return Deck;
    }

    public void setDeck(AssistantDeck deck) {
        Deck = deck;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public SchoolBoard getSchoolBoard() {
        return schoolBoard;
    }

    public void setSchoolBoard(SchoolBoard schoolBoard) {
        this.schoolBoard = schoolBoard;
    }

    public ArrayList<Coin> getCoins() {
        return Coins;
    }

    public void setCoins(ArrayList<Coin> coins) {
        Coins = coins;
    }

    public ArrayList<Tower> getTowers() {
        return Towers;
    }

    public void setTowers(ArrayList<Tower> towers) {
        Towers = towers;
    }

    public Game getPlayerGame() {
        return PlayerGame;
    }

    public void setPlayerGame(Game playerGame) {
        PlayerGame = playerGame;
    }
    // End of Getters, Setters, Constructor


    //builder
    public void Player() {
    }
    //choose card from deck
    public Card playCard(){
        return null;
    }

    //move a piece
    public void movePiece(){}

    //draw assistants from the bag
    public Set<Piece> Draw(){
        return null;
    }

    //merge two or more islands
    public void aggregateIslands(){}

    //get the number of coin owned
    public int getCoinOwned(){
        return 0;
    }

    //use a coin on a character
    public void useCoin(){}

}

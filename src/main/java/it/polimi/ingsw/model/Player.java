package it.polimi.ingsw.model;

import it.polimi.ingsw.model.cards.AssistantDeck;
import it.polimi.ingsw.model.cards.Card;
import it.polimi.ingsw.model.pieces.Piece;
import it.polimi.ingsw.model.pieces.Tower;
import it.polimi.ingsw.model.board.Coin;
import it.polimi.ingsw.model.board.SchoolBoard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class Player {

    private int playerNum;
    private String ID_player;
    private AssistantDeck deck;
    private boolean status;
    private SchoolBoard schoolBoard;
    private int influencePoints;
    private Collection<Coin> coins;
    private Game playerGame;
    private int gameID;
    private int MotherNatureMoves;

    // Start of Getters, Setters, Constructor

    public Player(int playerNum, String ID_player) {
        coins = new ArrayList<>();
        playerNum = playerNum;
        this.ID_player = ID_player;
        influencePoints = 0;
    }

    public int getInfluencePoints() {
        return influencePoints;
    }

    public void setInfluencePoints(int influencePoints) {
        this.influencePoints = influencePoints;
    }

    public int getMotherNatureMoves() {
        return MotherNatureMoves;
    }

    public void setMotherNatureMoves(int motherNatureMoves) {
        MotherNatureMoves = motherNatureMoves;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public void setPlayerNum(int playerNum) {
        this.playerNum = playerNum;
    }

    public String getID_player() {
        return ID_player;
    }

    public void setID_player(String ID_player) {
        this.ID_player = ID_player;
    }

    public AssistantDeck getDeck() {
        return deck;
    }

    public void setDeck(AssistantDeck deck) {
        this.deck = deck;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public SchoolBoard getSchoolBoard() {
        return schoolBoard;
    }

    public void setSchoolBoard(SchoolBoard schoolBoard) {
        this.schoolBoard = schoolBoard;
    }

    public Collection<Coin> getCoins() {
        return coins;
    }

    //this method add a coin to the owned ones
    public void addCoins(Coin coin) {
        this.coins.add(coin);
    }

    public Game getPlayerGame() {
        return playerGame;
    }

    public void setPlayerGame(Game playerGame) {
        this.playerGame = playerGame;
    }
    // End of Getters, Setters, Constructor

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

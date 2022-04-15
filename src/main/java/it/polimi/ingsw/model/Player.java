package it.polimi.ingsw.model;

import it.polimi.ingsw.model.board.*;
import it.polimi.ingsw.model.cards.AssistantDeck;
import it.polimi.ingsw.model.cards.Card;
import it.polimi.ingsw.model.pieces.Student;

import java.util.ArrayList;
import java.util.Collection;

public class Player {

    private int playerNum;
    private String ID_player;
    private AssistantDeck deck;
    private boolean status;
    private SchoolBoard schoolBoard;
    private int influencePoints;
    private ArrayList<Coin> coins;
    private Game playerGame;
    private int gameID;
    private int MotherNatureMoves;

    // Start of Getters, Setters, Constructor

    public Player(int playerNum, String ID_player) {
        this.coins = new ArrayList<>();
        this.playerNum = playerNum;
        this.ID_player = ID_player;
        this.influencePoints = 0;
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

    //choose card from deck and play it
    public Card playCard(int i) {
        if (deck.leftCard() > 0) {
            return this.deck.chooseCard(i);
        }
         else {
            System.out.println("All the cards has been chosen");
            return null;
        }


    }

    //move a piece (student) in the dining room of his School board
    public void movePiece(Student student){
        schoolBoard.moveStudent(student);
    }

    //move a piece (student) in the specified island
    public void movePiece(Student student, IslandCard islandCard){
        schoolBoard.moveStudent(student, islandCard);
    }


    //draw students from the bag. The number of drawings can be choosed.
    public ArrayList<Student> Draw(Bag bag, int numOfDrawings){
        ArrayList<Student> students = new ArrayList<>();

        for(int i = 0; i < numOfDrawings; i++){
            Student student = bag.draw();
            students.add(student);
        }

        return students;
    }

    //get the number of coin owned
    public int getCoinOwned(){
        return coins.size();
    }

    //use a coin on a character
    public void useCoin(){
        coins.remove(coins.get(coins.size() - 1));
    }

    public void printRemainingCard(){
        this.deck.printCards();
    }

}

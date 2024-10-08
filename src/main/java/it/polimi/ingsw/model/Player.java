package it.polimi.ingsw.model;

import it.polimi.ingsw.model.board.*;
import it.polimi.ingsw.model.cards.AssistantDeck;
import it.polimi.ingsw.model.cards.Card;
import it.polimi.ingsw.model.cards.CharacterCard;
import it.polimi.ingsw.model.pieces.Student;

import java.util.ArrayList;
import java.util.Collection;

public class Player {

    private final int playerNum;
    private final String ID_player;
    private AssistantDeck deck;
    private boolean status;
    private SchoolBoard schoolBoard;
    private int influencePoints;
    private final ArrayList<Coin> coins;
    private Game playerGame;
    private int gameID;
    private int MotherNatureMoves;
    private boolean character8;

    // Start of Getters, Setters, Constructor

    public Player(int playerNum, String ID_player) {;
        this.playerNum = playerNum;
        this.ID_player = ID_player;
        this.influencePoints = 0;
        this.coins = new ArrayList<>();
        character8 = false;
    }

    public boolean isCharacter8() {
        return character8;
    }

    public void setCharacter8(boolean character8) {
        this.character8 = character8;
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

    /**
     * removes the used coins from the wallet of a player
     * @param numcoins
     */
    public void useCoins(int numcoins){
        for(int i = 0; i < numcoins; i++){
            this.getPlayerGame().getGameComponents().getCoins().addCoin(this.coins.get(0));
            this.coins.remove(coins.size() - 1);
        }
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getID_player() {
        return ID_player;
    }

    public AssistantDeck getDeck() {
        return deck;
    }

    public void setDeck(AssistantDeck deck) {
        this.deck = deck;
    }

    public SchoolBoard getSchoolBoard() {
        return schoolBoard;
    }

    public void setSchoolBoard(SchoolBoard schoolBoard) {
        this.schoolBoard = schoolBoard;
    }

    /**
     * this method add a coin to the owned ones
     * @param coin
     */
    public void addCoins(Coin coin) {
        this.coins.add(coin);
    }

    public Game getPlayerGame() {
        return playerGame;
    }

    public void setPlayerGame(Game playerGame) {
        this.playerGame = playerGame;
    }

    /**
     * choose card from deck and play it
     * @param i (choice)
     * @return playedCard
     */
    public Card playCard(int i) {
        if (deck.leftCard() > 0) {
            return this.deck.chooseCard(i );

        }
         else {
            System.out.println("All the cards has been chosen");
            return null;

        }
    }

    /**
     * get the number of coin owned
     * @return wallet
     */
    public int getCoinOwned(){
        return coins.size();
    }

    /**
     * use some coin on a character
     * @param character
     * @param coinReserve
     */
    public void playCharacter(CharacterCard character, CoinReserve coinReserve){
        int numCoin = character.getNecessaryCoin();
        int coinsSize = this.coins.size() - 1;
        if(numCoin <= this.coins.size()){
            for(int i = 0; i < numCoin; i++){
                Coin coin = coins.get(coinsSize - i);
                this.coins.remove(coins.get(coinsSize - i));
                coinReserve.addCoin(coin);
            }
        } else {
            System.out.println("There are not enough coins");
        }
    }

    public void printRemainingCard(){
        this.deck.printCards();
    }

}

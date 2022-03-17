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

    //builder
    public void Player(){}

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

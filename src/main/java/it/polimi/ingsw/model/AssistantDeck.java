package it.polimi.ingsw.model;

import java.util.ArrayList;

public class AssistantDeck {

    //final: the value can't be reinitialized
    private Mage mage;
    private boolean free;
    private int RemainingCards;
    private ArrayList<Card> Cards;

    //builder
    public void AssistantDeck(){}

    //returns the number of the cars remaining
    public int LeftCard(){
        return 0;
    }

    //choose a card from the deck
    public Card chooseCard(){
        return null;
    }
}

package it.polimi.ingsw.model;

import java.util.Collection;

public class AssistantDeck {


    private Mage mage;
    private boolean free;
    private int RemainingCards;
    private Collection<Card> Cards;

    //Constructor
    public AssistantDeck(Mage mage, boolean free, int remainingCards, Collection<Card> cards) {
        this.mage = mage;
        this.free = free;
        RemainingCards = remainingCards;
        Cards = cards;
    }

    //getter and setter methods
    public Mage getMage() {
        return mage;
    }

    public void setMage(Mage mage) {
        this.mage = mage;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public int getRemainingCards() {
        return RemainingCards;
    }

    public void setRemainingCards(int remainingCards) {
        RemainingCards = remainingCards;
    }

    public Collection<Card> getCards() {
        return Cards;
    }

    public void setCards(Collection<Card> cards) {
        Cards = cards;
    }

    //returns the number of the cars remaining
    public int LeftCard(){
        return 0;
    }

    //choose a card from the deck
    public Card chooseCard(){
        return null;
    }
}

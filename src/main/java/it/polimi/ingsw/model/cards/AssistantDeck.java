package it.polimi.ingsw.model.cards;

import it.polimi.ingsw.model.Mage;

import java.util.ArrayList;
import java.util.Collection;

public class AssistantDeck {


    private final Mage mage;
    private boolean free;
    private int remainingCards;
    private final ArrayList<Card> cards;

    //Constructor
    public AssistantDeck(Mage mage, boolean free, int remainingCards, Collection<Card> cards) {

        this.cards = new ArrayList<>();

        this.mage = mage;
        this.free = free;
        this.remainingCards = remainingCards;
        this.cards.addAll(cards);
    }

    //getter and setter methods
    public Mage getMage() {
        return mage;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    //returns the number of the cars remaining
    public int leftCard(){
        return remainingCards;
    }

    //choose a card from the deck
    public Card chooseCard(int i){
        i--;
        if(remainingCards == 0){
            System.out.println("No cards remaining.");
            return null;
        }

        if(cards.get(i).isUsed()){
            System.out.println("You can't choose this card, it has been already choosen.");
            return null;
        }

        Card retCard = cards.get(i);
        cards.get(i).setUsed(true);
        remainingCards--;
        System.out.println("You choose " + retCard);

        if(remainingCards == 0){
            System.out.println("No cards remaining.");
            return null;
        }
        return retCard;
    }

    public void printCards(){
        for (Card card : cards) {
            if (!card.isUsed()) {
                System.out.println(card);
            }
        }
    }
}

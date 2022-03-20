package it.polimi.ingsw.model;

import java.util.ArrayList;

public class CharacterDeck {

    private ArrayList<CharacterCard> Cards;
    private int NumCards = 12;
    // Start of Getters, Setters, Constructor


    public CharacterDeck(ArrayList<CharacterCard> cards, int numCards) {
        Cards = cards;
        NumCards = numCards;
    }

    public ArrayList<CharacterCard> getCards() {
        return Cards;
    }

    public void setCards(ArrayList<CharacterCard> cards) {
        Cards = cards;
    }

    public int getNumCards() {
        return NumCards;
    }

    public void setNumCards(int numCards) {
        NumCards = numCards;
    }

    // End of Getters, Setters, Constructor
    public void Shuffle(){}

}

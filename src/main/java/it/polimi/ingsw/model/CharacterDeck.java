package it.polimi.ingsw.model;

import java.util.ArrayList;

public class CharacterDeck {

    private ArrayList<CharacterCard> cards;
    private final int numCards = 12;
    private Game game;
    // Start of Getters, Setters, Constructor

    public CharacterDeck() {
        int min = 1;
        for (int i = 0; i < 3; i++){
        int random = (int)Math.floor(Math.random()*(numCards-min+1)+min);
        CharacterCard card = new CharacterCard(random, game.getGameStructure().getBag());
        cards.add(card);
        }
    }

    public ArrayList<CharacterCard> getCards() {
        return cards;
    }

    public void setCards(ArrayList<CharacterCard> cards) {
        cards = cards;
    }

    public int getNumCards() {
        return numCards;
    }

    // End of Getters, Setters, Constructor

}

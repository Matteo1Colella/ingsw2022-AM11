package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.cards.AssistantDeck;
import it.polimi.ingsw.model.cards.Card;
import it.polimi.ingsw.model.Mage;

import java.util.ArrayList;
import java.util.Collection;

public class DeckManager {
    private final ArrayList<AssistantDeck> assistantDecks;

    //Creation of deck ready to be chosen
    public DeckManager() {
        Mage[] mages = {Mage.MAGE1, Mage.MAGE2, Mage.MAGE3, Mage.MAGE4};

        this.assistantDecks = new ArrayList<>();

        for(Mage tempMage : mages){
            //create a deck with no card in it and ready to be choosen
            AssistantDeck deck = new AssistantDeck(tempMage,  true, 10, generateCards(tempMage));
            assistantDecks.add(deck);
        }
    }

    public ArrayList<AssistantDeck> getAssistantDecks() {
        return assistantDecks;
    }

    /*
    Players can choose their mage: if the mage is occupied
    the function ask to choose an other mage.
    */
    public AssistantDeck generateDeck(Mage mage){
        AssistantDeck retDeck;

        for(AssistantDeck tempDeck : assistantDecks){
            if(tempDeck.getMage().equals(mage) && !tempDeck.isFree()){
                return null;
            } else if (tempDeck.getMage().equals(mage) && tempDeck.isFree()) {
                retDeck = tempDeck;
                tempDeck.setFree(false);
                retDeck.setFree(false);
                return retDeck;
            }
        }

        return null;
    }

    //Creation of cards
    private Collection<Card> generateCards(Mage mage){
       ArrayList<Card> cards = new ArrayList<>();
       int[] steps = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5};
       String[] names = {"Leopard", "Ostrich", "Kitten", "Eagle", "Fox", "Snake", "Octopus", "Dog", "Elephant", "Turtle"};

       for(int i = 0; i < 10; i++){
           Card card = new Card(names[i], steps[i], i+1, false, mage);
           cards.add(card);
       }
       return cards;
    }
}


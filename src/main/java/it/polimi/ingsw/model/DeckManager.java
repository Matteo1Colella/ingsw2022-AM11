package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Collection;

public class DeckManager {
    private final ArrayList<AssistantDeck> Decks;

    //Creation of deck ready to be chosen
    public DeckManager() {
        Mage[] mages = {Mage.MAGE1, Mage.MAGE2, Mage.MAGE3, Mage.MAGE4};

        this.Decks = new ArrayList<>();

        for(Mage tempMage : mages){
            //create a deck with no card in it and ready to be choosen
            AssistantDeck deck = new AssistantDeck(tempMage,  true, 10, generateCards(tempMage));
            Decks.add(deck);
        }
    }

    /*
    Players can choose their mage: if the mage is occupied
    the function ask to choose an other mage.
    */
    public AssistantDeck generateDeck(Mage mage){
        AssistantDeck retDeck;

        for(AssistantDeck tempDeck : Decks){
            if(tempDeck.getMage().equals(mage) && !tempDeck.isFree()){
                return null;
            } else {
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
       ArrayList<Card> Cards = new ArrayList<>();
       int[] steps = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5};
       String[] names = {"Leopard", "Ostrich", "Kitten", "Eagle", "Fox", "Snake", "Octopus", "Dog", "Elephant", "Turtle"};

       for(int i = 0; i < 10; i++){
           Card card = new Card(names[i], steps[i], i+1, false, mage);
           Cards.add(card);
       }
       return Cards;
    }


}


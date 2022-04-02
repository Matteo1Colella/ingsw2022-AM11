package it.polimi.ingsw;

import it.polimi.ingsw.model.cards.AssistantDeck;
import it.polimi.ingsw.model.cards.Card;
import it.polimi.ingsw.controller.DeckManager;
import it.polimi.ingsw.model.Mage;
import org.junit.Test;

import static org.junit.Assert.*;

public class AssistantDeckTest {
    @Test
    public void chooseCards(){

        DeckManager deckManager = new DeckManager();

        AssistantDeck assistantDeck = deckManager.generateDeck(Mage.MAGE1);

        Card choosenCard = assistantDeck.chooseCard(1);

        assertNotNull(choosenCard);
    }

    @Test
    public void choose10Cards(){

        DeckManager deckManager = new DeckManager();

        AssistantDeck assistantDeck = deckManager.generateDeck(Mage.MAGE1);

        for(int i = 0; i < 10; i++){
            Card choosenCard = assistantDeck.chooseCard(i);
        }

        assertEquals(0, assistantDeck.leftCard());
    }

    @Test
    public void chooseSameCard(){
        DeckManager deckManager = new DeckManager();

        AssistantDeck assistantDeck = deckManager.generateDeck(Mage.MAGE1);

        Card choosenCard = assistantDeck.chooseCard(1);

        choosenCard = assistantDeck.chooseCard(1);

        assertNull(choosenCard);
    }
}

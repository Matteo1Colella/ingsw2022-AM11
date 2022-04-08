package it.polimi.ingsw.cardsTests;

import it.polimi.ingsw.model.Player;
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

    @Test
    public void printCards(){
        Player player = new Player(1, "leo");
        DeckManager deckManager = new DeckManager();
        AssistantDeck assistantDeck = deckManager.generateDeck(Mage.MAGE1);
        player.setDeck(assistantDeck);

        player.printRemainingCard();
    }

    @Test
    public void printCardsAfterPlayingOne(){
        Player player = new Player(1, "leo");
        DeckManager deckManager = new DeckManager();
        AssistantDeck assistantDeck = deckManager.generateDeck(Mage.MAGE1);
        player.setDeck(assistantDeck);

        Card card = player.playCard(4);
        player.printRemainingCard();
        assertNotNull(card);
    }

    @Test
    public void playTenCards(){
        Player player = new Player(1, "leo");
        DeckManager deckManager = new DeckManager();
        AssistantDeck assistantDeck = deckManager.generateDeck(Mage.MAGE1);
        player.setDeck(assistantDeck);
        player.printRemainingCard();
        Card card = null;
        for(int i = 0; i < 10; i++){
            card = player.playCard(i);
        }
        assertNull(card);
        player.printRemainingCard();
    }
}

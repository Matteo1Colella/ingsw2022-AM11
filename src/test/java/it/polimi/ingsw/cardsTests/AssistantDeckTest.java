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
    public void choseCards(){

        DeckManager deckManager = new DeckManager();

        AssistantDeck assistantDeck = deckManager.generateDeck(Mage.MAGE1);

        Card chosenCard = assistantDeck.chooseCard(1);
        Card chosenCard2 = assistantDeck.chooseCard(2);
        Card chosenCard3 = assistantDeck.chooseCard(3);
        Card chosenCard4 = assistantDeck.chooseCard(4);
        Card chosenCard5 = assistantDeck.chooseCard(5);
        Card chosenCard6 = assistantDeck.chooseCard(6);
        Card chosenCard7 = assistantDeck.chooseCard(7);
        Card chosenCard8 = assistantDeck.chooseCard(8);
        Card chosenCard9 = assistantDeck.chooseCard(9);
        Card chosenCard10 = assistantDeck.chooseCard(10);

        chosenCard.getMage();

        assistantDeck.getCards();

        System.out.println(assistantDeck.leftCard());

        assistantDeck.chooseCard(1);

        assertNotNull(chosenCard);
    }

    @Test
    public void choose10Cards(){

        DeckManager deckManager = new DeckManager();

        AssistantDeck assistantDeck = deckManager.generateDeck(Mage.MAGE1);

        for(int i = 0; i < 10; i++){
            Card choosenCard = assistantDeck.chooseCard(i+1);
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
        for(int i = 0; i < 9; i++){
            card = player.playCard(i+1);
            player.setMotherNatureMoves(card.getSteps());
            assertNotNull(card);
        }
        card = player.playCard(10);
        assertNull(card);
        player.printRemainingCard();
    }
}

package it.polimi.ingsw;

import it.polimi.ingsw.model.AssistantDeck;
import it.polimi.ingsw.model.DeckManager;
import it.polimi.ingsw.model.Mage;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.model.GameManager;
import it.polimi.ingsw.model.Lobby;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Locale;


/**
 * Unit test for simple App.
 */
public class AppTest 
{

    @Test
    public void TestSameID(){
        ArrayList<Lobby> Lobbies = new ArrayList<Lobby>();
        GameManager GM = new GameManager(Lobbies);
        GM.Login("Cole", 3, true);
        GM.Login("Leo", 3, true);
        GM.Login("Cole", 3, true);
    }
    @Test
    public void TestFullLobby(){
        ArrayList<Lobby> Lobbies = new ArrayList<Lobby>();
        GameManager GM = new GameManager(Lobbies);
        GM.Login("Cole", 3, true);
        GM.Login("Leo", 3, true);
        GM.Login("Ale", 3, true);
        GM.Login("Gian", 3, true);
    }


    @Test
    public void sameDeck(){
        DeckManager deckManager = new DeckManager();
        Mage mage1 = Mage.MAGE1;
        AssistantDeck assistantDeck1;
        AssistantDeck assistantDeck2;

        assistantDeck1 = deckManager.generateDeck(mage1);
        assistantDeck2 = deckManager.generateDeck(mage1);

        assertNull(assistantDeck2);
        assertNotNull(assistantDeck1);

    }

    @Test
    public void multipleDeckAsk(){
        DeckManager deckManager = new DeckManager();
        Mage mage1 = Mage.MAGE1;
        Mage mage2 = Mage.MAGE2;
        Mage mage3 = Mage.MAGE3;
        Mage mage4 = Mage.MAGE4;

        AssistantDeck assistantDeck1;
        AssistantDeck assistantDeck2;
        AssistantDeck assistantDeck3;
        AssistantDeck assistantDeck4;

        assistantDeck1 = deckManager.generateDeck(mage1);
        assistantDeck2 = deckManager.generateDeck(mage2);
        assistantDeck3 = deckManager.generateDeck(mage3);
        assistantDeck4 = deckManager.generateDeck(mage4);

        assertNotNull(assistantDeck1);
        assertNotNull(assistantDeck2);
        assertNotNull(assistantDeck3);
        assertNotNull(assistantDeck4);

    }
}

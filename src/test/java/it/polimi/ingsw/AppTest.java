package it.polimi.ingsw;

import it.polimi.ingsw.model.AssistantDeck;
import it.polimi.ingsw.model.DeckManager;
import it.polimi.ingsw.model.Mage;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import it.polimi.ingsw.model.GameManager;
import it.polimi.ingsw.model.PlayersManager;

import java.util.ArrayList;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
    // testing lobbies functionalities
    @Test
    public void TestSameID(){
        ArrayList<PlayersManager> Lobbies = new ArrayList<PlayersManager>();
        GameManager GM = new GameManager(Lobbies);
        boolean added = GM.login("Cole", 3, true);
        assertTrue("not added first player", added);
        added = GM.login("Leo", 3, true);
        assertTrue("not added player", added);
        added = GM.login("Cole", 3, true);
        assertFalse("added duplicate player Cole", added);

        // bastard phase:
        added = GM.login("Cole1", 3, true);
        assertTrue("added Cole", added);

        added = GM.login("Cole2", 3, true);
        assertTrue("added duplicate player Cole", added);

        added = GM.login("Cole3", 3, true);
        assertTrue("added duplicate player Cole", added);

        int nOFLobbies = Lobbies.size();
        assertTrue("Lobbies must be 2 but are " + nOFLobbies, nOFLobbies == 2);
    }
    @Test
    public void TestFullLobby(){
        ArrayList<PlayersManager> Lobbies = new ArrayList<PlayersManager>();
        GameManager GM = new GameManager(Lobbies);
        GM.login("Cole", 3, true);
        GM.login("Leo", 3, true);
        GM.login("Ale", 3, true);
        GM.login("Gian", 3, true);
    }
    @Test
    // testing deck requests
    public void TestLoginAndDeck(){
        ArrayList<PlayersManager> Lobbies = new ArrayList<PlayersManager>();
        GameManager GM = new GameManager(Lobbies);

        boolean added = GM.login("Cole", 3, true);
        assertTrue("not added first player", added);

        boolean decksuccess=GM.deckRequest(GM.getPlayerLobby("Cole").getID(), Mage.MAGE1, "Cole");
        assertTrue("not added deck", decksuccess);

        added=GM.login("Leo", 3, true);
        assertTrue("not added player", added);

        decksuccess=GM.deckRequest(GM.getPlayerLobby("Leo").getID(), Mage.MAGE2, "Leo");
        assertTrue("not added deck", decksuccess);

        added=GM.login("Ale", 3, true);
        assertTrue("not added player", added);

        decksuccess=GM.deckRequest(GM.getPlayerLobby("Ale").getID(), Mage.MAGE3, "Ale");
        assertTrue("not added deck", decksuccess);

    }
    @Test
    public void TestLoginAndDeckMultipleLobbies(){
        ArrayList<PlayersManager> Lobbies = new ArrayList<PlayersManager>();
        GameManager GM = new GameManager(Lobbies);

        boolean added = GM.login("Cole", 3, true);
        assertTrue("not added first player", added);

        boolean decksuccess=GM.deckRequest(GM.getPlayerLobby("Cole").getID(), Mage.MAGE1, "Cole");
        assertTrue("not added deck", decksuccess);

        added=GM.login("Leo", 3, true);
        assertTrue("not added player", added);

        decksuccess=GM.deckRequest(GM.getPlayerLobby("Leo").getID(), Mage.MAGE2, "Leo");
        assertTrue("not added deck", decksuccess);

        added=GM.login("Ale", 3, true);
        assertTrue("not added player", added);

        decksuccess=GM.deckRequest(GM.getPlayerLobby("Ale").getID(), Mage.MAGE3, "Ale");
        assertTrue("not added deck", decksuccess);

        added=GM.login("Gian", 3, true);
        assertTrue("not added player", added);

        decksuccess=GM.deckRequest(GM.getPlayerLobby("Gian").getID(), Mage.MAGE1, "Gian");
        assertTrue("not added deck", decksuccess);

        added=GM.login("Pore", 3, true);
        assertTrue("not added player", added);

        decksuccess=GM.deckRequest(GM.getPlayerLobby("Pore").getID(), Mage.MAGE2, "Pore");
        assertTrue("not added deck", decksuccess);

    }

    @Test
    public void TestLoginAndDuplicateDeck(){
        ArrayList<PlayersManager> Lobbies = new ArrayList<PlayersManager>();
        GameManager GM = new GameManager(Lobbies);

        boolean added = GM.login("Cole", 3, true);
        assertTrue("not added first player", added);

        boolean decksuccess=GM.deckRequest(0, Mage.MAGE1, "Cole");
        assertTrue("not added deck", decksuccess);

        added=GM.login("Leo", 3, true);
        assertTrue("not added player", added);

        decksuccess=GM.deckRequest(0, Mage.MAGE1, "Leo");
        assertFalse("added deck", decksuccess);

        added=GM.login("Ale", 3, true);
        assertTrue("not added player", added);

        decksuccess=GM.deckRequest(0, Mage.MAGE3, "Ale");
        assertTrue("not added deck", decksuccess);

    }
    // testing deck functionalities
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

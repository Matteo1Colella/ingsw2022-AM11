package it.polimi.ingsw.gameTests;

import it.polimi.ingsw.controller.GameManager;
import it.polimi.ingsw.model.Mage;
import it.polimi.ingsw.controller.ComplexLobby;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginTest {
    // testing lobbies functionalities
    @Test
    public void TestSameID() {
        GameManager GM = new GameManager();
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

        int nOFLobbies = GM.getComplexLobbies().size();
        assertTrue("Lobbies must be 2 but are " + nOFLobbies, nOFLobbies == 2);
    }

    @Test
    public void TestFullLobby() {
        GameManager GM = new GameManager();
        GM.login("Cole", 3, true);
        GM.login("Leo", 3, true);
        GM.login("Ale", 3, true);
        GM.login("Gian", 3, true);
    }

    @Test
    // testing deck requests
    public void TestLoginAndDeck() {
        GameManager GM = new GameManager();

        boolean added = GM.login("Cole", 3, true);
        assertTrue("not added first player", added);

        added = GM.login("Leo", 3, true);
        assertTrue("not added player", added);

        added = GM.login("Ale", 3, true);
        assertTrue("not added player", added);

        boolean decksuccess = GM.getPlayerComplexLobby("Cole").deckRequest(Mage.MAGE1, "Cole");
        assertTrue("not added deck", decksuccess);

        decksuccess = GM.getPlayerComplexLobby("Cole").deckRequest(Mage.MAGE2, "Leo");
        assertTrue("not added deck", decksuccess);

        decksuccess = GM.getPlayerComplexLobby("Cole").deckRequest(Mage.MAGE3, "Ale");
        assertTrue("not added deck", decksuccess);

    }

    @Test
    public void TestLoginAndDeckMultipleLobbies() {
        GameManager GM = new GameManager();

        boolean added = GM.login("Cole", 3, true);
        assertTrue("not added first player", added);

        added = GM.login("Leo", 3, true);
        assertTrue("not added player", added);

        added = GM.login("Ale", 3, true);
        assertTrue("not added player", added);

        boolean decksuccess = GM.getPlayerComplexLobby("Cole").deckRequest(Mage.MAGE1, "Cole");
        assertTrue("not added deck", decksuccess);

        decksuccess = GM.getPlayerComplexLobby("Cole").deckRequest(Mage.MAGE2, "Leo");
        assertTrue("not added deck", decksuccess);

        decksuccess = GM.getPlayerComplexLobby("Cole").deckRequest(Mage.MAGE3, "Ale");
        assertTrue("not added deck", decksuccess);

        added = GM.login("Gian", 3, true);
        assertTrue("not added player", added);

        added = GM.login("Pore", 3, true);
        assertTrue("not added player", added);

        added = GM.login("Giulia", 3, true);
        assertTrue("not added player", added);

        decksuccess = GM.getPlayerComplexLobby("Gian").deckRequest(Mage.MAGE1, "Gian");
        assertTrue("not added deck", decksuccess);

        decksuccess = GM.getPlayerComplexLobby("Gian").deckRequest(Mage.MAGE2, "Pore");
        assertTrue("not added deck", decksuccess);

    }

    @Test
    public void TestLoginAndDuplicateDeck() {
        GameManager GM = new GameManager();

        boolean added = GM.login("Cole", 3, true);
        assertTrue("not added first player", added);

        added = GM.login("Leo", 3, true);
        assertTrue("not added player", added);

        added = GM.login("Ale", 3, true);
        assertTrue("not added player", added);

        boolean decksuccess = GM.getPlayerComplexLobby("Cole").deckRequest(Mage.MAGE1, "Cole");
        assertTrue("not added deck", decksuccess);

        decksuccess = GM.getPlayerComplexLobby("Cole").deckRequest(Mage.MAGE1, "Leo");
        assertFalse("added deck", decksuccess);

        decksuccess = GM.getPlayerComplexLobby("Cole").deckRequest(Mage.MAGE2, "Ale");
        assertTrue("not added deck", decksuccess);

    }

}

package it.polimi.ingsw.controllerTests;

import it.polimi.ingsw.controller.ComplexLobby;
import it.polimi.ingsw.controller.GameManager;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Mage;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.board.GameComponents;
import it.polimi.ingsw.model.cards.Card;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ComplexLobbyMethodsTest {
    @Test
    public void addChosenCardTest() {
        GameManager GM = new GameManager();

        GM.login("Ale", 3, true);
        GM.getPlayerComplexLobby("Ale").deckRequest(Mage.MAGE1, "Ale");
        GM.login("Leo", 3, true);
        GM.getPlayerComplexLobby("Leo").deckRequest(Mage.MAGE2, "Leo");
        GM.login("Cole", 3, true);
        GM.getPlayerComplexLobby("Cole").deckRequest(Mage.MAGE3, "Cole");

        Game newGame = GM.getComplexLobbies().get(0).getGame();
        ComplexLobby lobby = GM.getComplexLobbies().get(0);

        newGame.startGameWithRandomPlayer();
        Card first = new Card("cat",5,1,false, Mage.MAGE1);
        Card second = new Card("dog",4,2,false,Mage.MAGE2);
        Card third = new Card("hippo",3,3,false,Mage.MAGE1);
        lobby.checkIfPlayable(first);
        assertEquals(1,lobby.getChosenCards().size());
        lobby.checkIfPlayable(first); //prints: can't play this card
        assertEquals(1,lobby.getChosenCards().size());
        lobby.checkIfPlayable(second);
        lobby.checkIfPlayable(second); //prints: can't play this card
        lobby.checkIfPlayable(third);
        assertEquals(3,lobby.getChosenCards().size());
        lobby.checkIfPlayable(third);
        assertEquals(1,lobby.getChosenCards().size()); //At this moment begins a new round
        lobby.modifyPlayerTurn();
        assertFalse(lobby.checkIfPlayable(null));

    }
    @Test
    public void modifyTurnTest() {
        GameManager GM = new GameManager();

        GM.login("Ale", 3, true);
        GM.getPlayerComplexLobby("Ale").deckRequest(Mage.MAGE1, "Ale");
        GM.login("Leo", 3, true);
        GM.getPlayerComplexLobby("Leo").deckRequest(Mage.MAGE2, "Leo");
        GM.login("Cole", 3, true);
        GM.getPlayerComplexLobby("Cole").deckRequest(Mage.MAGE3, "Cole");

        Game newGame = GM.getComplexLobbies().get(0).getGame();
        ComplexLobby lobby = GM.getComplexLobbies().get(0);

        newGame.startGameWithRandomPlayer();
        Card first = new Card("cat",5,1,false, Mage.MAGE1);
        Card second = new Card("dog",4,2,false,Mage.MAGE1);
        Card third = new Card("hippo",3,3,false,Mage.MAGE1);

        lobby.setActivePlayer(lobby.getPlayers().get(0));

        lobby.checkIfPlayable(first);
        assertEquals(1,lobby.getChosenCards().size());
        lobby.checkIfPlayable(first); //prints: can't play this card
        assertEquals(1,lobby.getChosenCards().size());
        lobby.checkIfPlayable(second);
        lobby.checkIfPlayable(second); //prints: can't play this card
        lobby.checkIfPlayable(third);
        assertEquals(3,lobby.getChosenCards().size());
        lobby.modifyPlayerTurn();
        lobby.modifyPlayerTurn();
        lobby.modifyPlayerTurn();
        lobby.modifyPlayerTurn();
        lobby.modifyPlayerTurn();
        lobby.modifyPlayerTurn();
        lobby.modifyPlayerTurn();
        lobby.checkIfPlayable(first);
        lobby.checkIfPlayable(second);
        lobby.checkIfPlayable(third);
        lobby.modifyPlayerTurn();
        lobby.checkIfPlayable(third);
        lobby.checkIfPlayable(second);
        lobby.checkIfPlayable(first);
        lobby.modifyPlayerTurn();
        lobby.checkIfPlayable(second);
        lobby.checkIfPlayable(second); //print error
        lobby.checkIfPlayable(first);
        lobby.checkIfPlayable(third);
        lobby.modifyPlayerTurn();
        lobby.checkIfPlayable(first);
        lobby.checkIfPlayable(second);
        lobby.checkIfPlayable(first);
        lobby.modifyPlayerTurn(); //corner case

    }

    @Test
    public void modifyTurnTest2() {
        GameManager GM = new GameManager();

        GM.login("Ale", 3, true);
        GM.getPlayerComplexLobby("Ale").deckRequest(Mage.MAGE1, "Ale");
        GM.login("Leo", 3, true);
        GM.getPlayerComplexLobby("Leo").deckRequest(Mage.MAGE2, "Leo");
        GM.login("Cole", 3, true);
        GM.getPlayerComplexLobby("Cole").deckRequest(Mage.MAGE3, "Cole");

        Game newGame = GM.getComplexLobbies().get(0).getGame();
        ComplexLobby lobby = GM.getComplexLobbies().get(0);

        newGame.startGameWithRandomPlayer();
        Card first = new Card("cat",5,6,false, Mage.MAGE1);
        Card second = new Card("dog",4,4,false,Mage.MAGE1);
        Card third = new Card("hippo",3,5,false,Mage.MAGE1);
        lobby.checkIfPlayable(first);
        assertEquals(1,lobby.getChosenCards().size());
        lobby.checkIfPlayable(second);
        lobby.checkIfPlayable(third);
        assertEquals(3,lobby.getChosenCards().size());
        lobby.modifyPlayerTurn();

    }

    @Test
    public void modifyTurnTestWith4Players() {
        GameManager GM = new GameManager();

        GM.login("Ale", 4, true);
        GM.getPlayerComplexLobby("Ale").deckRequest(Mage.MAGE1, "Ale");
        GM.login("Leo", 4, true);
        GM.getPlayerComplexLobby("Leo").deckRequest(Mage.MAGE2, "Leo");
        GM.login("Cole", 4, true);
        GM.getPlayerComplexLobby("Cole").deckRequest(Mage.MAGE3, "Cole");
        GM.login("Giuseppe", 4, true);
        GM.getPlayerComplexLobby("Giuseppe").deckRequest(Mage.MAGE4, "Giuseppe");

        Game newGame = GM.getComplexLobbies().get(0).getGame();
        ComplexLobby lobby = GM.getComplexLobbies().get(0);

        newGame.startGameWithRandomPlayer();
        for(Player player : lobby.getPlayerOrder()){
            System.out.println(player.getID_player());
        }

        Card first = new Card("cat",1,1,false, Mage.MAGE1);
        Card second = new Card("dog",1,2,false,Mage.MAGE1);
        Card fourth = new Card("hippo",1,10,false,Mage.MAGE1);
        Card third = new Card("rat",1,3,false,Mage.MAGE1);
        lobby.checkIfPlayable(first);
        assertEquals(1,lobby.getChosenCards().size());
        lobby.checkIfPlayable(second);
        lobby.checkIfPlayable(third);
        assertEquals(3,lobby.getChosenCards().size());
        lobby.checkIfPlayable(fourth);
        lobby.modifyPlayerTurn();
        lobby.modifyPlayerTurn();
        lobby.modifyPlayerTurn();
        lobby.modifyPlayerTurn();
        lobby.modifyPlayerTurn();
        lobby.modifyPlayerTurn();
        lobby.checkIfPlayable(first);
        lobby.checkIfPlayable(second);
        lobby.checkIfPlayable(fourth);
        lobby.checkIfPlayable(fourth);
        lobby.modifyPlayerTurn();
        assertEquals(7,lobby.getRoundCounter());
        lobby.checkIfPlayable(fourth);
        lobby.checkIfPlayable(first);
        lobby.checkIfPlayable(second);
        lobby.checkIfPlayable(third);
        lobby.modifyPlayerTurn();
    }

}

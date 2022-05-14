package it.polimi.ingsw.controllerTests;

import it.polimi.ingsw.controller.ComplexLobby;
import it.polimi.ingsw.model.Mage;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.cards.Card;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ComplexLobbyMethodsTest {
    @Test
    public void addChosenCardTest() {
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player(1,"ale"));
        players.add(new Player(2,"leo"));
        players.add(new Player(3,"cole"));
        ComplexLobby lobby = new ComplexLobby(3,false,1,players);
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
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player(1,"ale"));
        players.add(new Player(2,"leo"));
        players.add(new Player(3,"cole"));
        ComplexLobby lobby = new ComplexLobby(3,false,1,players);
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
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player(1,"ale"));
        players.add(new Player(2,"leo"));
        players.add(new Player(3,"cole"));
        ComplexLobby lobby = new ComplexLobby(3,false,1,players);
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
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player(1,"ale"));
        players.add(new Player(2,"leo"));
        players.add(new Player(3,"cole"));
        players.add(new Player(4,"giuseppe"));
        ComplexLobby lobby = new ComplexLobby(4,false,1,players);
        Card first = new Card("cat",1,1,false, Mage.MAGE1);
        Card second = new Card("dog",1,2,false,Mage.MAGE1);
        Card third = new Card("hippo",1,10,false,Mage.MAGE1);
        Card fourth = new Card("rat",1,3,false,Mage.MAGE1);
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

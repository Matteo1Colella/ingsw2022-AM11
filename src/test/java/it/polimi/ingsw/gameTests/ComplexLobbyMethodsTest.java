package it.polimi.ingsw.gameTests;

import it.polimi.ingsw.controller.ComplexLobby;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Mage;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.cards.Card;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ComplexLobbyMethodsTest {
    @Test
    public void addChosenCardTest() {
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player(1,"ale"));
        players.add(new Player(2,"leo"));
        players.add(new Player(3,"cole"));
        ComplexLobby lobby = new ComplexLobby(3,false,1,players);
        Card first = new Card("cat",5,1,false, Mage.MAGE1);
        Card second = new Card("dog",4,2,false,Mage.MAGE1);
        Card third = new Card("hippo",3,3,false,Mage.MAGE1);
        lobby.addChosenCard(first);
        assertEquals(1,lobby.getChosenCards().size());
        lobby.addChosenCard(first); //prints: can't play this card
        assertEquals(1,lobby.getChosenCards().size());
        lobby.addChosenCard(second);
        lobby.addChosenCard(second); //prints: can't play this card
        lobby.addChosenCard(third);
        assertEquals(3,lobby.getChosenCards().size());
        lobby.addChosenCard(third);
        assertEquals(1,lobby.getChosenCards().size()); //At this moment begins a new round
        lobby.modifyPlayerTurn();
    }
    @Test
    public void modifyTurnTest() {
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player(1,"ale"));
        players.add(new Player(2,"leo"));
        players.add(new Player(3,"cole"));
        ComplexLobby lobby = new ComplexLobby(3,false,1,players);
        Card first = new Card("cat",5,1,false, Mage.MAGE1);
        Card second = new Card("dog",4,1,false,Mage.MAGE1);
        Card third = new Card("hippo",3,1,false,Mage.MAGE1);
        lobby.addChosenCard(first);
        assertEquals(1,lobby.getChosenCards().size());
        lobby.addChosenCard(first); //prints: can't play this card
        assertEquals(1,lobby.getChosenCards().size());
        lobby.addChosenCard(second);
        lobby.addChosenCard(second); //prints: can't play this card
        lobby.addChosenCard(third);
        assertEquals(3,lobby.getChosenCards().size());
        lobby.modifyPlayerTurn();
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
        lobby.addChosenCard(first);
        assertEquals(1,lobby.getChosenCards().size());
        lobby.addChosenCard(second);
        lobby.addChosenCard(third);
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
        Card third = new Card("hippo",1,3,false,Mage.MAGE1);
        Card fourth = new Card("rat",1,4,false,Mage.MAGE1);
        lobby.addChosenCard(first);
        assertEquals(1,lobby.getChosenCards().size());
        lobby.addChosenCard(second);
        lobby.addChosenCard(third);
        assertEquals(3,lobby.getChosenCards().size());
        lobby.addChosenCard(fourth);
        lobby.modifyPlayerTurn();
    }


}

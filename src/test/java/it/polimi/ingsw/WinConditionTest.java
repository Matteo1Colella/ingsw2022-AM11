package it.polimi.ingsw;

import it.polimi.ingsw.controller.ColorTower;
import it.polimi.ingsw.controller.ComplexLobby;
import it.polimi.ingsw.controller.DeckManager;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Mage;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.board.Bag;
import it.polimi.ingsw.model.board.IslandCard;
import it.polimi.ingsw.model.cards.AssistantDeck;
import it.polimi.ingsw.model.cards.Card;
import it.polimi.ingsw.model.colors.ColorStudent;
import it.polimi.ingsw.model.pieces.Professor;
import it.polimi.ingsw.model.pieces.Tower;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class WinConditionTest {
    @Test
    public void winWithNoTowersTest() {
        Game game = new Game(false,1,2);
        ArrayList<Player> players = new ArrayList<>();
        Player player1 = new Player(1,"ale");
        Player player2 = new Player(1,"leo");
        players.add(player1);
        players.add(player2);
        game.setComplexLobby(new ComplexLobby(2,false,1,players));
        game.setGameStructure(game.generateBoard());
        assertEquals(2,game.getGameComponents().getSchoolBoards().size());

        game.getGameComponents().getSchoolBoards().get(0).getTowers().clear();
        assertEquals(0,game.getGameComponents().getSchoolBoards().get(0).getTowers().size());

        assertEquals(player1, game.winCondition());
    }

    @Test

    public void DrawWith3Archipelagos() {
        Game game = new Game(false,1,2);
        ArrayList<Player> players = new ArrayList<>();
        Player player1 = new Player(1,"ale");
        Player player2 = new Player(1,"leo");
        players.add(player1);
        players.add(player2);
        game.setComplexLobby(new ComplexLobby(2,false,1,players));
        game.setGameStructure(game.generateBoard());
        assertEquals(2,game.getGameComponents().getSchoolBoards().size());

        ArrayList<IslandCard> islandCards = new ArrayList<>();
        islandCards.add(new IslandCard(1));
        islandCards.add(new IslandCard(2));
        islandCards.add(new IslandCard(3));



        game.getGameComponents().setArchipelago(islandCards);



        assertEquals(player1,players.get(0));
        assertEquals(ColorTower.BLACK,players.get(0).getSchoolBoard().getTowers().get(0).getColor());
        assertEquals(ColorTower.WHITE,players.get(1).getSchoolBoard().getTowers().get(0).getColor());


        assertEquals(null, game.winCondition());
    }

    @Test

    public void WinWith3Archipelagos() {
        Game game = new Game(false,1,2);
        ArrayList<Player> players = new ArrayList<>();
        Player player1 = new Player(1,"ale");
        Player player2 = new Player(1,"leo");
        players.add(player1);
        players.add(player2);
        DeckManager deckManager = new DeckManager();
        player1.setDeck(deckManager.generateDeck(Mage.MAGE1));
        player2.setDeck(deckManager.generateDeck(Mage.MAGE2));
        game.setComplexLobby(new ComplexLobby(2,false,1,players));
        game.setGameStructure(game.generateBoard());
        assertEquals(2,game.getGameComponents().getSchoolBoards().size());

        ArrayList<IslandCard> islandCards = new ArrayList<>();
        islandCards.add(new IslandCard(1));
        islandCards.add(new IslandCard(2));
        islandCards.add(new IslandCard(3));
        islandCards.get(0).setTower(new Tower(ColorTower.BLACK));
        islandCards.get(1).setTower(new Tower(ColorTower.BLACK));
        islandCards.get(2).setTower(new Tower(ColorTower.WHITE));


        game.getGameComponents().setArchipelago(islandCards);



        assertEquals(player1,players.get(0));
        assertEquals(ColorTower.BLACK,players.get(0).getSchoolBoard().getTowers().get(0).getColor());
        assertEquals(ColorTower.WHITE,players.get(1).getSchoolBoard().getTowers().get(0).getColor());


        assertEquals(players.get(0), game.winCondition());
    }

    @Test

    public void OtherWinWith3Archipelagos3Players() {
        Game game = new Game(false,1,3);
        ArrayList<Player> players = new ArrayList<>();
        Player player1 = new Player(1,"ale");
        Player player2 = new Player(2,"leo");
        players.add(player1);
        players.add(player2);
        players.add(new Player(3,"cole"));

        DeckManager deckManager = new DeckManager();
        player1.setDeck(deckManager.generateDeck(Mage.MAGE1));
        player2.setDeck(deckManager.generateDeck(Mage.MAGE2));
        players.get(2).setDeck(deckManager.generateDeck(Mage.MAGE3));
        game.setComplexLobby(new ComplexLobby(3,false,1,players));
        game.setGameStructure(game.generateBoard());
        assertEquals(3,game.getGameComponents().getSchoolBoards().size());

        ArrayList<IslandCard> islandCards = new ArrayList<>();
        islandCards.add(new IslandCard(1));
        islandCards.add(new IslandCard(2));
        islandCards.add(new IslandCard(3));
        islandCards.get(0).setTower(new Tower(ColorTower.GREY));
        islandCards.get(1).setTower(new Tower(ColorTower.WHITE));
        islandCards.get(2).setTower(new Tower(ColorTower.GREY));



        game.getGameComponents().setArchipelago(islandCards);



        assertEquals(player1,players.get(0));
        assertEquals(ColorTower.BLACK,players.get(0).getSchoolBoard().getTowers().get(0).getColor());
        assertEquals(ColorTower.WHITE,players.get(1).getSchoolBoard().getTowers().get(0).getColor());
        assertEquals(ColorTower.GREY,players.get(2).getSchoolBoard().getTowers().get(0).getColor());

        assertEquals(players.get(2), game.winCondition());

    }
    @Test

    public void WinWithNumProf() {
        Game game = new Game(false,1,3);
        ArrayList<Player> players = new ArrayList<>();
        Player player1 = new Player(1,"ale");
        Player player2 = new Player(2,"leo");
        players.add(player1);
        players.add(player2);
        players.add(new Player(3,"cole"));

        DeckManager deckManager = new DeckManager();
        player1.setDeck(deckManager.generateDeck(Mage.MAGE1));
        player2.setDeck(deckManager.generateDeck(Mage.MAGE2));
        players.get(2).setDeck(deckManager.generateDeck(Mage.MAGE3));
        game.setComplexLobby(new ComplexLobby(3,false,1,players));
        game.setGameStructure(game.generateBoard());
        assertEquals(3,game.getGameComponents().getSchoolBoards().size());

        ArrayList<IslandCard> islandCards = new ArrayList<>();
        islandCards.add(new IslandCard(1));
        islandCards.add(new IslandCard(2));
        islandCards.add(new IslandCard(3));
        islandCards.get(0).setTower(new Tower(ColorTower.GREY));
        islandCards.get(1).setTower(new Tower(ColorTower.WHITE));
        islandCards.get(2).setTower(new Tower(ColorTower.BLACK));



        game.getGameComponents().setArchipelago(islandCards);



        assertEquals(player1,players.get(0));
        assertEquals(ColorTower.BLACK,players.get(0).getSchoolBoard().getTowers().get(0).getColor());
        assertEquals(ColorTower.WHITE,players.get(1).getSchoolBoard().getTowers().get(0).getColor());
        assertEquals(ColorTower.GREY,players.get(2).getSchoolBoard().getTowers().get(0).getColor());

        game.getGameComponents().getSchoolBoards().get(2).getDiningRooms().get(1).setProfessor(new Professor(ColorStudent.PINK));
        assertEquals(players.get(2), game.winCondition());

    }

    @Test

    public void WinWithNoMoreCardsTest() {
        Game game = new Game(false,1,3);
        ArrayList<Player> players = new ArrayList<>();
        Player player1 = new Player(1,"ale");
        Player player2 = new Player(2,"leo");
        players.add(player1);
        players.add(player2);
        players.add(new Player(3,"cole"));

        DeckManager deckManager = new DeckManager();
        player1.setDeck(deckManager.generateDeck(Mage.MAGE1));
        player2.setDeck(deckManager.generateDeck(Mage.MAGE2));
        players.get(2).setDeck(deckManager.generateDeck(Mage.MAGE3));
        players.get(2).getDeck().chooseCard(0);
        players.get(2).getDeck().chooseCard(1);
        players.get(2).getDeck().chooseCard(2);
        players.get(2).getDeck().chooseCard(3);
        players.get(2).getDeck().chooseCard(4);
        players.get(2).getDeck().chooseCard(5);
        players.get(2).getDeck().chooseCard(6);
        players.get(2).getDeck().chooseCard(7);
        players.get(2).getDeck().chooseCard(8);
        players.get(2).getDeck().chooseCard(9);

        assertEquals(0,players.get(2).getDeck().leftCard());

        game.setComplexLobby(new ComplexLobby(3,false,1,players));
        game.setGameStructure(game.generateBoard());

        assertEquals(3,game.getGameComponents().getSchoolBoards().size());

        ArrayList<IslandCard> islandCards = new ArrayList<>();
        islandCards.add(new IslandCard(1));
        islandCards.add(new IslandCard(2));
        islandCards.add(new IslandCard(3));
        islandCards.add(new IslandCard(4));
        islandCards.get(0).setTower(new Tower(ColorTower.GREY));
        islandCards.get(1).setTower(new Tower(ColorTower.WHITE));
        islandCards.get(2).setTower(new Tower(ColorTower.BLACK));



        game.getGameComponents().setArchipelago(islandCards);



        assertEquals(player1,players.get(0));
        assertEquals(ColorTower.BLACK,players.get(0).getSchoolBoard().getTowers().get(0).getColor());
        assertEquals(ColorTower.WHITE,players.get(1).getSchoolBoard().getTowers().get(0).getColor());
        assertEquals(ColorTower.GREY,players.get(2).getSchoolBoard().getTowers().get(0).getColor());

        game.getGameComponents().getSchoolBoards().get(2).getDiningRooms().get(1).setProfessor(new Professor(ColorStudent.PINK));

        assertEquals(players.get(2), game.winCondition());

    }
    @Test

    public void NoMoreStudentTest() {
        Game game = new Game(false,1,3);
        ArrayList<Player> players = new ArrayList<>();
        Player player1 = new Player(1,"ale");
        Player player2 = new Player(2,"leo");
        players.add(player1);
        players.add(player2);
        players.add(new Player(3,"cole"));

        DeckManager deckManager = new DeckManager();
        player1.setDeck(deckManager.generateDeck(Mage.MAGE1));
        player2.setDeck(deckManager.generateDeck(Mage.MAGE2));
        players.get(2).setDeck(deckManager.generateDeck(Mage.MAGE3));


        game.setComplexLobby(new ComplexLobby(3,false,1,players));
        game.setGameStructure(game.generateBoard());

        assertEquals(96, game.getGameComponents().getBag().left());
        game.getGameComponents().getBag().draw();
        assertEquals(95, game.getGameComponents().getBag().left());

        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();
        game.getGameComponents().getBag().draw();



        assertEquals(0, game.getGameComponents().getBag().left());
        assertEquals(3,game.getGameComponents().getSchoolBoards().size());

        ArrayList<IslandCard> islandCards = new ArrayList<>();
        islandCards.add(new IslandCard(1));
        islandCards.add(new IslandCard(2));
        islandCards.add(new IslandCard(3));
        islandCards.add(new IslandCard(4));
        islandCards.get(0).setTower(new Tower(ColorTower.WHITE));
        islandCards.get(1).setTower(new Tower(ColorTower.WHITE));
        islandCards.get(2).setTower(new Tower(ColorTower.BLACK));



        game.getGameComponents().setArchipelago(islandCards);



        assertEquals(player1,players.get(0));
        assertEquals(ColorTower.BLACK,players.get(0).getSchoolBoard().getTowers().get(0).getColor());
        assertEquals(ColorTower.WHITE,players.get(1).getSchoolBoard().getTowers().get(0).getColor());
        assertEquals(ColorTower.GREY,players.get(2).getSchoolBoard().getTowers().get(0).getColor());

        game.getGameComponents().getSchoolBoards().get(2).getDiningRooms().get(1).setProfessor(new Professor(ColorStudent.PINK));

        assertEquals(players.get(1), game.winCondition());

    }

}

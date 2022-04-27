package it.polimi.ingsw.boardTests;

import it.polimi.ingsw.controller.ComplexLobby;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.board.CoinReserve;
import it.polimi.ingsw.model.cards.CharacterCard;
import it.polimi.ingsw.model.cards.Characters.Character3;
import it.polimi.ingsw.model.colors.ColorStudent;
import it.polimi.ingsw.model.pieces.Student;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CoinsTest {

    @Test
    public void giveCoin1Player(){
        Player player1 = new Player(1, "leo");
        Player player2 = new Player(2, "cole");
        ArrayList<Player> players = new ArrayList<>();

        players.add(player1);
        players.add(player2);

        ComplexLobby complexLobby = new ComplexLobby(2, true, 0, players);
        complexLobby.createGame(2, 0, true);

        Game game = complexLobby.getGame();


        player1.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));
        player1.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));
        player1.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));

        game.coinGiver();

        assertEquals(player1.getCoinOwned(), 2);

        player1.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));
        player1.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));
        player1.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));

        game.coinGiver();

        assertEquals(player1.getSchoolBoard().getStudentSize(ColorStudent.RED), 6);

        assertEquals(player1.getCoinOwned(), 3);
    }

    @Test
    public void giveCoin2Players(){
        Player player1 = new Player(1, "leo");
        Player player2 = new Player(2, "cole");
        ArrayList<Player> players = new ArrayList<>();

        players.add(player1);
        players.add(player2);

        ComplexLobby complexLobby = new ComplexLobby(2, true, 0, players);
        complexLobby.createGame(2, 0, true);
        Game game = complexLobby.getGame();

       //game.generateBoard();

        assertEquals(player1.getCoinOwned(), 1);
        assertEquals(player2.getCoinOwned(), 1);

        player1.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));
        player1.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));
        player1.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));
        player2.getSchoolBoard().getDiningRoomByColor(ColorStudent.GREEN).addStudent(new Student(ColorStudent.GREEN));
        player2.getSchoolBoard().getDiningRoomByColor(ColorStudent.GREEN).addStudent(new Student(ColorStudent.GREEN));

        game.coinGiver();

        assertEquals(player1.getCoinOwned(), 2);
        assertEquals(player2.getCoinOwned(), 1);

        player1.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));
        player1.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));
        player1.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));
        player2.getSchoolBoard().getDiningRoomByColor(ColorStudent.GREEN).addStudent(new Student(ColorStudent.GREEN));
        player2.getSchoolBoard().getDiningRoomByColor(ColorStudent.GREEN).addStudent(new Student(ColorStudent.GREEN));

        game.coinGiver();

        assertEquals(player1.getSchoolBoard().getStudentSize(ColorStudent.RED), 6);

        assertEquals(player1.getCoinOwned(), 3);
        assertEquals(player2.getCoinOwned(), 2);
    }

    @Test
    public void giveCoinMultiplePlayers(){
        Player player1 = new Player(1, "leo");
        Player player2 = new Player(2, "cole");
        Player player3 = new Player(3, "ale");
        ArrayList<Player> players = new ArrayList<>();

        players.add(player1);
        players.add(player2);
        players.add(player3);

        ComplexLobby complexLobby = new ComplexLobby(3, true, 0, players);
        complexLobby.createGame(3, 0, true);
        Game game = complexLobby.getGame();


        player1.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));
        player1.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));
        player1.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));
        player2.getSchoolBoard().getDiningRoomByColor(ColorStudent.GREEN).addStudent(new Student(ColorStudent.GREEN));
        player2.getSchoolBoard().getDiningRoomByColor(ColorStudent.GREEN).addStudent(new Student(ColorStudent.GREEN));
        player3.getSchoolBoard().getDiningRoomByColor(ColorStudent.BLUE).addStudent(new Student(ColorStudent.BLUE));
        player3.getSchoolBoard().getDiningRoomByColor(ColorStudent.BLUE).addStudent(new Student(ColorStudent.BLUE));
        player3.getSchoolBoard().getDiningRoomByColor(ColorStudent.PINK).addStudent(new Student(ColorStudent.PINK));

        game.coinGiver();

        assertEquals(player1.getCoinOwned(), 2);
        assertEquals(player2.getCoinOwned(), 1);
        assertEquals(player3.getCoinOwned(), 1);

        player1.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));
        player1.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));
        player1.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));
        player2.getSchoolBoard().getDiningRoomByColor(ColorStudent.GREEN).addStudent(new Student(ColorStudent.GREEN));
        player2.getSchoolBoard().getDiningRoomByColor(ColorStudent.GREEN).addStudent(new Student(ColorStudent.GREEN));
        player3.getSchoolBoard().getDiningRoomByColor(ColorStudent.BLUE).addStudent(new Student(ColorStudent.BLUE));
        player3.getSchoolBoard().getDiningRoomByColor(ColorStudent.PINK).addStudent(new Student(ColorStudent.PINK));
        player3.getSchoolBoard().getDiningRoomByColor(ColorStudent.PINK).addStudent(new Student(ColorStudent.PINK));

        game.coinGiver();

        assertEquals(player1.getSchoolBoard().getStudentSize(ColorStudent.RED), 6);

        assertEquals(player1.getCoinOwned(), 3);
        assertEquals(player2.getCoinOwned(), 2);
        assertEquals(player3.getCoinOwned(), 3);
    }

    @Test
    public void useCoinOnCharacter(){
        Player player1 = new Player(1, "leo");
        Player player2 = new Player(2, "cole");
        ArrayList<Player> players = new ArrayList<>();
        CharacterCard character = new Character3(3);
        CoinReserve coinReserve = null;

        players.add(player1);
        players.add(player2);

        ComplexLobby complexLobby = new ComplexLobby(2, true, 0, players);
        complexLobby.createGame(2, 0, true);
        Game game = complexLobby.getGame();

        coinReserve = game.getGameComponents().getCoins();

        player1.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));
        player1.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));
        player1.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));

        game.coinGiver();

        assertEquals(player1.getCoinOwned(), 2);

        player1.playCharacter(character, coinReserve);

        assertEquals(player1.getCoinOwned(), 2);
        assertEquals(coinReserve.getCoins(), 17);

        player1.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));
        player1.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));
        player1.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));

        game.coinGiver();

        assertEquals(player1.getSchoolBoard().getStudentSize(ColorStudent.RED), 6);

        assertEquals(player1.getCoinOwned(), 3);

        player1.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));
        player1.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));
        player1.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));

        game.coinGiver();

        assertEquals(player1.getSchoolBoard().getStudentSize(ColorStudent.RED), 9);

        assertEquals(player1.getCoinOwned(), 4);

        player1.playCharacter(character, coinReserve);

        assertEquals(coinReserve.getCoins(), 18);
    }
}

package it.polimi.ingsw.gameTests;

import it.polimi.ingsw.controller.ComplexLobby;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.cards.Characters.Character2;
import it.polimi.ingsw.model.colors.ColorStudent;
import it.polimi.ingsw.model.pieces.Student;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

public class giveProfessorTest {

    @Test
    public void giveProfessor(){
        Player player1 = new Player(1, "leo");
        Player player2 = new Player(2, "cole");
        ArrayList<Player> players = new ArrayList<>();

        players.add(player1);
        players.add(player2);

        ComplexLobby complexLobby = new ComplexLobby(2, false, 0, players);
        complexLobby.createGame(2, 0, true);
        Game game = complexLobby.getGame();

        game.generateBoard();

        player1.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));
        player1.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));

        game.colorDominance();

        HashMap<ColorStudent, Player> colorDominanceMap = game.getDominanceMap();

        assertNotNull(colorDominanceMap);
        assertNotNull("The professor is null",player1.getSchoolBoard().getProfessor(ColorStudent.RED));

        player2.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));
        player2.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));

        game.colorDominance();

        assertNull("The professor is not null",player2.getSchoolBoard().getProfessor(ColorStudent.RED));
    }

    @Test
    public void giveMultipleProfessor(){
        Player player1 = new Player(1, "leo");
        Player player2 = new Player(2, "cole");
        ArrayList<Player> players = new ArrayList<>();

        players.add(player1);
        players.add(player2);

        ComplexLobby complexLobby = new ComplexLobby(2, false, 0, players);
        complexLobby.createGame(2, 0, true);
        Game game = complexLobby.getGame();

        game.generateBoard();

        player1.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));
        player1.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));
        player1.getSchoolBoard().getDiningRoomByColor(ColorStudent.BLUE).addStudent(new Student(ColorStudent.BLUE));
        game.colorDominance();
        assertNotNull("The red professor in p1 is null",player1.getSchoolBoard().getProfessor(ColorStudent.RED));
        assertNotNull("The blu professor in p1 is null",player1.getSchoolBoard().getProfessor(ColorStudent.BLUE));

        player2.getSchoolBoard().getDiningRoomByColor(ColorStudent.GREEN).addStudent(new Student(ColorStudent.GREEN));
        player2.getSchoolBoard().getDiningRoomByColor(ColorStudent.GREEN).addStudent(new Student(ColorStudent.GREEN));
        game.colorDominance();
        assertNotNull("The green professor in p2 is null",player2.getSchoolBoard().getProfessor(ColorStudent.GREEN));


        player2.getSchoolBoard().getDiningRoomByColor(ColorStudent.BLUE).addStudent(new Student(ColorStudent.BLUE));
        game.colorDominance();
        assertNull("The green professor in p2 is not null",player2.getSchoolBoard().getProfessor(ColorStudent.BLUE));
    }

    @Test
    public void switchProfessor(){
        Player player1 = new Player(1, "leo");
        Player player2 = new Player(2, "cole");
        ArrayList<Player> players = new ArrayList<>();

        players.add(player1);
        players.add(player2);

        ComplexLobby complexLobby = new ComplexLobby(2, false, 0, players);
        complexLobby.createGame(2, 0, true);
        Game game = complexLobby.getGame();

        game.generateBoard();

        player1.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));
        player1.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));

        game.colorDominance();
        assertNotNull("The red professor in p1 is null",player1.getSchoolBoard().getProfessor(ColorStudent.RED));

        player2.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));
        player2.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));
        player2.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));

        game.colorDominance();

        assertNull("The red professor in p1 is not null",player1.getSchoolBoard().getProfessor(ColorStudent.RED));
        assertNotNull("The red professor in p2 is null",player2.getSchoolBoard().getProfessor(ColorStudent.RED));
    }

    @Test
    public void threePlayerProfessor(){
        Player player1 = new Player(1, "leo");
        Player player2 = new Player(2, "cole");
        Player player3 = new Player(3, "ale");
        ArrayList<Player> players = new ArrayList<>();

        players.add(player1);
        players.add(player2);
        players.add(player3);

        ComplexLobby complexLobby = new ComplexLobby(3, false, 0, players);
        complexLobby.createGame(3, 0, true);
        Game game = complexLobby.getGame();

        game.generateBoard();

        player1.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));
        player1.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));

        game.colorDominance();
        assertNotNull("The red professor in p1 is null",player1.getSchoolBoard().getProfessor(ColorStudent.RED));

        player2.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));
        player2.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));

        game.colorDominance();
        assertNull("The red professor in p2 is not null",player2.getSchoolBoard().getProfessor(ColorStudent.RED));

        player3.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));
        player3.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));

        game.colorDominance();
        assertNull("The red professor in p3 is not null",player3.getSchoolBoard().getProfessor(ColorStudent.RED));
    }

    @Test
    public void giveProfessorCharachter2twoPlayers(){
        Player player1 = new Player(1, "leo");
        Player player2 = new Player(2, "cole");
        ArrayList<Player> players = new ArrayList<>();

        players.add(player1);
        players.add(player2);

        ComplexLobby complexLobby = new ComplexLobby(2, false, 0, players);
        complexLobby.createGame(2, 0, true);
        Game game = complexLobby.getGame();

        game.generateBoard();

        Character2 character2 = new Character2(1);


        player2.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));
        player2.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));

        game.colorDominance();
        assertNotNull("The red professor in p2 is null",player2.getSchoolBoard().getProfessor(ColorStudent.RED));

        player1.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));
        player1.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));

        character2.effect(player1);

        game.colorDominance();

        assertNotNull("The red professor in p1 is null",player1.getSchoolBoard().getProfessor(ColorStudent.RED));
        assertNull("The red professor in p2 is not null",player2.getSchoolBoard().getProfessor(ColorStudent.RED));
    }

    @Test
    public void giveProfessorCharachter2threePlayers(){
        Player player1 = new Player(1, "leo");
        Player player2 = new Player(2, "cole");
        Player player3 = new Player(3, "ale");
        ArrayList<Player> players = new ArrayList<>();

        players.add(player1);
        players.add(player2);
        players.add(player3);

        ComplexLobby complexLobby = new ComplexLobby(3, false, 0, players);
        complexLobby.createGame(3, 0, true);
        Game game = complexLobby.getGame();

        game.generateBoard();

        Character2 character2 = new Character2(1);

        player1.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));
        player1.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));

        game.colorDominance();
        assertNotNull("The red professor in p1 is null",player1.getSchoolBoard().getProfessor(ColorStudent.RED));

        player2.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));
        player2.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));

        character2.effect(player2);
        game.colorDominance();
        assertNull("The red professor in p1 is not null",player1.getSchoolBoard().getProfessor(ColorStudent.RED));
        assertNotNull("The red professor in p2 is null",player2.getSchoolBoard().getProfessor(ColorStudent.RED));

        player3.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));
        player3.getSchoolBoard().getDiningRoomByColor(ColorStudent.RED).addStudent(new Student(ColorStudent.RED));

        character2.effect(player3);
        game.colorDominance();
        assertNull("The red professor in p1 is not null",player1.getSchoolBoard().getProfessor(ColorStudent.RED));
        assertNull("The red professor in p2 is not null",player2.getSchoolBoard().getProfessor(ColorStudent.RED));
        assertNotNull("The red professor in p3 is null",player3.getSchoolBoard().getProfessor(ColorStudent.RED));
    }
}
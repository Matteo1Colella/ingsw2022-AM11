package it.polimi.ingsw;

import it.polimi.ingsw.model.colors.ColorStudent;
import it.polimi.ingsw.controller.ColorTower;
import it.polimi.ingsw.controller.ComplexLobby;
import it.polimi.ingsw.controller.GameManager;
import it.polimi.ingsw.model.board.GameComponents;
import it.polimi.ingsw.model.*;


import it.polimi.ingsw.model.pieces.Student;
import it.polimi.ingsw.model.pieces.Tower;
import org.junit.Test;


import java.util.ArrayList;

import static org.junit.Assert.*;

public class DominanceTest {

    @Test
    public void test2PlayersDominance(){
        // game creation
        GameManager GM = new GameManager();

        GM.login("Cole", 2, true);
        GM.deckRequest(GM.getPlayerComplexLobby("Cole").getID(), Mage.MAGE1, "Cole");
        GM.login("Leo", 2, true);
        GM.deckRequest(GM.getPlayerComplexLobby("Leo").getID(), Mage.MAGE2, "Leo");

        Game newGame = GM.getComplexLobbies().get(0).getGame();
        GameComponents gameComponents = newGame.generateBoard(false, 2);
        // end game creation

        //game settings:
        //moving mother nature to island 2
        newGame.moveMotherNature(2, gameComponents.getMothernature(), gameComponents.getArchipelago());

        //loading some students on island
        Student green = new Student(ColorStudent.GREEN);
        Student red = new Student(ColorStudent.RED);
        Student yellow = new Student(ColorStudent.YELLOW);

        gameComponents.getArchipelago().get(2).addStudent(green);
        gameComponents.getArchipelago().get(2).addStudent(green);
        gameComponents.getArchipelago().get(2).addStudent(red);
        gameComponents.getArchipelago().get(2).addStudent(yellow);

        // giving players their schoolboard
        GM.getComplexLobbies().get(0).getPlayers().get(0).setSchoolBoard(gameComponents.getSchoolBoards().get(0));
        GM.getComplexLobbies().get(0).getPlayers().get(1).setSchoolBoard(gameComponents.getSchoolBoards().get(1));

        // giving players some professors
        GM.getComplexLobbies().get(0).getPlayers().get(0).getSchoolBoard().setProfessor(gameComponents.getProfessorCollection().get(0));
        GM.getComplexLobbies().get(0).getPlayers().get(0).getSchoolBoard().setProfessor(gameComponents.getProfessorCollection().get(1));
        GM.getComplexLobbies().get(0).getPlayers().get(1).getSchoolBoard().setProfessor(gameComponents.getProfessorCollection().get(3));

        //launching dominance
        newGame.islandDominance();

        assertEquals(GM.getComplexLobbies().get(0).getPlayers().get(0).getSchoolBoard().getTowers().get(0).getColor(), gameComponents.getArchipelago().get(2).getTower().getColor());
    }

    @Test
    public void test2PlayersDominanceWithATower(){
        // game creation
        GameManager GM = new GameManager();

        GM.login("Cole", 2, true);
        GM.deckRequest(GM.getPlayerComplexLobby("Cole").getID(), Mage.MAGE1, "Cole");
        GM.login("Leo", 2, true);
        GM.deckRequest(GM.getPlayerComplexLobby("Leo").getID(), Mage.MAGE2, "Leo");

        Game newGame = GM.getComplexLobbies().get(0).getGame();
        GameComponents gameComponents = newGame.generateBoard(false, 2);
        // end game creation

        //game settings:
        //moving mother nature to island 2
        newGame.moveMotherNature(2, gameComponents.getMothernature(), gameComponents.getArchipelago());

        //loading some students on island
        Student green = new Student(ColorStudent.GREEN);
        Student red = new Student(ColorStudent.RED);
        Student yellow = new Student(ColorStudent.YELLOW);

        gameComponents.getArchipelago().get(2).addStudent(green);
        gameComponents.getArchipelago().get(2).addStudent(green);
        gameComponents.getArchipelago().get(2).addStudent(red);
        gameComponents.getArchipelago().get(2).addStudent(yellow);

        //putting a tower on the island
        Tower tower = new Tower(ColorTower.WHITE);
        gameComponents.getArchipelago().get(2).setTower(tower);

        // giving players their schoolboard
        GM.getComplexLobbies().get(0).getPlayers().get(0).setSchoolBoard(gameComponents.getSchoolBoards().get(0));
        GM.getComplexLobbies().get(0).getPlayers().get(1).setSchoolBoard(gameComponents.getSchoolBoards().get(1));

        System.out.println(GM.getComplexLobbies().get(0).getPlayers().get(0).getSchoolBoard().getTowers().get(0).getColor());
        System.out.println( GM.getComplexLobbies().get(0).getPlayers().get(1).getSchoolBoard().getTowers().get(0).getColor());

        // giving players some professors
        GM.getComplexLobbies().get(0).getPlayers().get(0).getSchoolBoard().setProfessor(gameComponents.getProfessorCollection().get(0));
        GM.getComplexLobbies().get(0).getPlayers().get(0).getSchoolBoard().setProfessor(gameComponents.getProfessorCollection().get(1));
        GM.getComplexLobbies().get(0).getPlayers().get(0).getSchoolBoard().setProfessor(gameComponents.getProfessorCollection().get(2));

        //launching dominance
        newGame.islandDominance();

        assertEquals(GM.getComplexLobbies().get(0).getPlayers().get(0).getSchoolBoard().getTowers().get(0).getColor(), gameComponents.getArchipelago().get(2).getTower().getColor());
    }
}

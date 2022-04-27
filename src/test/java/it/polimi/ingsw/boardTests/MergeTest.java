package it.polimi.ingsw.boardTests;

import it.polimi.ingsw.model.colors.ColorTower;
import it.polimi.ingsw.controller.GameManager;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Mage;
import it.polimi.ingsw.model.board.GameComponents;
import it.polimi.ingsw.model.board.IslandCard;
import it.polimi.ingsw.model.colors.ColorStudent;
import it.polimi.ingsw.model.pieces.Student;
import it.polimi.ingsw.model.pieces.Tower;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;

public class MergeTest {
    @Test
    public void TestMergeNext() {
        GameManager GM = new GameManager();

        GM.login("Cole", 2, true);
        GM.getPlayerComplexLobby("Cole").deckRequest(Mage.MAGE1, "Cole");
        GM.login("Leo", 2, true);
        GM.getPlayerComplexLobby("Cole").deckRequest(Mage.MAGE2, "Leo");

        Game newGame = GM.getComplexLobbies().get(0).getGame();
        GameComponents gameComponents = newGame.generateBoard();

        newGame.moveMotherNature(3, gameComponents.getMotherNature(), gameComponents.getArchipelago());
        assertEquals(3, gameComponents.getMotherNature().getPosition().getId_island());
        assertEquals(12, gameComponents.getArchipelago().size());
        Tower tower1 = new Tower(ColorTower.BLACK);
        Tower tower2 = new Tower(ColorTower.BLACK);
        gameComponents.getArchipelago().get(2).setTower(tower1);
        gameComponents.getArchipelago().get(3).setTower(tower2);
        newGame.mergeIsland();
        assertEquals(11, gameComponents .getArchipelago().size());
    }
    @Test
    public void testMergePrev() {
        GameManager GM = new GameManager();

        GM.login("Cole", 2, true);
        GM.getPlayerComplexLobby("Cole").deckRequest(Mage.MAGE1, "Cole");
        GM.login("Leo", 2, true);
        GM.getPlayerComplexLobby("Cole").deckRequest(Mage.MAGE2, "Leo");

        Game game = GM.getComplexLobbies().get(0).getGame();
        GameComponents gameComponents = game.generateBoard();

        game.moveMotherNature(2, gameComponents.getMotherNature(), gameComponents.getArchipelago());
        assertEquals(2, gameComponents.getMotherNature().getPosition().getId_island());
        assertEquals(12, gameComponents.getArchipelago().size());
        Tower tower1 = new Tower(ColorTower.BLACK);
        Tower tower2 = new Tower(ColorTower.BLACK);
        gameComponents.getArchipelago().get(2).setTower(tower1);
        gameComponents.getArchipelago().get(3).setTower(tower2);
        game.mergeIsland();
        assertEquals(11, gameComponents .getArchipelago().size());
    }
    @Test
    public void testPrevAndNext() {
        GameManager GM = new GameManager();

        GM.login("Cole", 2, true);
        GM.getPlayerComplexLobby("Cole").deckRequest(Mage.MAGE1, "Cole");
        GM.login("Leo", 2, true);
        GM.getPlayerComplexLobby("Cole").deckRequest(Mage.MAGE2, "Leo");

        Game game = GM.getComplexLobbies().get(0).getGame();
        GameComponents gameComponents = game.generateBoard();

        game.moveMotherNature(3, gameComponents.getMotherNature(), gameComponents.getArchipelago());
        assertEquals(3, gameComponents.getMotherNature().getPosition().getId_island());
        assertEquals(12, gameComponents.getArchipelago().size());
        Tower tower1 = new Tower(ColorTower.BLACK);
        Tower tower2 = new Tower(ColorTower.BLACK);
        Tower tower3 = new Tower(ColorTower.BLACK);
        gameComponents.getArchipelago().get(2).setTower(tower1);
        gameComponents.getArchipelago().get(3).setTower(tower2);
        gameComponents.getArchipelago().get(4).setTower(tower2);
        game.mergeIsland();
        assertEquals(10, gameComponents .getArchipelago().size());
        game.getGameComponents().printArchipelago();
    }

    @Test
    public void cornerCase() {
        GameManager GM = new GameManager();

        GM.login("Cole", 2, true);
        GM.getPlayerComplexLobby("Cole").deckRequest(Mage.MAGE1, "Cole");
        GM.login("Leo", 2, true);
        GM.getPlayerComplexLobby("Cole").deckRequest(Mage.MAGE2, "Leo");

        Game game = GM.getComplexLobbies().get(0).getGame();
        GameComponents gameComponents = game.generateBoard();

        game.moveMotherNature(11, gameComponents.getMotherNature(), gameComponents.getArchipelago());
        assertEquals(11, gameComponents.getMotherNature().getPosition().getId_island());
        assertEquals(12, gameComponents.getArchipelago().size());
        Tower tower1 = new Tower(ColorTower.BLACK);
        Tower tower2 = new Tower(ColorTower.BLACK);
        gameComponents.getArchipelago().get(0).setTower(tower1);
        gameComponents.getArchipelago().get(11).setTower(tower2);
        game.mergeIsland();

        assertEquals(11, gameComponents .getArchipelago().size());
    }
    @Test
    public void sequentialMerging() {
        GameManager GM = new GameManager();

        GM.login("Cole", 2, true);
        GM.getPlayerComplexLobby("Cole").deckRequest(Mage.MAGE1, "Cole");
        GM.login("Leo", 2, true);
        GM.getPlayerComplexLobby("Cole").deckRequest(Mage.MAGE2, "Leo");

        Game game = GM.getComplexLobbies().get(0).getGame();
        GameComponents gameComponents = game.generateBoard();

        game.moveMotherNature(11, gameComponents.getMotherNature(), gameComponents.getArchipelago());
        assertEquals(11, gameComponents.getMotherNature().getPosition().getId_island());
        assertEquals(12, gameComponents.getArchipelago().size());
        Tower tower1 = new Tower(ColorTower.BLACK);
        Tower tower2 = new Tower(ColorTower.BLACK);
        gameComponents.getArchipelago().get(0).setTower(tower1);
        gameComponents.getArchipelago().get(11).setTower(tower2);
        game.mergeIsland();
        assertEquals(11, gameComponents .getArchipelago().size());

        game.moveMotherNature(3, gameComponents.getMotherNature(), gameComponents.getArchipelago());
        assertEquals(2, gameComponents.getMotherNature().getPosition().getId_island());
        assertEquals(11, gameComponents.getArchipelago().size());
        Tower tower6 = new Tower(ColorTower.BLACK);
        Tower tower7 = new Tower(ColorTower.BLACK);
        gameComponents.getArchipelago().get(2).setTower(tower7);
        gameComponents.getArchipelago().get(3).setTower(tower6);
        game.mergeIsland();

        for(IslandCard islandCard : gameComponents.getArchipelago().get(3).getMergedWith()){
            System.out.println("island: " + islandCard.getId_island());
        }

        assertEquals(10, gameComponents .getArchipelago().size());
    }

    @Test
    public void multiSequentialMerging() {
        GameManager GM = new GameManager();

        GM.login("Cole", 2, true);
        GM.getPlayerComplexLobby("Cole").deckRequest(Mage.MAGE1, "Cole");
        GM.login("Leo", 2, true);
        GM.getPlayerComplexLobby("Cole").deckRequest(Mage.MAGE2, "Leo");

        Game game = GM.getComplexLobbies().get(0).getGame();
        GameComponents gameComponents = game.generateBoard();

        game.moveMotherNature(11, gameComponents.getMotherNature(), gameComponents.getArchipelago());
        assertEquals(11, gameComponents.getMotherNature().getPosition().getId_island());
        assertEquals(12, gameComponents.getArchipelago().size());
        Tower tower1 = new Tower(ColorTower.BLACK);
        Tower tower2 = new Tower(ColorTower.BLACK);
        gameComponents.getArchipelago().get(0).setTower(tower1);
        gameComponents.getArchipelago().get(11).setTower(tower2);
        game.mergeIsland();
        assertEquals(11, gameComponents .getArchipelago().size());

        game.moveMotherNature(3, gameComponents.getMotherNature(), gameComponents.getArchipelago());
        assertEquals(2, gameComponents.getMotherNature().getPosition().getId_island());
        assertEquals(11, gameComponents.getArchipelago().size());
        Tower tower6 = new Tower(ColorTower.BLACK);
        Tower tower7 = new Tower(ColorTower.BLACK);
        gameComponents.getArchipelago().get(2).setTower(tower7);
        gameComponents.getArchipelago().get(3).setTower(tower6);
        game.mergeIsland();
        assertEquals(10, gameComponents .getArchipelago().size());

        game.moveMotherNature(8, gameComponents.getMotherNature(), gameComponents.getArchipelago());
        assertEquals(0, gameComponents.getMotherNature().getPosition().getId_island());
        assertEquals(10, gameComponents.getArchipelago().size());
        Tower tower8 = new Tower(ColorTower.BLACK);
        gameComponents.getArchipelago().get(0).setTower(tower7);
        game.mergeIsland();
        assertEquals(9, gameComponents .getArchipelago().size());

        game.moveMotherNature(1, gameComponents.getMotherNature(), gameComponents.getArchipelago());
        assertEquals(1, gameComponents.getMotherNature().getPosition().getId_island());
        assertEquals(9, gameComponents.getArchipelago().size());
        Tower tower9 = new Tower(ColorTower.BLACK);
        gameComponents.getArchipelago().get(1).setTower(tower9);
        game.mergeIsland();
        assertEquals(7, gameComponents .getArchipelago().size());
    }


    @Test
    public void getMergedIsland() {
        GameManager GM = new GameManager();
        ArrayList<Student> students1 = new ArrayList<>();
        students1.add(new Student(ColorStudent.BLUE));
        students1.add(new Student(ColorStudent.YELLOW));
        ArrayList<Student> students2 = new ArrayList<>();
        students2.add(new Student(ColorStudent.GREEN));
        ArrayList<Student> students3 = new ArrayList<>();
        students3.add(new Student(ColorStudent.RED));
        ArrayList<Student> students4 = new ArrayList<>();
        students4.add(new Student(ColorStudent.PINK));

        GM.login("Cole", 2, true);
        GM.getPlayerComplexLobby("Cole").deckRequest(Mage.MAGE1, "Cole");
        GM.login("Leo", 2, true);
        GM.getPlayerComplexLobby("Cole").deckRequest(Mage.MAGE2, "Leo");

        Game game = GM.getComplexLobbies().get(0).getGame();
        GameComponents gameComponents = game.generateBoard();

        game.moveMotherNature(1, gameComponents.getMotherNature(), gameComponents.getArchipelago());
        assertEquals(1, gameComponents.getMotherNature().getPosition().getId_island());
        assertEquals(12, gameComponents.getArchipelago().size());
        Tower tower1 = new Tower(ColorTower.BLACK);
        Tower tower2 = new Tower(ColorTower.BLACK);
        gameComponents.getArchipelago().get(1).setTower(tower1);
        gameComponents.getArchipelago().get(1).setStudents(students1);
        gameComponents.getArchipelago().get(2).setTower(tower2);
        gameComponents.getArchipelago().get(2).setStudents(students2);
        game.mergeIsland();
        assertEquals(11, gameComponents .getArchipelago().size());

        for(IslandCard islandCard : gameComponents.getArchipelago().get(1).getMergedWith()){
            System.out.println("island: " + islandCard + "students: " + islandCard.getStudents() + gameComponents.getArchipelago().get(1).getStudents());
        }
        System.out.println("");

        game.moveMotherNature(1, gameComponents.getMotherNature(), gameComponents.getArchipelago());
        assertEquals(2, gameComponents.getMotherNature().getPosition().getId_island());
        assertEquals(11, gameComponents.getArchipelago().size());
        Tower tower3 = new Tower(ColorTower.BLACK);

        gameComponents.getArchipelago().get(2).setTower(tower3);
        gameComponents.getArchipelago().get(2).setStudents(students3);
        game.mergeIsland();

        for(IslandCard islandCard : gameComponents.getArchipelago().get(1).getMergedWith()){
            System.out.println("island: " + islandCard + "students: " + islandCard.getStudents());
        }
        System.out.println("");

        assertEquals(10, gameComponents .getArchipelago().size());

        game.moveMotherNature(1, gameComponents.getMotherNature(), gameComponents.getArchipelago());
        assertEquals(2, gameComponents.getMotherNature().getPosition().getId_island());
        assertEquals(10, gameComponents.getArchipelago().size());
        Tower tower4 = new Tower(ColorTower.BLACK);

        gameComponents.getArchipelago().get(2).setTower(tower4);
        gameComponents.getArchipelago().get(2).setStudents(students4);
        game.mergeIsland();

        for(IslandCard islandCard : gameComponents.getArchipelago().get(1).getMergedWith()){
            System.out.println("island: " + islandCard + "students: " + islandCard.getStudents());
        }
        System.out.println("");

        assertEquals(9, gameComponents .getArchipelago().size());

    }
}


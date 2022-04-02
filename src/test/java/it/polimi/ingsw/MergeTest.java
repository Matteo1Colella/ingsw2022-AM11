package it.polimi.ingsw;

import it.polimi.ingsw.model.ColorTower;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.board.GameComponents;
import it.polimi.ingsw.model.pieces.Tower;
import static org.junit.Assert.*;
import org.junit.Test;

public class MergeTest {
    @Test
    public void TestMergeNext() {
        Game game = new Game(false, 0, 2);
        GameComponents gameComponents = game.generateBoard2players(false, 2);
        game.moveMotherNature(3, gameComponents.getMothernature(), gameComponents.getArchipelago());
        assertEquals(3, gameComponents.getMothernature().getPosition().getId_island());
        assertEquals(12, gameComponents.getArchipelago().size());
        Tower tower1 = new Tower(ColorTower.BLACK);
        Tower tower2 = new Tower(ColorTower.BLACK);
        gameComponents.getArchipelago().get(2).setTower(tower1);
        gameComponents.getArchipelago().get(3).setTower(tower2);
        game.mergeIsland();
        assertEquals(11, gameComponents .getArchipelago().size());
    }
    @Test
    public void testMergePrev() {
        Game game = new Game(false, 0, 2);
        GameComponents gameComponents = game.generateBoard2players(false, 2);
        game.moveMotherNature(2, gameComponents.getMothernature(), gameComponents.getArchipelago());
        assertEquals(2, gameComponents.getMothernature().getPosition().getId_island());
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
        Game game = new Game(false, 0, 2);
        GameComponents gameComponents = game.generateBoard2players(false, 2);
        game.moveMotherNature(3, gameComponents.getMothernature(), gameComponents.getArchipelago());
        assertEquals(3, gameComponents.getMothernature().getPosition().getId_island());
        assertEquals(12, gameComponents.getArchipelago().size());
        Tower tower1 = new Tower(ColorTower.BLACK);
        Tower tower2 = new Tower(ColorTower.BLACK);
        Tower tower3 = new Tower(ColorTower.BLACK);
        gameComponents.getArchipelago().get(2).setTower(tower1);
        gameComponents.getArchipelago().get(3).setTower(tower2);
        gameComponents.getArchipelago().get(4).setTower(tower2);
        game.mergeIsland();
        assertEquals(10, gameComponents .getArchipelago().size());
    }

    @Test
    public void cornerCase() {
        Game game = new Game(false, 0, 2);
        GameComponents gameComponents = game.generateBoard2players(false, 2);
        game.moveMotherNature(11, gameComponents.getMothernature(), gameComponents.getArchipelago());
        assertEquals(11, gameComponents.getMothernature().getPosition().getId_island());
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
        Game game = new Game(false, 0, 2);
        GameComponents gameComponents = game.generateBoard2players(false, 2);
        game.moveMotherNature(11, gameComponents.getMothernature(), gameComponents.getArchipelago());
        assertEquals(11, gameComponents.getMothernature().getPosition().getId_island());
        assertEquals(12, gameComponents.getArchipelago().size());
        Tower tower1 = new Tower(ColorTower.BLACK);
        Tower tower2 = new Tower(ColorTower.BLACK);
        gameComponents.getArchipelago().get(0).setTower(tower1);
        gameComponents.getArchipelago().get(11).setTower(tower2);
        game.mergeIsland();
        assertEquals(11, gameComponents .getArchipelago().size());

        game.moveMotherNature(3, gameComponents.getMothernature(), gameComponents.getArchipelago());
        assertEquals(2, gameComponents.getMothernature().getPosition().getId_island());
        assertEquals(11, gameComponents.getArchipelago().size());
        Tower tower6 = new Tower(ColorTower.BLACK);
        Tower tower7 = new Tower(ColorTower.BLACK);
        gameComponents.getArchipelago().get(2).setTower(tower7);
        gameComponents.getArchipelago().get(3).setTower(tower6);
        game.mergeIsland();
        assertEquals(10, gameComponents .getArchipelago().size());
    }

    @Test
    public void multiSequentialMerging() {
        Game game = new Game(false, 0, 2);
        GameComponents gameComponents = game.generateBoard2players(false, 2);
        game.moveMotherNature(11, gameComponents.getMothernature(), gameComponents.getArchipelago());
        assertEquals(11, gameComponents.getMothernature().getPosition().getId_island());
        assertEquals(12, gameComponents.getArchipelago().size());
        Tower tower1 = new Tower(ColorTower.BLACK);
        Tower tower2 = new Tower(ColorTower.BLACK);
        gameComponents.getArchipelago().get(0).setTower(tower1);
        gameComponents.getArchipelago().get(11).setTower(tower2);
        game.mergeIsland();
        assertEquals(11, gameComponents .getArchipelago().size());

        game.moveMotherNature(3, gameComponents.getMothernature(), gameComponents.getArchipelago());
        assertEquals(2, gameComponents.getMothernature().getPosition().getId_island());
        assertEquals(11, gameComponents.getArchipelago().size());
        Tower tower6 = new Tower(ColorTower.BLACK);
        Tower tower7 = new Tower(ColorTower.BLACK);
        gameComponents.getArchipelago().get(2).setTower(tower7);
        gameComponents.getArchipelago().get(3).setTower(tower6);
        game.mergeIsland();
        assertEquals(10, gameComponents .getArchipelago().size());

        game.moveMotherNature(8, gameComponents.getMothernature(), gameComponents.getArchipelago());
        assertEquals(0, gameComponents.getMothernature().getPosition().getId_island());
        assertEquals(10, gameComponents.getArchipelago().size());
        Tower tower8 = new Tower(ColorTower.BLACK);
        gameComponents.getArchipelago().get(0).setTower(tower7);
        game.mergeIsland();
        assertEquals(9, gameComponents .getArchipelago().size());

        game.moveMotherNature(1, gameComponents.getMothernature(), gameComponents.getArchipelago());
        assertEquals(1, gameComponents.getMothernature().getPosition().getId_island());
        assertEquals(9, gameComponents.getArchipelago().size());
        Tower tower9 = new Tower(ColorTower.BLACK);
        gameComponents.getArchipelago().get(1).setTower(tower9);
        game.mergeIsland();
        assertEquals(7, gameComponents .getArchipelago().size());
    }
}

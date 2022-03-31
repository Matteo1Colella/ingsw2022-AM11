package it.polimi.ingsw;

import it.polimi.ingsw.model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

public class GameMethodsTest {
    @Test
    public void moveMotherNatureTest() {
        Game game = new Game(false,1,2);
        GameComponents components = game.generateBoard2players(false);
        //motherNature starts at the position: ID_island 1 (the island with the index 0 in the ArrayList
        game.moveMotherNature(3, components.getMothernature(), (ArrayList<IslandCard>) components.getArchipelago());
        assertEquals(4,components.getMothernature().getPosition().getId_island());
    }
}

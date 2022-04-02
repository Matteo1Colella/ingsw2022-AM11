package it.polimi.ingsw;

import it.polimi.ingsw.controller.ComplexLobby;
import it.polimi.ingsw.model.board.GameComponents;
import it.polimi.ingsw.model.board.IslandCard;
import it.polimi.ingsw.model.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GameMethodsTest {
    @Test
    public void moveMotherNatureTest() {
        Game game = new Game(false,1,2);
        GameComponents components = game.generateBoard2players(false, 2);
        //motherNature starts at the position: ID_island 1 (the island with the index 0 in the ArrayList
        game.moveMotherNature(15, components.getMothernature(), (ArrayList<IslandCard>) components.getArchipelago());
        assertEquals(3,components.getMothernature().getPosition().getId_island());
    }
}

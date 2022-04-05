package it.polimi.ingsw;

import it.polimi.ingsw.controller.ComplexLobby;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
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
        game.setGameStructure(game.generateBoard(false, 2));
        assertEquals(2,game.getGameComponents().getSchoolBoards().size());

        game.getGameComponents().getSchoolBoards().get(0).getTowers().clear();
        assertEquals(0,game.getGameComponents().getSchoolBoards().get(0).getTowers().size());

        assertEquals(player1, game.winCondition());
    }


}

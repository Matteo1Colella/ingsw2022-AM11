package it.polimi.ingsw;

import it.polimi.ingsw.controller.ComplexLobby;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class generateBoardTest {
    @Test
    public void generateBoard3Player(){
        Player player1 = new Player(1, "leo");
        Player player2 = new Player(2, "cole");
        Player player3 = new Player(3, "ale");
        ArrayList<Player> players = new ArrayList<>();

        players.add(player1);
        players.add(player2);
        players.add(player3);

        ComplexLobby complexLobby = new ComplexLobby(3, false, 0, players);
        complexLobby.CreateGame(3, 0, true);
        Game game = complexLobby.getGame();

        game.generateBoard(false, 3);

        assertNotNull(player1.getSchoolBoard());
        assertNotNull(player2.getSchoolBoard());
        assertNotNull(player3.getSchoolBoard());
    }
}

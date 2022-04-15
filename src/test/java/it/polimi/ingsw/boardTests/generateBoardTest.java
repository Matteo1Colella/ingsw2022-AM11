package it.polimi.ingsw.boardTests;

import it.polimi.ingsw.controller.ComplexLobby;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class generateBoardTest {
    @Test
    public void generateBoard2Player(){
        Player player1 = new Player(1, "leo");
        Player player2 = new Player(2, "cole");
        ArrayList<Player> players = new ArrayList<>();

        players.add(player1);
        players.add(player2);

        ComplexLobby complexLobby = new ComplexLobby(2, false, 0, players);
        complexLobby.CreateGame(2, 0, false);
        Game game = complexLobby.getGame();

        game.generateBoard();

        assertNotNull(player1.getSchoolBoard());
        assertNotNull(player2.getSchoolBoard());
        assertNotNull(game.getGameComponents().getArchipelago());
        assertNotNull(game.getGameComponents().getMotherNature());
        assertNotNull(game.getGameComponents().getBag());
        assertNotNull(game.getGameComponents().getSchoolBoards());
        assertNotNull(game.getGameComponents().getCloudCards());
    }

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
        complexLobby.CreateGame(3, 0, false);
        Game game = complexLobby.getGame();

        game.generateBoard();

        assertNotNull(player1.getSchoolBoard());
        assertNotNull(player2.getSchoolBoard());
        assertNotNull(player3.getSchoolBoard());
        assertNotNull(game.getGameComponents().getArchipelago());
        assertNotNull(game.getGameComponents().getMotherNature());
        assertNotNull(game.getGameComponents().getBag());
        assertNotNull(game.getGameComponents().getSchoolBoards());
    }

    @Test
    public void generateProBoard2Player(){
        Player player1 = new Player(1, "leo");
        Player player2 = new Player(2, "cole");
        ArrayList<Player> players = new ArrayList<>();

        players.add(player1);
        players.add(player2);

        ComplexLobby complexLobby = new ComplexLobby(2, true, 0, players);
        complexLobby.CreateGame(2, 0, true);
        Game game = complexLobby.getGame();

        game.generateBoard();

        assertNotNull(player1.getSchoolBoard());
        assertNotNull(player2.getSchoolBoard());
        assertNotNull(game.getGameComponents().getArchipelago());
        assertNotNull(game.getGameComponents().getMotherNature());
        assertNotNull(game.getGameComponents().getBag());
        assertNotNull(game.getGameComponents().getSchoolBoards());
        assertNotNull(game.getGameComponents().getCloudCards());
    }

}
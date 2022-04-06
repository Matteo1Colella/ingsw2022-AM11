package it.polimi.ingsw;

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
        complexLobby.CreateGame(2, 0, true);
        Game game = complexLobby.getGame();

        game.generateBoard(false, 2);

        assertNotNull(player1.getSchoolBoard());
        assertNotNull(player2.getSchoolBoard());
        assertNotNull(game.getGameComponents().getArchipelago());
        assertNotNull(game.getGameComponents().getMothernature());
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
        complexLobby.CreateGame(3, 0, true);
        Game game = complexLobby.getGame();

        game.generateBoard(false, 3);

        assertNotNull(player1.getSchoolBoard());
        assertNotNull(player2.getSchoolBoard());
        assertNotNull(player3.getSchoolBoard());
        assertNotNull(game.getGameComponents().getArchipelago());
        assertNotNull(game.getGameComponents().getMothernature());
        assertNotNull(game.getGameComponents().getBag());
        assertNotNull(game.getGameComponents().getSchoolBoards());
    }

    @Test
    public void generateBoard2PlayerPro(){
        Player player1 = new Player(1, "leo");
        Player player2 = new Player(2, "cole");
        ArrayList<Player> players = new ArrayList<>();

        players.add(player1);
        players.add(player2);

        ComplexLobby complexLobby = new ComplexLobby(2, false, 0, players);
        complexLobby.CreateGame(2, 0, true);
        Game game = complexLobby.getGame();

        game.generateBoard(false, 2);

        assertNotNull(player1.getSchoolBoard());
        assertNotNull(player2.getSchoolBoard());
        assertNotNull(game.getGameComponents().getArchipelago());
        assertNotNull(game.getGameComponents().getMothernature());
        assertNotNull(game.getGameComponents().getBag());
        assertNotNull(game.getGameComponents().getSchoolBoards());

    }

    @Test
    public void generateBoard4Player(){
        Player player1 = new Player(1, "leo");
        Player player2 = new Player(2, "cole");
        Player player3 = new Player(3, "ale");
        Player player4 = new Player(3, "guise");
        ArrayList<Player> players = new ArrayList<>();

        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);

        ComplexLobby complexLobby = new ComplexLobby(4, false, 0, players);
        complexLobby.CreateGame(4, 0, true);
        Game game = complexLobby.getGame();

        game.generateBoard(false, 4);

        assertNotNull(player1.getSchoolBoard());
        assertNotNull(player2.getSchoolBoard());
        assertNotNull(player3.getSchoolBoard());
        assertEquals(0,player3.getSchoolBoard().getTowers().size());
    }

}
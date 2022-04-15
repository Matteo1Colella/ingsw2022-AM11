package it.polimi.ingsw.gameTests;

import it.polimi.ingsw.controller.ComplexLobby;
import it.polimi.ingsw.controller.GameManager;
import it.polimi.ingsw.model.board.GameComponents;
import it.polimi.ingsw.model.board.IslandCard;
import it.polimi.ingsw.model.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GameMethodsTest {
    @Test
    public void moveMotherNatureTest() {
        GameManager GM = new GameManager();

        GM.login("Cole", 2, true);
        GM.deckRequest(GM.getPlayerComplexLobby("Cole").getID(), Mage.MAGE1, "Cole");
        GM.login("Leo", 2, true);
        GM.deckRequest(GM.getPlayerComplexLobby("Leo").getID(), Mage.MAGE2, "Leo");

        Game game = GM.getComplexLobbies().get(0).getGame();
        GameComponents gameComponents = game.generateBoard();

        //motherNature starts at the position: ID_island 1 (the island with the index 0 in the ArrayList
        game.moveMotherNature(15, game.getGameComponents().getMotherNature(), (ArrayList<IslandCard>) game.getGameComponents().getArchipelago());
        assertEquals(3,game.getGameComponents().getMotherNature().getPosition().getId_island());
    }

    @Test
    public void otherMethodsGameClass(){
        Player player1 = new Player(1, "leo");
        Player player2 = new Player(2, "cole");
        ArrayList<Player> players = new ArrayList<>();

        players.add(player1);
        players.add(player2);

        ComplexLobby complexLobby = new ComplexLobby(2, false, 0, players);
        complexLobby.CreateGame(2, 0, false);
        Game game = complexLobby.getGame();

        game.generateBoard();
        assertNotNull(game.startGameWithRandomPlayer());
        assertNotNull(game.getComplexLobby());
        game.setStatus(Status.ONLINE);
        assertNotNull(game.getStatus());
        game.setPro(false);
        assertNotNull(game.isPro());


    }



}

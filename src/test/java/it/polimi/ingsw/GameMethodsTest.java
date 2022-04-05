package it.polimi.ingsw;

import it.polimi.ingsw.controller.ComplexLobby;
import it.polimi.ingsw.controller.GameManager;
import it.polimi.ingsw.model.board.GameComponents;
import it.polimi.ingsw.model.board.IslandCard;
import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.cards.AssistantDeck;
import it.polimi.ingsw.model.cards.Card;
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
        GameComponents gameComponents = game.generateBoard(false, 2);

        //motherNature starts at the position: ID_island 1 (the island with the index 0 in the ArrayList
        game.moveMotherNature(15, game.getGameComponents().getMothernature(), (ArrayList<IslandCard>) game.getGameComponents().getArchipelago());
        assertEquals(3,game.getGameComponents().getMothernature().getPosition().getId_island());
    }

    @Test
    public void addChosenCardTest() {
        Game game = new Game(false,1,3);
        Card first = new Card("cat",5,1,false,Mage.MAGE1);
        Card second = new Card("dog",4,2,false,Mage.MAGE1);
        Card third = new Card("hippo",3,3,false,Mage.MAGE1);
        game.addChosenCard(first,3);
        assertEquals(1,game.getChosenCards().size());
        game.addChosenCard(first, 3); //prints: can't play this card
        assertEquals(1,game.getChosenCards().size());
        game.addChosenCard(second,3);
        game.addChosenCard(second,3); //prints: can't play this card
        game.addChosenCard(third,3);
        assertEquals(3,game.getChosenCards().size());
        game.addChosenCard(third,3);
        assertEquals(1,game.getChosenCards().size()); //At this moment begins a new round
    }


}

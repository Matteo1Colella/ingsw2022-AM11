package it.polimi.ingsw;

import it.polimi.ingsw.controller.GameManager;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Mage;
import it.polimi.ingsw.model.board.GameComponents;
import it.polimi.ingsw.model.cards.CharacterCard;
import it.polimi.ingsw.model.cards.CharacterDeck;
import it.polimi.ingsw.model.cards.Characters.Character1;
import it.polimi.ingsw.model.cards.Characters.Character2;
import it.polimi.ingsw.model.cards.Characters.Character3;
import it.polimi.ingsw.model.pieces.Student;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class CharacterTests {
    @Test
    public void deckCreationTest() {
        GameManager GM = new GameManager();

        GM.login("Cole", 2, true);
        GM.deckRequest(GM.getPlayerComplexLobby("Cole").getID(), Mage.MAGE1, "Cole");
        GM.login("Leo", 2, true);
        GM.deckRequest(GM.getPlayerComplexLobby("Leo").getID(), Mage.MAGE2, "Leo");

        Game newGame = GM.getComplexLobbies().get(0).getGame();
        GameComponents gameComponents = newGame.generateBoard(true, 2);
        newGame.getGameComponents().getSpecialDeck().getCards().stream().map(CharacterCard::getNum).forEach(System.out::println);

        assertEquals(newGame.getGameComponents().getSpecialDeck().getCards().size(), 3);

    }
    @Test
    public void Effect1Test() {
        GameManager GM = new GameManager();

        GM.login("Cole", 2, true);
        GM.deckRequest(GM.getPlayerComplexLobby("Cole").getID(), Mage.MAGE1, "Cole");
        GM.login("Leo", 2, true);
        GM.deckRequest(GM.getPlayerComplexLobby("Leo").getID(), Mage.MAGE2, "Leo");

        Game newGame = GM.getComplexLobbies().get(0).getGame();
        GameComponents gameComponents = newGame.generateBoard(true, 2);

        ArrayList<CharacterCard> cards = new ArrayList<>();

        CharacterCard card1 = new Character1(1);
        for(int j = 0; j < 4; j++){
            card1.addSudent(newGame.getGameComponents().getBag().draw());
        }
        cards.add(card1);

        CharacterCard card2 = new Character2(2);
        cards.add(card2);

        CharacterCard card3 = new Character3(3);
        cards.add(card3);

        newGame.getGameComponents().getSpecialDeck().setCards(cards);
        newGame.getGameComponents().getArchipelago().get(1).getStudents().stream().map(Student::getColor).forEach(System.out::println);

        //lauching character1 effect
        newGame.getGameComponents().getSpecialDeck().getcard(1).effect(GM.getPlayerComplexLobby("Cole").getPlayers().get(0), newGame.getGameComponents().getArchipelago().get(1), 1);

        assertEquals(newGame.getGameComponents().getArchipelago().get(1).getStudents().size(), 2);
    }
    @Test
    public void Effect2Test() {
        GameManager GM = new GameManager();

        GM.login("Cole", 2, true);
        GM.deckRequest(GM.getPlayerComplexLobby("Cole").getID(), Mage.MAGE1, "Cole");
        GM.login("Leo", 2, true);
        GM.deckRequest(GM.getPlayerComplexLobby("Leo").getID(), Mage.MAGE2, "Leo");

        Game newGame = GM.getComplexLobbies().get(0).getGame();
        GameComponents gameComponents = newGame.generateBoard(true, 2);

        ArrayList<CharacterCard> cards = new ArrayList<>();

        CharacterCard card2 = new Character2(2);
        cards.add(card2);

        newGame.getGameComponents().getSpecialDeck().setCards(cards);

        newGame.getGameComponents().getSpecialDeck().getcard(2).effect(GM.getPlayerComplexLobby("Cole").getPlayers().get(0));
    }
    @Test
    public void Effect3Test() {
        GameManager GM = new GameManager();

        GM.login("Cole", 2, true);
        GM.deckRequest(GM.getPlayerComplexLobby("Cole").getID(), Mage.MAGE1, "Cole");
        GM.login("Leo", 2, true);
        GM.deckRequest(GM.getPlayerComplexLobby("Leo").getID(), Mage.MAGE2, "Leo");

        Game newGame = GM.getComplexLobbies().get(0).getGame();
        GameComponents gameComponents = newGame.generateBoard(true, 2);

        ArrayList<CharacterCard> cards = new ArrayList<>();

        CharacterCard card3 = new Character3(3);
        assertNotEquals(null, card3);

        cards.add(card3);

        newGame.getGameComponents().getSpecialDeck().setCards(cards);

        newGame.getGameComponents().getSpecialDeck().getcard(3).effect(GM.getPlayerComplexLobby("Cole").getPlayers().get(0), newGame.getGameComponents().getArchipelago().get(0));


    }
}

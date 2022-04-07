package it.polimi.ingsw;

import it.polimi.ingsw.controller.GameManager;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Mage;
import it.polimi.ingsw.model.board.GameComponents;
import it.polimi.ingsw.model.cards.CharacterCard;
import it.polimi.ingsw.model.cards.CharacterDeck;
import it.polimi.ingsw.model.cards.Characters.*;
import it.polimi.ingsw.model.colors.ColorStudent;
import it.polimi.ingsw.model.pieces.NoEntryTile;
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
    @Test
    public void Effect4Test() {
        GameManager GM = new GameManager();

        GM.login("Cole", 2, true);
        GM.deckRequest(GM.getPlayerComplexLobby("Cole").getID(), Mage.MAGE1, "Cole");
        GM.login("Leo", 2, true);
        GM.deckRequest(GM.getPlayerComplexLobby("Leo").getID(), Mage.MAGE2, "Leo");

        Game newGame = GM.getComplexLobbies().get(0).getGame();
        GameComponents gameComponents = newGame.generateBoard(true, 2);

        ArrayList<CharacterCard> cards = new ArrayList<>();

        CharacterCard card4 = new Character4(4);
        assertNotEquals(null, card4);

        cards.add(card4);

        newGame.getGameComponents().getSpecialDeck().setCards(cards);

        newGame.getGameComponents().getSpecialDeck().getcard(4).effect(GM.getPlayerComplexLobby("Cole").getPlayers().get(0));
        assertEquals(GM.getPlayerComplexLobby("Cole").getPlayers().get(0).getMotherNatureMoves(), 2);

    }

    @Test
    public void Effect5Test() {
        GameManager GM = new GameManager();

        GM.login("Cole", 2, true);
        GM.deckRequest(GM.getPlayerComplexLobby("Cole").getID(), Mage.MAGE1, "Cole");
        GM.login("Leo", 2, true);
        GM.deckRequest(GM.getPlayerComplexLobby("Leo").getID(), Mage.MAGE2, "Leo");

        Game newGame = GM.getComplexLobbies().get(0).getGame();
        GameComponents gameComponents = newGame.generateBoard(true, 2);

        ArrayList<CharacterCard> cards = new ArrayList<>();

        CharacterCard card5 = new Character5(5);
        assertNotEquals(null, card5);

        cards.add(card5);
        for(int j = 0; j < 4; j++){
            NoEntryTile tile = new NoEntryTile(false, null);
            card5.addTile(tile);

        }

        newGame.getGameComponents().getSpecialDeck().setCards(cards);

        newGame.getGameComponents().getSpecialDeck().getcard(5).effect(GM.getPlayerComplexLobby("Cole").getPlayers().get(0), newGame.getGameComponents().getArchipelago().get(0));

        assertTrue(newGame.getGameComponents().getArchipelago().get(0).getLocked());


    }
    @Test
    public void Effect6Test() {
        GameManager GM = new GameManager();

        GM.login("Cole", 2, true);
        GM.deckRequest(GM.getPlayerComplexLobby("Cole").getID(), Mage.MAGE1, "Cole");
        GM.login("Leo", 2, true);
        GM.deckRequest(GM.getPlayerComplexLobby("Leo").getID(), Mage.MAGE2, "Leo");

        Game newGame = GM.getComplexLobbies().get(0).getGame();
        GameComponents gameComponents = newGame.generateBoard(true, 2);

        ArrayList<CharacterCard> cards = new ArrayList<>();

        CharacterCard card6 = new Character6(6);
        assertNotEquals(null, card6);

        cards.add(card6);

        newGame.getGameComponents().getSpecialDeck().setCards(cards);

        newGame.getGameComponents().getSpecialDeck().getcard(6).effect(GM.getPlayerComplexLobby("Cole").getPlayers().get(0), newGame.getGameComponents().getArchipelago().get(0));
        assertTrue(newGame.isNoTower());
    }
    @Test
    public void Effect7Test() {
        GameManager GM = new GameManager();

        GM.login("Cole", 2, true);
        GM.deckRequest(GM.getPlayerComplexLobby("Cole").getID(), Mage.MAGE1, "Cole");
        GM.login("Leo", 2, true);
        GM.deckRequest(GM.getPlayerComplexLobby("Leo").getID(), Mage.MAGE2, "Leo");

        Game newGame = GM.getComplexLobbies().get(0).getGame();
        GameComponents gameComponents = newGame.generateBoard(true, 2);

        ArrayList<CharacterCard> cards = new ArrayList<>();

        CharacterCard card7 = new Character7(7);
        assertNotEquals(null, card7);
        int[] chosen1 = {0, 0, 0};
        for (int i = 0; i < 3; i++){
            Student s = new Student(ColorStudent.RED);
            chosen1[i]=i;
            card7.addSudent(s);

        }

        int[] chosen2 = {0, 0, 0};
        for (int i = 0; i < 3; i++){
            Student s = new Student(ColorStudent.BLU);
            chosen2[i]=i+7;
            GM.getPlayerComplexLobby("Cole").getPlayers().get(0).getSchoolBoard().getEntrance().addStudent(s);
        }

        cards.add(card7);
        System.out.println("pre function:");
        System.out.println("Schoolboard:");
        GM.getPlayerComplexLobby("Cole").getPlayers().get(0).getSchoolBoard().getEntrance().getStudents().stream().map(Student::getColor).forEach(System.out::println);
        System.out.println(" ");
        System.out.println("Card:");
        card7.getStudents().stream().map(Student::getColor).forEach(System.out::println);
        System.out.println(" ");

        newGame.getGameComponents().getSpecialDeck().setCards(cards);

        newGame.getGameComponents().getSpecialDeck().getcard(7).effect(GM.getPlayerComplexLobby("Cole").getPlayers().get(0), chosen2, chosen1);
        System.out.println("post function:");
        System.out.println("Schoolboard:");
        GM.getPlayerComplexLobby("Cole").getPlayers().get(0).getSchoolBoard().getEntrance().getStudents().stream().map(Student::getColor).forEach(System.out::println);
        System.out.println(" ");
        System.out.println("Card:");
        card7.getStudents().stream().map(Student::getColor).forEach(System.out::println);

        for (int i = 0; i < 3; i++) {
            assertEquals(GM.getPlayerComplexLobby("Cole").getPlayers().get(0).getSchoolBoard().getEntrance().getStudents().get(i+7).getColor(), ColorStudent.RED );
        }
        for (int i = 0; i < 3; i++) {
            assertEquals(card7.getStudents().get(i).getColor(), ColorStudent.BLU );
        }
    }
    @Test
    public void Effect8Test() {
        GameManager GM = new GameManager();

        GM.login("Cole", 2, true);
        GM.deckRequest(GM.getPlayerComplexLobby("Cole").getID(), Mage.MAGE1, "Cole");
        GM.login("Leo", 2, true);
        GM.deckRequest(GM.getPlayerComplexLobby("Leo").getID(), Mage.MAGE2, "Leo");

        Game newGame = GM.getComplexLobbies().get(0).getGame();
        GameComponents gameComponents = newGame.generateBoard(true, 2);

        ArrayList<CharacterCard> cards = new ArrayList<>();

        CharacterCard card8 = new Character8(8);
        assertNotEquals(null, card8);

        cards.add(card8);

        newGame.getGameComponents().getSpecialDeck().setCards(cards);

        newGame.getGameComponents().getSpecialDeck().getcard(8).effect(GM.getPlayerComplexLobby("Cole").getPlayers().get(0));
        assertEquals(2, GM.getPlayerComplexLobby("Cole").getPlayers().get(0).getInfluencePoints());
    }
    @Test
    public void Effect9Test() {
        GameManager GM = new GameManager();

        GM.login("Cole", 2, true);
        GM.deckRequest(GM.getPlayerComplexLobby("Cole").getID(), Mage.MAGE1, "Cole");
        GM.login("Leo", 2, true);
        GM.deckRequest(GM.getPlayerComplexLobby("Leo").getID(), Mage.MAGE2, "Leo");

        Game newGame = GM.getComplexLobbies().get(0).getGame();
        GameComponents gameComponents = newGame.generateBoard(true, 2);

        ArrayList<CharacterCard> cards = new ArrayList<>();

        CharacterCard card9 = new Character9(9);
        cards.add(card9);

        newGame.getGameComponents().getSpecialDeck().setCards(cards);

        newGame.getGameComponents().getSpecialDeck().getcard(9).effect(GM.getPlayerComplexLobby("Cole").getPlayers().get(0), ColorStudent.RED);

        assertEquals(ColorStudent.RED, GM.getPlayerComplexLobby("Cole").getGame().getExcludedColor());
    }
}

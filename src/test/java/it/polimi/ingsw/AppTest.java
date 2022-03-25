package it.polimi.ingsw;

import it.polimi.ingsw.model.*;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;


/**
 * Unit test for simple App.
 */
public class AppTest 
{

    @Test
    public void TestSameID(){
        ArrayList<PlayersManager> Lobbies = new ArrayList<PlayersManager>();
        GameManager GM = new GameManager(Lobbies);
        boolean added = GM.login("Cole", 3, true);
        assertTrue("not added first player", added);
        added = GM.login("Leo", 3, true);
        assertTrue("not added first player", added);
        added = GM.login("Cole", 3, true);
        assertFalse("added duplicate player Cole", added);

        // bastard phase:
        added = GM.login("Cole1", 3, true);
        assertTrue("added Cole", added);

        added = GM.login("Cole2", 3, true);
        assertTrue("added duplicate player Cole", added);

        added = GM.login("Cole3", 3, true);
        assertTrue("added duplicate player Cole", added);

        int nOFLobbies = Lobbies.size();
        assertTrue("Lobbies must be 2 but are " + nOFLobbies, nOFLobbies == 2);
    }
    @Test
    public void TestFullLobby(){
        ArrayList<PlayersManager> Lobbies = new ArrayList<PlayersManager>();
        GameManager GM = new GameManager(Lobbies);
        GM.login("Cole", 3, true);
        GM.login("Leo", 3, true);
        GM.login("Ale", 3, true);
        GM.login("Gian", 3, true);
    }


    @Test
    public void sameDeck(){
        DeckManager deckManager = new DeckManager();
        Mage mage1 = Mage.MAGE1;
        AssistantDeck assistantDeck1;
        AssistantDeck assistantDeck2;

        assistantDeck1 = deckManager.generateDeck(mage1);
        assistantDeck2 = deckManager.generateDeck(mage1);

        assertNull(assistantDeck2);
        assertNotNull(assistantDeck1);

    }

    @Test
    public void multipleDeckAsk(){
        DeckManager deckManager = new DeckManager();
        Mage mage1 = Mage.MAGE1;
        Mage mage2 = Mage.MAGE2;
        Mage mage3 = Mage.MAGE3;
        Mage mage4 = Mage.MAGE4;

        AssistantDeck assistantDeck1;
        AssistantDeck assistantDeck2;
        AssistantDeck assistantDeck3;
        AssistantDeck assistantDeck4;

        assistantDeck1 = deckManager.generateDeck(mage1);
        assistantDeck2 = deckManager.generateDeck(mage2);
        assistantDeck3 = deckManager.generateDeck(mage3);
        assistantDeck4 = deckManager.generateDeck(mage4);

        assertNotNull(assistantDeck1);
        assertNotNull(assistantDeck2);
        assertNotNull(assistantDeck3);
        assertNotNull(assistantDeck4);

    }

    //SchoolBoard test
    @Test
    public void schoolBoardCreation(){
        ArrayList<SchoolBoard> schoolBoard = new ArrayList<>();
        ColorTower[] colors =  ColorTower.values();
        Collection<Student> students = new ArrayList<>();
        int numberOfPlayers = 3;
        Student student = new Student();

        students.add(student);

        schoolBoard.add(new SchoolBoard(colors[0], numberOfPlayers, students));
        schoolBoard.add(new SchoolBoard(colors[1], numberOfPlayers, students));

        assertNotNull(schoolBoard.get(0));
        assertNotNull(schoolBoard.get(1));
    }

    //checking if the colors of the towers ar correct for each player
    @Test
    public void schoolBoardColors(){
        ArrayList<SchoolBoard> schoolBoard = new ArrayList<>();
        ColorTower[] colors =  ColorTower.values();
        Collection<Student> students = new ArrayList<>();
        int numberOfPlayers = 3;
        Student student = new Student();
        ColorTower black = ColorTower.BLACK;
        ColorTower white = ColorTower.WHITE;
        ColorTower grey = ColorTower.GREY;

        students.add(student);

        schoolBoard.add(new SchoolBoard(colors[0], numberOfPlayers, students));
        schoolBoard.add(new SchoolBoard(colors[1], numberOfPlayers, students));
        schoolBoard.add(new SchoolBoard(colors[2], numberOfPlayers, students));

        assertEquals(schoolBoard.get(0).schoolBoardTowerColor(), black);
        assertEquals(schoolBoard.get(1).schoolBoardTowerColor(), white);
        assertEquals(schoolBoard.get(2).schoolBoardTowerColor(), grey);
    }

    //move tower test with 2 players
    @Test
    public void moveTowerFromSchoolBoard1(){
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student());
        IslandCard islandCard = new IslandCard();

        SchoolBoard schoolBoard= new SchoolBoard(ColorTower.BLACK, 2, students);

        schoolBoard.moveTower(islandCard);
        int towerSize = schoolBoard.getTowers().size();

        assertEquals(7, towerSize);
    }

    //move tower test with 3 players
    @Test
    public void moveTowerFromSchoolBoard2(){
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student());
        IslandCard islandCard = new IslandCard();

        SchoolBoard schoolBoard = new SchoolBoard(ColorTower.BLACK, 3, students);

        schoolBoard.moveTower(islandCard);
        int towerSize = schoolBoard.getTowers().size();

        assertEquals(5, towerSize);
    }

    //moove 9 tower: the lastes doesn't cause anything
    @Test
    public void move9Towers(){
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student());
        IslandCard islandCard = new IslandCard();

        SchoolBoard schoolBoard = new SchoolBoard(ColorTower.BLACK, 3, students);

        for(int i = 0; i < 9; i++){
            schoolBoard.moveTower(islandCard);
        }

        int towerSize = schoolBoard.getTowers().size();
        assertEquals(0, towerSize);
    }

    //move a student on an island or dashboard
    //CANT TESTED UNTIL SOMEONE WRITES STUDENT
    @Test
    public void movingStudent(){

    }
}


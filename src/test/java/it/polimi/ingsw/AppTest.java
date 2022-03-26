package it.polimi.ingsw;

import it.polimi.ingsw.model.*;

import it.polimi.ingsw.model.Components.*;
import org.junit.Test;


import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

import java.util.Iterator;

import static org.junit.Assert.*;



/**
 * Unit test for simple App.
 */
public class AppTest {
    // testing lobbies functionalities
    @Test
    public void TestSameID() {
        ArrayList<PlayersManager> Lobbies = new ArrayList<PlayersManager>();
        GameManager GM = new GameManager(Lobbies);
        boolean added = GM.login("Cole", 3, true);
        assertTrue("not added first player", added);
        added = GM.login("Leo", 3, true);
        assertTrue("not added player", added);
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
    public void TestFullLobby() {
        ArrayList<PlayersManager> Lobbies = new ArrayList<PlayersManager>();
        GameManager GM = new GameManager(Lobbies);
        GM.login("Cole", 3, true);
        GM.login("Leo", 3, true);
        GM.login("Ale", 3, true);
        GM.login("Gian", 3, true);
    }

    @Test
    // testing deck requests
    public void TestLoginAndDeck() {
        ArrayList<PlayersManager> Lobbies = new ArrayList<PlayersManager>();
        GameManager GM = new GameManager(Lobbies);

        boolean added = GM.login("Cole", 3, true);
        assertTrue("not added first player", added);

        boolean decksuccess = GM.deckRequest(GM.getPlayerLobby("Cole").getID(), Mage.MAGE1, "Cole");
        assertTrue("not added deck", decksuccess);

        added = GM.login("Leo", 3, true);
        assertTrue("not added player", added);

        decksuccess = GM.deckRequest(GM.getPlayerLobby("Leo").getID(), Mage.MAGE2, "Leo");
        assertTrue("not added deck", decksuccess);

        added = GM.login("Ale", 3, true);
        assertTrue("not added player", added);

        decksuccess = GM.deckRequest(GM.getPlayerLobby("Ale").getID(), Mage.MAGE3, "Ale");
        assertTrue("not added deck", decksuccess);

    }

    @Test
    public void TestLoginAndDeckMultipleLobbies() {
        ArrayList<PlayersManager> Lobbies = new ArrayList<PlayersManager>();
        GameManager GM = new GameManager(Lobbies);

        boolean added = GM.login("Cole", 3, true);
        assertTrue("not added first player", added);

        boolean decksuccess = GM.deckRequest(GM.getPlayerLobby("Cole").getID(), Mage.MAGE1, "Cole");
        assertTrue("not added deck", decksuccess);

        added = GM.login("Leo", 3, true);
        assertTrue("not added player", added);

        decksuccess = GM.deckRequest(GM.getPlayerLobby("Leo").getID(), Mage.MAGE2, "Leo");
        assertTrue("not added deck", decksuccess);

        added = GM.login("Ale", 3, true);
        assertTrue("not added player", added);

        decksuccess = GM.deckRequest(GM.getPlayerLobby("Ale").getID(), Mage.MAGE3, "Ale");
        assertTrue("not added deck", decksuccess);

        added = GM.login("Gian", 3, true);
        assertTrue("not added player", added);

        decksuccess = GM.deckRequest(GM.getPlayerLobby("Gian").getID(), Mage.MAGE1, "Gian");
        assertTrue("not added deck", decksuccess);

        added = GM.login("Pore", 3, true);
        assertTrue("not added player", added);

        decksuccess = GM.deckRequest(GM.getPlayerLobby("Pore").getID(), Mage.MAGE2, "Pore");
        assertTrue("not added deck", decksuccess);

    }

    @Test
    public void TestLoginAndDuplicateDeck() {
        ArrayList<PlayersManager> Lobbies = new ArrayList<PlayersManager>();
        GameManager GM = new GameManager(Lobbies);

        boolean added = GM.login("Cole", 3, true);
        assertTrue("not added first player", added);

        boolean decksuccess = GM.deckRequest(0, Mage.MAGE1, "Cole");
        assertTrue("not added deck", decksuccess);

        added = GM.login("Leo", 3, true);
        assertTrue("not added player", added);

        decksuccess = GM.deckRequest(0, Mage.MAGE1, "Leo");
        assertFalse("added deck", decksuccess);

        added = GM.login("Ale", 3, true);
        assertTrue("not added player", added);

        decksuccess = GM.deckRequest(0, Mage.MAGE3, "Ale");
        assertTrue("not added deck", decksuccess);

    }

    // testing deck functionalities
    @Test
    public void sameDeck() {
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
    public void multipleDeckAsk() {
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


    @Test
    public void testArchipelago() {
        //create Archipelago
        Archipelago islands = new Archipelago();
        islands.createCircularLinkedList();
        islands.printIslands(); //prints the 12 ID_Islands
        //motherNature in first island
        MotherNature motherNature = new MotherNature(islands.next());
        System.out.println(motherNature.getPosition().getId_island());
        int test = motherNature.getPosition().getId_island();
        assertEquals(1, test); //motherNature on the island: ID=1 (at the beginning of a game)
    }

        @Test
        public void tryPrintAnArray () {
            ArrayList<Student> studenti;
            Student first = new Student(ColorStudent.YELLOW);
            first.setColor(ColorStudent.YELLOW);
            studenti = new ArrayList<Student>();
            studenti.add(first);
            for (int i = 0; i < studenti.size(); i++) {
                System.out.println(studenti.get(i).getColor());
            }
        }

    @Test
    public void TestPrintIslands() {
        //trying with circular array
        CircularArrayList<IslandCard> islandsCircularArray = new CircularArrayList<>(12);

        //creation of tower container
        ArrayList<Tower> towersBlack = new ArrayList<Tower>();
        ArrayList<Tower> towersWhite = new ArrayList<Tower>();

        towersBlack.add(new Tower(ColorTower.BLACK)); //repeat 8 times for blacks
        towersBlack.add(new Tower(ColorTower.BLACK));
        towersBlack.add(new Tower(ColorTower.BLACK));
        towersBlack.add(new Tower(ColorTower.BLACK));
        towersBlack.add(new Tower(ColorTower.BLACK));
        towersBlack.add(new Tower(ColorTower.BLACK));
        towersBlack.add(new Tower(ColorTower.BLACK));
        towersBlack.add(new Tower(ColorTower.BLACK));
        towersWhite.add(new Tower(ColorTower.WHITE)); //repeat 8 times for whites
        towersWhite.add(new Tower(ColorTower.WHITE));
        towersWhite.add(new Tower(ColorTower.WHITE));
        towersWhite.add(new Tower(ColorTower.WHITE));
        towersWhite.add(new Tower(ColorTower.WHITE));
        towersWhite.add(new Tower(ColorTower.WHITE));
        towersWhite.add(new Tower(ColorTower.WHITE));
        towersWhite.add(new Tower(ColorTower.WHITE));

        //creation of students container
        ArrayList<Student> green = new ArrayList<>();
        ArrayList<Student> yellow = new ArrayList<>();
        ArrayList<Student> red = new ArrayList<>();
        ArrayList<Student> pink = new ArrayList<>();
        ArrayList<Student> blue = new ArrayList<>();

        //adding 26 students for each container
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));






        //creating 12 islands
        Collection<Student> studentsIsland1 = new ArrayList<Student>(); //new Array for the students in the island
        studentsIsland1.add(yellow.get(0)); //adding 1 student
        Collection<Student> studentsIsland2 = new ArrayList<Student>();
        studentsIsland1.add(yellow.get(1));


        IslandCard island1 = new IslandCard(1, studentsIsland1,null, null,true,null,false);
        IslandCard island2 = new IslandCard(2, studentsIsland2 ,null,null,false,null,null);


        //adding 12 islands to circularArray
        islandsCircularArray.add(island1);
        islandsCircularArray.add(island2);


        assertTrue("false because there isn't motherNature in the first island",islandsCircularArray.get(0).getMotherNature());
        assertFalse(islandsCircularArray.get(1).getMotherNature());

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


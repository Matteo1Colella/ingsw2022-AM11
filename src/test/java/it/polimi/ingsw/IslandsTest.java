package it.polimi.ingsw;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.Components.Archipelago;
import it.polimi.ingsw.model.Components.CircularArrayList;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

public class IslandsTest {
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

        //repeat 8 times for black
        for(int i = 0; i < 8; i++){
            towersBlack.add(new Tower(ColorTower.BLACK));
        }

        //repeat 8 times for whites
        for(int i = 0; i < 8; i++){
            towersBlack.add(new Tower(ColorTower.WHITE));
        }

        //creation of students container
        ArrayList<Student> green = new ArrayList<>();
        ArrayList<Student> yellow = new ArrayList<>();
        ArrayList<Student> red = new ArrayList<>();
        ArrayList<Student> pink = new ArrayList<>();
        ArrayList<Student> blue = new ArrayList<>();

        //adding 26 students for each container
        for(int i = 0; i < 26; i++){
            yellow.add(new Student(ColorStudent.YELLOW));
            green.add(new Student(ColorStudent.GREEN));
            red.add(new Student(ColorStudent.RED));
        }

        //creating 12 islands
        Collection<Student> studentsIsland1 = new ArrayList<Student>(); //new Array for the students in the island
        studentsIsland1.add(yellow.get(0)); //adding 1 student
        Collection<Student> studentsIsland2 = new ArrayList<Student>();
        studentsIsland1.add(yellow.get(1));

        IslandCard island1 = new IslandCard(1 ,null);
        IslandCard island2 = new IslandCard(2, null);

        //adding 12 islands to circularArray
        islandsCircularArray.add(island1);
        islandsCircularArray.add(island2);

        assertTrue("false because there isn't motherNature in the first island", islandsCircularArray.get(0).getMotherNature());
        assertFalse(islandsCircularArray.get(1).getMotherNature());
    }

    @Test
    public void addStudentsToIsland(){
        Collection<Student> students= new ArrayList<>();
        Student student = new Student(ColorStudent.BLU);
        Player player = new Player(1, "leo");
        IslandCard islandCard = new IslandCard(1, player);

        students.add(student);
        islandCard.setStudents(students);

        assertNotNull(islandCard.getStudents());
    }

}

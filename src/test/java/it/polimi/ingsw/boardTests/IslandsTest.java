package it.polimi.ingsw.boardTests;

import it.polimi.ingsw.model.colors.ColorStudent;
import it.polimi.ingsw.model.colors.ColorTower;
import it.polimi.ingsw.model.pieces.Student;
import it.polimi.ingsw.model.pieces.Tower;
import it.polimi.ingsw.model.board.IslandCard;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class IslandsTest {


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
        ArrayList<IslandCard> islandsCircularArray = new ArrayList<>(12);

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




        IslandCard island1 = new IslandCard(1);
        IslandCard island2 = new IslandCard(2);
        island1.setMotherNature(true);



        //adding 12 islands to circularArray
        islandsCircularArray.add(island1);
        islandsCircularArray.add(island2);

        assertTrue("false because there isn't motherNature in the first island", islandsCircularArray.get(0).getMotherNature());
        assertFalse(islandsCircularArray.get(1).getMotherNature());
    }


    @Test
    public void testRemove() {
        ArrayList<Student> yellow = new ArrayList<>();
        yellow.add(new Student(ColorStudent.YELLOW));
        yellow.add(new Student(ColorStudent.YELLOW));
        yellow.remove(0);
        System.out.println(yellow.size());
    }

    @Test
    public void randomDraw() {
        ArrayList<Student> initialBag = new ArrayList<>();
        //creation of students container
        ArrayList<Student> green = new ArrayList<>();
        ArrayList<Student> yellow = new ArrayList<>();
        ArrayList<Student> red = new ArrayList<>();
        ArrayList<Student> pink = new ArrayList<>();
        ArrayList<Student> blu = new ArrayList<>();
        ArrayList<Student> studentsIsland1 = new ArrayList<Student>(); //new Array for the students in the island
        ArrayList<Student> studentsIsland2 = new ArrayList<Student>();
        ArrayList<Student> studentsIsland3 = new ArrayList<Student>();
        ArrayList<Student> studentsIsland4 = new ArrayList<Student>();
        ArrayList<Student> studentsIsland5 = new ArrayList<Student>();
        ArrayList<Student> studentsIsland6 = new ArrayList<Student>();
        ArrayList<Student> studentsIsland7 = new ArrayList<Student>();
        ArrayList<Student> studentsIsland8 = new ArrayList<Student>();
        ArrayList<Student> studentsIsland9 = new ArrayList<Student>();
        ArrayList<Student> studentsIsland10 = new ArrayList<Student>();
        ArrayList<Student> studentsIsland11 = new ArrayList<Student>();
        ArrayList<Student> studentsIsland12 = new ArrayList<Student>();

        //adding 26 students for each container
        for(int i=0;i<26;i++){
            yellow.add(new Student(ColorStudent.YELLOW));
            green.add(new Student(ColorStudent.GREEN));
            red.add(new Student(ColorStudent.RED));
            pink.add(new Student(ColorStudent.PINK));
            blu.add(new Student(ColorStudent.BLUE));
        }
        //random choice
        for(int i=0; i<2; i++){
            initialBag.add(yellow.get(i));
            initialBag.add(red.get(i));
            initialBag.add(green.get(i));
            initialBag.add(pink.get(i));
            initialBag.add(blu.get(i));
            yellow.remove(i);
            red.remove(i);
            green.remove(i);
            pink.remove(i);
            blu.remove(i);
        }
        for(int i=0;i<initialBag.size();i++)
        System.out.println(initialBag.get(i).getColor());
        //adding students (we have to implement random choice)

            int index = (int) (Math.random() * initialBag.size());
            studentsIsland2.add(initialBag.get(index)); //adding 1 student
            initialBag.remove(index);

            index = (int) (Math.random() * initialBag.size());
            studentsIsland3.add(initialBag.get(index));
            initialBag.remove(index);

            index = (int) (Math.random() * initialBag.size());
            studentsIsland4.add(initialBag.get(index));
            initialBag.remove(index);

            index = (int) (Math.random() * initialBag.size());
            studentsIsland5.add(initialBag.get(index));
            initialBag.remove(index);

            index = (int) (Math.random() * initialBag.size());
            studentsIsland6.add(initialBag.get(index));
            initialBag.remove(index);

            index = (int) (Math.random() * initialBag.size());
            studentsIsland8.add(initialBag.get(index));
            initialBag.remove(index);

            index = (int) (Math.random() * initialBag.size());
            studentsIsland9.add(initialBag.get(index));
            initialBag.remove(index);

            index = (int) (Math.random() * initialBag.size());
            studentsIsland10.add(initialBag.get(index));
            initialBag.remove(index);

            index = (int) (Math.random() * initialBag.size());
            studentsIsland11.add(initialBag.get(index));
            initialBag.remove(index);

            index = (int) (Math.random() * initialBag.size());
            studentsIsland12.add(initialBag.get(index));
            initialBag.remove(index);


        System.out.println("\n");
        System.out.println("\n");
        System.out.println("");

                System.out.println(studentsIsland2.get(0).getColor());
                System.out.println(studentsIsland3.get(0).getColor());
                System.out.println(studentsIsland4.get(0).getColor());
                System.out.println(studentsIsland5.get(0).getColor());
                System.out.println(studentsIsland6.get(0).getColor());
                System.out.println(studentsIsland8.get(0).getColor());
                System.out.println(studentsIsland9.get(0).getColor());
                System.out.println(studentsIsland10.get(0).getColor());
                System.out.println(studentsIsland11.get(0).getColor());
                System.out.println(studentsIsland12.get(0).getColor());

    }

     

    


}

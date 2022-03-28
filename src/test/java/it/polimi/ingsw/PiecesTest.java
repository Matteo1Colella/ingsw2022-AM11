package it.polimi.ingsw;

import it.polimi.ingsw.model.*;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PiecesTest {
    //move tower test with 2 players
    @Test
    public void moveTowerFromSchoolBoard1(){
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student(ColorStudent.RED));
        IslandCard islandCard = new IslandCard(0, null, null, null, null, null, null);

        SchoolBoard schoolBoard= new SchoolBoard(ColorTower.BLACK, 2, students);

        schoolBoard.moveTower(islandCard);
        int towerSize = schoolBoard.getTowers().size();

        assertEquals(7, towerSize);
    }

    //move tower test with 3 players
    @Test
    public void moveTowerFromSchoolBoard2(){
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student(ColorStudent.RED));
        IslandCard islandCard = new IslandCard(0, null, null, null, null, null, null);

        SchoolBoard schoolBoard = new SchoolBoard(ColorTower.BLACK, 3, students);

        schoolBoard.moveTower(islandCard);
        int towerSize = schoolBoard.getTowers().size();

        assertEquals(5, towerSize);
    }

    //moove 9 tower: the lastes doesn't cause anything
    @Test
    public void move9Towers(){
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student(ColorStudent.RED));
        IslandCard islandCard = new IslandCard(0, null, null, null, null, null, null);

        SchoolBoard schoolBoard = new SchoolBoard(ColorTower.BLACK, 3, students);

        for(int i = 0; i < 9; i++){
            schoolBoard.moveTower(islandCard);
        }

        int towerSize = schoolBoard.getTowers().size();
        assertEquals(0, towerSize);
    }

}

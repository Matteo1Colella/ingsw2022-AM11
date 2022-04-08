package it.polimi.ingsw.piecesTests;

import it.polimi.ingsw.model.colors.ColorStudent;
import it.polimi.ingsw.model.colors.ColorTower;
import it.polimi.ingsw.model.pieces.Professor;
import it.polimi.ingsw.model.pieces.Student;
import it.polimi.ingsw.model.board.IslandCard;
import it.polimi.ingsw.model.board.SchoolBoard;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PiecesTest {
    //move tower test with 2 players
    @Test
    public void moveTowerFromSchoolBoard1(){
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student(ColorStudent.RED));
        IslandCard islandCard = new IslandCard(0);

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
        IslandCard islandCard = new IslandCard(0);

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
        IslandCard islandCard = new IslandCard(0);

        SchoolBoard schoolBoard = new SchoolBoard(ColorTower.BLACK, 3, students);

        for(int i = 0; i < 9; i++){
            schoolBoard.moveTower(islandCard);
        }

        int towerSize = schoolBoard.getTowers().size();
        assertEquals(0, towerSize);
    }

    @Test
    public void professorPosition(){
        ArrayList<Student> students = new ArrayList<>();
        Student student = new Student(ColorStudent.BLU);
        students.add(student);
        SchoolBoard schoolBoard = new SchoolBoard(ColorTower.BLACK, 2, students);
        Professor professor = new Professor(ColorStudent.RED);

        schoolBoard.setProfessor(professor);

        assertNotNull("the professor's position is null", professor.getPosition());
    }

    @Test
    public void setStudentPosition(){
        Student student = new Student(ColorStudent.BLU);
        IslandCard islandCard = new IslandCard(1);

        student.setPosition(islandCard);

        assertNotNull("la positzione dello student è null", student.getPosition());
    }
}

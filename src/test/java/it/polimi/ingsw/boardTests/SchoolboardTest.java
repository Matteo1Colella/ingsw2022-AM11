package it.polimi.ingsw.boardTests;

import it.polimi.ingsw.model.board.IslandCard;
import it.polimi.ingsw.model.colors.ColorStudent;
import it.polimi.ingsw.model.colors.ColorTower;
import it.polimi.ingsw.model.pieces.Student;
import it.polimi.ingsw.model.board.SchoolBoard;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SchoolboardTest {
    //SchoolBoard test
    @Test
    public void schoolBoardCreation(){
        ArrayList<SchoolBoard> schoolBoard = new ArrayList<>();
        ColorTower[] colors =  ColorTower.values();
        Collection<Student> students = new ArrayList<>();
        int numberOfPlayers = 3;
        Student student = new Student(ColorStudent.RED);

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
        Student student = new Student(ColorStudent.RED);
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

    @Test
    public void addStudentToEntrance(){
        ArrayList<Student> students = new ArrayList<>();
        for(int i = 0; i < 7; i ++ ){
            students.add(new Student(ColorStudent.RED));
        }
        SchoolBoard schoolBoard = new SchoolBoard(ColorTower.BLACK, 2, students);

        Student movedStudent1 = schoolBoard.chooseStudentFromEntrance(students.get(0));
        Student movedStudent2 = schoolBoard.chooseStudentFromEntrance(students.get(1));
        Student movedStudent3 = schoolBoard.chooseStudentFromEntrance(students.get(2));

        schoolBoard.moveStudent(movedStudent1);
        schoolBoard.moveStudent(movedStudent2);
        schoolBoard.moveStudent(movedStudent3);

        ArrayList<Student> addingStudents = new ArrayList<>();
        for(int i = 0; i < 3; i ++ ){
            addingStudents.add(new Student(ColorStudent.PINK));
        }

        schoolBoard.addStudetsToEntrance(addingStudents);
        assertEquals(schoolBoard.getEntrance().getStudents().size(), 7);
    }
    @Test
    public void noAddStudentToEntrance(){
        ArrayList<Student> students = new ArrayList<>();
        for(int i = 0; i < 7; i ++ ){
            students.add(new Student(ColorStudent.RED));
        }
        SchoolBoard schoolBoard = new SchoolBoard(ColorTower.BLACK, 2, students);

        Student movedStudent1 = schoolBoard.chooseStudentFromEntrance(students.get(0));
        Student movedStudent2 = schoolBoard.chooseStudentFromEntrance(students.get(1));
        Student movedStudent3 = schoolBoard.chooseStudentFromEntrance(students.get(2));

        schoolBoard.moveStudent(movedStudent1);
        schoolBoard.moveStudent(movedStudent2);
        schoolBoard.moveStudent(movedStudent3);

        ArrayList<Student> addingStudents = new ArrayList<>();
        for(int i = 0; i < 7; i ++ ){
            addingStudents.add(new Student(ColorStudent.PINK));
        }

        schoolBoard.addStudetsToEntrance(addingStudents);
        assertEquals(schoolBoard.getEntrance().getStudents().size(), 4);
    }

    @Test
    public void moveStudentToIsland(){
        ArrayList<Student> students = new ArrayList<>();
        for(int i = 0; i < 7; i ++ ){
            students.add(new Student(ColorStudent.RED));
        }
        SchoolBoard schoolBoard = new SchoolBoard(ColorTower.BLACK, 2, students);

        IslandCard islandCard = new IslandCard(1);

        Student movedStudent1 = schoolBoard.chooseStudentFromEntrance(students.get(0));

        schoolBoard.moveStudent(movedStudent1, islandCard);


        assertEquals(islandCard.getStudents().get(0), movedStudent1);
        assertEquals(schoolBoard.getEntrance().getStudents().size(), 6);
    }

    @Test
    public void moveMultipleStudentToIsland(){
        ArrayList<Student> students = new ArrayList<>();
        for(int i = 0; i < 7; i ++ ){
            students.add(new Student(ColorStudent.RED));
        }
        SchoolBoard schoolBoard = new SchoolBoard(ColorTower.BLACK, 2, students);

        IslandCard islandCard = new IslandCard(1);

        ArrayList<Student> studentsOnIsland = new ArrayList<>();
        for(int i = 0; i < 2; i++){
            Student movedStudent = schoolBoard.chooseStudentFromEntrance(students.get(i));
            studentsOnIsland.add(movedStudent);
            schoolBoard.moveStudent(studentsOnIsland.get(i), islandCard);
        }

        schoolBoard.printSchoolBoard();
        schoolBoard.getStudents();

        assertEquals(islandCard.getStudents(), studentsOnIsland);
        assertEquals(schoolBoard.getEntrance().getStudents().size(), 5);
    }
}

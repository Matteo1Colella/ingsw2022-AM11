package it.polimi.ingsw;

import it.polimi.ingsw.model.pieces.Student;
import it.polimi.ingsw.model.board.SchoolBoard;
import it.polimi.ingsw.model.*;
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
}

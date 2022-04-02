package it.polimi.ingsw;


import it.polimi.ingsw.model.board.Bag;
import it.polimi.ingsw.model.pieces.Student;
import org.junit.Test;

import static org.junit.Assert.*;

public class BagTest {
    @Test
    public void draw1Student(){
        Bag bag = new Bag();
        Student student = bag.draw();
        int leftStudent = bag.left();

        assertNotNull(student);
        assertEquals(leftStudent, 119);
    }

    @Test
    public void drawAllStudent(){
        Bag bag = new Bag();
        for(int i = 0; i < 120; i++){
            Student student = bag.draw();
        }
        assertTrue(bag.isEmpty());
    }
}
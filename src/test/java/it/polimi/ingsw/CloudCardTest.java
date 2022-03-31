package it.polimi.ingsw;

import it.polimi.ingsw.model.*;

import it.polimi.ingsw.model.Components.*;
import org.junit.Test;


import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

import java.util.Iterator;

import static org.junit.Assert.*;

public class CloudCardTest {

    @Test
    public void getStudentsFromCloud() {
        Collection<Student> students = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            Student student = new Student(ColorStudent.GREEN);
            students.add(student);
        }

        CloudCard cloudCard = new CloudCard(1, students);

        Collection<Student> retStudents = cloudCard.getStudents();

        assertNotNull("students is null", retStudents);

    }
}

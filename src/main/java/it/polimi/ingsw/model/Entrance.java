package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Collection;

public class Entrance {
    private final ArrayList<Student> students;

    //the entrance is initialized with 7 students
    public Entrance(Collection<Student> students){
        this.students = new ArrayList<>();
        this.students.addAll(students);
    }
    //the caller have the control of the drawing of the students
    public void addStudents(Collection<Student> students){
        this.students.addAll(students);
    }

    public Student chooseStudent(int position){
        return students.get(position);
    }
}

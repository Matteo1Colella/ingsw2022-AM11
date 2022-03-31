package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Collection;

public class Entrance {

    private final ArrayList<Student> students;

    public ArrayList<Student> getStudents() {
        return students;
    }

    //the entrance is initialized with 7 students
    public Entrance(){
        this.students = new ArrayList<>();
    }
    //the caller have the control of the drawing of the students
    public void addStudents(Collection<Student> students){
        this.students.addAll(students);

    }

    public Student chooseStudent(int position){
        return students.get(position);
    }
}

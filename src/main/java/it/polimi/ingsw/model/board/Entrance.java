package it.polimi.ingsw.model.board;

import it.polimi.ingsw.model.pieces.Student;

import java.util.ArrayList;
import java.util.Collection;

public class Entrance {

    private ArrayList<Student> students;

    public ArrayList<Student> getStudents() {
        return students;
    }

    //the entrance is initialized with 7 students
    public Entrance(){
        this.students = new ArrayList<>();
    }

    //the caller have the control of the drawing of the students
    public void addStudents(Collection<Student> students){
        Collection<Student> addingStudents = new ArrayList<>(students);
        this.students.addAll(addingStudents);
    }

    public Student chooseStudent(Student student){
        students.remove(student);
        return student;
    }
}
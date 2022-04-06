package it.polimi.ingsw.model.board;

import it.polimi.ingsw.model.pieces.Student;

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
    public void addStudent(Student student){
        this.students.add(student);
    }
    public void removeStudent(Student student){
        this.students.remove(student);
    }
    public int getStudentPosition(Student student){
        return students.indexOf(student);
    }

    public Student chooseStudent(int position){
        Student retStudent = students.get(position);
        students.remove(retStudent);
        return retStudent;
    }
}
package it.polimi.ingsw.model.board;

import it.polimi.ingsw.model.pieces.Professor;
import it.polimi.ingsw.model.pieces.Student;
import it.polimi.ingsw.model.colors.ColorStudent;

import java.util.ArrayList;
import java.util.Collection;

public class DiningRoom implements Board {
    private final ColorStudent color;
    private ArrayList<Student> students; //array of occupations
    private Professor professor;
    private int coinsGiven;

    public DiningRoom(ColorStudent color){
        this.students = new ArrayList<>();
        this.color = color;
        this.professor = null;
    }

    public void addStudent(Student student){
        students.add(student);
    }

    //who will call this function have to control the number of the students with the same color for all Players
    public void setProfessor(Professor professor){
        this.professor = professor;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void removeProfessor(){
        this.professor = null;

    }

    public int getStudentsSize(){
        return students.size();
    }

    //says if it is possible to earn a coin
    public boolean giveCoin(){
        return students.size() % 3 == 0;
    }

    public ColorStudent getColor(){
        return this.color;
    }
    public boolean IsProfessor(){
        return this.professor != null;
    }
    @Override
    public Collection<Student> getStudents() {
        return students;
    }

}


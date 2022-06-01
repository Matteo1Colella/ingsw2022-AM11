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
    private boolean coinGiven;
    private int oldStudents;

    public DiningRoom(ColorStudent color){
        this.students = new ArrayList<>();
        this.color = color;
        this.professor = null;
    }
    public void removeStudent(Student student){
        this.students.remove(student);
    }

    /**
     * who will call this function have to control the number of the students with the same color for all Players
     * @param student
     */
    public void addStudent(Student student){
        int sizeBeforeAdding = getStudentsSize();
        if(student.getColor().equals(this.getColor())){
            students.add(student);
        }
        int sizeAfterAdding = getStudentsSize();
        if(!(sizeBeforeAdding % 3 == 0)){
            if(!(sizeAfterAdding % 3 == 0)){
                coinGiven = false;
            }
        }
    }

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

    /**
     * says if it is possible to earn a coin
     * @return
     */
    public boolean giveCoin(){

        if (oldStudents == students.size()){
            return false;
        }

        if((int)(oldStudents/3) != (int)(students.size()/3)){
            oldStudents = students.size();
            return true;
        }

        if(!students.isEmpty() && students.size() != 1 && students.size() != 2){
            if(students.size() % 3 == 0){
                oldStudents = students.size();
                return true;
            }
        }

        oldStudents = students.size();

        return false;
    }

    public ColorStudent getColor(){
        return this.color;
    }

    public boolean IsProfessor(){
        return this.professor != null;
    }

    @Override
    public ArrayList<Student> getStudents() {
        return students;
    }

}


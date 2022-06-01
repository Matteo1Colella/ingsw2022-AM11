package it.polimi.ingsw.model.board;

import it.polimi.ingsw.model.pieces.Student;

import java.util.ArrayList;
import java.util.Collection;

public class CloudCard implements Board{
    private final int id_cloud;
    private ArrayList<Student> students;

    public CloudCard(int id_cloud, Collection<Student> students) {

        this.students = new ArrayList<>();

        this.id_cloud = id_cloud;
        this.students.addAll(students);
    }

    /*
    public int getId_cloud() {
        return id_cloud;
    }
    */

    /**
     * fill the students' arraylist, the sise of the new array have to be exactly 3: the control on that is left to the caller
     * @param students
     */
    public void setStudents(ArrayList<Student> students){
        if(this.students.isEmpty()){
            this.students.addAll(students);
        }
    }

    /**
     * override of interface Board
     * @return students
     */

    @Override
    public ArrayList<Student> getStudents() {
       return this.students;
    }

    public ArrayList<Student> drawStudents(){
        ArrayList<Student> retStudents = new ArrayList<>(this.students);
        this.students.clear();
        return retStudents;
    }

    @Override
    public String toString() {
        StringBuilder students = new StringBuilder();
        for(Student s : this.students){
            students.append(s.toString()).append(" ");
        }

        return "" + students;
    }
}


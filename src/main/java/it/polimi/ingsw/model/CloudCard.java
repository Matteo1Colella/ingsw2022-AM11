package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Collection;

public class CloudCard implements Board{
    private final int id_cloud;
    private Collection<Student> students;

    public CloudCard(int id_cloud, Collection<Student> students) {

        this.students = new ArrayList<>();

        this.id_cloud = id_cloud;
        this.students.addAll(students);
    }

    public int getId_cloud() {
        return id_cloud;
    }

    //fill the students' arraylist
    //the sise of the new array have to be exactly 3: the control on that is left to the caller
    public void setStudents(Collection<Student> students){
        if(this.students.isEmpty()){
            this.students.addAll(students);
        }
    }

    //override of interface Board
    @Override
    public Collection<Student> getStudents() {
        Collection<Student> retStudents = new ArrayList<>(this.students);
        this.students = null;

        return retStudents;
    }

}


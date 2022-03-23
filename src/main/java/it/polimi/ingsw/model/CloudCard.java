package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Set;

public class CloudCard extends Board{
    private int id_cloud;
    private ArrayList<Student> students;

    public CloudCard(int id_cloud, ArrayList<Student> students) {
        this.id_cloud = id_cloud;
        this.students = students;
    }

    public int getId_cloud() {
        return id_cloud;
    }

    public void setId_cloud(int id_cloud) {
        this.id_cloud = id_cloud;
    }



    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public Set<Student> getStudent(){
        return null;
    }
}

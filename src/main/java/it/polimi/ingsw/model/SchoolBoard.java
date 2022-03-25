package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Collection;

public class SchoolBoard implements Board{

    private ArrayList<Tower> towers;
    private ArrayList<DiningRoom> Classes; //a class is an array of students
    private String ID_player;
    private Entrance entrance;

    public SchoolBoard(ArrayList<Tower> towers, ArrayList<DiningRoom> classes, String ID_player, Entrance entrance) {
        this.towers = towers;
        Classes = classes;
        this.ID_player = ID_player;
        this.entrance = entrance;
    }

    public ArrayList<Tower> getTowers() {
        return towers;
    }

    public void setTowers(ArrayList<Tower> towers) {
        this.towers = towers;
    }

    public ArrayList<DiningRoom> getClasses() {
        return Classes;
    }

    public void setClasses(ArrayList<DiningRoom> classes) {
        Classes = classes;
    }

    public String getID_player() {
        return ID_player;
    }

    public void setID_player(String ID_player) {
        this.ID_player = ID_player;
    }

    public Entrance getEntrance() {
        return entrance;
    }

    public void setEntrance(Entrance entrance) {
        this.entrance = entrance;
    }

    //override of interface Board
    @Override
    public Collection<Student> getStudents() {
        return null;
    }

    @Override
    public String getID() {
        return null;
    }
}

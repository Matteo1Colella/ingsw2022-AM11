package it.polimi.ingsw.model.board;

import it.polimi.ingsw.model.pieces.Student;
import it.polimi.ingsw.model.pieces.Tower;
import it.polimi.ingsw.model.Player;

import java.util.ArrayList;
import java.util.Collection;

public class IslandCard implements Board {
    private int id_island;
    private Collection<Student> students;
    private Tower tower;
    private Boolean motherNature;
    private ArrayList<IslandCard> mergedWith; //array of islands
    private Boolean locked;

    //start constructors, getters, setters
    public IslandCard(int id_island) {
        this.id_island = id_island;
        this.students = new ArrayList<>();
        this.tower = null;
        this.motherNature = false;
        this.mergedWith = new ArrayList<>();
        this.locked = false;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setId_island(int id_island) {
        this.id_island = id_island;
    }

    public int getId_island() {
        return id_island;
    }

    public Tower getTower() {
        return tower;
    }

    public void setTower(Tower tower) {
        this.tower = tower;
    }


    public Boolean getMotherNature() {
        return motherNature;
    }

    public void setMotherNature(Boolean motherNature) {
        this.motherNature = motherNature;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public boolean isLocked(){
        return locked;
    }

    public void setMergedWith(IslandCard islandCard){
        if(!mergedWith.contains(islandCard)){
            mergedWith.add(islandCard);
        }
    }

    public void addMergedWith(ArrayList<IslandCard> islands){
        this.mergedWith.addAll(islands);
    }

    public ArrayList<IslandCard> getMergedWith(){
        return new ArrayList<>(this.mergedWith);
    }

    public void setStudents(Collection<Student> students) {
        this.students.addAll(students);
    }

    //override of interface Board
    @Override
    public ArrayList<Student> getStudents() {
        return new ArrayList<>(this.students);
    }

    public void addStudent(Student student){
        this.students.add(student);
    }
}
package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Collection;

public class IslandCard implements Board {
    private final int id_island;
    private Collection<Student> students;
    private Tower tower;
    private final Player playerOwner;
    private Boolean motherNature;
    private Collection<IslandCard> mergedWith; //array of integers
    private Boolean locked;
    private IslandCard next;  //pointer of the next Island in the circularArrayList

    //start constructors, getters, setters
    public IslandCard(int id_island, Player playerOwner) {
        this.id_island = id_island;
        this.students = new ArrayList<>();
        this.tower = null;
        this.playerOwner = playerOwner;
        this.motherNature = false;
        this.mergedWith = new ArrayList<>();
        this.locked = null;
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

    public Player getPlayerOwner() {
        return playerOwner;
    }

    public Boolean getMotherNature() {
        return motherNature;
    }

    public void setMotherNature(Boolean motherNature) {
        this.motherNature = motherNature;
    }

    public boolean isMotherNature(){
        return motherNature;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public boolean isLocked(){
        return locked;
    }

    public IslandCard getNext() {
        return next;
    }

    public void setNext(IslandCard next) {
        this.next = next;
    }

    public boolean isMerged(){
        return mergedWith.size() != 0;
    }

    public void setMergedWith(IslandCard islandCard){
        if(!mergedWith.contains(islandCard)){
            mergedWith.add(islandCard);
        }
    }

    public void setStudents(Collection<Student> students) {
        this.students.addAll(students);
    }

    //override of interface Board
    @Override
    public Collection<Student> getStudents() {
        return new ArrayList<>(students);
    }

}

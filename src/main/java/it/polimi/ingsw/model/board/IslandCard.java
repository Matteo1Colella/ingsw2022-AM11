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
    private final Player playerOwner;
    private Boolean motherNature;
    private Collection<IslandCard> mergedWith; //array of islands
    private Boolean locked;

    //start constructors, getters, setters
    public IslandCard(int id_island) {
        this.id_island = id_island;
        this.students = new ArrayList<>();
        this.tower = null;
        this.playerOwner = null;
        this.motherNature = false;
        this.mergedWith = new ArrayList<>();
        this.locked = null;
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
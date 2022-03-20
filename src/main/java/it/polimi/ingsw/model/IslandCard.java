package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Set;

public class IslandCard extends Board{
    int ID_island;
    Set<Student> Occupation;
    Tower Tower;
    Player PlayerOwner;
    Boolean MotherNature;
    int[] mergedWith; //array of integers
    Boolean Locked;

    public int getID_island() {
        return ID_island;
    }

    public void setID_island(int ID_island) {
        this.ID_island = ID_island;
    }

    public void setOccupation(Set<Student> occupation) {
        Occupation = occupation;
    }

    public Tower getTower() {
        return Tower;
    }

    public void setTower(it.polimi.ingsw.model.Tower tower) {
        Tower = tower;
    }

    public Player getPlayerOwner() {
        return PlayerOwner;
    }

    public void setPlayerOwner(Player playerOwner) {
        PlayerOwner = playerOwner;
    }

    public Boolean getMotherNature() {
        return MotherNature;
    }

    public void setMotherNature(Boolean motherNature) {
        MotherNature = motherNature;
    }

    public int[] getMergedWith() {
        return mergedWith;
    }

    public void setMergedWith(int[] mergedWith) {
        this.mergedWith = mergedWith;
    }

    public Boolean getLocked() {
        return Locked;
    }

    public void setLocked(Boolean locked) {
        Locked = locked;
    }

    public boolean isAggregable(){
        return true;
    }
    public Set<Student> getOccupation(){
        return null;
    }
    public boolean isMotherNature(){
        return false;
    }
    public boolean isMerged(){
        return false;
    }
    public boolean isLocked(){
        return false;
    }

}

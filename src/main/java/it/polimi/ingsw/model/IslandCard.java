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

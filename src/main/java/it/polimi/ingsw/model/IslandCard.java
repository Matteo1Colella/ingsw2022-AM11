package it.polimi.ingsw.model;

import java.util.Collection;

public class IslandCard implements Board {
        int id_island;
        Collection<Student> occupation;
        Tower tower;
        Player playerOwner;
        Boolean motherNature;
        int[] mergedWith; //array of integers
        Boolean locked;
        private it.polimi.ingsw.model.IslandCard next;  //pointer of the next Island in the circularArrayList

        //start constructors, getters, setters
        public IslandCard(int id_island, Collection<Student> occupation, Tower tower, Player playerOwner, Boolean motherNature, int[] mergedWith, Boolean locked) {
            this.id_island = id_island;
            this.occupation = occupation;
            this.tower = tower;
            this.playerOwner = playerOwner;
            this.motherNature = motherNature;
            this.mergedWith = mergedWith;
            this.locked = locked;
        }



    public int getId_island() {
            return id_island;
        }

        public void setId_island(int id_island) {
            this.id_island = id_island;
        }

        public void setOccupation(Collection<Student> occupation) {
            this.occupation = occupation;
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

        public void setPlayerOwner(Player playerOwner) {
            this.playerOwner = playerOwner;
        }

        public Boolean getMotherNature() {
            return motherNature;
        }

        public void setMotherNature(Boolean motherNature) {
            this.motherNature = motherNature;
        }

        public int[] getMergedWith() {
            return mergedWith;
        }

        public void setMergedWith(int[] mergedWith) {
            this.mergedWith = mergedWith;
        }

        public Boolean getLocked() {
            return locked;
        }

        public void setLocked(Boolean locked) {
            this.locked = locked;
        }

        public IslandCard getNext() {
            return next;
        }

        public void setNext(IslandCard next) {
            this.next = next;
        }

        //end of constructors, getters, setters

        public boolean isAggregable(){
            return true;
        }
        public Collection<Student> getOccupation(){
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

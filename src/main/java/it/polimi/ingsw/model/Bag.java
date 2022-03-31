package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class Bag {
    private int remaining;
    private ArrayList<Student> bagStudents;

    // Start Getters, Setters, Constructor


    public Bag(int remaining, ArrayList<Student> bagStudents) {
        this.remaining = remaining;
        this.bagStudents = bagStudents;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public ArrayList<Student> getBagStudents() {
        return bagStudents;
    }

    public void setBagStudents(ArrayList<Student> bagStudents) {
        this.bagStudents = bagStudents;
    }
    // End Getters, Setters, Constructor


    public Student Draw(){
        int index = (int) (Math.random() * bagStudents.size());
        Student studentToReturn = bagStudents.get(index);
        bagStudents.remove(index);
        return studentToReturn;
    }

    public boolean IsEmpty(){
        return false;
    }
    public int Left(){
        return 0;
    }
}

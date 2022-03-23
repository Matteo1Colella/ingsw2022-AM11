package it.polimi.ingsw.model;

import java.util.Collection;
import java.util.Set;

public class Bag {
    private int remaining;
    private Collection<Student> Pieces;

    // Start Getters, Setters, Constructor

    public Bag(int remaining, Collection<Student> pieces) {
        this.remaining = remaining;
        Pieces = pieces;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public Collection<Student> getPieces() {
        return Pieces;
    }

    public void setPieces(Collection<Student> pieces) {
        Pieces = pieces;
    }
    // End Getters, Setters, Constructor

    public void Draw(){}
    public boolean IsEmpty(){
        return false;
    }
    public int Left(){
        return 0;
    }
}

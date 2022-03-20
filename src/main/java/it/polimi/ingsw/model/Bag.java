package it.polimi.ingsw.model;

import java.util.Set;

public class Bag {
    private int remaining;
    private Set<Piece> Pieces;

    // Start Getters, Setters, Constructor
    public Bag(int remaining, Set<Piece> pieces) {
        this.remaining = remaining;
        Pieces = pieces;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public Set<Piece> getPieces() {
        return Pieces;
    }

    public void setPieces(Set<Piece> pieces) {
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

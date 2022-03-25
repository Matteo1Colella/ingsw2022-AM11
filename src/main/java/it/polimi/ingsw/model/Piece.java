package it.polimi.ingsw.model;

public abstract class Piece implements Moovable {
    private Board Position;
    // Start of Getters, Setters, Constructor

    public Board getPosition() {
        return Position;
    }

    public void setPosition(Board position) {
        Position = position;
    }


    // End of Getters, Setters, Constructor
}

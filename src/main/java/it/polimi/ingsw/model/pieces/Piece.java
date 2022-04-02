package it.polimi.ingsw.model.pieces;

import it.polimi.ingsw.model.board.Board;

public abstract class Piece implements Moovable {
    private Board position;
    // Start of Getters, Setters, Constructor


    public Board getPosition() {
        return position;
    }

    public void setPosition(Board position) {
        this.position = position;
    }


    // End of Getters, Setters, Constructor
}

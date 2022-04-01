package it.polimi.ingsw.model.pieces;


import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.ColorStudent;

public class Professor extends Piece {
    private final ColorStudent color;
    private Board position;

    public Professor(ColorStudent color) {
        this.color = color;
        this.position = null;
    }

    public ColorStudent getColor(){
        return this.color;

    }

    @Override
    public Board getPosition() {
        return position;
    }

    //A professor must be only in an Entrance. Position's type must be Entrance.
    @Override
    public void setPosition(Board position) {
        this.position = position;
    }
}

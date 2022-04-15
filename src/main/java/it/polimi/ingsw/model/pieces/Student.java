package it.polimi.ingsw.model.pieces;

import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.colors.ColorStudent;

public class Student extends Piece{
    private ColorStudent color;
    private Board position;

    public Student(ColorStudent color) {
        this.color = color;
        position = null;
    }

    public ColorStudent getColor() {
        return color;
    }

    @Override
    public Board getPosition() {
        return position;
    }

    @Override
    public void setPosition(Board position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return this.color.toString();
    }
}

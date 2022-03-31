package it.polimi.ingsw.model;

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
}

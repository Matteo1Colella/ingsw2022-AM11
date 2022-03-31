package it.polimi.ingsw.model;

public class Student extends Piece{
    private ColorStudent Color;
    private Board position;

    public Student(ColorStudent color) {
        Color = color;
        position = null;
    }

    public ColorStudent getColor() {
        return Color;
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

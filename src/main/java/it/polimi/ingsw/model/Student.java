package it.polimi.ingsw.model;

public class Student extends Piece{
    private ColorStudent Color;


    public Student(ColorStudent color) {
        Color = color;
    }


    public ColorStudent getColor() {
        return Color;
    }

    public void setColor(ColorStudent color) {
        Color = color;
    }

    @Override
    public void setPosition() {

    }
}

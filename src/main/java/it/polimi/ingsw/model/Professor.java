package it.polimi.ingsw.model;

public class Professor extends Piece {
    private final ColorStudent color;
    private Board position;

    public Professor(ColorStudent color) {
        this.color = color;
        this.position = null;
    }

    public void setPosition(DiningRoom diningRoom){
        this.position = diningRoom;
    }

    public ColorStudent getColor(){
        return this.color;
    }
}

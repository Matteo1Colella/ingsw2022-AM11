package it.polimi.ingsw.model;

public class Tower extends Piece{

    private ColorTower colorTower;


    //builder


    public Tower(ColorTower colorTower) {
        this.colorTower = colorTower;
    }


    //set the position of the tower
    public void setPosition(Board position){}

    //returns the position of the tower
    public Board getPosition(){
        return null;
    }

    //returns the color of the tower
    public ColorTower getColor(){
        return null;
    }

}

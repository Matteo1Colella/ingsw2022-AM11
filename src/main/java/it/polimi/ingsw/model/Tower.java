package it.polimi.ingsw.model;

public class Tower extends Piece{

    private final ColorTower colorTower;


    //builder
    public Tower(ColorTower colorTower) {
        this.colorTower = colorTower;
    }


    //set the position of the tower
    public void setTowerPosition(Board position){
        setPosition(position);
    }

    //returns the position of the tower
    public Board getPosition(){
        return null;
    }

    //returns the color of the tower
    public ColorTower getColor(){
        return this.colorTower;
    }

}

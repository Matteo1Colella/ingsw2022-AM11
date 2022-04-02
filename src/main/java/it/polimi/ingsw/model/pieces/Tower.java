package it.polimi.ingsw.model.pieces;

import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.controller.ColorTower;

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

    @Override
    public void setPosition(Board position) {

    }
}

package it.polimi.ingsw.model;

import java.util.ArrayList;

public class DiningRoom {
    private ColorStudent Color;
    private SchoolBoard SchoolBoard;
    private ArrayList<Boolean> Occupation; //array of occupations
    private Professor Professor;
    private int CoinsGiven;
    public void earnProfessor(){
    }
    public Coin giveCoin(){
        return null;
    }

    public DiningRoom(ColorStudent color, it.polimi.ingsw.model.SchoolBoard schoolBoard, ArrayList<Boolean> occupation, it.polimi.ingsw.model.Professor professor, int coinsGiven) {
        Color = color;
        SchoolBoard = schoolBoard;
        Occupation = occupation;
        Professor = professor;
        CoinsGiven = coinsGiven;
    }

    public ColorStudent getColor() {
        return Color;
    }

    public void setColor(ColorStudent color) {
        Color = color;
    }

    public it.polimi.ingsw.model.SchoolBoard getSchoolBoard() {
        return SchoolBoard;
    }

    public void setSchoolBoard(it.polimi.ingsw.model.SchoolBoard schoolBoard) {
        SchoolBoard = schoolBoard;
    }

    public ArrayList<Boolean> getOccupation() {
        return Occupation;
    }

    public void setOccupation(ArrayList<Boolean> occupation) {
        Occupation = occupation;
    }

    public it.polimi.ingsw.model.Professor getProfessor() {
        return Professor;
    }

    public void setProfessor(it.polimi.ingsw.model.Professor professor) {
        Professor = professor;
    }

    public int getCoinsGiven() {
        return CoinsGiven;
    }

    public void setCoinsGiven(int coinsGiven) {
        CoinsGiven = coinsGiven;
    }
}

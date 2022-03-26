package it.polimi.ingsw.model;

import java.util.ArrayList;



public class DiningRoom extends Board{
    private final ColorStudent color;
    private ArrayList<Student> occupation; //array of occupations
    private Professor professor;
    private int coinsGiven;

    public DiningRoom(ColorStudent color){
        this.occupation = new ArrayList<>();
        this.color = color;
        this.professor = null;
    }

    public void addStudent(Student student){
        occupation.add(student);
    }

    //who will call this function have to control the number of the students with the same color for all Players
    public void setProfessor(Professor professor){
        this.professor = professor;
        professor.setPosition(this);
    }

    public void removeProfessor(){
        this.professor = null;

    }

    public int getOccupation(){
        return occupation.size();
    }

    //says if it is possible to earn a coin
    public boolean giveCoin(){
        if(occupation.size() % 3 == 0){
            return true;
        }
        return false;
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


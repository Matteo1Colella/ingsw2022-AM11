package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class Game {
    private Status status;
    private boolean isPro;
    private int MovedPieces;
    private GameStructure GameStructure;
    private int ID;

    // Start of Getters, Setters, Constructor

    public Game(boolean isPro, int ID) {
        this.isPro = isPro;
        this.ID = ID;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isPro() {
        return isPro;
    }

    public void setPro(boolean pro) {
        isPro = pro;
    }

    public int getMovedPieces() {
        return MovedPieces;
    }

    public void setMovedPieces(int movedPieces) {
        MovedPieces = movedPieces;
    }

    public it.polimi.ingsw.model.GameStructure getGameStructure() {
        return GameStructure;
    }

    public void setGameStructure(it.polimi.ingsw.model.GameStructure gameStructure) {
        GameStructure = gameStructure;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    // End of Getters, Setters, Constructor

    public void endTurn(){}

    public Player activePlayer(){
        return null;
    }

    public Set<Player> playerList(){
        return null;
    }

    public void changeActivePlayer(){ }

    public Set<Student> drawStudents(){
        return null;
    }

    public Map<ColorStudent, Player> colorDominance(){
        return null;
    }

    public ArrayList<IslandCard> islandDominance(){
        return null;
    }

    public void mergeIsland(){
    }

    public void generateBoard(){}

    public void addChosenCard(){}

    public void pickCharacters(){}

    public void startGame(){}

    public Player AddPlayer(){
        return null;
    }



    public boolean IsIn(String ID){
        return false;
    }
    public void AddPlayer(String ID){

    }
}

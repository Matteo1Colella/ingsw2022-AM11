package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class Game {
    private Status status;
    private boolean isPro;
    private int MovedPieces;
    private GameStructure GameStructure;

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

    public void mergeIsland(){}

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

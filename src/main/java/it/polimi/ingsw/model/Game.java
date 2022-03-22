package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class Game {
    private Status status;
    private boolean isPro;
    private int MovedPieces;
    private GameComponents GameComponents;
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

    public GameComponents getGameStructure() {
        return GameComponents;
    }

    public void setGameStructure(GameComponents gameComponents) {
        GameComponents = gameComponents;
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

    public void AddPlayer(String ID){}

    /*
    If two adjacent island are dominated by two towers
      of the same colors, then the island are merged.
    */
    public void mergeIsland(){
        Collection<IslandCard> Archipelago = GameComponents.getArchipelago();
        IslandCard courrentIsland = GameComponents.getMothernature().getPosition();
        IslandCard previousIsland = null;

        for(IslandCard tempIsland : Archipelago){
            if(tempIsland.equals(courrentIsland)){
                if((previousIsland.getTower()) == (courrentIsland.getTower())){

                }
            }

           previousIsland = tempIsland;
        }
    }
}

package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.Board;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class Game_proRules {

    Status StatusGame = Status.OFFLINE;
    Player CurrentPlayer;
    int CurrentTurn = 0;
    boolean isPro = false;
    int numPlayer;
    Bag Bag;
    MotherNature MotherNature;
    ArrayList<Player> PlayerList;
    ArrayList<Card> chosenCard;
    Set<Student> students;
    int MovedPieces;
    ArrayList<CharacterCard> Characters;
    ArrayList<IslandCard> Archipelago;
    ArrayList<CloudCard> CloudCards;
    ArrayList<SchoolBoard> SchoolBoards;


    //Constructors


    public Game_proRules(Status statusGame, Player currentPlayer, int currentTurn, boolean isPro, int numPlayer, it.polimi.ingsw.model.Bag bag, it.polimi.ingsw.model.MotherNature motherNature, ArrayList<Player> playerList, ArrayList<Card> chosenCard, Set<Student> students, int movedPieces, ArrayList<CharacterCard> characters, ArrayList<IslandCard> archipelago, ArrayList<CloudCard> cloudCards, ArrayList<SchoolBoard> schoolBoards) {
        StatusGame = statusGame;
        CurrentPlayer = currentPlayer;
        CurrentTurn = currentTurn;
        this.isPro = isPro;
        this.numPlayer = numPlayer;
        Bag = bag;
        MotherNature = motherNature;
        PlayerList = playerList;
        this.chosenCard = chosenCard;
        this.students = students;
        MovedPieces = movedPieces;
        Characters = characters;
        Archipelago = archipelago;
        CloudCards = cloudCards;
        SchoolBoards = schoolBoards;
    }

    public void endTurn(){

    }
    public Player activePlayer(){
        return null;
    }
    public Set<Player> playerList(){
        return null;
    }
    public void changeActivePlayer(){

    }
    public void setGameType(){

    }
    public Set<Student> drawStudents(){

        return null;

    }
    public void moveMotherNature(){

    }
    public Map<ColorStudent,Player> colorDominance(){
        return null;
    }
    public ArrayList<IslandCard> islandDominance(){
        return null;
    }
    public void mergeIsland(){

    }
    public void generateBoard(){

    }
    public void addChosenCard(){

    }
    public void pickCharacters(){

    }


}

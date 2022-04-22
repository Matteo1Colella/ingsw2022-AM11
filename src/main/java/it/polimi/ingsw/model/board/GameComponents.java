package it.polimi.ingsw.model.board;

import it.polimi.ingsw.model.cards.CharacterDeck;
import it.polimi.ingsw.model.pieces.MotherNature;
import it.polimi.ingsw.model.pieces.NoEntryTile;
import it.polimi.ingsw.model.pieces.Professor;

import java.util.ArrayList;
import java.util.Collection;

public class GameComponents {
    private ArrayList<IslandCard> archipelago;
    private MotherNature motherNature;
    private ArrayList<SchoolBoard> SchoolBoards;
    private Bag bag;
    private ArrayList<CloudCard> cloudCards;
    private ArrayList<Professor> professorCollection;
    private CoinReserve coins;
    private Collection<NoEntryTile> prohibitionCards;
    private CharacterDeck specialDeck;

    // Start of Getters, Setters, Constructor

    //constructor pro
    public GameComponents(ArrayList<IslandCard> archipelago, MotherNature motherNature, ArrayList<SchoolBoard> schoolBoards, Bag bag, ArrayList<CloudCard> cloudCards, ArrayList<Professor> professorCollection, CoinReserve coins, Collection<NoEntryTile> prohibitionCards, CharacterDeck specialDeck) {
        this.archipelago = archipelago;
        this.motherNature = motherNature;
        SchoolBoards = schoolBoards;
        this.bag = bag;
        this.cloudCards = cloudCards;
        this.professorCollection = professorCollection;
        this.coins = coins;
        this.prohibitionCards = prohibitionCards;
        this.specialDeck = specialDeck;
    }

    //normal constructor
    public GameComponents(ArrayList<IslandCard> archipelago, MotherNature motherNature, ArrayList<SchoolBoard> schoolBoards, Bag bag, ArrayList<CloudCard> cloudCards, ArrayList<Professor> professorCollection) {
        this.archipelago = archipelago;
        this.motherNature = motherNature;
        SchoolBoards = schoolBoards;
        this.bag = bag;
        this.cloudCards = cloudCards;
        this.professorCollection = professorCollection;
    }

    public CoinReserve getCoins() {
        return coins;
    }

    public Collection<NoEntryTile> getProhibitionCards() {
        return prohibitionCards;
    }


    public CharacterDeck getSpecialDeck() {
        return specialDeck;
    }


    public ArrayList<IslandCard> getArchipelago() {

        return archipelago;
    }

    public void setArchipelago(ArrayList<IslandCard> archipelago) {

        this.archipelago = archipelago;
    }

    public MotherNature getMotherNature() {
        return motherNature;
    }

    public ArrayList<SchoolBoard> getSchoolBoards() {

        return SchoolBoards;
    }


    public Bag getBag() {

        return bag;
    }


    public ArrayList<CloudCard> getCloudCards() {

        return cloudCards;
    }


    public ArrayList<Professor> getProfessorCollection() {
        return professorCollection;
    }


    // End of Getters, Setters, Constructor

    public void printClouds(){
        int j = 1;
        System.out.println("Cloud cards: ");
        for(CloudCard cloudCard : this.cloudCards){
            System.out.println("Cloud card " + j + " students: ");
            for(int i = 0; i < cloudCard.getStudents().size(); i++){
                System.out.println("Student " + i + " color: " + cloudCard.getStudents().get(i));
            }
            j++;
        }
    }

    public void printArchipelago(){
        int i = 0;
        System.out.println("Archipelago: ");
        for(IslandCard islandCard : this.archipelago){
            if(this.getArchipelago().get(i).getTower() != null){
                System.out.println("Island: " + i + "\tTower : " + this.getArchipelago().get(i).getTower().getColor().toString());
            } else {
                System.out.println("Island: " + i + "\tTower : no tower");
            }
            System.out.println("Students:");
            for(int j = 0; j < islandCard.getStudents().size(); j++){
                System.out.println("Student " + j + " color : " + islandCard.getStudents().get(j));
            }
            System.out.println("Merged with: ");
            for(int j = 0; j < islandCard.getMergedWith().size(); j++){
                IslandCard tempIslandCard = islandCard.getMergedWith().get(j);
                for(int k = 0; k < tempIslandCard.getStudents().size(); k++){
                    System.out.println("Student " + k + " color : " + tempIslandCard.getStudents().get(k));
                }
            }
            i++;
        }
    }
}

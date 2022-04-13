package it.polimi.ingsw.model.board;

import it.polimi.ingsw.model.cards.CharacterDeck;
import it.polimi.ingsw.model.pieces.MotherNature;
import it.polimi.ingsw.model.pieces.NoEntryTile;
import it.polimi.ingsw.model.pieces.Professor;

import java.util.ArrayList;
import java.util.Collection;

public class GameComponents {
    private ArrayList<IslandCard> Archipelago;
    private MotherNature Mothernature;
    private ArrayList<SchoolBoard> SchoolBoards;
    private Bag Bag;
    private Collection<CloudCard> CloudCards;
    private ArrayList<Professor> professorCollection;
    private CoinReserve coins;
    private Collection<NoEntryTile> prohibitionCards;
    private CharacterDeck specialDeck;



    // Start of Getters, Setters, Constructor

    //constructor pro


    public GameComponents(ArrayList<IslandCard> archipelago, MotherNature mothernature, ArrayList<SchoolBoard> schoolBoards, Bag bag, Collection<CloudCard> cloudCards, ArrayList<Professor> professorCollection, CoinReserve coins, Collection<NoEntryTile> prohibitionCards, CharacterDeck specialDeck) {
        Archipelago = archipelago;
        Mothernature = mothernature;
        SchoolBoards = schoolBoards;
        Bag = bag;
        CloudCards = cloudCards;
        this.professorCollection = professorCollection;
        this.coins = coins;
        this.prohibitionCards = prohibitionCards;
        this.specialDeck = specialDeck;
    }

    //normal constructor
    public GameComponents(ArrayList<IslandCard> archipelago, MotherNature mothernature, ArrayList<SchoolBoard> schoolBoards,Bag bag, Collection<CloudCard> cloudCards, ArrayList<Professor> professorCollection) {
        Archipelago = archipelago;
        Mothernature = mothernature;
        SchoolBoards = schoolBoards;
        Bag = bag;
        CloudCards = cloudCards;
        this.professorCollection = professorCollection;
    }

    public CoinReserve getCoins() {
        return coins;
    }

    public Collection<NoEntryTile> getProhibitionCards() {
        return prohibitionCards;
    }

    public void setProhibitionCards(Collection<NoEntryTile> prohibitionCards) {
        this.prohibitionCards = prohibitionCards;
    }

    public CharacterDeck getSpecialDeck() {
        return specialDeck;
    }

    public void setSpecialDeck(CharacterDeck specialDeck) {
        this.specialDeck = specialDeck;
    }

    public ArrayList<IslandCard> getArchipelago() {

        return Archipelago;
    }

    public void setArchipelago(ArrayList<IslandCard> archipelago) {

        Archipelago = archipelago;
    }

    public MotherNature getMothernature() {
        return Mothernature;
    }

    public void setMothernature(MotherNature mothernature) {

        Mothernature = mothernature;
    }

    public ArrayList<SchoolBoard> getSchoolBoards() {

        return SchoolBoards;
    }

    public void setSchoolBoards(ArrayList<SchoolBoard> schoolBoards) {

        SchoolBoards = schoolBoards;
    }

    public it.polimi.ingsw.model.board.Bag getBag() {

        return Bag;
    }

    public void setBag(it.polimi.ingsw.model.board.Bag bag) {

        Bag = bag;
    }

    public Collection<CloudCard> getCloudCards() {

        return CloudCards;
    }

    public void setCloudCards(Collection<CloudCard> cloudCards) {

        CloudCards = cloudCards;
    }

    public ArrayList<Professor> getProfessorCollection() {
        return professorCollection;
    }

    public void setProfessorCollection(ArrayList<Professor> professorCollection) {
        this.professorCollection = professorCollection;
    }

    // End of Getters, Setters, Constructor



}

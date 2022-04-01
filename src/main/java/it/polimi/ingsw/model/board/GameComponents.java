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
    private Collection<SchoolBoard> SchoolBoards;
    private Bag Bag;
    private Collection<CloudCard> CloudCards;
    private Collection<Professor> professorCollection;
    private CoinReserve coins;
    private Collection<NoEntryTile> prohibitionCards;
    private CharacterDeck specialDeck;



    // Start of Getters, Setters, Constructor

    //constructor pro


    public GameComponents(ArrayList<IslandCard> archipelago, MotherNature mothernature, Collection<SchoolBoard> schoolBoards, it.polimi.ingsw.model.board.Bag bag, Collection<CloudCard> cloudCards, Collection<Professor> professorCollection, CoinReserve coins, Collection<NoEntryTile> prohibitionCards, CharacterDeck specialDeck) {
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
    public GameComponents(ArrayList<IslandCard> archipelago, MotherNature mothernature, Collection<SchoolBoard> schoolBoards, it.polimi.ingsw.model.board.Bag bag, Collection<CloudCard> cloudCards, Collection<Professor> professorCollection) {
        Archipelago = archipelago;
        Mothernature = mothernature;
        SchoolBoards = schoolBoards;
        Bag = bag;
        CloudCards = cloudCards;
        this.professorCollection = professorCollection;
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

    public Collection<SchoolBoard> getSchoolBoards() {

        return SchoolBoards;
    }

    public void setSchoolBoards(Collection<SchoolBoard> schoolBoards) {

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

    public Collection<Professor> getProfessorCollection() {
        return professorCollection;
    }

    public void setProfessorCollection(Collection<Professor> professorCollection) {
        this.professorCollection = professorCollection;
    }

    // End of Getters, Setters, Constructor



}

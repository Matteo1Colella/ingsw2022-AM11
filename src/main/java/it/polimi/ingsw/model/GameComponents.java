package it.polimi.ingsw.model;

import java.util.Collection;

public class GameComponents {
    private Collection<IslandCard> Archipelago;
    private MotherNature Mothernature;
    private Collection<SchoolBoard> SchoolBoards;
    private Bag Bag;
    private Collection<CloudCard> CloudCards;

    // Start of Getters, Setters, Constructor
    public Collection<IslandCard> getArchipelago() {

        return Archipelago;
    }

    public void setArchipelago(Collection<IslandCard> archipelago) {

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

    public it.polimi.ingsw.model.Bag getBag() {

        return Bag;
    }

    public void setBag(it.polimi.ingsw.model.Bag bag) {

        Bag = bag;
    }

    public Collection<CloudCard> getCloudCards() {

        return CloudCards;
    }

    public void setCloudCards(Collection<CloudCard> cloudCards) {

        CloudCards = cloudCards;
    }

    public GameComponents(Collection<IslandCard> archipelago, MotherNature mothernature, Collection<SchoolBoard> schoolBoards, it.polimi.ingsw.model.Bag bag, Collection<CloudCard> cloudCards) {
        Archipelago = archipelago;
        Mothernature = mothernature;
        SchoolBoards = schoolBoards;
        Bag = bag;
        CloudCards = cloudCards;
    }
    // End of Getters, Setters, Constructor
}

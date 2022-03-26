package it.polimi.ingsw.model;

public class MotherNature implements Moovable{

    private IslandCard Position;

    // Start of Getters, Setters, Constructor
    public MotherNature(IslandCard position) {

        Position = position;
    }

    public IslandCard getPosition() {

        return Position;
    }

    public void setPosition(IslandCard position) {

        Position = position;
    }

    @Override
    public Board setPosition() {
        return null;
    }
    // End of Getters, Setters, Constructor
}

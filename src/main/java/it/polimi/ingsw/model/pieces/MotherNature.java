package it.polimi.ingsw.model.pieces;

import it.polimi.ingsw.model.board.Board;
import it.polimi.ingsw.model.board.IslandCard;

public class MotherNature implements Moovable {

    private IslandCard position;

    // Start of Getters, Setters, Constructor
    public MotherNature(IslandCard position) {

        this.position = position;
    }

    public IslandCard getPosition() {

        return position;
    }

    public void setPosition(IslandCard position) {

        this.position = position;
    }

    @Override
    public void setPosition(Board position) {
        this.position = (IslandCard) position;
    }

    // End of Getters, Setters, Constructor
}
package it.polimi.ingsw.model.pieces;

import it.polimi.ingsw.model.board.IslandCard;

public class NoEntryTile {
    private boolean Used;
    private IslandCard Position;

    // Start of Getters, Setters, Constructor
    public NoEntryTile(boolean used, IslandCard position) {
        Used = used;
        Position = position;
    }

    public boolean isUsed() {
        return Used;
    }

    public void setUsed(boolean used) {
        Used = used;
    }

    public IslandCard getPosition() {
        return Position;
    }

    public void setPosition(IslandCard position) {
        Position = position;
    }
    // End of Getters, Setters, Constructor
}

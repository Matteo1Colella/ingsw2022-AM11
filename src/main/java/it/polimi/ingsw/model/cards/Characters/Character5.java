package it.polimi.ingsw.model.cards.Characters;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.board.IslandCard;
import it.polimi.ingsw.model.cards.CharacterCard;
import it.polimi.ingsw.model.pieces.NoEntryTile;

import java.util.ArrayList;

public class Character5 extends CharacterCard {
    private ArrayList<NoEntryTile> tiles;
    private int num;
    public Character5(int num) {
        this.tiles = new ArrayList<>();
        this.num = num;
    }

    public void effect(Player activePlayer, IslandCard island){
        NoEntryTile temp = null;
        for(NoEntryTile t : tiles){
            if (!t.isUsed()) {
                temp = t;
            }
        }
        if (temp == null) return;
        temp.setPosition(island);
        temp.setUsed(true);
    }
}

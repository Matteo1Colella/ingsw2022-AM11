package it.polimi.ingsw.model.cards.characters;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.board.IslandCard;
import it.polimi.ingsw.model.cards.CharacterCard;
import it.polimi.ingsw.model.pieces.NoEntryTile;

import java.util.ArrayList;

public class Character5 extends CharacterCard {
    private transient ArrayList<NoEntryTile> tiles;
    private transient int num;
    private transient int necessaryCoin;

    public Character5(int num) {
        this.tiles = new ArrayList<>();
        this.num = num;
        super.setNum(num);
        this.necessaryCoin = 2;
        super.setNecessaryCoin(necessaryCoin);
    }

    public void effect(Player activePlayer, IslandCard island){
        NoEntryTile temp = null;
        for(NoEntryTile t : super.getTiles()){
            if (!t.isUsed()) {
                temp = t;
            }
        }
        if (temp == null) return;
        island.setLocked(true);
        temp.setPosition(island);
        temp.setUsed(true);
        activePlayer.useCoins(this.necessaryCoin);
        super.setNecessaryCoin(necessaryCoin + 1);
        this.setNecessaryCoin(necessaryCoin + 1);
    }

    @Override
    public int getNecessaryCoin() {
        return necessaryCoin;
    }
}

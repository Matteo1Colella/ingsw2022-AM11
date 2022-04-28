package it.polimi.ingsw.model.cards.characters;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.board.IslandCard;
import it.polimi.ingsw.model.cards.CharacterCard;

public class Character3 extends CharacterCard {
    private int num;
    private final int necessaryCoin;

    public Character3(int num) {
        this.num = num;
        super.setNum(num);
        this.necessaryCoin = 3;
    }

    public void effect(Player activePlayer, IslandCard island){
        activePlayer.getPlayerGame().islandDominance(island);
    }

    @Override
    public int getNecessaryCoin() {
        return necessaryCoin;
    }
}

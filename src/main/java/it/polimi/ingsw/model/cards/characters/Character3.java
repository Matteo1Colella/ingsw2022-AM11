package it.polimi.ingsw.model.cards.characters;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.board.IslandCard;
import it.polimi.ingsw.model.cards.CharacterCard;

public class Character3 extends CharacterCard {
    private transient int num;
    private transient int necessaryCoin;

    public Character3(int num) {
        this.num = num;
        super.setNum(num);
        this.necessaryCoin = 3;
        super.setNecessaryCoin(necessaryCoin);
    }

    public void effect(Player activePlayer, IslandCard island){
        activePlayer.getPlayerGame().islandDominance(island);
        activePlayer.useCoins(this.necessaryCoin);
        super.setNecessaryCoin(necessaryCoin + 1);
        this.setNecessaryCoin(necessaryCoin + 1);
    }

    @Override
    public int getNecessaryCoin() {
        return necessaryCoin;
    }
}

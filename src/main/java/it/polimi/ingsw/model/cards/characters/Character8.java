package it.polimi.ingsw.model.cards.characters;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.cards.CharacterCard;


public class Character8 extends CharacterCard {
    private transient int num;
    private transient final int necessaryCoin;

    public Character8(int num) {
        super.setNum(num);
        this.num = num;
        this.necessaryCoin = 2;
    }

    public void effect(Player activePlayer){
        activePlayer.setInfluencePoints(activePlayer.getInfluencePoints()+2);
        activePlayer.useCoins(this.necessaryCoin);
    }

    @Override
    public int getNecessaryCoin() {
        return necessaryCoin;
    }
}

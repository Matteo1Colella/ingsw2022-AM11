package it.polimi.ingsw.model.cards.characters;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.cards.CharacterCard;


public class Character8 extends CharacterCard {
    private transient int num;
    private transient int necessaryCoin;

    public Character8(int num) {
        super.setNum(num);
        this.num = num;
        this.necessaryCoin = 2;
        super.setNecessaryCoin(necessaryCoin);

    }

    public void effect(Player activePlayer){
        activePlayer.setCharacter8(true);
        activePlayer.useCoins(this.necessaryCoin);
        super.setNecessaryCoin(necessaryCoin++);
    }

    @Override
    public int getNecessaryCoin() {
        return necessaryCoin;
    }
}

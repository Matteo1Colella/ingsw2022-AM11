package it.polimi.ingsw.model.cards.characters;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.cards.CharacterCard;

public class Character4 extends CharacterCard {
    private transient int num;
    private transient int necessaryCoin;

    public Character4(int num) {
        this.num = num;
        super.setNum(num);
        this.necessaryCoin = 1;
        super.setNecessaryCoin(necessaryCoin);
    }

    public void effect(Player activePlayer){
        activePlayer.setMotherNatureMoves(activePlayer.getMotherNatureMoves()+2);
        activePlayer.useCoins(this.necessaryCoin);
        super.setNecessaryCoin(necessaryCoin++);
    }

    @Override
    public int getNecessaryCoin() {
        return necessaryCoin;
    }
}

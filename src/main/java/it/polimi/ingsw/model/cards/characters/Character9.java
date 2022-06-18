package it.polimi.ingsw.model.cards.characters;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.cards.CharacterCard;
import it.polimi.ingsw.model.colors.ColorStudent;

public class Character9 extends CharacterCard {
    private transient int num;
    private transient int necessaryCoin;

    public Character9(int num) {
        super.setNum(num);
        this.num = num;
        this.necessaryCoin = 3;
        super.setNecessaryCoin(necessaryCoin);
    }

    public void effect(Player activePlayer, ColorStudent color){
        if (color == null) return;
        activePlayer.getPlayerGame().setExcludedColor(color);
        activePlayer.useCoins(this.necessaryCoin);
        super.setNecessaryCoin(necessaryCoin++);
    }

    @Override
    public int getNecessaryCoin() {
        return necessaryCoin;
    }
}

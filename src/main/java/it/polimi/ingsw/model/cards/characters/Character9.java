package it.polimi.ingsw.model.cards.characters;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.cards.CharacterCard;
import it.polimi.ingsw.model.colors.ColorStudent;

public class Character9 extends CharacterCard {
    private int num;
    private final int necessaryCoin;

    public Character9(int num) {
        super.setNum(num);
        this.num = num;
        this.necessaryCoin = 3;
    }

    public void effect(Player activePlayer, ColorStudent color){
        activePlayer.getPlayerGame().setExcludedColor(color);
    }

    @Override
    public int getNecessaryCoin() {
        return necessaryCoin;
    }
}

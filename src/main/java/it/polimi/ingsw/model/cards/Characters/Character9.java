package it.polimi.ingsw.model.cards.Characters;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.cards.CharacterCard;
import it.polimi.ingsw.model.colors.ColorStudent;

public class Character9 extends CharacterCard {
    private int num;

    public Character9(int num) {
        super.setNum(num);
        this.num = num;
    }

    public void effect(Player activePlayer, ColorStudent color){
        activePlayer.getPlayerGame().setExcludedColor(color);
    }
}

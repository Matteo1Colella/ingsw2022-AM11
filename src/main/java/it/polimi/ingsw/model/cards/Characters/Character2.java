package it.polimi.ingsw.model.cards.Characters;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.cards.CharacterCard;

public class Character2 extends CharacterCard {
    private int num;

    public Character2(int num) {
        this.num = num;
        super.setNum(num);
    }

    public void effect(Player activePlayer){
        // nardo needs to do this
    }
}

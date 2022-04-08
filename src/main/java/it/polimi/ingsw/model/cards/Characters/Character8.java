package it.polimi.ingsw.model.cards.Characters;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.cards.CharacterCard;


public class Character8 extends CharacterCard {
    private int num;

    public Character8(int num) {
        super.setNum(num);
        this.num = num;
    }

    public void effect(Player activePlayer){
        activePlayer.setInfluencePoints(activePlayer.getInfluencePoints()+2);
    }
}

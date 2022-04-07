package it.polimi.ingsw.model.cards.Characters;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.board.IslandCard;
import it.polimi.ingsw.model.cards.CharacterCard;

public class Character4 extends CharacterCard {
    private int num;

    public Character4(int num) {
        this.num = num;
        super.setNum(num);
    }

    public void effect(Player activePlayer){
        activePlayer.setMotherNatureMoves(activePlayer.getMotherNatureMoves()+2);
    }
}

package it.polimi.ingsw.model.cards.Characters;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.board.IslandCard;
import it.polimi.ingsw.model.cards.CharacterCard;

public class Character3 extends CharacterCard {
    private int num;

    public Character3(int num) {
        this.num = num;
    }

    public void effect(Player activePlayer, IslandCard island){
        activePlayer.getPlayerGame().islandDominance(island);
    }
}

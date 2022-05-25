package it.polimi.ingsw.model.cards.characters;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.board.IslandCard;
import it.polimi.ingsw.model.cards.CharacterCard;

public class Character6 extends CharacterCard {
    private transient int num;
    private transient int necessaryCoin;

    public Character6(int num) {
        this.num = num;
        super.setNum(num);
        this.necessaryCoin = 3;
        super.setNecessaryCoin(necessaryCoin);
    }

    public void effect(Player activePlayer){
        activePlayer.getPlayerGame().setNoTower(true);
        activePlayer.useCoins(this.necessaryCoin);
    }

    @Override
    public int getNecessaryCoin() {
        return necessaryCoin;
    }
}

package it.polimi.ingsw.model.cards.characters;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.cards.CharacterCard;

public class Character2 extends CharacterCard {
    private transient int num;
    private transient int necessaryCoin;

    public Character2(int num) {
        this.num = num;
        super.setNum(num);
        this.necessaryCoin = 2;
        super.setNecessaryCoin(necessaryCoin);
    }

    public void effect(Player activePlayer){
        //set an attribute in the activePlayer's schoolboard who indicates thad the character 2 is used.
        // in Game, the function colorDominance will take into account this boolean value to calculate
        // correctly the setting of professors.
        activePlayer.getSchoolBoard().setCharachter2used(true);
        activePlayer.useCoins(this.necessaryCoin);
        super.setNecessaryCoin(necessaryCoin + 1);
        this.setNecessaryCoin(necessaryCoin + 1);
    }

    @Override
    public int getNecessaryCoin() {
        return necessaryCoin;
    }
}

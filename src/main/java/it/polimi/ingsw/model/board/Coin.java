package it.polimi.ingsw.model.board;

import it.polimi.ingsw.model.cards.CharacterCard;
import it.polimi.ingsw.model.Player;

public class Coin {
    private final int ID_coin;
    private boolean isUsed;
    private CharacterCard card;
    private Player ownerPlayer;


    public Coin(int id_coin) {
        this.ID_coin = id_coin;
        this.isUsed = false;
        this.card = null;
        this.ownerPlayer = null;
    }

   /* public int getID_coin() {
        return ID_coin;
    } */

   /* public boolean isUsed() {
        return isUsed;
    }*/

   /* public void setUsed(boolean used) {
        isUsed = used;
    }*/

    /*
    public CharacterCard getCard() {
        return card;
    }*/


   /* public void playOnCharacterCard(CharacterCard card) {
        this.card = card;
    } */

    public Player getOwnerPlayer() {
        return ownerPlayer;
    }

    public void setOwnerPlayer(Player ownerPlayer) {
        this.ownerPlayer = ownerPlayer;
    }
}

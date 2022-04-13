package it.polimi.ingsw.model.board;

import it.polimi.ingsw.model.Player;

import java.util.ArrayList;

public class CoinReserve {

    private final ArrayList<Coin> coins;

    //constructor
    public CoinReserve() {
        coins = new ArrayList<>();
        int totCoins = 20;
        for(int i = 0; i < totCoins; i++){
            coins.add(new Coin(i));
        }
    }

    //give a coin to a player
    public void giveCoin(Player player){
        for(Coin tempCoin : coins){
            if(tempCoin.getOwnerPlayer() == null){
                tempCoin.setOwnerPlayer(player);
                player.addCoins(tempCoin);
                this.coins.remove(tempCoin);
                break;
            }
        }
    }

    //when a character card end its power, the coins on it have to return in the coins reserve.
    //the caller have to call this function for each coin on the card
    public void addCoin(Coin coin){
        this.coins.add(coin);
    }

    public int getCoins() {
        return this.coins.size();
    }
}

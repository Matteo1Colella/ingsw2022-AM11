package it.polimi.ingsw.model;

import java.util.ArrayList;

public class CoinReserve {

    private ArrayList<Coin> coins;
    private final int totCoins = 20;

    //constructor
    public CoinReserve() {
        coins = new ArrayList<>();
        for(int i = 0; i < totCoins; i++){
            coins.add(new Coin(i));
        }
    }

    //assign a player to a coin
    public ArrayList<Coin> getCoins() {
        return coins;
    }

    //give a coin to a player
    public void giveCoin(Player player){
        for(Coin tempCoin : coins){
            if(tempCoin.getOwnerPlayer() == null){
                tempCoin.setOwnerPlayer(player);
                player.addCoins(tempCoin);
            }
            break;
        }
    }

    //when a character card end its power, the coins on it have to return in the coins reserve.
    //the caller have to call this function for each coin on the card
    public void addCoin(Coin coin){
        this.coins.add(coin);
    }
}

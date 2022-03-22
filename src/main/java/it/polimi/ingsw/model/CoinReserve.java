package it.polimi.ingsw.model;

import java.util.ArrayList;

public class CoinReserve {

    private ArrayList<Coin> Coins;

    //constructor
    public CoinReserve(ArrayList<Coin> coins) {
        Coins = coins;
    }

    //getter and setter methods

    public ArrayList<Coin> getCoins() {
        return Coins;
    }

    public void setCoins(ArrayList<Coin> coins) {
        Coins = coins;
    }
}

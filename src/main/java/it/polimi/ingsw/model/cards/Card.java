package it.polimi.ingsw.model.cards;

import it.polimi.ingsw.model.Mage;

public class Card {
    private final String Name;
    private final int Steps;
    private final int Influence;
    private boolean used;
    private final Mage mage;

    //Costructor

    public Card(String name, int steps, int influence, boolean used, Mage mage) {
        Name = name;
        Steps = steps;
        Influence = influence;
        this.used = used;
        this.mage = mage;
    }

    //getter and setter methods

    public String getName() {
        return Name;
    }

    public int getSteps() {
        return Steps;
    }

    public int getInfluence() {
        return Influence;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public Mage getMage() {
        return mage;
    }

    @Override
    public String toString() {
        return  "Name='" + Name + '\'' +
                ", Steps=" + Steps +
                ", Influence=" + Influence +
                ", mage=" + mage;
    }
}

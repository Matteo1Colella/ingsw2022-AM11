package it.polimi.ingsw.model;

public class Card {
    private String Name;
    private int Steps;
    private int Influence;
    private boolean used;
    private Mage mage;

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

    public void setName(String name) {
        Name = name;
    }

    public int getSteps() {
        return Steps;
    }

    public void setSteps(int steps) {
        Steps = steps;
    }

    public int getInfluence() {
        return Influence;
    }

    public void setInfluence(int influence) {
        Influence = influence;
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

    public void setMage(Mage mage) {
        this.mage = mage;
    }
}

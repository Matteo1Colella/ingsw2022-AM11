package it.polimi.ingsw.model;

import java.util.Collection;
import java.util.Set;

public class Bag {
    private int remaining;
    private Collection<Student> yellow;
    private Collection<Student> pink;
    private Collection<Student> red;
    private Collection<Student> green;
    private Collection<Student> blue;


    // Start Getters, Setters, Constructor

    public Bag(int remaining, Collection<Student> yellow, Collection<Student> pink, Collection<Student> red, Collection<Student> green, Collection<Student> blue) {
        this.remaining = remaining;
        this.yellow = yellow;
        this.pink = pink;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public Collection<Student> getYellow() {
        return yellow;
    }

    public void setYellow(Collection<Student> yellow) {
        this.yellow = yellow;
    }

    public Collection<Student> getPink() {
        return pink;
    }

    public void setPink(Collection<Student> pink) {
        this.pink = pink;
    }

    public Collection<Student> getRed() {
        return red;
    }

    public void setRed(Collection<Student> red) {
        this.red = red;
    }

    public Collection<Student> getGreen() {
        return green;
    }

    public void setGreen(Collection<Student> green) {
        this.green = green;
    }

    public Collection<Student> getBlue() {
        return blue;
    }

    public void setBlue(Collection<Student> blue) {
        this.blue = blue;
    }
    // End Getters, Setters, Constructor

    public void Draw(){}
    public boolean IsEmpty(){
        return false;
    }
    public int Left(){
        return 0;
    }
}

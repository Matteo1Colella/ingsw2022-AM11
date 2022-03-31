package it.polimi.ingsw.model.Components;

import it.polimi.ingsw.model.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;


//first try to implement circularArrayList! still working on it...

public class Archipelago implements Iterator<IslandCard> {

    private IslandCard last;
    private final int lenght;



    public Archipelago(){
        last = null;
        lenght = 12;
    }
    public int lenght(){
        return lenght;
    }
    public boolean isEmpty(){
        return lenght == 0;
    }
    public void createCircularLinkedList(){
        IslandCard one = new IslandCard(1, null);
        IslandCard two = new IslandCard(2, null);
        IslandCard three = new IslandCard(3, null);
        IslandCard four = new IslandCard(4, null);
        IslandCard five = new IslandCard(5, null);
        IslandCard six = new IslandCard(6, null);
        IslandCard seven = new IslandCard(7, null);
        IslandCard eight = new IslandCard(8,null);
        IslandCard nine = new IslandCard(9, null);
        IslandCard ten = new IslandCard(10, null);
        IslandCard eleven = new IslandCard(11, null);
        IslandCard twelve = new IslandCard(12, null);

        one.setNext(two);
        two.setNext(three);
        three.setNext(four);
        four.setNext(five);
        five.setNext(six);
        six.setNext(seven);
        seven.setNext(eight);
        eight.setNext(nine);
        nine.setNext(ten);
        ten.setNext(eleven);
        eleven.setNext(twelve);
        twelve.setNext(one);

        last = twelve;

    }

    //trying to print with an iterator, not finished yet...
    //public void printAll(Iterator<IslandCard> itr){
    //    if(last == null){
    //        return;
    //    }
    //    IslandCard first = last.getNext();
    //    while(!itr.equals(last)){
    //        System.out.println(itr.next().getId_island());
    //    }
    //}

    public void printIslands(){
        if(last == null){
            return;
        }
        IslandCard first = last.getNext();
        while (first!=last){
            System.out.println(first.getId_island() + "");
            first = first.getNext();
        }
        System.out.println(first.getId_island() + "");

    }


    //I'm working on the iterator, not finished yet..
    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public IslandCard next() {

        return last.getNext();
    }

    @Override
    public void remove() {
        Iterator.super.remove();
    }
}

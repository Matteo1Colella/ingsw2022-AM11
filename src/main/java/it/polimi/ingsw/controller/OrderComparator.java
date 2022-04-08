package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.cards.Card;

import java.util.ArrayList;
import java.util.Comparator;

public class OrderComparator implements Comparator {


    @Override
    public int compare(Object obj1, Object obj2) {
        if (obj1!=null && obj2!=null){
            Card objOfMyClass1 = (Card)obj1;
            Card objOfMyClass2 = (Card)obj2;
            if (objOfMyClass1.getInfluence()>objOfMyClass2.getInfluence()){
                return 1;
            }else{
                if (objOfMyClass1.getInfluence()==objOfMyClass2.getInfluence()){
                    return 0;
                }
            }
        }
        return -1;
    }

}

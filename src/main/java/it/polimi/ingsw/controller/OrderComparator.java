package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.cards.Card;

import java.util.ArrayList;
import java.util.Comparator;

public class OrderComparator implements Comparator {


    /**
     * compares the influence of 2 assistant cards
     * @param obj1 the first object to be compared.
     * @param obj2 the second object to be compared.
     * @return int (bool)
     */
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

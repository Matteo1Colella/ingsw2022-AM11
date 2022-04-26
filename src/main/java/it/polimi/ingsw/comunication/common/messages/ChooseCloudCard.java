package it.polimi.ingsw.comunication.common.messages;

public class ChooseCloudCard {
    private int id;

    private ChooseCloudCard(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

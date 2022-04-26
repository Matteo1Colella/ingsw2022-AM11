package it.polimi.ingsw.comunication.common.messages;

public class UseCharacter {
    private int idCharacter;

    public UseCharacter(int idCharacter) {
        this.idCharacter = idCharacter;
    }

    public int getIdCharacter() {
        return idCharacter;
    }
}

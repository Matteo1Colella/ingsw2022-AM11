package it.polimi.ingsw.communication.common.messages;

import it.polimi.ingsw.communication.common.MessageInterface;
import it.polimi.ingsw.communication.common.MessageType;

public class UseCharacterMessage implements MessageInterface {
    private String message;
    private MessageType code;
    private int id;
    private int[] usableCharacters;

    public UseCharacterMessage(int id) {
        message = "Play Character message.\r";
        code = MessageType.CHARACTERCHOICE;
        this.id = id;
    }

    public UseCharacterMessage(int[] usable) {
        message = "Avaiable Character message.\r";
        code = MessageType.CHARACTERCHOICE;
        this.id = -1;
        this.usableCharacters = usable;
    }

    public UseCharacterMessage(){
        message = "Ask for available mage message.\r";
        code = MessageType.CHARACTERCHOICE;
        this.id = 0;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public MessageType getCode() {
        return code;
    }

    public int[] getUsableCharacters() {
        return usableCharacters;
    }

    public int getId() {
        return id;
    }
}

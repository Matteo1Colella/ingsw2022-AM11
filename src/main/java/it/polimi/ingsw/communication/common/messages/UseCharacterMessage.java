package it.polimi.ingsw.communication.common.messages;

import it.polimi.ingsw.communication.common.MessageInterface;
import it.polimi.ingsw.communication.common.MessageType;

public class UseCharacterMessage implements MessageInterface {
    private String message;
    private MessageType code;
    private int id;

    public UseCharacterMessage(int id) {
        message = "Play Character message.\r";
        code = MessageType.CHARACTERCHOICE;
        this.id = id;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public MessageType getCode() {
        return code;
    }

    public int getId() {
        return id;
    }
}

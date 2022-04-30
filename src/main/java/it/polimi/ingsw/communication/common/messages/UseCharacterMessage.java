package it.polimi.ingsw.communication.common.messages;

import it.polimi.ingsw.communication.common.MessageInterface;

public class UseCharacterMessage implements MessageInterface {
    private String message;
    private int code;
    private int id;

    public UseCharacterMessage(int id) {
        message = "Play Character message.\r";
        code = 7;
        this.id = id;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public int getCode() {
        return code;
    }

    public int getId() {
        return id;
    }
}

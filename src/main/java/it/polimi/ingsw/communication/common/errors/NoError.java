package it.polimi.ingsw.communication.common.errors;

import it.polimi.ingsw.communication.common.MessageInterface;
import it.polimi.ingsw.communication.common.MessageType;

public class NoError implements MessageInterface {

    private String message;
    private MessageType code;

    public NoError() {
        this.message = "ok.\r";
        this.code = MessageType.NOERROR;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public MessageType getCode() {
        return code;
    }
}

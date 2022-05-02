package it.polimi.ingsw.communication.common.errors;

import it.polimi.ingsw.communication.common.MessageInterface;
import it.polimi.ingsw.communication.common.MessageType;

public class ErrorMessage implements MessageInterface {
    private String message;
    private MessageType code;

    public ErrorMessage() {
        message = "Error message.\r";
        code = MessageType.ERROR;
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

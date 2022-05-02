package it.polimi.ingsw.communication.common.errors;

import it.polimi.ingsw.communication.common.MessageInterface;
import it.polimi.ingsw.communication.common.MessageType;

public class MageError implements MessageInterface {
    private String message;
    private MessageType code;

    public MageError() {
        message = "Not valid mage.\r";
        code = MessageType.MAGEERROR;
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

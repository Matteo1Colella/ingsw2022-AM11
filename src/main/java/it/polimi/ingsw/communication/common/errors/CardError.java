package it.polimi.ingsw.communication.common.errors;

import it.polimi.ingsw.communication.common.MessageInterface;
import it.polimi.ingsw.communication.common.MessageType;

public class CardError implements MessageInterface {
    private String message;
    private MessageType code;

    public CardError() {
        message = "Error in playing card.\r";
        code = MessageType.CARDERROR;
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

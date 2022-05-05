package it.polimi.ingsw.communication.common.messages;

import it.polimi.ingsw.communication.common.MessageInterface;
import it.polimi.ingsw.communication.common.MessageType;

public class TurnMessage implements MessageInterface {
    private String message;
    private MessageType code;

    public TurnMessage() {
        message = "Is your turn.\r";
        code = MessageType.TURN;
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

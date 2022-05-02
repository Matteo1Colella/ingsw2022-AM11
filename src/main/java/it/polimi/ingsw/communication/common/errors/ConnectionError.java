package it.polimi.ingsw.communication.common.errors;

import it.polimi.ingsw.communication.common.MessageInterface;
import it.polimi.ingsw.communication.common.MessageType;

public class ConnectionError implements MessageInterface {
    private String message;
    private MessageType code;

    public ConnectionError() {
        message = "Due to a connection error, the match is terminated.\n";
        code = MessageType.CONNECTIONERROR;
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

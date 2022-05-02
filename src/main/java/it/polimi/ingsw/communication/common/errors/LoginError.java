package it.polimi.ingsw.communication.common.errors;

import it.polimi.ingsw.communication.common.MessageInterface;
import it.polimi.ingsw.communication.common.MessageType;

public class LoginError implements MessageInterface {

    private String message;
    private MessageType code;

    public LoginError() {
        message = "Login error.\r";
        code = MessageType.LOGINERROR;
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

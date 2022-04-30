package it.polimi.ingsw.communication.common;

import it.polimi.ingsw.communication.common.MessageInterface;

public class ErrorMessage implements MessageInterface {
    private String message;
    private int code;

    public ErrorMessage() {
        message = "Error message.\r";
        code = -1;
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public int getCode() {
        return 0;
    }
}

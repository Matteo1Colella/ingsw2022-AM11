package it.polimi.ingsw.communication.common.errors;

import it.polimi.ingsw.communication.common.MessageInterface;

public class MageError implements MessageInterface {
    private String message;
    private int code;

    public MageError() {
        message = "Not valid mage.\r";
        code = 101;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public int getCode() {
        return code;
    }
}

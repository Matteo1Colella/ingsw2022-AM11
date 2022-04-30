package it.polimi.ingsw.communication.common.errors;

import it.polimi.ingsw.communication.common.MessageInterface;

public class NoError implements MessageInterface {

    private String message;
    private int code;

    public NoError() {
        this.message = "ok.\r";
        this.code = 1000;
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

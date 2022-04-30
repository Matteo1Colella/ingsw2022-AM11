package it.polimi.ingsw.communication.common.errors;

import it.polimi.ingsw.communication.common.MessageInterface;

public class LoginError implements MessageInterface {

    private String message;
    private int code;

    public LoginError() {
        message = "Login error.\r";
        code = 100;
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

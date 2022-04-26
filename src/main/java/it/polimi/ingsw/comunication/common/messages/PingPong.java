package it.polimi.ingsw.comunication.common.messages;

public class PingPong {
    private String message;
    private boolean code;

    private PingPong(String message, boolean code){
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public boolean getCode() {
        return code;
    }
}



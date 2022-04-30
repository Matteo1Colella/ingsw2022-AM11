package it.polimi.ingsw.communication.common.messages;

import it.polimi.ingsw.communication.common.MessageInterface;

public class PingPongMessage implements MessageInterface {
    private String message;
    private int code;

    public PingPongMessage(String pingPong){

        if(pingPong.equalsIgnoreCase("ping")){
            this.message = pingPong;
        } else if(pingPong.equalsIgnoreCase("pong")){
            this.message = pingPong;
        }
        this.code = 0;

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
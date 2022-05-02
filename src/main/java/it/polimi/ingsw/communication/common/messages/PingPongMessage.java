package it.polimi.ingsw.communication.common.messages;

import it.polimi.ingsw.communication.common.MessageInterface;
import it.polimi.ingsw.communication.common.MessageType;

public class PingPongMessage implements MessageInterface {
    private String message;
    private MessageType code;

    public PingPongMessage(String pingPong){

        if(pingPong.equalsIgnoreCase("ping")){
            this.message = pingPong;
        } else if(pingPong.equalsIgnoreCase("pong")){
            this.message = pingPong;
        }
        this.code = MessageType.PINGPONG;

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
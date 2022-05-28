package it.polimi.ingsw.communication.common.messages;

import it.polimi.ingsw.communication.common.MessageInterface;
import it.polimi.ingsw.communication.common.MessageType;
import it.polimi.ingsw.model.Player;

public class WinMessage implements MessageInterface {

    private String message;
    private MessageType code;
    private String winner;

    public WinMessage(String player){
       this.code = MessageType.WIN;
       this.winner = player;
       this.message = "-------------------------\n" +
               "Player: " + winner + " WON!" +
               "-------------------------\n";
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

package it.polimi.ingsw.communication.common.messages;

import it.polimi.ingsw.communication.common.MessageInterface;
import it.polimi.ingsw.communication.common.MessageType;

public class MoveMotherNatureMessage implements MessageInterface {
    private String message;
    private MessageType code;
    private int moves;

    public MoveMotherNatureMessage(int moves) {
        message = "Move MotherNature message.\r";
        code = MessageType.MOTHERNATURE;
        this.moves = moves;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public MessageType getCode() {
        return code;
    }

    public int getMoves() {
        return moves;
    }
}

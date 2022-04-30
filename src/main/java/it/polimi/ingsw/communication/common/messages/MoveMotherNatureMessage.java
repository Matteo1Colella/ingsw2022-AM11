package it.polimi.ingsw.communication.common.messages;

import it.polimi.ingsw.communication.common.MessageInterface;

public class MoveMotherNatureMessage implements MessageInterface {
    private String message;
    private int code;
    private int moves;

    public MoveMotherNatureMessage(int moves) {
        message = "Move MotherNature message.\r";
        code = 6;
        this.moves = moves;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public int getCode() {
        return code;
    }

    public int getMoves() {
        return moves;
    }
}

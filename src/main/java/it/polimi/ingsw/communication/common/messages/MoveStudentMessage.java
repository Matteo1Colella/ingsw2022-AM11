package it.polimi.ingsw.communication.common.messages;

import it.polimi.ingsw.communication.common.MessageInterface;

public class MoveStudentMessage implements MessageInterface {
    private String message;
    private int code;
    private int where;
    private int position;

    public MoveStudentMessage(int where, int position) {
        message = "Move student to IslandCard or DiningRoom message.\r";
        code = 5;
        this.where = where;
        this.position = position;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public int getCode() {
        return code;
    }

    public int getWhere() {
        return where;
    }

    public int getPosition() {
        return position;
    }
}

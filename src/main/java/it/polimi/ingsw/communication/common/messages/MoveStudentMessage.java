package it.polimi.ingsw.communication.common.messages;

import it.polimi.ingsw.communication.common.MessageInterface;
import it.polimi.ingsw.communication.common.MessageType;

public class MoveStudentMessage implements MessageInterface {
    private String message;
    private MessageType code;
    private int where;
    private int position;

    public MoveStudentMessage(int where, int position) {
        message = "Move student to IslandCard or DiningRoom message.\r";
        code = MessageType.STUDENT;
        this.where = where;
        this.position = position;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public MessageType getCode() {
        return code;
    }

    public int getWhere() {
        return where;
    }

    public int getPosition() {
        return position;
    }
}

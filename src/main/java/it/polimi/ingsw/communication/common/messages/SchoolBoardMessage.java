package it.polimi.ingsw.communication.common.messages;

import it.polimi.ingsw.communication.common.MessageInterface;
import it.polimi.ingsw.communication.common.MessageType;
import it.polimi.ingsw.model.board.SchoolBoard;

public class SchoolBoardMessage implements MessageInterface {
    private String message;
    private MessageType code;
    private SchoolBoard schoolBoard;

    public SchoolBoardMessage(String message, MessageType code, SchoolBoard schoolBoard) {
        this.message = message;
        this.code = code;
        this.schoolBoard = schoolBoard;
    }

    public SchoolBoardMessage() {
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(MessageType code) {
        this.code = code;
    }

    public SchoolBoard getSchoolBoard() {
        return schoolBoard;
    }

    public void setSchoolBoard(SchoolBoard schoolBoard) {
        this.schoolBoard = schoolBoard;
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

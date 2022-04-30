package it.polimi.ingsw.communication.common.messages;

import it.polimi.ingsw.communication.common.MessageInterface;

public class PlayCardMessage implements MessageInterface {
    private String message;
    private int code;
    private int idCard;

    public PlayCardMessage(int idCard) {
        message = "Play card message.\r";
        code = 3;
        this.idCard = idCard;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public int getCode() {
        return code;
    }

    public int getIdCard() {
        return idCard;
    }
}

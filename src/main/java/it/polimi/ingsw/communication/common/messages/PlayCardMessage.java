package it.polimi.ingsw.communication.common.messages;

import it.polimi.ingsw.communication.common.MessageInterface;
import it.polimi.ingsw.communication.common.MessageType;

public class PlayCardMessage implements MessageInterface {
    private String message;
    private MessageType code;
    private int idCard;

    public PlayCardMessage(int idCard) {
        message = "Play card message.\r";
        code = MessageType.CARD;
        this.idCard = idCard;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public MessageType getCode() {
        return code;
    }

    public int getIdCard() {
        return idCard;
    }
}

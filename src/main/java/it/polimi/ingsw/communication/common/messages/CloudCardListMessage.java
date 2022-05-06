package it.polimi.ingsw.communication.common.messages;

import it.polimi.ingsw.communication.common.MessageInterface;
import it.polimi.ingsw.communication.common.MessageType;
import it.polimi.ingsw.model.board.CloudCard;

import java.util.List;

public class CloudCardListMessage implements MessageInterface {
    private String message;
    private MessageType code;
    private List<CloudCard> cloudCardList;


    public CloudCardListMessage() {
    }

    public CloudCardListMessage(String message, MessageType code, List<CloudCard> cloudCardList) {
        this.message = message;
        this.code = code;
        this.cloudCardList = cloudCardList;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(MessageType code) {
        this.code = code;
    }

    public List<CloudCard> getCloudCardList() {
        return cloudCardList;
    }

    public void setCloudCardList(List<CloudCard> cloudCardList) {
        this.cloudCardList = cloudCardList;
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

package it.polimi.ingsw.communication.common.messages;

import it.polimi.ingsw.communication.common.MessageInterface;
import it.polimi.ingsw.communication.common.MessageType;

public class CloudCardMessage implements MessageInterface {
    private String message;
    private MessageType code;
    private int cloud;

    public CloudCardMessage(int cloud) {
        message = "Cloud selection message.\r";
        code = MessageType.CLOUDCARD;
        this.cloud = cloud;
    }

    public String getMessage() {
        return message;
    }

    public MessageType getCode() {
        return code;
    }

    public int getCloud() {
        return cloud;
    }
}

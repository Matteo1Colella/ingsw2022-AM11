package it.polimi.ingsw.communication.common.messages;

import it.polimi.ingsw.communication.common.MessageInterface;

public class CloudCardMessage implements MessageInterface {
    private String message;
    private int code;
    private int cloud;

    public CloudCardMessage(int cloud) {
        message = "Cloud selection message.\r";
        code = 4;
        this.cloud = cloud;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public int getCloud() {
        return cloud;
    }
}

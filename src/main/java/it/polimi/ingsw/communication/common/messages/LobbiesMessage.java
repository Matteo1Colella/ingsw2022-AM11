package it.polimi.ingsw.communication.common.messages;

import it.polimi.ingsw.communication.common.MessageInterface;

public class LobbiesMessage implements MessageInterface {

    private String message;
    private int code;
    private int idLobby;

    public LobbiesMessage(int idLobby) {
        message = "Current lobby message.\r";
        code = 8;
        this.idLobby = idLobby;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public int getCode() {
        return code;
    }

    public int getIdLobby() {
        return idLobby;
    }
}

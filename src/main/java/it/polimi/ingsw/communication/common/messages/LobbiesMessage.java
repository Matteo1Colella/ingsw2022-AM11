package it.polimi.ingsw.communication.common.messages;

import it.polimi.ingsw.communication.common.MessageInterface;
import it.polimi.ingsw.communication.common.MessageType;

public class LobbiesMessage implements MessageInterface {

    private String message;
    private MessageType code;
    private int idLobby;

    public LobbiesMessage(int idLobby) {
        message = "Current lobby message.\r";
        code = MessageType.LOBBIES;
        this.idLobby = idLobby;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public MessageType getCode() {
        return code;
    }

    public int getIdLobby() {
        return idLobby;
    }
}

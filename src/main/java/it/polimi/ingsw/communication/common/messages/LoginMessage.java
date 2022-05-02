package it.polimi.ingsw.communication.common.messages;

import it.polimi.ingsw.communication.common.MessageInterface;
import it.polimi.ingsw.communication.common.MessageType;

public class LoginMessage implements MessageInterface {
    private String message;
    private MessageType code;
    private String username;
    private int numOfPlayers;
    private boolean isPro;

    public LoginMessage(String username, int numOfPlayers, boolean isPro) {
        message = "Login message.\r";
        code = MessageType.LOGIN;
        this.username = username;
        this.numOfPlayers = numOfPlayers;
        this.isPro = isPro;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public MessageType getCode() {
        return code;
    }

    public String getUsername() {
        return username;
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public boolean isPro() {
        return isPro;
    }
}

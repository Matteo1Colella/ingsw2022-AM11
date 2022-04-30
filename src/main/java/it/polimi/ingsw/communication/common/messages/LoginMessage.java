package it.polimi.ingsw.communication.common.messages;

import it.polimi.ingsw.communication.common.MessageInterface;

public class LoginMessage implements MessageInterface {
    private String message;
    private int code;
    private String username;
    private int numOfPlayers;
    private boolean isPro;

    public LoginMessage(String username, int numOfPlayers, boolean isPro) {
        message = "Login message.\r";
        code = 1;
        this.username = username;
        this.numOfPlayers = numOfPlayers;
        this.isPro = isPro;
    }

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public int getCode() {
        return 0;
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

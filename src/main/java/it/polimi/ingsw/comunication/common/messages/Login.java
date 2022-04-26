package it.polimi.ingsw.comunication.common.messages;

public class Login {
    private String userName;
    private int numOfPlayers;
    private boolean isPro;

    public Login(String userName, int numOfPlayers, boolean isPro) {
        this.userName = userName;
        this.numOfPlayers = numOfPlayers;
        this.isPro = isPro;
    }

    public String getUserName() {
        return userName;
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public boolean isPro() {
        return isPro;
    }
}

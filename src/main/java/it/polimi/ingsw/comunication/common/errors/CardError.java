package it.polimi.ingsw.comunication.common.errors;

public class CardError {
    private String cardError;
    private int code;

    void CardError(int i){
        switch (i){
            case 1:
                cardError = "Invalid number error: choose an existing card.\r";
                code = 200;
                break;
            case 2:
                cardError = "Already chosen card error: choose\r";
                code = 201;
                break;
            case 3:
                cardError = "Missing card error: you already played this card.\r";
                code = 202;
                break;
        }
    }

    public String getCardError() {
        return cardError;
    }

    public int getCode() {
        return code;
    }
}

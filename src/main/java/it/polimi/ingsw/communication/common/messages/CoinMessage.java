package it.polimi.ingsw.communication.common.messages;

import it.polimi.ingsw.communication.common.MessageInterface;
import it.polimi.ingsw.communication.common.MessageType;

public class CoinMessage implements MessageInterface {
    private String message;
    private MessageType code;
    private int coinOwned;

    public CoinMessage() {
        this.message = "coins\r";
        this.code = MessageType.COIN;
    }

    public CoinMessage( int coinOwned) {
        this.message = "coins\r";
        this.code = MessageType.COIN;
        this.coinOwned = coinOwned;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(MessageType code) {
        this.code = code;
    }

    public int getCoinOwned() {
        return coinOwned;
    }

    public void setCoinOwned(int coinOwned) {
        this.coinOwned = coinOwned;
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

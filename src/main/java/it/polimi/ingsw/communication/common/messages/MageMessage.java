package it.polimi.ingsw.communication.common.messages;

import it.polimi.ingsw.communication.common.MessageInterface;

public class MageMessage implements MessageInterface {
    private String message;
    private int code;
    private int mage;
    private int[] availableMage;

    public MageMessage(int mage) {
        message = "Select mage message.\r";
        code = 2;
        this.mage = mage;
        availableMage = null;
    }

    public MageMessage(){
        message = "Ask for available mage message.\r";
        code = 2;
        this.mage = 0;
        availableMage = null;
    }

    public MageMessage(int[] availableMage){
        message = "Available mage message.\r";
        code = 2;
        this.mage = 0;
        this.availableMage = availableMage;
    }

    @Override
    public int getCode() {
        return code;
    }

    public int getMageSelection() {
        return mage;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int[] getAviableMage() {
        return availableMage;
    }
}

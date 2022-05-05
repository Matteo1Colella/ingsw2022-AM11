package it.polimi.ingsw.communication.common.messages;

import it.polimi.ingsw.communication.common.MessageInterface;
import it.polimi.ingsw.communication.common.MessageType;

public class MageMessage implements MessageInterface {
    private String message;
    private MessageType code;
    private int mage;
    private int[] availableMage;

    public MageMessage(int mage) {
        message = "Select mage message.\r";
        code = MessageType.MAGE;
        this.mage = mage;
        availableMage = null;
    }

    public MageMessage(){
        message = "Ask for mage message.\r";
        code = MessageType.MAGE;
        this.mage = 0;
        availableMage = null;
    }

    public MageMessage(int[] availableMage){
        message = "Available mage message.\r";
        code = MessageType.MAGE;
        this.mage = 0;
        this.availableMage = availableMage;
    }

    @Override
    public MessageType getCode() {
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

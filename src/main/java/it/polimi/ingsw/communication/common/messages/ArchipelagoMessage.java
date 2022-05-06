package it.polimi.ingsw.communication.common.messages;

import it.polimi.ingsw.communication.common.MessageInterface;
import it.polimi.ingsw.communication.common.MessageType;
import it.polimi.ingsw.model.board.IslandCard;
import it.polimi.ingsw.model.board.SchoolBoard;

import java.util.ArrayList;

public class ArchipelagoMessage implements MessageInterface {
    private String message;
    private MessageType code;
    private ArrayList<IslandCard> archipelago;

    public ArchipelagoMessage(String message, MessageType code, ArrayList<IslandCard> archipelago) {
        this.message = message;
        this.code = code;
        this.archipelago = archipelago;
    }

    public ArchipelagoMessage() {
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(MessageType code) {
        this.code = code;
    }

    public ArrayList<IslandCard> getArchipelago() {
        return archipelago;
    }

    public void setArchipelago(ArrayList<IslandCard> archipelago) {
        this.archipelago = archipelago;
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

package it.polimi.ingsw.communication.common.messages;

import it.polimi.ingsw.communication.common.MessageInterface;
import it.polimi.ingsw.communication.common.MessageType;
import it.polimi.ingsw.model.cards.CharacterCard;

import java.util.ArrayList;

public class CharacterCardsMessage implements MessageInterface {
    private String message;
    private MessageType code;
    private ArrayList<CharacterCard> characterCards;

    public CharacterCardsMessage( ArrayList<CharacterCard> characterCards) {
        this.message = "list of character cards\r";
        this.code = MessageType.CHARACTERLIST;
        this.characterCards = characterCards;
    }

    public CharacterCardsMessage() {
        this.message = "list of character cards\r";
        this.code = MessageType.CHARACTERLIST;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(MessageType code) {
        this.code = code;
    }

    public ArrayList<CharacterCard> getCharacterCards() {
        return characterCards;
    }

    public void setCharacterCards(ArrayList<CharacterCard> characterCards) {
        this.characterCards = characterCards;
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

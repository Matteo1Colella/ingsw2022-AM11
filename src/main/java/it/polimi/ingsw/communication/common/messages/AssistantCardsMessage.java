package it.polimi.ingsw.communication.common.messages;

import it.polimi.ingsw.communication.common.MessageInterface;
import it.polimi.ingsw.communication.common.MessageType;
import it.polimi.ingsw.model.cards.Card;


import java.util.List;

public class AssistantCardsMessage implements MessageInterface {
    private String message;
    private MessageType code;
    private List<Card> deck; //for the message of list of remaining assistantCards
    private List<Card> chosenCard; //for the message of list of chosen cards played from all the player in a round

    public AssistantCardsMessage() {
        message = "Ask for cards message.\r";
        code = MessageType.CARD;
    }

    public AssistantCardsMessage(List<Card> deck, List<Card> chosenCard) {
        this.message = "Get you cards message.\r";
        this.code = MessageType.CARD;
        this.deck = deck;
        this.chosenCard = chosenCard;
    }

    public List<Card> getChosenCard() {
        return chosenCard;
    }

    public void setChosenCard(List<Card> chosenCard) {
        this.chosenCard = chosenCard;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCode(MessageType code) {
        this.code = code;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public void setDeck(List<Card> deck) {
        this.deck = deck;
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

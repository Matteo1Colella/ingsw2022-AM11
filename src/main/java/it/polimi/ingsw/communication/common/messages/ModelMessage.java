package it.polimi.ingsw.communication.common.messages;

import it.polimi.ingsw.communication.common.MessageInterface;
import it.polimi.ingsw.communication.common.MessageType;
import it.polimi.ingsw.model.board.CloudCard;
import it.polimi.ingsw.model.board.IslandCard;
import it.polimi.ingsw.model.board.SchoolBoard;
import it.polimi.ingsw.model.cards.CharacterCard;

import java.util.ArrayList;
import java.util.List;

public class ModelMessage implements MessageInterface {
    private String message;
    private MessageType code;
    private ArrayList<IslandCard> archipelago;
    private List<CloudCard> cloudCardList;
    private SchoolBoard schoolBoard;
    private ArrayList<CharacterCard> characterCards;
    private int coinOwned;

    //generic constructor
    public ModelMessage() {
        this.message = "Model message\r";
        this.code = MessageType.MODEL;
        this.characterCards = null;
        this.coinOwned = -100;
    }

    //normal
    public ModelMessage( ArrayList<IslandCard> archipelago, List<CloudCard> cloudCardList, SchoolBoard schoolBoard) {
        this.message = "Model message\r";
        this.code = MessageType.MODEL;
        this.archipelago = archipelago;
        this.cloudCardList = cloudCardList;
        this.schoolBoard = schoolBoard;
        this.characterCards = null;
        this.coinOwned = -100;
    }

    //pro
    public ModelMessage(String message, MessageType code, ArrayList<IslandCard> archipelago, List<CloudCard> cloudCardList, SchoolBoard schoolBoard, ArrayList<CharacterCard> characterCards, int coinOwned) {
        this.message = "Model message\r";
        this.code = MessageType.MODEL;
        this.archipelago = archipelago;
        this.cloudCardList = cloudCardList;
        this.schoolBoard = schoolBoard;
        this.characterCards = characterCards;
        this.coinOwned = coinOwned;
    }

    public ArrayList<CharacterCard> getCharacterCards() {
        return characterCards;
    }

    public void setCharacterCards(ArrayList<CharacterCard> characterCards) {
        this.characterCards = characterCards;
    }

    public int getCoinOwned() {
        return coinOwned;
    }

    public void setCoinOwned(int coinOwned) {
        this.coinOwned = coinOwned;
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

    public List<CloudCard> getCloudCardList() {
        return cloudCardList;
    }

    public void setCloudCardList(List<CloudCard> cloudCardList) {
        this.cloudCardList = cloudCardList;
    }

    public SchoolBoard getSchoolBoard() {
        return schoolBoard;
    }

    public void setSchoolBoard(SchoolBoard schoolBoard) {
        this.schoolBoard = schoolBoard;
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

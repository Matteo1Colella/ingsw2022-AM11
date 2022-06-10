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
    private SchoolBoard schoolBoard1;
    private SchoolBoard schoolBoard2;
    private SchoolBoard schoolBoard3;
    private SchoolBoard schoolBoard4;
    private ArrayList<String> playerNames;

    private ArrayList<CharacterCard> characterCards;
    private int coinOwned;

    //generic constructor
    public ModelMessage() {
        this.message = "Model message\r";
        this.code = MessageType.MODEL;
        this.characterCards = null;
        this.coinOwned = -100;
    }

    //With all the schoolBoard
    //normal
    public ModelMessage( ArrayList<IslandCard> archipelago, List<CloudCard> cloudCardList,SchoolBoard schoolBoard, SchoolBoard schoolBoard1,SchoolBoard schoolBoard2,SchoolBoard schoolBoard3,SchoolBoard schoolBoard4, ArrayList<String> playerNames) {
        this.message = "Model message\r";
        this.code = MessageType.MODEL;
        this.archipelago = archipelago;
        this.cloudCardList = cloudCardList;
        this.schoolBoard = schoolBoard;
        this.schoolBoard1 = schoolBoard1;
        this.schoolBoard2 = schoolBoard2;
        this.schoolBoard3 = schoolBoard3;
        this.schoolBoard4 = schoolBoard4;
        this.characterCards = null;
        this.coinOwned = -100;
        this.playerNames = playerNames;
    }
    //pro
    public ModelMessage(ArrayList<IslandCard> archipelago, List<CloudCard> cloudCardList, SchoolBoard schoolBoard, ArrayList<CharacterCard> characterCards, int coinOwned,SchoolBoard schoolBoard1,SchoolBoard schoolBoard2,SchoolBoard schoolBoard3,SchoolBoard schoolBoard4, ArrayList<String> playerNames) {
        this.message = "Model message\r";
        this.code = MessageType.MODEL;
        this.archipelago = archipelago;
        this.cloudCardList = cloudCardList;
        this.schoolBoard = schoolBoard;
        this.characterCards = characterCards;
        this.coinOwned = coinOwned;
        this.schoolBoard1 = schoolBoard1;
        this.schoolBoard2 = schoolBoard2;
        this.schoolBoard3 = schoolBoard3;
        this.schoolBoard4 = schoolBoard4;
        this.playerNames = playerNames;
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

    public SchoolBoard getSchoolBoard1() {
        return schoolBoard1;
    }

    public SchoolBoard getSchoolBoard2() {
        return schoolBoard2;
    }

    public SchoolBoard getSchoolBoard3() {
        return schoolBoard3;
    }

    public SchoolBoard getSchoolBoard4() {
        return schoolBoard4;
    }

    public ArrayList<String> getPlayerNames() {
        return playerNames;
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

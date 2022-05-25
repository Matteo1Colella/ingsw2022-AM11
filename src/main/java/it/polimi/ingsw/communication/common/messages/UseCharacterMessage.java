package it.polimi.ingsw.communication.common.messages;

import it.polimi.ingsw.communication.common.MessageInterface;
import it.polimi.ingsw.communication.common.MessageType;
import it.polimi.ingsw.model.colors.ColorStudent;

public class UseCharacterMessage implements MessageInterface {
    private String message;
    private MessageType code;
    private int id;
    private int island;
    private int student;
    private int[] studentsFromEntrance;
    private int[] studentsOnCard;
    private ColorStudent colorStudent;
    private int[] studentsFromDinignRoom;
    int choice;

    public UseCharacterMessage() {
        message = "Play character.\r";
        code = MessageType.CHARACTERCHOICE;
        id = 0;
        island = 0;
        student = 0;
        studentsFromEntrance = null;
        studentsOnCard = null;
        colorStudent = null;
        studentsFromDinignRoom = null;
        choice = -1;
    }

    public UseCharacterMessage useCharacter1Message(int island, int student, int choice){
        message = "Play character 1.\r";
        code = MessageType.CHARACTERCHOICE;
        id = 1;
        this.island = island;
        this.student = student;
        studentsFromEntrance = null;
        studentsOnCard = null;
        colorStudent = null;
        studentsFromDinignRoom = null;
        this.choice = choice;
        return this;
    }

    public UseCharacterMessage useCharacter2Message(int choice){
        message = "Play character 2.\r";
        code = MessageType.CHARACTERCHOICE;
        id = 2;
        island = 0;
        student = 0;
        studentsFromEntrance = null;
        studentsOnCard = null;
        colorStudent = null;
        studentsFromDinignRoom = null;
        this.choice = choice;
        return this;
    }

    public UseCharacterMessage useCharacter3Message(int island, int choice){
        message = "Play character 3.\r";
        code = MessageType.CHARACTERCHOICE;
        id = 3;
        this.island = island;
        student = 0;
        studentsFromEntrance = null;
        studentsOnCard = null;
        colorStudent = null;
        studentsFromDinignRoom = null;
        this.choice = choice;
        return this;
    }

    public UseCharacterMessage useCharacter4Message(int choice){
        message = "Play character 4.\r";
        code = MessageType.CHARACTERCHOICE;
        id = 4;
        island = 0;
        student = 0;
        studentsFromEntrance = null;
        studentsOnCard = null;
        colorStudent = null;
        studentsFromDinignRoom = null;
        this.choice = choice;
        return this;
    }

    public UseCharacterMessage useCharacter5Message(int island, int choice){
        message = "Play character 5.\r";
        code = MessageType.CHARACTERCHOICE;
        id = 5;
        this.island = island;
        student = 0;
        studentsFromEntrance = null;
        studentsOnCard = null;
        colorStudent = null;
        studentsFromDinignRoom = null;
        this.choice = choice;
        return this;
    }

    public UseCharacterMessage useCharacter6Message(int choice){
        message = "Play character 6.\r";
        code = MessageType.CHARACTERCHOICE;
        id = 6;
        island = 0;
        student = 0;
        studentsFromEntrance = null;
        studentsOnCard = null;
        colorStudent = null;
        studentsFromDinignRoom = null;
        this.choice = choice;
        return this;
    }

    public UseCharacterMessage useCharacter7Message(int[] studentsFromEntrance, int[] studentsOnCard, int choice){
        message = "Play character 7.\r";
        code = MessageType.CHARACTERCHOICE;
        id = 7;
        island = 0;
        student = 0;
        this.studentsFromEntrance = studentsFromEntrance;
        this.studentsOnCard = studentsOnCard;
        colorStudent = null;
        studentsFromDinignRoom = null;
        this.choice = choice;
        return this;
    }

    public UseCharacterMessage useCharacter8Message(int choice){
        message = "Play character 8.\r";
        code = MessageType.CHARACTERCHOICE;
        id = 8;
        island = 0;
        student = 0;
        studentsFromEntrance = null;
        studentsOnCard = null;
        colorStudent = null;
        studentsFromDinignRoom = null;
        this.choice = choice;
        return this;
    }

    public UseCharacterMessage useCharacter9Message(ColorStudent colorStudent, int choice){
        message = "Play character 9.\r";
        code = MessageType.CHARACTERCHOICE;
        id = 9;
        island = 0;
        student = 0;
        studentsFromEntrance = null;
        studentsOnCard = null;
        this.colorStudent = colorStudent;
        studentsFromDinignRoom = null;
        this.choice = choice;
        return this;
    }

    public UseCharacterMessage useCharacter10Message(int[] studentsFromEntrance, int[] studentsFromDinignRoom, int choice){
        message = "Play character 10.\r";
        code = MessageType.CHARACTERCHOICE;
        id = 6;
        island = 0;
        student = 0;
        this.studentsFromEntrance = studentsFromEntrance;
        studentsOnCard = null;
        colorStudent = null;
        this.studentsFromDinignRoom = studentsFromDinignRoom;
        this.choice = choice;
        return this;
    }

    public UseCharacterMessage useCharacter11Message(int student, int choice){
        message = "Play character 11.\r";
        code = MessageType.CHARACTERCHOICE;
        id = 11;
        island = 0;
        this.student = student;
        studentsFromEntrance = null;
        studentsOnCard = null;
        colorStudent = null;
        studentsFromDinignRoom = null;
        this.choice = choice;
        return this;
    }

    public UseCharacterMessage useCharacter12Message(ColorStudent colorStudent, int choice){
        message = "Play character 12.\r";
        code = MessageType.CHARACTERCHOICE;
        id = 12;
        island = 0;
        student = 0;
        studentsFromEntrance = null;
        studentsOnCard = null;
        this.colorStudent = colorStudent;
        studentsFromDinignRoom = null;
        this.choice = choice;
        return this;
    }

    public int getId() {
        return id;
    }

    public int getIslandCharacter() {
        return island;
    }

    public int getStudentCharacter() {
        return student;
    }

    public int[] getStudentsFromEntranceCharacter() {
        return studentsFromEntrance;
    }

    public int[] getStudentsOnCardCharacter() {
        return studentsOnCard;
    }

    public ColorStudent getColorStudentCharacter() {
        return colorStudent;
    }

    public int[] getStudentsFromDinignRoomCharacter() {
        return studentsFromDinignRoom;
    }

    public int getChoice() {
        return choice;
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

package it.polimi.ingsw.communication.common.messages;

import it.polimi.ingsw.communication.common.MessageInterface;
import it.polimi.ingsw.communication.common.MessageType;

public class MoveStudentMessage implements MessageInterface {
    private String message;
    private MessageType code;
    private int student1Entrance;
    private int student1WhereToPut;
    private int indexIslandIf1ToIsland;
    private int student2Entrance;
    private int student2WhereToPut;
    private int indexIslandIf2ToIsland;
    private int student3Entrance;
    private int student3WhereToPut;
    private int indexIslandIf3ToIsland;
    private int student4Entrance;
    private int student4WhereToPut;
    private int indexIslandIf4ToIsland;


    public MoveStudentMessage(int student1Entrance, int student1WhereToPut, int indexIslandIf1ToIsland,
                              int student2Entrance, int student2WhereToPut, int indexIslandIf2ToIsland,
                              int student3Entrance, int student3WhereToPut, int indexIslandIf3ToIsland,
                              int student4Entrance, int student4WhereToPut, int indexIslandIf4ToIsland) {
        message = "Move student to IslandCard or DiningRoom message.\r";
        code = MessageType.STUDENT;
        this.student1Entrance = student1Entrance;
        this.student1WhereToPut = student1WhereToPut;
        this.indexIslandIf1ToIsland = indexIslandIf1ToIsland;
        this.student2Entrance = student2Entrance;
        this.student2WhereToPut = student2WhereToPut;
        this.indexIslandIf2ToIsland = indexIslandIf2ToIsland;
        this.student3Entrance = student3Entrance;
        this.student3WhereToPut = student3WhereToPut;
        this.indexIslandIf3ToIsland = indexIslandIf3ToIsland;
        this.student4Entrance = student4Entrance;
        this.student4WhereToPut = student4WhereToPut;
        this.indexIslandIf4ToIsland = indexIslandIf4ToIsland;
    }

    public MoveStudentMessage() {
        message = "Move student to IslandCard or DiningRoom message.\r";
        code = MessageType.STUDENT;
        this.indexIslandIf1ToIsland = -1;
        this.indexIslandIf2ToIsland = -1;
        this.indexIslandIf3ToIsland = -1;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public MessageType getCode() {
        return code;
    }

    public int getStudent1Entrance() {
        return student1Entrance;
    }

    public int getStudent1WhereToPut() {
        return student1WhereToPut;
    }

    public int getStudent2Entrance() {
        return student2Entrance;
    }

    public int getStudent2WhereToPut() {
        return student2WhereToPut;
    }

    public int getStudent3Entrance() {
        return student3Entrance;
    }

    public int getStudent3WhereToPut() {
        return student3WhereToPut;
    }

    public int getIndexIslandIf1ToIsland() {
        return indexIslandIf1ToIsland;
    }

    public int getIndexIslandIf2ToIsland() {
        return indexIslandIf2ToIsland;
    }

    public int getIndexIslandIf3ToIsland() {
        return indexIslandIf3ToIsland;
    }

    public int getStudent4Entrance() {
        return student4Entrance;
    }

    public int getStudent4WhereToPut() {
        return student4WhereToPut;
    }

    public int getIndexIslandIf4ToIsland() {
        return indexIslandIf4ToIsland;
    }
}

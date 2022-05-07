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

    public MoveStudentMessage(int student1Entrance, int student1WhereToPut, int indexIslandIf1ToIsland, int student2Entrance, int student2WhereToPut, int indexIslandIf2ToIsland, int student3Entrance, int student3WhereToPut, int indexIslandIf3ToIsland) {
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

    public void setStudent1Entrance(int student1Entrance) {
        this.student1Entrance = student1Entrance;
    }

    public void setStudent1WhereToPut(int student1WhereToPut) {
        this.student1WhereToPut = student1WhereToPut;
    }

    public void setIndexIslandIf1ToIsland(int indexIslandIf1ToIsland) {
        this.indexIslandIf1ToIsland = indexIslandIf1ToIsland;
    }

    public void setStudent2Entrance(int student2Entrance) {
        this.student2Entrance = student2Entrance;
    }

    public void setStudent2WhereToPut(int student2WhereToPut) {
        this.student2WhereToPut = student2WhereToPut;
    }

    public void setIndexIslandIf2ToIsland(int indexIslandIf2ToIsland) {
        this.indexIslandIf2ToIsland = indexIslandIf2ToIsland;
    }

    public void setStudent3Entrance(int student3Entrance) {
        this.student3Entrance = student3Entrance;
    }

    public void setStudent3WhereToPut(int student3WhereToPut) {
        this.student3WhereToPut = student3WhereToPut;
    }

    public void setIndexIslandIf3ToIsland(int indexIslandIf3ToIsland) {
        this.indexIslandIf3ToIsland = indexIslandIf3ToIsland;
    }
}

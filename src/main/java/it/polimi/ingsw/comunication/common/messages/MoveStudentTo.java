package it.polimi.ingsw.comunication.common.messages;

public class MoveStudentTo {
    private int idIsland;
    private int studentPosition;

    public MoveStudentTo(int idIsland, int studentPosition) {
        this.idIsland = idIsland;
        this.studentPosition = studentPosition;
    }

    public int getIdIsland() {
        return idIsland;
    }

    public int getStudentPosition() {
        return studentPosition;
    }
}

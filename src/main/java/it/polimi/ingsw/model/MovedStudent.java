package it.polimi.ingsw.model;

public class MovedStudent {
    private int index;
    private int pose;
    private int island;

    public int getIndex() {
        return index;
    }

    public int getPose() {
        return pose;
    }

    public int getIsland() {
        return island;
    }

    public MovedStudent(int index, int pose, int island) {
        this.index = index;
        this.pose = pose;
        this.island = island;
    }
}

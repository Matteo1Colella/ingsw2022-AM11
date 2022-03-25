package it.polimi.ingsw.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;

public class SchoolBoard extends Board{

    private final ArrayList<Tower> towers;
    private final ArrayList<DiningRoom> diningRooms; //a class is an array of students
    private final Entrance entrance;

    //Costructor
    public SchoolBoard(ColorTower colorTower, int numOfPlayers, Collection<Student> students){

        this.towers = new ArrayList<>();
        this.diningRooms = new ArrayList<>();

        generateTowers(numOfPlayers, colorTower);

        ColorStudent[] colorStudent = ColorStudent.values();
        for(ColorStudent tempColor : colorStudent){
            DiningRoom diningRoom = new DiningRoom(tempColor);
            this.diningRooms.add(diningRoom);
        }

        this.entrance = new Entrance(students);
    }

    //Getter and setter methods
    public ArrayList<Tower> getTowers() {
        return towers;
    }

    private void generateTowers(int numOfPlayers, ColorTower colorTower){
        if(numOfPlayers == 3){
            for(int i = 0; i < 6; i++){
                Tower tempTower = new Tower(colorTower);
                this.towers.add(tempTower);

            }
        } else {
            for(int i = 0; i < 8; i++){
                Tower tempTower = new Tower(colorTower);
                towers.add(tempTower);
            }
        }
    }

    //a tower can be placed on the islands
    public void moveTower(IslandCard islandCard){
        if(this.getTowers().size() > 0){
            Tower movedTower = towers.get(towers.size() - 1);
            this.towers.remove(towers.size() - 1);

            movedTower.setTowerPosition(islandCard);
        } else if(this.getTowers().size() == 1) {
            Tower movedTower = towers.get(towers.size() - 1);
            this.towers.remove(towers.size() - 1);

            movedTower.setTowerPosition(islandCard);
            System.out.println("The player has won.");
        }
    }

    public void moveStudent(int position){
        Student student = this.chooseStudentFromEntrance(position);
        DiningRoom diningRoom = (DiningRoom) diningRooms.stream().filter(diningRoom1 -> diningRoom1.getColor().equals(student.getColor()));
        student.setPosition(diningRoom);
        diningRoom.addStudent(student);
    }

    //a student can be placed on an island
    public void moveStudent(int position, IslandCard islandCard){
        Student student = this.chooseStudentFromEntrance(position);
        student.setPosition(islandCard);
    }
    //a student can be placed in the dining room

    //set the professor of the color specified color
    public void setProfessor(Professor professor){
        this.getDiningRoom(professor.getColor()).setProfessor(professor);
    }

    //get the occupation of a specified color
    public int getOccupation(ColorStudent colorStudent){
         return this.getDiningRoom(colorStudent).getOccupation();
    }

    //check if it is possible to earn a coin from a dining room
    public boolean giveCoin(ColorStudent colorStudent){
        return this.getDiningRoom(colorStudent).giveCoin();
    }

    //add students to the entrance
    public void addStudetsToEntrance(Collection<Student> students){
        this.getEntrance().addStudents(students);
    }

    //it returns the color of the tower
    public ColorTower schoolBoardTowerColor(){
        return this.getTowers().get(0).getColor();
    }

    //choose a student from the entrance
    public Student chooseStudentFromEntrance(int position){
        return this.getEntrance().chooseStudent(position);
    }

    private DiningRoom getDiningRoom(ColorStudent color) {
        return (DiningRoom) diningRooms.stream().filter(diningRoom1 -> diningRoom1.getColor().equals(color));
    }

    private Entrance getEntrance(){
        return entrance;
    }
}

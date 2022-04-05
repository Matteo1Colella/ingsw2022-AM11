package it.polimi.ingsw.model.board;

import it.polimi.ingsw.model.pieces.Professor;
import it.polimi.ingsw.model.pieces.Student;
import it.polimi.ingsw.model.pieces.Tower;
import it.polimi.ingsw.model.colors.ColorStudent;
import it.polimi.ingsw.controller.ColorTower;

import java.util.ArrayList;
import java.util.Collection;


public class SchoolBoard implements Board {

    private final ArrayList<Tower> towers;
    private final ArrayList<DiningRoom> diningRooms; //a class is an array of students
    private final Entrance entrance;

    //Constructor
    public SchoolBoard(ColorTower colorTower, int numOfPlayers, Collection<Student> students){

        this.towers = new ArrayList<>();
        this.diningRooms = new ArrayList<>();

        generateTowers(numOfPlayers, colorTower);

        ColorStudent[] colorStudent = ColorStudent.values();
        for(ColorStudent tempColor : colorStudent){
            DiningRoom diningRoom = new DiningRoom(tempColor);
            this.diningRooms.add(diningRoom);
        }

        this.entrance = new Entrance();
    }

    public SchoolBoard( int numOfPlayers, Collection<Student> students){

        this.towers = new ArrayList<>();
        towers.clear();
        this.diningRooms = new ArrayList<>();

        ColorStudent[] colorStudent = ColorStudent.values();
        for(ColorStudent tempColor : colorStudent){
            DiningRoom diningRoom = new DiningRoom(tempColor);
            this.diningRooms.add(diningRoom);
        }

        this.entrance = new Entrance();
    }

    //Getter and setter methods
    public ArrayList<Tower> getTowers() {
        return towers;
    }

    public ArrayList<DiningRoom> getDiningRooms() {
        return diningRooms;
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
    // get a dining room by color
    public DiningRoom getDiningRoomByColor(ColorStudent color){
        for(DiningRoom temp : diningRooms){
            if (temp.getColor().equals(color)) return temp;
        }
        return null;
    }

    //move a student in the dining room
    public void moveStudent(Student student){
        int position = this.getStudentPosition(student);
        Student movedStudent = this.chooseStudentFromEntrance(position);
        DiningRoom diningRoom = (DiningRoom) diningRooms.stream().filter(diningRoom1 -> diningRoom1.getColor().equals(movedStudent.getColor()));
        student.setPosition((Board) diningRoom);
        diningRoom.addStudent(movedStudent);
    }

    //a student can be placed on an island
    public void moveStudent(Student student, IslandCard islandCard){
        int position = this.getStudentPosition(student);
        Student movedStudent = this.chooseStudentFromEntrance(position);
        movedStudent.setPosition(islandCard);
    }
    //a student can be placed in the dining room

    //set the professor of the specified color
    public void setProfessor(Professor professor){
        DiningRoom diningRoom = this.getDiningRoom(professor.getColor());
        assert diningRoom != null;
        diningRoom.setProfessor(professor);
        professor.setPosition(diningRoom);
    }

    //get the occupation of a specified color
    public int getStudentSize(ColorStudent colorStudent){
        return this.getDiningRoom(colorStudent).getStudentsSize();
    }

    @Override
    public Collection<Student> getStudents() {
        ColorStudent[] colors = ColorStudent.values();
        Collection<Student> allStudents = new ArrayList<>();
        for(ColorStudent tempColor : colors){
            allStudents.addAll(this.getDiningRoom(tempColor).getStudents());
        }
        return allStudents;
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

    public DiningRoom getDiningRoom(ColorStudent color) {
        DiningRoom retDiningRoom = null;
        for(DiningRoom tempDiningRoom : diningRooms){
            if(tempDiningRoom.getColor().equals(color)){

               retDiningRoom = tempDiningRoom;

            }
        }
        return retDiningRoom;
    }

    private int getStudentPosition(Student student){
        return this.entrance.getStudentPosition(student);
    }

    public Entrance getEntrance(){
        return entrance;
    }

    //if the return is null, there is no professor
    public Professor getProfessor(ColorStudent color){
        return this.getDiningRoom(color).getProfessor();
    }

    public void setProfessorNull(ColorStudent color){
        this.getDiningRoom(color).setProfessor(null);
    }
}
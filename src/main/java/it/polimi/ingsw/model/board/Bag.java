package it.polimi.ingsw.model.board;

import it.polimi.ingsw.model.pieces.Student;
import it.polimi.ingsw.model.colors.ColorStudent;

import java.util.ArrayList;
import java.util.Random;

public class Bag {
    private int remaining;
    private final ArrayList<Student> students;


    // Start Getters, Setters, Constructor

    public Bag() {
        this.students = new ArrayList<>();
        //generates all the 120 students, 24 for each color
        //the first 10 students are managed in the class Game to init the board
        int totStudentsColor = 24;
        for(int i = 0; i < totStudentsColor; i++){
            for(ColorStudent tempColor : ColorStudent.values()){
                students.add(new Student(tempColor));
            }
        }
        this.remaining = students.size();
    }

    /**
     * add a student to the bag
     * @param student
     */
    public void addStudent(Student student){
        this.students.add(student);
    }

    /**
     * If there is no student in the bag, it will return null.
     * @return pseudo-random student caught from the bag. The student is removed from the bag.
     */
    public Student draw(){
        Random random = new Random();
        //generates a number between 0 and remaining
        int randInt = random.nextInt(remaining);

        if(remaining != 0){
            Student retStudent = students.get(randInt);
            students.remove(retStudent);
            remaining = students.size();
            //System.out.println("The color of the student is" + " " + retStudent.getColor());
            return retStudent;
        } else {
            return null;
        }
    }

    public boolean isEmpty(){
        return students.size() == 0;
    }

    public int left(){
        return remaining;
    }
}
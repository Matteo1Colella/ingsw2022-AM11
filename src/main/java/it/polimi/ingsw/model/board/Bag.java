package it.polimi.ingsw.model.board;

import it.polimi.ingsw.model.pieces.Student;
import it.polimi.ingsw.model.ColorStudent;

import java.util.ArrayList;
import java.util.Random;

public class Bag {
    private int remaining;
    private final ArrayList<Student> students;


    // Start Getters, Setters, Constructor

    public Bag() {
        this.students = new ArrayList<>();
        //generates all the 120 students, 24 for each color
        int totStudentsColor = 24;
        for(int i = 0; i < totStudentsColor; i++){
            for(ColorStudent tempColor : ColorStudent.values()){
                students.add(new Student(tempColor));
            }
        }
        this.remaining = students.size();
    }

    //Returns a pseudo-random student caught from the bag. The student is removed from the bag.
    //If there is no student in the bag, it will return null.
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
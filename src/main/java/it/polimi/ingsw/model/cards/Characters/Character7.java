package it.polimi.ingsw.model.cards.Characters;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.board.IslandCard;
import it.polimi.ingsw.model.cards.CharacterCard;
import it.polimi.ingsw.model.pieces.Student;

import java.util.ArrayList;

public class Character7 extends CharacterCard {

    private ArrayList<Student> students;
    private int num;

    public Character7(int num) {
        this.students = new ArrayList<>();
        super.setNum(num);
        this.num = num;
    }

    public void effect(Player activePlayer, IslandCard island, int[] selectionFromSchoolboard, int[] selectionFromCard){
        ArrayList<Student> chosen1 = new ArrayList<>();
        ArrayList<Student> chosen2 = new ArrayList<>();
        int i =0;

        // stores students from schoolboard to this card
        for (int temp : selectionFromSchoolboard) {
            chosen1.add(activePlayer.getSchoolBoard().getEntrance().getStudents().get(temp));
            this.addSudent(activePlayer.getSchoolBoard().getEntrance().getStudents().get(temp));
        }

        //stores students from this card to schoolboard
        for (int temp : selectionFromCard) {
            chosen2.add(this.students.get(temp));
            activePlayer.getSchoolBoard().getEntrance().addStudent(this.students.get(temp));
        }

        //removes the students
        for(Student temp : chosen1){
            activePlayer.getSchoolBoard().getEntrance().removeStudent(temp);
        }

        //removes the students
        for(Student temp : chosen2){
            this.students.remove(temp);
        }
    }
    public void addSudent(Student student){
        this.students.add(student);
    }
}

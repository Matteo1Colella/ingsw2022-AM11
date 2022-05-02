package it.polimi.ingsw.model.cards.characters;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.cards.CharacterCard;
import it.polimi.ingsw.model.pieces.Student;

import java.util.ArrayList;

public class Character7 extends CharacterCard {

    private ArrayList<Student> students;
    private int num;
    private final int necessaryCoin;

    public Character7(int num) {
        this.students = new ArrayList<>();
        super.setNum(num);
        this.num = num;
        this.necessaryCoin = 1;
    }

    public void effect(Player activePlayer, int[] selectionFromSchoolboard, int[] selectionFromCard){
        ArrayList<Student> chosen1 = new ArrayList<>();
        ArrayList<Student> chosen2 = new ArrayList<>();

        // stores students from schoolboard to this card
        for (int temp : selectionFromSchoolboard) {
            chosen1.add(activePlayer.getSchoolBoard().getEntrance().getStudents().get(temp));
            this.addSudent(activePlayer.getSchoolBoard().getEntrance().getStudents().get(temp));
        }

        //stores students from this card to schoolboard
        for (int temp : selectionFromCard) {
            chosen2.add(super.getStudents().get(temp));
            activePlayer.getSchoolBoard().getEntrance().addStudent(super.getStudents().get(temp));
        }

        //removes the students
        for(Student temp : chosen1){
            activePlayer.getSchoolBoard().getEntrance().removeStudent(temp);
        }

        //removes the students
        for(Student temp : chosen2){
            super.getStudents().remove(temp);
        }
        activePlayer.useCoins(this.necessaryCoin);
    }
    public void addSudent(Student student){
        super.getStudents().add(student);
    }

    @Override
    public int getNecessaryCoin() {
        return necessaryCoin;
    }
}

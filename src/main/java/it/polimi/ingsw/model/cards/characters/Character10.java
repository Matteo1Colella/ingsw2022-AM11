package it.polimi.ingsw.model.cards.characters;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.board.DiningRoom;
import it.polimi.ingsw.model.cards.CharacterCard;
import it.polimi.ingsw.model.pieces.Student;

import java.util.ArrayList;

public class Character10 extends CharacterCard {
    private int num;
    private final int necessaryCoin;

    public Character10(int num) {
        super.setNum(num);
        this.num = num;
        this.necessaryCoin = 1;
    }

    public void effect(Player activePlayer, ArrayList<Student> selectionFromDiningRoom, int[] selectionFromEntrance){
        ArrayList<Student> chosen1 = new ArrayList<>();
        ArrayList<Student> chosen2 = new ArrayList<>();

        int i =0;

        // stores students from diningroom to this entrance
        for (Student temp : selectionFromDiningRoom) {
            chosen1.add(temp);
            activePlayer.getSchoolBoard().getEntrance().addStudents(selectionFromDiningRoom);
        }

        //stores students from entrance to diningroom
        for (int temp : selectionFromEntrance) {
            chosen2.add(activePlayer.getSchoolBoard().getEntrance().getStudents().get(temp));
            DiningRoom d = activePlayer.getSchoolBoard().getDiningRoomByColor(activePlayer.getSchoolBoard().getEntrance().getStudents().get(temp).getColor());
            d.addStudent(activePlayer.getSchoolBoard().getEntrance().getStudents().get(temp));
        }

        //removes the students
        for(Student temp : chosen2){
            activePlayer.getSchoolBoard().getEntrance().removeStudent(temp);
        }

        //removes the students
        for(Student temp : chosen1){
            DiningRoom d = activePlayer.getSchoolBoard().getDiningRoomByColor(temp.getColor());
            d.removeStudent(temp);
        }
        activePlayer.useCoins(this.necessaryCoin);
    }

    @Override
    public int getNecessaryCoin() {
        return necessaryCoin;
    }
}

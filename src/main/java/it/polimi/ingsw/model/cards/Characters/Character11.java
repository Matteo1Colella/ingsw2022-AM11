package it.polimi.ingsw.model.cards.Characters;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.board.DiningRoom;
import it.polimi.ingsw.model.cards.CharacterCard;
import it.polimi.ingsw.model.pieces.Student;

import java.util.ArrayList;

public class Character11 extends CharacterCard {
    private ArrayList<Student> students;
    private int num;
    private final int necessaryCoin;

    public Character11(int num) {
        super.setNum(num);
        this.num = num;
        this.students = new ArrayList<>();
        this.necessaryCoin = 2;
    }

    public void effect(Player activePlayer, int takenStudent){
        DiningRoom d = activePlayer.getSchoolBoard().getDiningRoomByColor(super.getStudents().get(takenStudent).getColor());
        d.addStudent(super.getStudents().get(takenStudent));
        super.getStudents().remove(takenStudent);
        super.addSudent(activePlayer.getPlayerGame().getGameComponents().getBag().draw());
    }

    @Override
    public int getNecessaryCoin() {
        return necessaryCoin;
    }
}

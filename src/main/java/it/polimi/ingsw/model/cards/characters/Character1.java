package it.polimi.ingsw.model.cards.characters;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.board.IslandCard;
import it.polimi.ingsw.model.cards.CharacterCard;
import it.polimi.ingsw.model.pieces.Student;

import java.util.ArrayList;

public class Character1 extends CharacterCard {
    private transient ArrayList<Student> students;
    private transient int num;
    private transient int necessaryCoin;

    public Character1(int num) {
        super.setNum(num);
        this.num = num;
        this.students = super.getStudents();
        this.necessaryCoin = 1;
        super.setNecessaryCoin(necessaryCoin);
    }
    public void effect(Player activePlayer, IslandCard island, int input){
        students.get(input).setPosition(island);
        island.addStudent(students.get(input));
        students.remove(1);
        students.add(activePlayer.getPlayerGame().getGameComponents().getBag().draw());
        activePlayer.useCoins(this.necessaryCoin);
        super.setNecessaryCoin(necessaryCoin + 1);
        this.setNecessaryCoin(necessaryCoin + 1);
    }

    @Override
    public void setNecessaryCoin(int necessaryCoin) {
        this.necessaryCoin = necessaryCoin;
    }

    @Override
    public int getNecessaryCoin() {
        return necessaryCoin;
    }
}

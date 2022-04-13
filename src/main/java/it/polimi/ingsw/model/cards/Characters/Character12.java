package it.polimi.ingsw.model.cards.Characters;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.board.DiningRoom;
import it.polimi.ingsw.model.cards.CharacterCard;
import it.polimi.ingsw.model.colors.ColorStudent;

import java.util.ArrayList;

public class Character12 extends CharacterCard {
    private int num;
    private final int necessaryCoin;

    public Character12(int num) {
        super.setNum(num);
        this.num = num;
        this.necessaryCoin = 3;
    }

    public void effect(Player activePlayer, ColorStudent color){
        ArrayList<Player> players = activePlayer.getPlayerGame().playerList();
        for(Player temp : players){
            DiningRoom d = temp.getSchoolBoard().getDiningRoomByColor(color);

            for (int i = 0; i <3; i++) {

                if (d.getStudentsSize() > 0) {
                    activePlayer.getPlayerGame().getGameComponents().getBag().addStudent(d.getStudents().get(0));
                    d.removeStudent(d.getStudents().get(0));
                }
            }
        }
    }

    @Override
    public int getNecessaryCoin() {
        return necessaryCoin;
    }
}

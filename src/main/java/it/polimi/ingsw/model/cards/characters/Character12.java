package it.polimi.ingsw.model.cards.characters;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.board.DiningRoom;
import it.polimi.ingsw.model.cards.CharacterCard;
import it.polimi.ingsw.model.colors.ColorStudent;

import java.util.ArrayList;

public class Character12 extends CharacterCard {
    private transient int num;
    private transient int necessaryCoin;

    public Character12(int num) {
        super.setNum(num);
        this.num = num;
        this.necessaryCoin = 3;
        super.setNecessaryCoin(necessaryCoin);
    }

    public void effect(Player activePlayer, ColorStudent color){
        ArrayList<Player> players = activePlayer.getPlayerGame().playerList();
        for(Player temp : players){
            DiningRoom d = temp.getSchoolBoard().getDiningRoomByColor(color);

            for (int i = 0; i <3; i++) {
                System.out.println("color: " + d.getColor() + "size: " + d.getStudentsSize());
                if (d.getStudentsSize() > 0) {
                    activePlayer.getPlayerGame().getGameComponents().getBag().addStudent(d.getStudents().get(0));
                    d.removeStudent(d.getStudents().get(0));
                    System.out.println("removing student");
                }
            }
        }
        activePlayer.useCoins(this.necessaryCoin);

        super.setNecessaryCoin(necessaryCoin + 1);
        this.setNecessaryCoin(necessaryCoin + 1);
    }

    @Override
    public int getNecessaryCoin() {
        return necessaryCoin;
    }
}

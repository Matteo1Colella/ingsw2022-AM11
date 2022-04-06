package it.polimi.ingsw.model.cards;

import it.polimi.ingsw.model.board.DiningRoom;
import it.polimi.ingsw.model.board.IslandCard;
import it.polimi.ingsw.model.colors.ColorStudent;
import it.polimi.ingsw.model.pieces.NoEntryTile;
import it.polimi.ingsw.model.pieces.Student;
import it.polimi.ingsw.model.board.Bag;
import it.polimi.ingsw.model.Player;

import java.util.ArrayList;

public class CharacterCard {
    private int num;
    private ArrayList<Student> students;
    private ArrayList<NoEntryTile> tiles;
    public CharacterCard() {}
    public int getNum() {
        return num;
    }
    public void addSudent(Student student){
        this.students.add(student);
    }
    public void addTile(NoEntryTile tile){
        this.tiles.add(tile);
    }
}

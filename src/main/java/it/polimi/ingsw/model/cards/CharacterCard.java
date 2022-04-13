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
    private int necessaryCoin;

    public CharacterCard() {
        this.students = new ArrayList<>();
        this.tiles = new ArrayList<>();
    }

    public ArrayList<NoEntryTile> getTiles() {
        return tiles;
    }

    public void setTiles(ArrayList<NoEntryTile> tiles) {
        this.tiles = tiles;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public int getNecessaryCoin(){
        return  this.necessaryCoin;
    }

    public int getNum() {
        return num;
    }
    public void addSudent(Student student){
        this.students.add(student);
    }
    public void addTile(NoEntryTile tile){
        this.tiles.add(tile);
    }

    public void effect(Player activePlayer, IslandCard island, int input){}
    public void effect(Player activePlayer){}
    public void effect(Player activePlayer, IslandCard island){}
    public void effect(Player activePlayer,  int[] selectionFromSchoolboard, int[] selectionFromCard){}
    public void effect(Player activePlayer, ColorStudent color){}
    public void effect(Player activePlayer, ArrayList<Student> selectionFromDiningRoom, int[] selectionFromEntrance){}
    public void effect(Player activePlayer, Student givenStudent, int takenStudent){}






}

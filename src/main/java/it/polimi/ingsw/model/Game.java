package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Components.Archipelago;

import java.util.*;

public class Game {
    private Status status;
    private boolean isPro;
    private int MovedPieces;
    private GameComponents GameComponents;
    private int ID;
    private int numplayers;

    // Start of Getters, Setters, Constructor

    public Game(boolean isPro, int ID, int numplayers) {
        this.isPro = isPro;
        this.ID = ID;
        this.numplayers = numplayers;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isPro() {
        return isPro;
    }

    public void setPro(boolean pro) {
        isPro = pro;
    }

    public int getMovedPieces() {
        return MovedPieces;
    }

    public void setMovedPieces(int movedPieces) {
        MovedPieces = movedPieces;
    }

    public GameComponents getGameStructure() {
        return GameComponents;
    }

    public void setGameStructure(GameComponents gameComponents) {
        GameComponents = gameComponents;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    // End of Getters, Setters, Constructor

    public void endTurn(){}

    public Player activePlayer(){
        return null;
    }

    public Set<Player> playerList(){
        return null;
    }

    public void changeActivePlayer(){ }

    public Set<Student> drawStudents(){
        return null;
    }

    public Map<ColorStudent, Player> colorDominance(){
        return null;
    }

    public ArrayList<IslandCard> islandDominance(){
        return null;
    }


    public void generateBoard2players(){
        Archipelago islands = new Archipelago();
        islands.createCircularLinkedList();
        islands.printIslands();

        ArrayList<Student> allstudents = new ArrayList<Student>();
        ArrayList<Student> studentscloud = new ArrayList<Student>();
        ArrayList<Student> player1_student = new ArrayList<Student>();

        ArrayList<Tower> towersblack = new ArrayList<Tower>();

        towersblack.add(new Tower(ColorTower.BLACK)); //repeat 8 times
        towersblack.add(new Tower(ColorTower.BLACK));
        towersblack.add(new Tower(ColorTower.BLACK));
        towersblack.add(new Tower(ColorTower.BLACK));
        towersblack.add(new Tower(ColorTower.BLACK));
        towersblack.add(new Tower(ColorTower.BLACK));
        towersblack.add(new Tower(ColorTower.BLACK));
        towersblack.add(new Tower(ColorTower.BLACK));

        ArrayList<DiningRoom> player1_diningroom = new ArrayList<DiningRoom>();

        Entrance entrance1 = new Entrance(player1_student);

        //mothernature in position: first island
        MotherNature motherNature = new MotherNature(islands.next());

        SchoolBoard schoolBoard = new SchoolBoard(towersblack, player1_diningroom, AddPlayer().getID_player(), entrance1 );

        Bag bag = new Bag(130,allstudents);

        CloudCard cloud1 = new CloudCard(1,studentscloud);








    }

    public void addChosenCard(){}

    public void pickCharacters(){}

    public void startGame(){}

    public Player AddPlayer(){
        return null;
    }

    public boolean IsIn(String ID){
        return false;
    }

    public void AddPlayer(String ID){}

    /*
    If two adjacent island are dominated by two towers
      of the same colors, then the island are merged.
    */
    public void mergeIsland(){
        Collection<IslandCard> Archipelago = GameComponents.getArchipelago();
        IslandCard courrentIsland = GameComponents.getMothernature().getPosition();
        IslandCard previousIsland = null;

        for(IslandCard tempIsland : Archipelago){
            if(tempIsland.equals(courrentIsland)){
                if((previousIsland.getTower()) == (courrentIsland.getTower())){

                }
            }

           previousIsland = tempIsland;
        }
    }
}

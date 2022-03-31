package it.polimi.ingsw.model;

import it.polimi.ingsw.model.Components.CircularArrayList;

import java.util.*;

public class Game {
    private Status status;
    private boolean isPro;
    private int MovedPieces;
    private GameComponents GameComponents;
    private int ID;
    private int numPlayers;
    private ComplexLobby complexLobby;

    // Start of Getters, Setters, Constructor

    public Game(boolean isPro, int ID, int numPlayers) {
        this.isPro = isPro;
        this.ID = ID;
        this.numPlayers = numPlayers;
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

        //trying with circular array
        CircularArrayList<IslandCard> islandsCircularArray = new CircularArrayList<>(12);

        //creation of tower container
        ArrayList<Tower> towersBlack = new ArrayList<Tower>();
        ArrayList<Tower> towersWhite = new ArrayList<Tower>();

        towersBlack.add(new Tower(ColorTower.BLACK)); //repeat 8 times for blacks
        towersBlack.add(new Tower(ColorTower.BLACK));
        towersBlack.add(new Tower(ColorTower.BLACK));
        towersBlack.add(new Tower(ColorTower.BLACK));
        towersBlack.add(new Tower(ColorTower.BLACK));
        towersBlack.add(new Tower(ColorTower.BLACK));
        towersBlack.add(new Tower(ColorTower.BLACK));
        towersBlack.add(new Tower(ColorTower.BLACK));
        towersWhite.add(new Tower(ColorTower.WHITE)); //repeat 8 times for whites
        towersWhite.add(new Tower(ColorTower.WHITE));
        towersWhite.add(new Tower(ColorTower.WHITE));
        towersWhite.add(new Tower(ColorTower.WHITE));
        towersWhite.add(new Tower(ColorTower.WHITE));
        towersWhite.add(new Tower(ColorTower.WHITE));
        towersWhite.add(new Tower(ColorTower.WHITE));
        towersWhite.add(new Tower(ColorTower.WHITE));

        //creation of students container
        ArrayList<Student> green = new ArrayList<>();
        ArrayList<Student> yellow = new ArrayList<>();
        ArrayList<Student> red = new ArrayList<>();
        ArrayList<Student> pink = new ArrayList<>();
        ArrayList<Student> blue = new ArrayList<>();

        //adding 26 students for each container
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));
        yellow.add(new Student(ColorStudent.YELLOW));green.add(new Student(ColorStudent.GREEN));red.add(new Student(ColorStudent.RED));






        //creating 12 islands
        Collection<Student> studentsIsland1 = new ArrayList<Student>(); //new Array for the students in the island
        studentsIsland1.add(yellow.get(0)); //adding 1 student
        Collection<Student> studentsIsland2 = new ArrayList<Student>();
        studentsIsland1.add(yellow.get(1));


        IslandCard island1 = new IslandCard(1, null);
        IslandCard island2 = new IslandCard(2, null);


        //adding 12 islands to circularArray
        islandsCircularArray.add(island1);
        islandsCircularArray.add(island2);

        for (int i = 0; i < islandsCircularArray.size(); i++) {
            System.out.println(islandsCircularArray.get(i).getMotherNature());
        }

        //not finished yet
        MotherNature motherPiece = new MotherNature(islandsCircularArray.get(0));
        ArrayList<SchoolBoard> school = new ArrayList<>();
        Bag container = new Bag(130,yellow,pink,red,green,blue); //e array da 26 studenti
        ArrayList<CloudCard> cloudContainer = new ArrayList<>();
        cloudContainer.add(new CloudCard(1, (ArrayList<Student>) container.getYellow()));




        GameComponents table = new GameComponents(islandsCircularArray, motherPiece, school,container,cloudContainer);


         /**

        //first try of creation:
        Archipelago islands = new Archipelago();
        islands.createCircularLinkedList();
        islands.printIslands();

        ArrayList<Student> allstudents = new ArrayList<Student>();
        ArrayList<Student> studentscloud = new ArrayList<Student>();
        ArrayList<Student> player1_student = new ArrayList<Student>();

        ArrayList<Tower> towerswhite = new ArrayList<Tower>();

        towerswhite.add(new Tower(ColorTower.WHITE)); //repeat 8 times
        towerswhite.add(new Tower(ColorTower.WHITE));
        towerswhite.add(new Tower(ColorTower.WHITE));
        towerswhite.add(new Tower(ColorTower.WHITE));
        towerswhite.add(new Tower(ColorTower.WHITE));
        towerswhite.add(new Tower(ColorTower.WHITE));
        towerswhite.add(new Tower(ColorTower.WHITE));
        towerswhite.add(new Tower(ColorTower.WHITE));

        ArrayList<DiningRoom> player1_diningroom = new ArrayList<DiningRoom>();

        Entrance entrance1 = new Entrance(player1_student);

        //mothernature in position: first island
        MotherNature motherNature = new MotherNature(islands.next());

        SchoolBoard schoolBoard = new SchoolBoard(towersBlack, player1_diningroom, AddPlayer().getID_player(), entrance1 );

        //Bag

        CloudCard cloud1 = new CloudCard(1,studentscloud);
        **/







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

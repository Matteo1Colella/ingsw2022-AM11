package it.polimi.ingsw.model;

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

    public Set<Player> playerList(){
        return null;
    }

    public Set<Student> drawStudents(){
        return null;
    }

    public Map<ColorStudent, Player> colorDominance(){
        return null;
    }

    public ArrayList<IslandCard> islandDominance(){
        return null;
    }

    public GameComponents generateBoard2players(Boolean isPro){

        ArrayList<Student> initialBag = new ArrayList<>();

        //IslandsArray
        ArrayList<IslandCard> islandsCircularArray = new ArrayList<>();

        //creation of students container
        ArrayList<Student> green = new ArrayList<>();
        ArrayList<Student> yellow = new ArrayList<>();
        ArrayList<Student> red = new ArrayList<>();
        ArrayList<Student> pink = new ArrayList<>();
        ArrayList<Student> blu = new ArrayList<>();
        //adding 26 students for each container
        for(int i=0;i<26;i++){
            yellow.add(new Student(ColorStudent.YELLOW));
            green.add(new Student(ColorStudent.GREEN));
            red.add(new Student(ColorStudent.RED));
            pink.add(new Student(ColorStudent.PINK));
            blu.add(new Student(ColorStudent.BLU));
        }
        //creating 12 islands, and containers in the island
        ArrayList<Student> studentsIsland1 = new ArrayList<Student>(); //new Array for the students in the island
        ArrayList<Student> studentsIsland2 = new ArrayList<Student>();
        ArrayList<Student> studentsIsland3 = new ArrayList<Student>();
        ArrayList<Student> studentsIsland4 = new ArrayList<Student>();
        ArrayList<Student> studentsIsland5 = new ArrayList<Student>();
        ArrayList<Student> studentsIsland6 = new ArrayList<Student>();
        ArrayList<Student> studentsIsland7 = new ArrayList<Student>();
        ArrayList<Student> studentsIsland8 = new ArrayList<Student>();
        ArrayList<Student> studentsIsland9 = new ArrayList<Student>();
        ArrayList<Student> studentsIsland10 = new ArrayList<Student>();
        ArrayList<Student> studentsIsland11 = new ArrayList<Student>();
        ArrayList<Student> studentsIsland12 = new ArrayList<Student>();

        //random choice
        for(int i=0; i<2; i++){
            initialBag.add(yellow.get(i));
            initialBag.add(red.get(i));
            initialBag.add(green.get(i));
            initialBag.add(pink.get(i));
            initialBag.add(blu.get(i));
            yellow.remove(i);
            red.remove(i);
            green.remove(i);
            pink.remove(i);
            blu.remove(i);
        }

        //adding random students
        int index = (int) (Math.random() * initialBag.size());
        studentsIsland2.add(initialBag.get(index)); //adding 1 student
        initialBag.remove(index);

        index = (int) (Math.random() * initialBag.size());
        studentsIsland3.add(initialBag.get(index));
        initialBag.remove(index);

        index = (int) (Math.random() * initialBag.size());
        studentsIsland4.add(initialBag.get(index));
        initialBag.remove(index);

        index = (int) (Math.random() * initialBag.size());
        studentsIsland5.add(initialBag.get(index));
        initialBag.remove(index);

        index = (int) (Math.random() * initialBag.size());
        studentsIsland6.add(initialBag.get(index));
        initialBag.remove(index);

        index = (int) (Math.random() * initialBag.size());
        studentsIsland8.add(initialBag.get(index));
        initialBag.remove(index);

        index = (int) (Math.random() * initialBag.size());
        studentsIsland9.add(initialBag.get(index));
        initialBag.remove(index);

        index = (int) (Math.random() * initialBag.size());
        studentsIsland10.add(initialBag.get(index));
        initialBag.remove(index);

        index = (int) (Math.random() * initialBag.size());
        studentsIsland11.add(initialBag.get(index));
        initialBag.remove(index);

        index = (int) (Math.random() * initialBag.size());
        studentsIsland12.add(initialBag.get(index));
        initialBag.remove(index);


        IslandCard island1 = new IslandCard(1, studentsIsland1,null, null,true,null,false);
        IslandCard island2 = new IslandCard(2, studentsIsland2 ,null,null,false,null,false);
        IslandCard island3 = new IslandCard(3, studentsIsland3 ,null,null,false,null,false);
        IslandCard island4 = new IslandCard(4, studentsIsland4 ,null,null,false,null,false);
        IslandCard island5 = new IslandCard(5, studentsIsland5 ,null,null,false,null,false);
        IslandCard island6 = new IslandCard(6, studentsIsland6 ,null,null,false,null,false);
        IslandCard island7 = new IslandCard(7, studentsIsland7 ,null,null,false,null,false);
        IslandCard island8 = new IslandCard(8, studentsIsland8 ,null,null,false,null,false);
        IslandCard island9 = new IslandCard(9, studentsIsland9 ,null,null,false,null,false);
        IslandCard island10 = new IslandCard(10, studentsIsland10 ,null,null,false,null,false);
        IslandCard island11 = new IslandCard(11, studentsIsland11 ,null,null,false,null,false);
        IslandCard island12 = new IslandCard(12, studentsIsland12 ,null,null,false,null,false);

        //adding 12 islands to circularArray
        islandsCircularArray.add(island1);
        islandsCircularArray.add(island2);
        islandsCircularArray.add(island3);
        islandsCircularArray.add(island4);
        islandsCircularArray.add(island5);
        islandsCircularArray.add(island6);
        islandsCircularArray.add(island7);
        islandsCircularArray.add(island8);
        islandsCircularArray.add(island9);
        islandsCircularArray.add(island10);
        islandsCircularArray.add(island11);
        islandsCircularArray.add(island12);

        //create MotherNature and set the position to the first island
        MotherNature motherPiece = new MotherNature(islandsCircularArray.get(0));

        //create Bag
        ArrayList<Student> bagStudents = new ArrayList<>();
        bagStudents.addAll(yellow);
        bagStudents.addAll(red);
        bagStudents.addAll(green);
        bagStudents.addAll(blu);
        bagStudents.addAll(pink);

        Bag container = new Bag(yellow.size()+pink.size()+red.size()+blu.size()+ green.size(),bagStudents);

        //create Clouds
        ArrayList<CloudCard> cloudContainer = new ArrayList<>();
        ArrayList<Student> cloudStudents1 = new ArrayList<>();
        ArrayList<Student> cloudStudents2 = new ArrayList<>();
        //Clouds are initially empty
        cloudContainer.add(new CloudCard(1, cloudStudents1));
        cloudContainer.add(new CloudCard(2, cloudStudents2));

        //create Professors
        ArrayList<Professor> professors = new ArrayList<>();
        Professor redProf = new Professor(ColorStudent.RED);
        Professor pinkProf = new Professor(ColorStudent.PINK);
        Professor bluProf = new Professor(ColorStudent.BLU);
        Professor greenProf = new Professor(ColorStudent.GREEN);
        Professor yellowProf = new Professor(ColorStudent.YELLOW);
        professors.add(redProf);
        professors.add(pinkProf);
        professors.add(bluProf);
        professors.add(greenProf);
        professors.add(yellowProf);

        //create schoolBoard
        ArrayList<SchoolBoard> schools = new ArrayList<>();

        ArrayList<Student> entrancePlayer1 = new ArrayList<>();
        ArrayList<Student> entrancePlayer2 = new ArrayList<>();
        for(int i=0;i<7;i++) {
            entrancePlayer1.add(container.Draw());
            entrancePlayer2.add(container.Draw());
        }


        SchoolBoard boardPlayer1 = new SchoolBoard(ColorTower.BLACK,2,entrancePlayer1);
        SchoolBoard boardPlayer2 = new SchoolBoard(ColorTower.WHITE,2,entrancePlayer2);

        schools.add(boardPlayer1);
        schools.add(boardPlayer2);


        //if isPro ==> generate coins, character cards, prohibition cards
        if(isPro==true){
            ArrayList<Coin> coins = new ArrayList<>();
            for(int i = 0; i<20;i++){
                coins.add(new Coin());
            }
            CoinReserve coinContainer = new CoinReserve(coins);

            ArrayList<NoEntryTile> prohibitionCards = new ArrayList<>();
            for(int i = 0; i<4;i++) {
                prohibitionCards.add(new NoEntryTile(false, null));
            }

            CharacterDeck specialCards = new CharacterDeck(null,3);

            GameComponents table = new GameComponents(islandsCircularArray, motherPiece, schools,container,cloudContainer,professors,coinContainer,prohibitionCards,specialCards);
            return table;
        }

        GameComponents table = new GameComponents(islandsCircularArray, motherPiece, schools,container,cloudContainer,professors);

        return table;

    }

    public GameComponents generateBoard3players(Boolean isPro){
        return null;
    }
    public GameComponents generateBoard4players(Boolean isPro){
        return null;
    }
    public void addChosenCard(){}

    public void pickCharacters(){}

    public void startGame(){}

    public void winCondition(){}

    /*
    If two adjacent island are dominated by two towers
      of the same colors, then the island are merged.
    */
    public ArrayList<IslandCard> mergeIsland(ArrayList<IslandCard> islands){
        //IslandCard currentIsland = islands.stream().findAny().get().getMotherNature().compareTo(true);
        //IslandCard c = islands.stream().findFirst().get().getMotherNature().compareTo(true)



        /*
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

         */
        return null;
    }

    /**
     * @param islands ArrayList of the islands on the board
     * @param steps number of steps to take
     * @param motherNat with initial ID_island
     * @return motherNature with the new position where ends
     */
    public MotherNature moveMotherNature(int steps, MotherNature motherNat, ArrayList<IslandCard> islands) {

        int id_final_island = 0;
        if ((motherNat.getPosition().getId_island() + steps) <= 12) {
            id_final_island = motherNat.getPosition().getId_island() + steps;
            islands.get(motherNat.getPosition().getId_island()-1).setMotherNature(false);
            motherNat.setPosition(islands.get(id_final_island - 1));
            islands.get(id_final_island - 1).setMotherNature(true);
            return motherNat;
        }
        //if (steps + ID_island) is > 12
        id_final_island = motherNat.getPosition().getId_island() + steps;
        id_final_island = id_final_island - 12;
        islands.get(motherNat.getPosition().getId_island()-1).setMotherNature(false);
        motherNat.setPosition(islands.get(id_final_island - 1));
        islands.get(id_final_island - 1).setMotherNature(true);
        return motherNat;
    }
}

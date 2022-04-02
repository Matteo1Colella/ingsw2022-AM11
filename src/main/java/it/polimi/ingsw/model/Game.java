package it.polimi.ingsw.model;

import it.polimi.ingsw.Colors.ColorStudent;
import it.polimi.ingsw.controller.ColorTower;
import it.polimi.ingsw.model.cards.CharacterDeck;
import it.polimi.ingsw.controller.ComplexLobby;
import it.polimi.ingsw.model.pieces.*;
import it.polimi.ingsw.model.board.*;

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

    public ComplexLobby getComplexLobby() {
        return complexLobby;
    }

    public void setComplexLobby(ComplexLobby complexLobby) {
        this.complexLobby = complexLobby;
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

    public GameComponents getGameComponents() {
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

    public void islandDominance(){

        IslandCard selectedIsland = null;
        ArrayList<ColorStudent> presentColors = new ArrayList<>();
        ArrayList<Player> players = this.complexLobby.getPlayers();
        int indexIsland;
        int numGreen = 0;
        int numYellow = 0;
        int numRed = 0;
        int numBlue = 0;
        int numPink = 0;
        Player kingPlayer = null;

        // gets island where mother nature is
        for (IslandCard tempIsland : this.getGameComponents().getArchipelago())
        {
            if (tempIsland.getMotherNature()){
                selectedIsland = tempIsland;
                indexIsland = tempIsland.getId_island();
                break;
            }
        }

        //calculates how many students of each color are on the island
        for(Student temp : selectedIsland.getStudents())
        {
            switch (temp.getColor()){
                case RED:
                    numRed++;
                    break;
                case PINK:
                    numPink++;
                    break;
                case BLU:
                    numBlue++;
                    break;
                case YELLOW:
                    numYellow ++;
                    break;
                case GREEN:
                    numGreen ++;
                    break;
            }
            if (!presentColors.contains(temp.getColor())){
                presentColors.add(temp.getColor());
        }
        }

        // if no students no dominance
        if (presentColors.size()==0) return;

        //checks which players has color dominance of the students on the island and calculates influences
        int i = 0;
        for (ColorStudent tempColor : presentColors){
            for (Player tempPlayer : players){
                if (tempPlayer.getSchoolBoard().getDiningRoomByColor(tempColor).IsProfessor()){
                    switch (tempColor){
                        case RED:
                            tempPlayer.setInfluencePoints(tempPlayer.getInfluencePoints()+numRed);
                            break;
                        case PINK:
                            tempPlayer.setInfluencePoints(tempPlayer.getInfluencePoints()+numPink);
                            break;
                        case BLU:
                            tempPlayer.setInfluencePoints(tempPlayer.getInfluencePoints()+numBlue);
                            break;
                        case YELLOW:
                            tempPlayer.setInfluencePoints(tempPlayer.getInfluencePoints()+numYellow);
                            break;
                        case GREEN:
                            tempPlayer.setInfluencePoints(tempPlayer.getInfluencePoints()+numGreen);
                            break;
                    }
                }
                // if the island has a tower and matches color of tower of the selected player he gets +1 points
                if ( i== 0 && selectedIsland.getTower()!= null && selectedIsland.getTower().getColor().equals(tempPlayer.getSchoolBoard().getTowers().get(0).getColor())){
                    tempPlayer.setInfluencePoints(tempPlayer.getInfluencePoints()+1);
                    kingPlayer = tempPlayer;
                    i++;
                }
            }
        }

        // setting max calculation
        Player maxPlayer = players.get(0);

        // calculates who's the king of the island
        for (Player tempPlayer : players)
        {
           if (tempPlayer.getInfluencePoints() > maxPlayer.getInfluencePoints())
           {
               maxPlayer = tempPlayer;
           }

        }

        // if max influence is 0, no dominance
        if (maxPlayer.getInfluencePoints()==0) return;

        // if there's a draw nothing happens
        for(Player tempPlayer : players)
        {
            if (tempPlayer.getInfluencePoints() == maxPlayer.getInfluencePoints() && !tempPlayer.equals(maxPlayer)){
                return;
        }
        }

        // if the king of the island hasn't changed nothing happens
        if (maxPlayer.equals(kingPlayer)){
            return;
        }

        // if there wasn't the king a new king is declared
        if (kingPlayer == null){
            selectedIsland.setTower(maxPlayer.getSchoolBoard().getTowers().get(0));
            maxPlayer.getSchoolBoard().getTowers().remove(0);
            return;
        }

        // if there was a king that was beaten, it gets substitued
        kingPlayer.getSchoolBoard().getTowers().add(selectedIsland.getTower());
        selectedIsland.setTower(maxPlayer.getSchoolBoard().getTowers().get(0));
        maxPlayer.getSchoolBoard().getTowers().remove(0);

    }


    public GameComponents generateBoard2players(Boolean isPro, int numOfPlayers){

        ArrayList<Student> initialBag = new ArrayList<>();

        //IslandsArray
        ArrayList<IslandCard> islandsCircularArray = new ArrayList<>();

        //creating initial bag useful for board setup
        for(ColorStudent temp : ColorStudent.values()){
            initialBag.add(new Student(temp));
            initialBag.add(new Student(temp));
        }

        //creating 12 islands, and containers in the island
        ArrayList<ArrayList<Student>> islandsContainers = new ArrayList<>();
        for (int i = 0; i < 12 ; i++){
            islandsContainers.add(new ArrayList<>());
            IslandCard island = new IslandCard(i);
            int index = (int) (Math.random() * initialBag.size());
            if (i != 0 && i != 7){
                islandsContainers.get(i).add(initialBag.get(index));
                island.setStudents(islandsContainers.get(i));
                initialBag.remove(index);
            } else {
                island.setStudents(islandsContainers.get(i));
            }
            islandsCircularArray.add(island);
        }

        //create MotherNature and set the position to the first island
        MotherNature motherPiece = new MotherNature(islandsCircularArray.get(0));

        Bag studentsBag = new Bag();

        //create Clouds
        ArrayList<CloudCard> cloudContainer = new ArrayList<>();
        for (int i = 0; i < numOfPlayers; i++){
            ArrayList<Student> cloudStudents = new ArrayList<>();
            cloudContainer.add(new CloudCard(i, cloudStudents));
        }

        //create Professors
        ArrayList<Professor> professors = new ArrayList<>();
        for (ColorStudent temp : ColorStudent.values())
        {
            Professor prof = new Professor(temp);
            professors.add(prof);
        }

        //create schoolBoard
        ArrayList<SchoolBoard> schools = new ArrayList<>();
        for (int i = 0; i < numOfPlayers; i++){
            ArrayList<Student> entrancePlayer = new ArrayList<>();
            if (numOfPlayers == 2 || numOfPlayers == 4){
                for(int j=0; j<7; j++) {
                    entrancePlayer.add(studentsBag.draw());
                }
                switch(i){
                    case 0:
                        SchoolBoard boardPlayer1 = new SchoolBoard(ColorTower.BLACK,numOfPlayers,entrancePlayer);
                        schools.add(boardPlayer1);
                        break;
                    case 1:
                        SchoolBoard boardPlayer2 = new SchoolBoard(ColorTower.WHITE,numOfPlayers,entrancePlayer);
                        schools.add(boardPlayer2);
                        break;
                }
            } else {
                for(int j=0; j<8; j++) {
                    entrancePlayer.add(studentsBag.draw());
                }
                switch(i){
                    case 0:
                        SchoolBoard boardPlayer1 = new SchoolBoard(ColorTower.BLACK,numOfPlayers,entrancePlayer);
                        schools.add(boardPlayer1);
                        break;
                    case 1:
                        SchoolBoard boardPlayer2 = new SchoolBoard(ColorTower.WHITE,numOfPlayers,entrancePlayer);
                        schools.add(boardPlayer2);
                        break;
                    case 3:
                        SchoolBoard boardPlayer3 = new SchoolBoard(ColorTower.GREY,numOfPlayers,entrancePlayer);
                        schools.add(boardPlayer3);
                        break;
                }
            }
        }

        //if isPro ==> generate coins, character cards, prohibition cards
        if(isPro){

            CoinReserve coinContainer = new CoinReserve();

            ArrayList<NoEntryTile> prohibitionCards = new ArrayList<>();
            for(int i = 0; i<4;i++) {
                prohibitionCards.add(new NoEntryTile(false, null));
            }

            CharacterDeck specialCards = new CharacterDeck();

            GameComponents table = new GameComponents(islandsCircularArray, motherPiece, schools,studentsBag,cloudContainer,professors,coinContainer,prohibitionCards,specialCards);
            return table;
        }

        GameComponents table = new GameComponents(islandsCircularArray, motherPiece, schools,studentsBag,cloudContainer,professors);

        this.GameComponents = table;
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
    public ArrayList<IslandCard> mergeIsland(){
        ArrayList<IslandCard> islands = this.GameComponents.getArchipelago();
        System.out.println("Archipelago before merging");
        islands.stream().map(IslandCard::getId_island).forEach(System.out::println);
        IslandCard selectedIsland = null, next = null, prev = null;
        int indexIsland = 0;
        for (IslandCard tempIsland: islands){
            if (tempIsland.getMotherNature()){
                selectedIsland = tempIsland;
                indexIsland = tempIsland.getId_island();
                break;
            }
        }
        if (indexIsland == 0){
            next = islands.get(1);
            prev = islands.get(islands.size()-1);
        } else if (indexIsland == islands.size()-1){
            next = islands.get(0);
            prev = islands.get(islands.size()-2);
        } else{
            next = islands.get(indexIsland+1);
            prev = islands.get(indexIsland-1);
        }
        if (selectedIsland == null || prev == null || next == null){
            System.out.println("error not found island");
            return null;
        }
        Tower currTower = selectedIsland.getTower();
        Tower nextTower = next.getTower();
        Tower prevTower = prev.getTower();

        if ((nextTower == null || !nextTower.getColor().equals(currTower.getColor())) && prevTower!=null && currTower.getColor().equals(prevTower.getColor())){
            selectedIsland.setMergedWith(prev);
            next.setMergedWith(selectedIsland);
            islands.remove(prev.getId_island());
        } else if (( prevTower == null || !prevTower.getColor().equals(currTower.getColor())) && nextTower!=null && currTower.getColor().equals(nextTower.getColor())){
            selectedIsland.setMergedWith(next);
            prev.setMergedWith(selectedIsland);
            islands.remove(next.getId_island());
        } else if (prevTower!=null && nextTower!=null && currTower.getColor().equals(nextTower.getColor()) && currTower.getColor().equals(prevTower.getColor())){
            System.out.println("entering");
            selectedIsland.setMergedWith(prev);
            selectedIsland.setMergedWith(next);
            next.setMergedWith(selectedIsland);
            prev.setMergedWith(selectedIsland);
            islands.remove(prev.getId_island());
            islands.remove(next.getId_island()-1);
        }


        System.out.println("Archipelago after merging");
        islands.stream().map(IslandCard::getId_island).forEach(System.out::println);

        int i = 0;
        for(IslandCard temp : islands){
            temp.setId_island(i);
            i++;
        }

        this.GameComponents.setArchipelago(islands);
        System.out.println("Archipelago after mapping and merging");
        islands.stream().map(IslandCard::getId_island).forEach(System.out::println);
        return islands;
    }

    /**
     * @param islands ArrayList of the islands on the board
     * @param steps number of steps to take
     * @param motherNat with initial ID_island
     * @return motherNature with the new position where ends
     */
    public MotherNature moveMotherNature(int steps, MotherNature motherNat, ArrayList<IslandCard> islands) {

        int id_final_island = 0;
        if ((motherNat.getPosition().getId_island() + steps) < islands.size()) {
            id_final_island = motherNat.getPosition().getId_island() + steps;
            islands.get(motherNat.getPosition().getId_island()).setMotherNature(false);
            motherNat.setPosition(islands.get(id_final_island));
            islands.get(id_final_island).setMotherNature(true);
            return motherNat;
        }
        //if (steps + ID_island) is > 12
        id_final_island = motherNat.getPosition().getId_island() + steps;
        id_final_island = id_final_island - islands.size();
        islands.get(motherNat.getPosition().getId_island()).setMotherNature(false);
        motherNat.setPosition(islands.get(id_final_island ));
        islands.get(id_final_island ).setMotherNature(true);
        return motherNat;
    }
}

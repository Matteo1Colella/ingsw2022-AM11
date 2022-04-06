package it.polimi.ingsw.model;

import it.polimi.ingsw.model.cards.Card;
import it.polimi.ingsw.model.cards.CharacterCard;
import it.polimi.ingsw.model.colors.ColorStudent;
import it.polimi.ingsw.controller.ColorTower;
import it.polimi.ingsw.model.cards.CharacterDeck;
import it.polimi.ingsw.controller.ComplexLobby;
import it.polimi.ingsw.model.pieces.*;
import it.polimi.ingsw.model.board.*;

import java.awt.*;
import java.util.*;

public class Game {
    private Status status;
    private boolean isPro;
    private boolean noTower;
    private ColorStudent excludedColor;
    private int characterUsed;
    private int MovedPieces;
    private GameComponents GameComponents;
    private int ID;
    private int numPlayers;
    private ComplexLobby complexLobby;
    private ArrayList<Card> chosenCards;
    private HashMap<ColorStudent, Player> dominanceMap;


    // Start of Getters, Setters, Constructor

    public Game(boolean isPro, int ID, int numPlayers) {
        this.isPro = isPro;
        this.ID = ID;
        this.numPlayers = numPlayers;
        this.chosenCards = new ArrayList<>();
        this.dominanceMap = new HashMap<>();
        this.noTower = false;
        this.excludedColor = null;
    }

    public ColorStudent getExcludedColor() {
        return excludedColor;
    }

    public void setExcludedColor(ColorStudent excludedColor) {
        this.excludedColor = excludedColor;
    }

    public ArrayList<Card> getChosenCards() {return chosenCards;}

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

    public boolean isNoTower() {
        return noTower;
    }

    public void setNoTower(boolean noTower) {
        this.noTower = noTower;
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

    public ArrayList<Player> playerList(){
        return this.complexLobby.getPlayers();
    }

    //checks if a player earns a professor
    public void colorDominance(){
        ArrayList<Player> players = this.complexLobby.getPlayers();
        HashMap<Player, int[]> sizeMap = new HashMap<>();
        int i = 0, j = 0;
        int[] colorVector = new int[5];
        int max = 0, indexMax = 0, countMax = 0;

        //for al player, fill the sizeMap with the size of the students in the dining rooms
        for(Player player : players){
            int[] sizeVector = new int[5];
            for(ColorStudent tempColor : ColorStudent.values()){
               sizeVector[i] = player.getSchoolBoard().getDiningRoomByColor(tempColor).getStudentsSize();
               i++;
            }
            sizeMap.put(player, sizeVector);
            i = 0;
        }

        //for each color, calculate the player that has the largest number of students

        //for each color, assign the player which has the dominance
        //if two or more players have the same dominance in the same color, nothing changes for this color
        for(ColorStudent tempColor : ColorStudent.values()){
            i = 0;
            //for all color, valuesVector is filled with the number of the players' students of the same color.
            for(Player player : players){
                int[] valuesVector = sizeMap.get(player);
                colorVector[i] = valuesVector[j];
                i++;
            }
            //find the max of ValuesVector and assign the dominance at the correct player
            for(i = 0; i < colorVector.length; i++){
                if(colorVector[i] > max){
                    max = colorVector[i];
                    indexMax = i;
                }
            }
            //check if there is multiple max
            for (int value : colorVector) {
                if (value == max) {
                    countMax++;
                }
            }
            if(countMax == 1){
                this.dominanceMap.put(tempColor, players.get(indexMax));
            }
            j++;
            max = 0;
            indexMax = 0;
            countMax = 0;
        }
        //set the professors
        for(ColorStudent tempColor : ColorStudent.values()){
            Professor professor = null;
            Player player = this.dominanceMap.get(tempColor);
            if(player != null){
                // find the correct proifessor in the professors' array
                for(i = 0; i < this.getGameComponents().getProfessorCollection().size(); i++){
                    if(this.getGameComponents().getProfessorCollection().get(i).getColor().equals(tempColor)){
                        professor = this.getGameComponents().getProfessorCollection().get(i);
                        break;
                    }
                }
                if(professor.getPosition() == null){
                    player.getSchoolBoard().setProfessor(professor);
                    professor.setPosition(player.getSchoolBoard().getDiningRoom(tempColor));
                } else {
                    // find the former possessor
                    for(Player tempPlayer : this.playerList()){
                        if(tempPlayer.getSchoolBoard().getProfessor(tempColor) != null){
                            tempPlayer.getSchoolBoard().setProfessorNull(tempColor);
                        }
                        break;
                    }
                    player.getSchoolBoard().setProfessor(professor);
                    professor.setPosition(player.getSchoolBoard().getDiningRoom(tempColor));
                }
            }
        }
    }

    public HashMap<ColorStudent, Player> getDominanceMap(){
        return this.dominanceMap;
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

        if (selectedIsland == null) return;

        if (selectedIsland.getLocked()) return;

        if (selectedIsland.getStudents().isEmpty()) return;

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

        //check excludedColor
        if (excludedColor == null){

        } else switch(this.excludedColor){
            case RED:
                numRed = 0;
                break;
            case PINK:
                numPink = 0;
                break;
            case YELLOW:
                numYellow = 0;
                break;
            case BLU:
                numBlue = 0;
                break;
            case GREEN:
                numGreen = 0;
                break;
        }

        //resets excludedColor
        this.excludedColor = null;

        // if no students no dominance
        if (presentColors.size()==0) return;

        //checks which player has color dominance of the students on the island and calculates influences
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
                if (!noTower && i== 0 && selectedIsland.getTower()!= null && selectedIsland.getTower().getColor().equals(tempPlayer.getSchoolBoard().getTowers().get(0).getColor())){
                    tempPlayer.setInfluencePoints(tempPlayer.getInfluencePoints()+1);
                    kingPlayer = tempPlayer;
                    i++;
                }
            }
        }
        this.noTower = false;
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

    public void islandDominance(IslandCard island){

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

        // gets selected island
        selectedIsland = island;

        if (selectedIsland.getLocked()) return;

        if (selectedIsland.getStudents().isEmpty()) return;

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

        //check excludedColor
        switch(this.excludedColor){
            case RED:
                numRed = 0;
                break;
            case PINK:
                numPink = 0;
                break;
            case YELLOW:
                numYellow = 0;
                break;
            case BLU:
                numBlue = 0;
                break;
            case GREEN:
                numGreen = 0;
                break;
        }

        //resets excludedColor
        this.excludedColor = null;

        //checks which player has color dominance of the students on the island and calculates influences
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
                if (!noTower && i== 0 && selectedIsland.getTower()!= null && selectedIsland.getTower().getColor().equals(tempPlayer.getSchoolBoard().getTowers().get(0).getColor())){
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


    public GameComponents generateBoard(Boolean isPro, int numOfPlayers){

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
            if (i != 0 && i != 6){
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
                        this.playerList().get(i).setSchoolBoard(boardPlayer1);
                        break;
                    case 1:
                        SchoolBoard boardPlayer2 = new SchoolBoard(ColorTower.WHITE,numOfPlayers,entrancePlayer);
                        schools.add(boardPlayer2);
                        this.playerList().get(i).setSchoolBoard(boardPlayer2);
                        break;
                    case 2:
                    case 3:
                        SchoolBoard boardPlayerWithNoTowers = new SchoolBoard(numOfPlayers,entrancePlayer);
                        schools.add(boardPlayerWithNoTowers);
                        this.playerList().get(i).setSchoolBoard(boardPlayerWithNoTowers);
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
                        this.playerList().get(i).setSchoolBoard(boardPlayer1);
                        break;
                    case 1:
                        SchoolBoard boardPlayer2 = new SchoolBoard(ColorTower.WHITE,numOfPlayers,entrancePlayer);
                        schools.add(boardPlayer2);
                        this.playerList().get(i).setSchoolBoard(boardPlayer2);
                        break;
                    case 2:
                        SchoolBoard boardPlayer3 = new SchoolBoard(ColorTower.GREY,numOfPlayers,entrancePlayer);
                        schools.add(boardPlayer3);
                        this.playerList().get(i).setSchoolBoard(boardPlayer3);
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

            CharacterDeck specialCards = new CharacterDeck(this);

            GameComponents table = new GameComponents(islandsCircularArray, motherPiece, schools, studentsBag, cloudContainer, professors, coinContainer,prohibitionCards,specialCards);
            this.GameComponents = table;
            return table;
        }

        GameComponents table = new GameComponents(islandsCircularArray, motherPiece, schools, studentsBag, cloudContainer, professors);
        this.GameComponents = table;
        return table;

    }

    //adds the Card to the Array of chosen cards
    public void addChosenCard(Card chosen, int numOfPlayers){

        //necessary because, in the new round, a Player can play the card played by the last player at the previous round
        if(this.chosenCards.size() == numOfPlayers)
            this.chosenCards.clear(); //clear the array if already full

        if (this.chosenCards.contains(chosen)) {
            System.out.println("ERROR: You can't play this card in this round because someone has already played that");
            return;
        }

        //chosenCards is a private attribute of game, it has the same size as numOfPlayers, at the end of a round becomes empty
        this.chosenCards.add(chosen);
    }

    //shows the 3 Character cards that a player can use?
    public void pickCharacters(){
        for (int i=0; i<3; i++) {
            System.out.println(this.GameComponents.getSpecialDeck().getCards().get(i));
        }
    }

    //selects a random player to begin
    public Player startGameWithRandomPlayer(){
        int index = (int) (Math.random() * this.complexLobby.getPlayers().size());
        return this.complexLobby.getPlayers().get(index);
    }

    //checks if the game ends, returns the winner player
    public Player winCondition() {

        //check if a player finished his free towers in his schoolboard
        for(int i = 0; i<this.complexLobby.getPlayers().size(); i++)
            if (this.getGameComponents().getSchoolBoards().get(i).getTowers().size() == 0)
                return this.complexLobby.getPlayers().get(i);


        //there are three archipelagos
        if (this.GameComponents.getArchipelago().size() == 3) {

            //tower counters
            int grey = -1;
            int black = 0;
            int white = 0;

            if (this.complexLobby.getPlayers().size() == 3)
                grey = 0;

            for (IslandCard tempIsland : this.GameComponents.getArchipelago()) {
                switch (tempIsland.getTower().getColor()) {
                    case BLACK:
                        black++;
                    case WHITE:
                        white++;
                    case GREY:
                        grey++;
                }
            }

            // winner of: game with 2,4 players
            if ((black > white && grey == -1) || (black > white && black > grey))
                return this.complexLobby.getPlayers().get(0);

            else if ((black < white && grey == -1) || (black < white && white > grey))
                return this.complexLobby.getPlayers().get(1);

                // winner of: game with 3 players
            else if (black > white && black > grey)
                return this.complexLobby.getPlayers().get(0);

            else if (black < white && white > grey)
                return this.complexLobby.getPlayers().get(1);

            else if (grey > black && grey > white)
                return this.complexLobby.getPlayers().get(2);

            //condition of draw: the winner is the player with more professors
            else if (grey == black && black == white) {

                int numProfBlackPlayer = 0;
                int numProfWhitePlayer = 0;
                int numProfGreyPlayer = 0;

                for (int i = 0; i < this.GameComponents.getSchoolBoards().size(); i++) {
                    for (DiningRoom diningRoom : this.GameComponents.getSchoolBoards().get(i).getDiningRooms()) {
                        if (diningRoom.IsProfessor()) {
                            switch (i) {
                                case 0:
                                    numProfBlackPlayer++;
                                case 1:
                                    numProfWhitePlayer++;
                                case 2:
                                    numProfGreyPlayer++;
                            }
                        }
                    }
                }
                if(numProfBlackPlayer > numProfWhitePlayer && numProfBlackPlayer > numProfGreyPlayer)
                    return this.complexLobby.getPlayers().get(0);

                if(numProfBlackPlayer < numProfWhitePlayer && numProfWhitePlayer > numProfGreyPlayer)
                    return this.complexLobby.getPlayers().get(1);

                if(numProfGreyPlayer > numProfWhitePlayer && numProfBlackPlayer < numProfGreyPlayer)
                    return this.complexLobby.getPlayers().get(2);
            }
        }

        //if a player finishes his playable cards
        for (Player p: this.complexLobby.getPlayers()) {
            if(p.getDeck().leftCard()==0)
                return p;
        }

        //recursion to check the winner if there isn't any student in the bag
        if(this.GameComponents.getBag().left()==0)
            return winCondition();

        return null; //if there isn't a winner yet
    }

    /**
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

    public void useEffect(int characterUsed){

    }
}

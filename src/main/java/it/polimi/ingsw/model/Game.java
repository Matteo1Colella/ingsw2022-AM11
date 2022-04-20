package it.polimi.ingsw.model;

import it.polimi.ingsw.model.colors.ColorStudent;
import it.polimi.ingsw.model.colors.ColorTower;
import it.polimi.ingsw.model.cards.CharacterDeck;
import it.polimi.ingsw.controller.ComplexLobby;
import it.polimi.ingsw.model.pieces.*;
import it.polimi.ingsw.model.board.*;

import java.util.*;

public class Game {
    private Status status;
    private boolean isPro;
    private boolean noTower;
    private ColorStudent excludedColor;
    private GameComponents GameComponents;
    private int ID;
    private final int numPlayers;
    private ComplexLobby complexLobby;
    private final HashMap<ColorStudent, Player> dominanceMap;


    // Start of Getters, Setters, Constructor

    public Game(boolean isPro, int ID, int numPlayers) {
        this.isPro = isPro;
        this.ID = ID;
        this.numPlayers = numPlayers;
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

    public GameComponents getGameComponents() {
        return GameComponents;
    }

    public void setGameStructure(GameComponents gameComponents) {
        GameComponents = gameComponents;
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
                sizeVector[i] = player.getSchoolBoard().getStudentSize(tempColor);
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
            if(max != 0){
                for (int value : colorVector) {
                    if (value == max) {
                        countMax++;
                    }
                }
            }

            if(countMax == 1){
                // if there is only a player with the maximum number of student in a dining room, he earns the dominance
                this.dominanceMap.put(tempColor, players.get(indexMax));
            } else if (isPro && countMax > 1 && colorVector[j] != 0) {
                // if there are multiple player with the same number of student in a dining room,
                // only the player who uses the character 2 will earn the dominance.
                // in no one player is using charachter 2, nothing change
                for(Player player: players){
                    SchoolBoard schoolBoardPlayer = player.getSchoolBoard();
                    if(schoolBoardPlayer.isCharachter2used()){
                        if(schoolBoardPlayer.getDiningRoom(tempColor).getStudentsSize() == max){
                            this.dominanceMap.put(tempColor, player);
                            //the character2's validity lasts only one turn
                            player.getSchoolBoard().setCharachter2used(false);
                            break;
                        }
                    }
                }
            } else {
                this.dominanceMap.put(tempColor, null);
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
                // find the correct professor in the professors' array
                for(i = 0; i < this.getGameComponents().getProfessorCollection().size(); i++){
                    ArrayList<Professor> professors = this.getGameComponents().getProfessorCollection();
                    if(professors.get(i).getColor().equals(tempColor)){
                        professor = professors.get(i);
                        break;
                    }
                }
                if(professor != null && professor.getPosition() == null){
                    player.getSchoolBoard().setProfessor(professor);
                    professor.setPosition(player.getSchoolBoard().getDiningRoom(tempColor));
                } else {
                    // find the former possessor
                    for(Player tempPlayer : this.playerList()){
                        if(tempPlayer.getSchoolBoard().getProfessor(tempColor) != null){
                            tempPlayer.getSchoolBoard().setProfessorNull(tempColor);
                            break;
                        }

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
                case BLUE:
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
            case BLUE:
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
                        case BLUE:
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
                    tempPlayer.setInfluencePoints(tempPlayer.getInfluencePoints()+1 + selectedIsland.getMergedWith().size());
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
                case BLUE:
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
            case BLUE:
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
                        case BLUE:
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

    public GameComponents generateBoard(){

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
        for (int i = 0; i < this.numPlayers; i++){
            ArrayList<Student> cloudStudents = new ArrayList<>();
            if(this.numPlayers == 2 || this.numPlayers == 4){
                for(int j = 0; j < 3; j++){
                    Student student = studentsBag.draw();
                    cloudStudents.add(student);
                }
            } else if (this.numPlayers == 3){
                for(int j = 0; j < 4; j++){
                    Student student = studentsBag.draw();
                    cloudStudents.add(student);
                }
            }
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
        for (int i = 0; i < this.numPlayers; i++){
            ArrayList<Student> entrancePlayer = new ArrayList<>();
            if (this.numPlayers == 2 || this.numPlayers == 4){
                for(int j=0; j<7; j++) {
                    entrancePlayer.add(studentsBag.draw());
                }
                switch(i){
                    case 0:
                        SchoolBoard boardPlayer1 = new SchoolBoard(ColorTower.BLACK,this.numPlayers,entrancePlayer);
                        schools.add(boardPlayer1);
                        this.playerList().get(i).setSchoolBoard(boardPlayer1);
                        break;
                    case 1:
                        SchoolBoard boardPlayer2 = new SchoolBoard(ColorTower.WHITE,this.numPlayers,entrancePlayer);
                        schools.add(boardPlayer2);
                        this.playerList().get(i).setSchoolBoard(boardPlayer2);
                        break;
                    case 2:
                        SchoolBoard boardPlayerWithNoTowers1 = new SchoolBoard(entrancePlayer);
                        schools.add(boardPlayerWithNoTowers1);
                        this.playerList().get(i).setSchoolBoard(boardPlayerWithNoTowers1);
                        break;
                    case 3:
                        SchoolBoard boardPlayerWithNoTowers2 = new SchoolBoard(entrancePlayer);
                        schools.add(boardPlayerWithNoTowers2);
                        this.playerList().get(i).setSchoolBoard(boardPlayerWithNoTowers2);
                        break;
                }
            } else {
                for(int j=0; j<9; j++) {
                    entrancePlayer.add(studentsBag.draw());
                }
                switch(i){
                    case 0:
                        SchoolBoard boardPlayer1 = new SchoolBoard(ColorTower.BLACK,this.numPlayers,entrancePlayer);
                        schools.add(boardPlayer1);
                        this.playerList().get(i).setSchoolBoard(boardPlayer1);
                        break;
                    case 1:
                        SchoolBoard boardPlayer2 = new SchoolBoard(ColorTower.WHITE,this.numPlayers,entrancePlayer);
                        schools.add(boardPlayer2);
                        this.playerList().get(i).setSchoolBoard(boardPlayer2);
                        break;
                    case 2:
                        SchoolBoard boardPlayer3 = new SchoolBoard(ColorTower.GREY,this.numPlayers,entrancePlayer);
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

            CharacterDeck temp = null;

            GameComponents temptable = new GameComponents(islandsCircularArray, motherPiece, schools, studentsBag, cloudContainer, professors, coinContainer,prohibitionCards,temp);
            this.GameComponents = temptable;

            CharacterDeck specialCards = new CharacterDeck(this);
            GameComponents table = new GameComponents(islandsCircularArray, motherPiece, schools, studentsBag, cloudContainer, professors, coinContainer,prohibitionCards,specialCards);
            this.GameComponents = table;

            System.out.println("Game with PRO rules... \n");
            pickCharacters();
            return table;
        }

        GameComponents table = new GameComponents(islandsCircularArray, motherPiece, schools, studentsBag, cloudContainer, professors);
        this.GameComponents = table;

        this.startGameWithRandomPlayer();

        return table;

    }

    //shows the 3 Character cards that a player can use?
    public void pickCharacters(){
        System.out.println("Character cards chosen for this game: ");
        for (int i=0; i<3; i++) {
            System.out.println(this.GameComponents.getSpecialDeck().getCards().get(i).getNum());
        }
    }

    //selects a random player to begin
    public Player startGameWithRandomPlayer(){
        int index = (int) (Math.random() * this.complexLobby.getPlayers().size());
        this.complexLobby.setActivePlayer(this.complexLobby.getPlayers().get(index));
        ArrayList<Player> startingList = new ArrayList<>();
        startingList.add(this.complexLobby.getPlayers().get(index));

        for(int i=0; i<this.complexLobby.getNumPlayers(); i++)
            if(startingList.contains(this.complexLobby.getPlayers().get(i)))
                ;
            else
                startingList.add(this.complexLobby.getPlayers().get(i));


        this.complexLobby.setPlayerOrder(startingList);


        this.complexLobby.setActivePlayer(this.complexLobby.getPlayerOrder().get(0));
        return this.complexLobby.getPlayerOrder().get(0);
    }

    //checks if the game ends, returns the winner player
    public Player winCondition() {

        //check if a player finished his free towers in his schoolBoard
        for(int i = 0; i<this.complexLobby.getPlayers().size(); i++)
            if (this.complexLobby.getPlayers().get(i).getSchoolBoard().getTowers().size() == 0)
                return this.complexLobby.getPlayers().get(i);


        //there are three archipelagos
        if (this.GameComponents.getArchipelago().size() == 3 || (this.GameComponents.getBag().left()==0 ) ){

            //tower counters
            int grey = -1;
            int black = 0;
            int white = 0;

            if (this.complexLobby.getPlayers().size() == 3)
                grey = 0;

            for (IslandCard tempIsland : this.GameComponents.getArchipelago()) {
                if(tempIsland.getTower()==null)
                    ;
                else {
                    if(tempIsland.getTower().getColor()==ColorTower.BLACK)
                        black++;
                    else if (tempIsland.getTower().getColor()==ColorTower.WHITE)
                        white++;
                    else if (tempIsland.getTower().getColor()==ColorTower.GREY)
                        grey++;
                }
            }

            // winner of: game with 2,4 players
            if ((black > white && grey == -1) )
                return this.complexLobby.getPlayers().get(0);

            else if ((black < white && grey == -1) )
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
                    for (DiningRoom diningRoom : this.complexLobby.getPlayers().get(i).getSchoolBoard().getDiningRooms()) {
                        if (diningRoom.IsProfessor()) {
                            if(i==0)
                                numProfBlackPlayer++;
                            else if (i==1)
                                numProfWhitePlayer++;
                            else if(i==2)
                                numProfGreyPlayer++;
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
            if(p.getDeck()==null)
                System.out.println("ERROR: the player "+ p.getID_player()+" doesn't have a deck!");
            else if(p.getDeck().leftCard()==0)
                return p;
        }

        return null; //if there isn't a winner yet
    }

    /**
    If two adjacent island are dominated by two towers
      of the same colors, then the island are merged.
    */
    public void mergeIsland(){
        ArrayList<IslandCard> islands = this.GameComponents.getArchipelago();
        //System.out.println("Archipelago before merging");
        //islands.stream().map(IslandCard::getId_island).forEach(System.out::println);
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
            return;
        }
        Tower currTower = selectedIsland.getTower();
        Tower nextTower = next.getTower();
        Tower prevTower = prev.getTower();

        //if nextTower is null or has a different color then the selected, the prev is merged with the selected
        if ((nextTower == null || !nextTower.getColor().equals(currTower.getColor())) && prevTower!=null && currTower.getColor().equals(prevTower.getColor())){
            selectedIsland.setMergedWith(prev);
            //next.setMergedWith(selectedIsland);
            if(!prev.getMergedWith().isEmpty()){
                selectedIsland.addMergedWith(prev.getMergedWith());
            }
            islands.remove(prev.getId_island());
        }
        //if prevTower is null or has a different color then the selected, the prev is merged with the selected
        else if (( prevTower == null || !prevTower.getColor().equals(currTower.getColor())) && nextTower!=null && currTower.getColor().equals(nextTower.getColor())){
            selectedIsland.setMergedWith(next);
            //prev.setMergedWith(selectedIsland);
            if(!next.getMergedWith().isEmpty()){
                selectedIsland.addMergedWith(next.getMergedWith());
            }
            islands.remove(next.getId_island());
        }
        // if prevTower and nextTower have the same color of selectedTower, they are both merged with selected
        else if (prevTower!=null && nextTower!=null && currTower.getColor().equals(nextTower.getColor()) && currTower.getColor().equals(prevTower.getColor())){
            selectedIsland.setMergedWith(prev);
            selectedIsland.setMergedWith(next);
            //next.setMergedWith(selectedIsland);
            //prev.setMergedWith(selectedIsland);

            if(!prev.getMergedWith().isEmpty()){
                selectedIsland.addMergedWith(prev.getMergedWith());
            }
            if(!next.getMergedWith().isEmpty()){
                selectedIsland.addMergedWith(next.getMergedWith());
            }
            islands.remove(prev.getId_island());
            //islands.remove(next.getId_island()-1);
            islands.remove(next.getId_island());
        }

        //System.out.println("Archipelago after merging");
        //islands.stream().map(IslandCard::getId_island).forEach(System.out::println);

        int i = 0;
        for(IslandCard temp : islands){
            temp.setId_island(i);
            i++;
        }

        this.GameComponents.setArchipelago(islands);
        //System.out.println("Archipelago after mapping and merging");
        //islands.stream().map(IslandCard::getId_island).forEach(System.out::println);
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

    //function which, at the end of each turn, check if a player can earn some conis
    public void coinGiver(){
        for(Player player : this.complexLobby.getPlayers()){
            SchoolBoard schoolBoard = player.getSchoolBoard();
            //for all color check if the player can earn a coin
            for(ColorStudent colorStudent : ColorStudent.values()){
                if(schoolBoard.giveCoin(colorStudent)){
                    this.getGameComponents().getCoins().giveCoin(player);
                }
            }
        }
    }

    public void refillCloudCards(){
        ArrayList<CloudCard> cloudCards = this.getGameComponents().getCloudCards();
        for(CloudCard cloudCard : cloudCards){
            if(this.numPlayers == 2 || this.numPlayers == 4){
                ArrayList<Student> students = new ArrayList<>();
                for(int j = 0; j < 3; j++){
                    Student student = this.getGameComponents().getBag().draw();
                    students.add(student);
                }
                cloudCard.setStudents(students);
            } else if (this.numPlayers == 3){
                ArrayList<Student> students = new ArrayList<>();
                for(int j = 0; j < 4; j++){
                    Student student = this.getGameComponents().getBag().draw();
                    students.add(student);
                }
                cloudCard.setStudents(students);
            }
        }
    }
}

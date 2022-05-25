package it.polimi.ingsw;

import it.polimi.ingsw.controller.ComplexLobby;
import it.polimi.ingsw.controller.GameManager;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Mage;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.board.CloudCard;
import it.polimi.ingsw.model.board.Entrance;
import it.polimi.ingsw.model.board.GameComponents;
import it.polimi.ingsw.model.cards.Card;
import it.polimi.ingsw.model.cards.CharacterCard;
import it.polimi.ingsw.model.cards.characters.*;
import it.polimi.ingsw.model.colors.ColorStudent;
import it.polimi.ingsw.model.pieces.Student;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{

    private static void askCharacter(Player currentPlayer){
        //shows usable characters
        int choice;
        String input;
        int pcoins;
        Game newGame = currentPlayer.getPlayerGame();


        pcoins = currentPlayer.getCoinOwned();
        input = "";
        choice = -1;


        while(!Objects.equals(input, "no") && !Objects.equals(input, "yes"))
        {
            System.out.println("Do you want to use a character?");
            System.out.println("Coins: " + currentPlayer.getCoinOwned());
            Scanner charscanner = new Scanner(System.in);
            input = charscanner.nextLine();
        }
        if (input.equals("yes")) {
            int n = 0;
            for (CharacterCard temp : newGame.getGameComponents().getSpecialDeck().getCards()) {
                if (temp.getNecessaryCoin() <= pcoins) {
                    System.out.println(n + ": Character " + temp.getNum());

                }
                n++;
            }
            if (n == 0){
                System.out.println("No character usable");
                return;
            }
            while (choice < 0 || choice >=n) {
                System.out.println("Select the character you want to use");
                Scanner charscanner = new Scanner(System.in);
                choice = charscanner.nextInt();
            }
            switch(newGame.getGameComponents().getSpecialDeck().getCards().get(choice).getNum()){
                case 1:

                    Character1 card = (Character1) newGame.getGameComponents().getSpecialDeck().getCards().get(choice);
                    int val = -1;
                    int val2 = - 1;

                    card.getStudents().stream().map(Student::getColor).forEach(System.out::println);
                    while(val < 0 || val > card.getStudents().size()) {
                        System.out.println("choose Student");
                        Scanner charscanner = new Scanner(System.in);
                        val = charscanner.nextInt();
                    }

                    while(val2 < 0 || val2 > newGame.getGameComponents().getArchipelago().size()) {
                        System.out.println("choose Island");
                        Scanner charscanner = new Scanner(System.in);
                        val2 = charscanner.nextInt();
                    }
                    card.effect(currentPlayer, newGame.getGameComponents().getArchipelago().get(val), val2);

                    break;
                case 2:
                    Character2 card2 = (Character2) newGame.getGameComponents().getSpecialDeck().getCards().get(choice);
                    card2.effect(currentPlayer);
                    break;
                case 3:
                    Character3 card3 = (Character3) newGame.getGameComponents().getSpecialDeck().getCards().get(choice);
                    int val3 = -1;

                    while(val3 < 0 || val3 > newGame.getGameComponents().getArchipelago().size()) {
                        System.out.println("choose Island");
                        Scanner charscanner = new Scanner(System.in);
                        val3 = charscanner.nextInt();
                    }

                    card3.effect(currentPlayer,  newGame.getGameComponents().getArchipelago().get(val3));

                    break;
                case 4:
                    Character4 card4 = (Character4) newGame.getGameComponents().getSpecialDeck().getCards().get(choice);
                    card4.effect(currentPlayer);
                    break;
                case 5:
                    Character5 card5 = (Character5) newGame.getGameComponents().getSpecialDeck().getCards().get(choice);
                    int val5 = -1;

                    while(val5 < 0 || val5 > newGame.getGameComponents().getArchipelago().size()) {
                        System.out.println("choose Island");
                        Scanner charscanner = new Scanner(System.in);
                        val5 = charscanner.nextInt();
                    }

                    card5.effect(currentPlayer, newGame.getGameComponents().getArchipelago().get(val5));

                    break;
                case 6:
                    Character6 card6 = (Character6) newGame.getGameComponents().getSpecialDeck().getCards().get(choice);
                    card6.effect(currentPlayer);
                    break;
                case 7:
                    Character7 card7 = (Character7) newGame.getGameComponents().getSpecialDeck().getCards().get(choice);
                    int val7 = -1;
                    int[] fromEntrance = {-1, -1, -1};
                    int[] fromCard = {-1, -1, -1};
                    int duplicate = 0;

                    for(int n7 = 0; n7 < 3; n7++){
                        val7 = -1;
                        while(val7 < 0 || val7 > currentPlayer.getSchoolBoard().getEntrance().getStudents().size() || duplicate > 0) {
                            duplicate = 0;
                            System.out.println("choose the " + n7 + " student from entrance");
                            Scanner charscanner = new Scanner(System.in);
                            val7 = charscanner.nextInt();

                            for(int j = 0; j < fromEntrance.length; j++){
                                if (fromEntrance[j] == val7){
                                    duplicate ++;
                                }
                            }

                        }
                        fromEntrance[n7] = val7;
                    }

                    System.out.println("students on character:");
                    card7.getStudents().stream().map(Student::getColor).forEach(System.out::println);
                    for(int n7 = 0; n7 < 3; n7++){
                        val7 = -1;
                        while(val7 < 0 || val7 > card7.getStudents().size()||duplicate>0) {
                            duplicate = 0;
                            System.out.println("choose the " + n7 + " student from entrance");
                            Scanner charscanner = new Scanner(System.in);
                            val7 = charscanner.nextInt();

                            for(int j = 0; j < fromEntrance.length; j++){
                                if (fromCard[j] == val7){
                                    duplicate ++;
                                }
                            }
                        }
                        fromCard[n7] = val7;
                    }
                    card7.effect(currentPlayer, fromEntrance, fromCard);

                    break;
                case 8:
                    Character8 card8 = (Character8) newGame.getGameComponents().getSpecialDeck().getCards().get(choice);
                    card8.effect(currentPlayer);

                    break;
                case 9:
                    String chosencolor = "";
                    ColorStudent color = null;
                    System.out.println("choose a color to exclude");
                    while(!chosencolor.equals("red") && !chosencolor.equals("blue") && !chosencolor.equals("yellow") && !chosencolor.equals("pink") && !chosencolor.equals("green")){
                        Scanner charscanner = new Scanner(System.in);
                        chosencolor = charscanner.nextLine();
                    }

                    switch(chosencolor){
                        case "red":
                            color = ColorStudent.RED;
                            break;
                        case "blue":
                            color = ColorStudent.BLUE;
                            break;
                        case "green":
                            color = ColorStudent.GREEN;
                            break;
                        case "yellow":
                            color = ColorStudent.YELLOW;
                            break;
                        case "pink":
                            color = ColorStudent.PINK;
                            break;
                    }

                    Character9 card9 = (Character9) newGame.getGameComponents().getSpecialDeck().getCards().get(choice);

                    card9.effect(currentPlayer, color);

                    break;
                case 10:

                    int[] fromE = {-1,-1,-1};
                    ArrayList<Student> aFromDining = new ArrayList<>();
                    int dup = 0;
                    String diningroomcolor = "";
                    ColorStudent c = null;
                    int s = -1;
                    int j = 0;

                    int numred = 0;
                    int numblue = 0;
                    int numgreen = 0;
                    int numyellow = 0;
                    int numpink = 0;

                    while(j<2){
                        s = -1;
                        while(s < 0 || s > currentPlayer.getSchoolBoard().getEntrance().getStudents().size() || dup > 0) {
                            dup = 0;
                            System.out.println("choose the " + j + " student from entrance");
                            Scanner charscanner = new Scanner(System.in);
                            s = charscanner.nextInt();
                            for(int z = 0; z < fromE.length; z++){
                                if (fromE[z] == s){
                                    dup ++;
                                }
                            }
                        }
                        fromE[j] = s;
                        j++;
                    }


                    j = 0;
                    while(j<2){
                        diningroomcolor = "";
                        while(!diningroomcolor.equals("red") && !diningroomcolor.equals("blue") && !diningroomcolor.equals("yellow") && !diningroomcolor.equals("pink") && !diningroomcolor.equals("green")) {
                            System.out.println("choose the color of the dining room you want to draw a student from");
                            Scanner charscanner = new Scanner(System.in);
                            diningroomcolor = charscanner.nextLine();
                        }



                        if(diningroomcolor.equals("red")){
                            c = ColorStudent.RED;
                            numred++;
                        }
                        if(diningroomcolor.equals("blue")){
                            c = ColorStudent.BLUE;
                            numblue++;
                        }
                        if(diningroomcolor.equals("green")){
                            c = ColorStudent.GREEN;
                            numgreen++;
                        }
                        if(diningroomcolor.equals("pink")){
                            c = ColorStudent.PINK;
                            numpink++;
                        }
                        if(diningroomcolor.equals("yellow")){
                            c = ColorStudent.YELLOW;
                            numyellow++;
                        }

                        switch (c){
                            case RED:
                                if(currentPlayer.getSchoolBoard().getDiningRoom(c).getStudents().size()>=numred){
                                    j++;
                                    aFromDining.add(currentPlayer.getSchoolBoard().getDiningRoom(c).getStudents().get(numred - 1));
                                }
                                System.out.println("not enough students");

                                break;
                            case GREEN:
                                if(currentPlayer.getSchoolBoard().getDiningRoom(c).getStudents().size()>=numgreen){
                                    j++;
                                    aFromDining.add(currentPlayer.getSchoolBoard().getDiningRoom(c).getStudents().get(numgreen - 1));
                                }
                                System.out.println("not enough students");
                                break;
                            case BLUE:
                                if(currentPlayer.getSchoolBoard().getDiningRoom(c).getStudents().size()>=numblue){
                                    j++;
                                    aFromDining.add(currentPlayer.getSchoolBoard().getDiningRoom(c).getStudents().get(numblue - 1));
                                }
                                System.out.println("not enough students");
                                break;
                            case PINK:
                                if(currentPlayer.getSchoolBoard().getDiningRoom(c).getStudents().size()>=numpink ){
                                    j++;
                                    aFromDining.add(currentPlayer.getSchoolBoard().getDiningRoom(c).getStudents().get(numpink - 1));
                                }
                                System.out.println("not enough students");
                                break;
                            case YELLOW:
                                if(currentPlayer.getSchoolBoard().getDiningRoom(c).getStudents().size()>=numyellow){
                                    j++;
                                    aFromDining.add(currentPlayer.getSchoolBoard().getDiningRoom(c).getStudents().get(numyellow - 1));
                                }
                                System.out.println("not enough students");
                                break;
                        }
                    }


                    Character10 card10 = (Character10) newGame.getGameComponents().getSpecialDeck().getCards().get(choice);
                    card10.effect(currentPlayer, aFromDining, fromE);
                    break;
                case 11:
                    int val11 = -1;
                    System.out.println("test");
                    Character11 card11 = (Character11) newGame.getGameComponents().getSpecialDeck().getCards().get(choice);
                    card11.getStudents().stream().map(Student::getColor).forEach(System.out::println);

                    while(val11 < 0 || val11 > card11.getStudents().size()) {
                        System.out.println("choose Student");
                        Scanner charscanner = new Scanner(System.in);
                        val11 = charscanner.nextInt();
                    }

                    card11.effect(currentPlayer, val11);


                    break;
                case 12:
                    String col = "";
                    ColorStudent c12 = null;
                    while(!col.equals("red") && !col.equals("blue") && !col.equals("green") && !col.equals("yellow") && !col.equals("pink")){
                        System.out.println("choose Color");
                        Scanner charscanner = new Scanner(System.in);
                        col = charscanner.nextLine();
                    }
                    switch(col){
                        case "red":
                            c12 = ColorStudent.RED;
                            break;
                        case "blue":
                            c12 = ColorStudent.BLUE;
                            break;
                        case "green":
                            c12 = ColorStudent.GREEN;
                            break;
                        case "yellow":
                            c12 = ColorStudent.YELLOW;
                            break;
                        case "pink":
                            c12 = ColorStudent.PINK;
                            break;
                    }

                    System.out.println("test");
                    Character12 card12 = (Character12) newGame.getGameComponents().getSpecialDeck().getCards().get(choice);
                    card12.effect(currentPlayer, c12);
                    break;
            }
        }
    }

    public static void progame(){
        GameManager GM = new GameManager();

        GM.login("Cole", 4, true);
        GM.getPlayerComplexLobby("Cole").deckRequest(Mage.MAGE1, "Cole");
        GM.login("Leo", 4, true);
        GM.getPlayerComplexLobby("Leo").deckRequest(Mage.MAGE2, "Leo");
        GM.login("ale", 4, true);
        GM.getPlayerComplexLobby("ale").deckRequest(Mage.MAGE3, "ale");
        GM.login("giuseppe", 4, true);
        GM.getPlayerComplexLobby("giuseppe").deckRequest(Mage.MAGE4, "giuseppe");

        Game newGame = GM.getComplexLobbies().get(0).getGame();
        GameComponents gameComponents = newGame.getGameComponents();
        ComplexLobby lobby = GM.getComplexLobbies().get(0);

        newGame.startGameWithRandomPlayer();
        for(Player player : lobby.getPlayerOrder()){
            System.out.println(player.getID_player());
        }


        System.out.println("TEAM 1: "+ newGame.playerList().get(0).getID_player() + " and " +  newGame.playerList().get(2).getID_player());
        System.out.println("TEAM 2: "+ newGame.playerList().get(1).getID_player() + " and " +  newGame.playerList().get(3).getID_player());

        System.out.println("size of towers of the 4 players:");
        System.out.println(newGame.playerList().get(0).getSchoolBoard().getTowers().size());
        System.out.println(newGame.playerList().get(1).getSchoolBoard().getTowers().size());
        System.out.println(newGame.playerList().get(2).getSchoolBoard().getTowers().size());
        System.out.println(newGame.playerList().get(3).getSchoolBoard().getTowers().size());
        System.out.println();

        Player winnerPlayer = null;
        Player currentPlayer = lobby.getActivePlayer();
        int numOfPlayers = newGame.playerList().size();

        while(true){
            int i = 0;
            System.out.println(" ");
            newGame.getGameComponents().printArchipelago();
            System.out.println(" ");

            // The player has to play a card to start the turn
            while(i != numOfPlayers){
                System.out.println("Player " + (i + 1) + " " + currentPlayer.getID_player());
                currentPlayer.printRemainingCard();
                System.out.println("Choose a card: ");

                Card card = null;
                do{
                    Scanner scanner = new Scanner(System.in);
                    int playableCard = scanner.nextInt();
                    card = currentPlayer.playCard(playableCard);

                    //added because the round number 10 playCards returns null! (there is no card remaining) so the rest of the code doesn't word if card == null
                    if(card==null){
                        winnerPlayer = newGame.winCondition();

                        if (winnerPlayer != null){
                            System.out.println(winnerPlayer.getID_player() + " TEAM won!");
                            return;

                        }
                    }

                } while (!newGame.getComplexLobby().checkIfPlayable(card));


                currentPlayer.setMotherNatureMoves(card.getSteps());
                newGame.getComplexLobby().changeActivePlayer();
                currentPlayer = lobby.getActivePlayer();
                i++;

            }
            i = 0;

            newGame.getComplexLobby().modifyPlayerTurn();
            currentPlayer = lobby.getActivePlayer();

            while(i != numOfPlayers){

                askCharacter(currentPlayer);
                String input = "";
                int choice = -1;

                // change the position of three students from the entrance
                for(int j = 0; j < 3; j++){
                    currentPlayer.getSchoolBoard().printSchoolBoard();
                    Entrance entrance = currentPlayer.getSchoolBoard().getEntrance();
                    System.out.println("Select a student from the entrance");
                    Scanner scanner = new Scanner(System.in);
                    int studentIndex = scanner.nextInt();
                    System.out.println("1. Move student to island. \n 2. Move student in the dining room.");
                    Scanner scanner1 = new Scanner(System.in);
                    choice = scanner1.nextInt();
                    if(choice == 1){
                        newGame.getGameComponents().printArchipelago();
                        System.out.println("Select island: ");
                        Scanner scanner2 = new Scanner(System.in);
                        int island = scanner2.nextInt();
                        currentPlayer.getSchoolBoard().moveStudent(entrance.getStudents().get(studentIndex), newGame.getGameComponents().getArchipelago().get(island));
                    } else if(choice == 2){
                        currentPlayer.getSchoolBoard().moveStudent(entrance.getStudents().get(studentIndex));
                        newGame.colorDominance();
                        newGame.coinGiver();
                    }
                }


                askCharacter(currentPlayer);

                int moves;
                do{
                    System.out.println("Select the moves of mn: ");
                    Scanner scanner3 = new Scanner(System.in);
                    moves = scanner3.nextInt();
                }while(moves > currentPlayer.getMotherNatureMoves());

                newGame.moveMotherNature(moves, newGame.getGameComponents().getMotherNature(), newGame.getGameComponents().getArchipelago());

                askCharacter(currentPlayer);

                newGame.islandDominance();
                newGame.mergeIsland();

                System.out.println("Choose a cloud:");
                newGame.getGameComponents().printClouds();
                int cloud;
                do{
                    Scanner scanner = new Scanner(System.in);
                    cloud = scanner.nextInt();
                }while(cloud > newGame.getGameComponents().getCloudCards().size());

                CloudCard cloudCardChosen = newGame.getGameComponents().getCloudCards().get(cloud - 1);
                currentPlayer.getSchoolBoard().addStudetsToEntrance(cloudCardChosen.drawStudents());

                newGame.getGameComponents().printClouds();
                System.out.println(" ");
                newGame.getGameComponents().printArchipelago();
                System.out.println(" ");

                winnerPlayer = newGame.winCondition();

                if (winnerPlayer != null){
                    System.out.println(winnerPlayer.getID_player() + " TEAM won!");
                    return;
                }
                newGame.getComplexLobby().changeActivePlayer();
                currentPlayer = lobby.getActivePlayer();

                i++;
            }
            newGame.refillCloudCards();
            newGame.getComplexLobby().modifyPlayerTurn();
            currentPlayer = lobby.getActivePlayer();
            System.out.println("size of towers of the 4 players:");
            System.out.println(newGame.playerList().get(0).getSchoolBoard().getTowers().size());
            System.out.println(newGame.playerList().get(1).getSchoolBoard().getTowers().size());
            System.out.println(newGame.playerList().get(2).getSchoolBoard().getTowers().size());
            System.out.println(newGame.playerList().get(3).getSchoolBoard().getTowers().size());
        }

    }



    public static void main( String[] args )
    {
        System.out.println("1 pro 2 normal");
        Scanner scan = new Scanner(System.in);
        int game = scan.nextInt();

        if (game == 1) {
            progame();
            return;
        }

        GameManager GM = new GameManager();

        GM.login("Cole", 4, true);
        GM.getPlayerComplexLobby("Cole").deckRequest(Mage.MAGE1, "Cole");
        GM.login("Leo", 4, true);
        GM.getPlayerComplexLobby("Leo").deckRequest(Mage.MAGE2, "Leo");
        GM.login("ale", 4, true);
        GM.getPlayerComplexLobby("ale").deckRequest(Mage.MAGE3, "ale");
        GM.login("giuseppe", 4, true);
        GM.getPlayerComplexLobby("giuseppe").deckRequest(Mage.MAGE4, "giuseppe");

        Game newGame = GM.getComplexLobbies().get(0).getGame();
        GameComponents gameComponents = newGame.getGameComponents();
        ComplexLobby lobby = GM.getComplexLobbies().get(0);

        newGame.startGameWithRandomPlayer();
        for(Player player : lobby.getPlayerOrder()){
            System.out.println(player.getID_player());
        }

        System.out.println("TEAM 1: "+ newGame.playerList().get(0).getID_player() + " and " +  newGame.playerList().get(2).getID_player());
        System.out.println("TEAM 2: "+ newGame.playerList().get(1).getID_player() + " and " +  newGame.playerList().get(3).getID_player());

        System.out.println("size of towers of the 4 players:");
        System.out.println(newGame.playerList().get(0).getSchoolBoard().getTowers().size());
        System.out.println(newGame.playerList().get(1).getSchoolBoard().getTowers().size());
        System.out.println(newGame.playerList().get(2).getSchoolBoard().getTowers().size());
        System.out.println(newGame.playerList().get(3).getSchoolBoard().getTowers().size());


        Player winnerPlayer = null;
        Player currentPlayer = lobby.getActivePlayer();
        int numOfPlayers = newGame.playerList().size();

        while(winnerPlayer == null){
            int i = 0;
            System.out.println(" ");
            newGame.getGameComponents().printArchipelago();
            System.out.println(" ");
            // The player have to play a card to start the turn
            while(i != numOfPlayers){
                System.out.println("Player " + (i + 1) + " " + currentPlayer.getID_player());
                currentPlayer.printRemainingCard();
                System.out.println("Choose a card: ");

                Card card = null;
                do{
                    Scanner scanner = new Scanner(System.in);
                    int playableCard = scanner.nextInt();
                    card = currentPlayer.playCard(playableCard);
                } while (!newGame.getComplexLobby().checkIfPlayable(card));
                currentPlayer.setMotherNatureMoves(card.getSteps());
                newGame.getComplexLobby().changeActivePlayer();
                currentPlayer = lobby.getActivePlayer();
                i++;

            }
            i = 0;

            newGame.getComplexLobby().modifyPlayerTurn();
            currentPlayer = lobby.getActivePlayer();
            while(i != numOfPlayers){
                System.out.println("Current player: " + currentPlayer.getID_player());

                // change the position of three students from the entrance
                for(int j = 0; j < 3; j++){
                    currentPlayer.getSchoolBoard().printSchoolBoard();
                    Entrance entrance = currentPlayer.getSchoolBoard().getEntrance();
                    System.out.println("Select a student from the entrance");
                    Scanner scanner = new Scanner(System.in);
                    int studentIndex = scanner.nextInt();
                    System.out.println("1. Move student to island. \n 2. Move student in the dining room.");
                    Scanner scanner1 = new Scanner(System.in);
                    int choice = scanner1.nextInt();
                    if(choice == 1){
                        newGame.getGameComponents().printArchipelago();
                        System.out.println("Select island: ");
                        Scanner scanner2 = new Scanner(System.in);
                        int island = scanner2.nextInt();
                        currentPlayer.getSchoolBoard().moveStudent(entrance.getStudents().get(studentIndex), newGame.getGameComponents().getArchipelago().get(island));
                    } else if(choice == 2){
                        currentPlayer.getSchoolBoard().moveStudent(entrance.getStudents().get(studentIndex));
                        newGame.colorDominance();
                    }
                }

                int moves;
                do{
                    System.out.println("Select the moves of mn: ");
                    Scanner scanner3 = new Scanner(System.in);
                    moves = scanner3.nextInt();
                }while(moves > currentPlayer.getMotherNatureMoves());

                newGame.moveMotherNature(moves, newGame.getGameComponents().getMotherNature(), newGame.getGameComponents().getArchipelago());
                newGame.islandDominance();
                newGame.mergeIsland();

                System.out.println("Choose a cloud:");
                newGame.getGameComponents().printClouds();
                int cloud;
                do{
                    Scanner scanner = new Scanner(System.in);
                    cloud = scanner.nextInt();
                }while(cloud > newGame.getGameComponents().getCloudCards().size());


                CloudCard cloudCardChosen = newGame.getGameComponents().getCloudCards().get(cloud - 1);
                currentPlayer.getSchoolBoard().addStudetsToEntrance(cloudCardChosen.drawStudents());

                newGame.getGameComponents().printClouds();
                System.out.println(" ");
                newGame.getGameComponents().printArchipelago();
                System.out.println(" ");
                newGame.getComplexLobby().changeActivePlayer();
                currentPlayer = lobby.getActivePlayer();

                i++;
            }
            newGame.refillCloudCards();
            winnerPlayer = newGame.winCondition();
            newGame.getComplexLobby().modifyPlayerTurn();
            currentPlayer = lobby.getActivePlayer();
        }
        System.out.println(winnerPlayer.getID_player() + " TEAM WON!!! <3");

    }
}

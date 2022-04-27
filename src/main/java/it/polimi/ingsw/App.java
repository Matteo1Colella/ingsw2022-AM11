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

import java.util.Objects;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void progame(){
        GameManager GM = new GameManager();

        GM.login("Cole", 2, true);
        GM.getPlayerComplexLobby("Cole").deckRequest(Mage.MAGE1, "Cole");
        GM.login("Leo", 2, true);
        GM.getPlayerComplexLobby("Cole").deckRequest(Mage.MAGE2, "Leo");

        Game newGame = GM.getComplexLobbies().get(0).getGame();
        GameComponents gameComponents = newGame.getGameComponents();
        ComplexLobby lobby = GM.getComplexLobbies().get(0);

        newGame.startGameWithRandomPlayer();
        for(Player player : lobby.getPlayerOrder()){
            System.out.println(player.getID_player());
        }


        Player winnerPlayer = null;
        Player currentPlayer = lobby.getActivePlayer();
        int numOfPlayers = newGame.playerList().size();

        while(winnerPlayer == null){
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
                System.out.println("Coins: " + currentPlayer.getCoinOwned());
                //shows usable characters
                int pcoins = currentPlayer.getCoinOwned();
                String input = "";
                int choice = -1;
                while(!Objects.equals(input, "no") && !Objects.equals(input, "yes"))
                {
                    System.out.println("Do you want to use a character?");
                    Scanner charscanner = new Scanner(System.in);
                    input = charscanner.nextLine();
                }
                if (input.equals("yes")) {
                    int n = 0;
                    for (CharacterCard temp : newGame.getGameComponents().getSpecialDeck().getCards()) {
                        if (temp.getNecessaryCoin() <= pcoins) {
                            System.out.println(n + " Character " + temp.getNum());
                            n++;
                        }
                    }
                    if (n == 0){
                        System.out.println("No character usable");
                    }
                    if (n != 0){
                    while (choice < 0 || choice >= n) {
                            System.out.println("Select the character you want to use");
                            Scanner charscanner = new Scanner(System.in);
                            choice = charscanner.nextInt();
                        }
                        switch(newGame.getGameComponents().getSpecialDeck().getCards().get(choice).getNum()){
                            case 1:
                                System.out.println("test");
                            case 2:
                                System.out.println("test");
                            case 3:
                                System.out.println("test");
                            case 4:
                                System.out.println("test");
                            case 5:
                                System.out.println("test");
                            case 6:
                                System.out.println("test");
                            case 7:
                                System.out.println("test");
                            case 8:
                                System.out.println("test");
                            case 9:
                                System.out.println("test");
                            case 10:
                                System.out.println("test");
                            case 11:
                                System.out.println("test");
                            case 12:
                                System.out.println("test");
                        }
                    }
                }

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

                //shows usable characters
                pcoins = currentPlayer.getCoinOwned();
                input = "";
                choice = -1;
                while(!Objects.equals(input, "no") && !Objects.equals(input, "yes"))
                {
                    System.out.println("Do you want to use a character?");
                    Scanner charscanner = new Scanner(System.in);
                    input = charscanner.nextLine();
                }
                if (input.equals("yes")) {
                    int n = 0;
                    for (CharacterCard temp : newGame.getGameComponents().getSpecialDeck().getCards()) {
                        if (temp.getNecessaryCoin() <= pcoins) {
                            System.out.println(n + " Character " + temp.getNum());
                            n++;
                        }
                    }
                    if (n == 0){
                        System.out.println("No character usable");
                    }
                    if (n != 0){
                        while (choice < 0 || choice >= n) {
                            System.out.println("Select the character you want to use");
                            Scanner charscanner = new Scanner(System.in);
                            choice = charscanner.nextInt();
                        }
                        switch(newGame.getGameComponents().getSpecialDeck().getCards().get(choice).getNum()){
                            case 1:
                                System.out.println("test");
                            case 2:
                                System.out.println("test");
                            case 3:
                                System.out.println("test");
                            case 4:
                                System.out.println("test");
                            case 5:
                                System.out.println("test");
                            case 6:
                                System.out.println("test");
                            case 7:
                                System.out.println("test");
                            case 8:
                                System.out.println("test");
                            case 9:
                                System.out.println("test");
                            case 10:
                                System.out.println("test");
                            case 11:
                                System.out.println("test");
                            case 12:
                                System.out.println("test");
                        }
                    }
                }
                int moves;
                do{
                    System.out.println("Select the moves of mn: ");
                    Scanner scanner3 = new Scanner(System.in);
                    moves = scanner3.nextInt();
                }while(moves > currentPlayer.getMotherNatureMoves());

                newGame.moveMotherNature(moves, newGame.getGameComponents().getMotherNature(), newGame.getGameComponents().getArchipelago());

                //shows usable characters
                pcoins = currentPlayer.getCoinOwned();
                input = "";
                choice = -1;
                while(!Objects.equals(input, "no") && !Objects.equals(input, "yes"))
                {
                    System.out.println("Do you want to use a character?");
                    Scanner charscanner = new Scanner(System.in);
                    input = charscanner.nextLine();
                }
                if (input.equals("yes")) {
                    int n = 0;
                    for (CharacterCard temp : newGame.getGameComponents().getSpecialDeck().getCards()) {
                        if (temp.getNecessaryCoin() <= pcoins) {
                            System.out.println(n + " Character " + temp.getNum());
                            n++;
                        }
                    }
                    if (n == 0){
                        System.out.println("No character usable");
                    }
                    if (n != 0){
                        while (choice < 0 || choice >= n) {
                            System.out.println("Select the character you want to use");
                            Scanner charscanner = new Scanner(System.in);
                            choice = charscanner.nextInt();
                        }
                        switch(newGame.getGameComponents().getSpecialDeck().getCards().get(choice).getNum()){
                            case 1:
                                System.out.println("test");
                            case 2:
                                System.out.println("test");
                            case 3:
                                System.out.println("test");
                            case 4:
                                System.out.println("test");
                            case 5:
                                System.out.println("test");
                            case 6:
                                System.out.println("test");
                            case 7:
                                System.out.println("test");
                            case 8:
                                System.out.println("test");
                            case 9:
                                System.out.println("test");
                            case 10:
                                System.out.println("test");
                            case 11:
                                System.out.println("test");
                            case 12:
                                System.out.println("test");
                        }
                    }
                }

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

                newGame.getComplexLobby().changeActivePlayer();
                currentPlayer = lobby.getActivePlayer();

                i++;
            }
            newGame.refillCloudCards();
            newGame.getComplexLobby().modifyPlayerTurn();
            currentPlayer = lobby.getActivePlayer();
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

        GM.login("Cole", 2, false);
        GM.getPlayerComplexLobby("Cole").deckRequest(Mage.MAGE1, "Cole");
        GM.login("Leo", 2, false);
        GM.getPlayerComplexLobby("Cole").deckRequest(Mage.MAGE2, "Leo");

        Game newGame = GM.getComplexLobbies().get(0).getGame();
        GameComponents gameComponents = newGame.getGameComponents();
        ComplexLobby lobby = GM.getComplexLobbies().get(0);

        newGame.startGameWithRandomPlayer();
        for(Player player : lobby.getPlayerOrder()){
            System.out.println(player.getID_player());
        }


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

    }
}

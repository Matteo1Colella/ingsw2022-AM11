package it.polimi.ingsw.jsonTest;

import com.google.gson.Gson;
import it.polimi.ingsw.communication.common.messages.*;
import it.polimi.ingsw.controller.ComplexLobby;
import it.polimi.ingsw.controller.GameManager;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Mage;
import it.polimi.ingsw.model.board.*;
import it.polimi.ingsw.model.cards.Card;
import it.polimi.ingsw.model.cards.CharacterCard;
import it.polimi.ingsw.model.cards.CharacterDeck;
import it.polimi.ingsw.model.pieces.Student;
import org.junit.Test;

import java.util.List;


public class jsonTests {
    @Test
    public void testClouds() {

        //initial setting
        GameManager GM = new GameManager();

        GM.login("Cole", 2, true);
        GM.getPlayerComplexLobby("Cole").deckRequest(Mage.MAGE1, "Cole");
        GM.login("Leo", 2, true);
        GM.getPlayerComplexLobby("Cole").deckRequest(Mage.MAGE2, "Leo");

        Game newGame = GM.getComplexLobbies().get(0).getGame();
        GameComponents gameComponents = newGame.getGameComponents();
        ComplexLobby lobby = GM.getComplexLobbies().get(0);

        newGame.startGameWithRandomPlayer();
        System.out.println("");
        System.out.println("");

        //CLOUDS IN JSON
        CloudCardListMessage cloudCardListMessage = new CloudCardListMessage();
        cloudCardListMessage.setCloudCardList(gameComponents.getCloudCards());
        //serialize
        Gson gson2 = new Gson();
        String json2 = gson2.toJson(cloudCardListMessage);
        //deserialize
        Gson gson3 = new Gson();
        CloudCardListMessage order = gson3.fromJson(json2, CloudCardListMessage.class);
        int i = 0;
        for(CloudCard card : order.getCloudCardList()) {
            System.out.println("cloud " + i + ":" );
            System.out.println(card.getStudents());
            i++;
        }

    }

    @Test
    public void testCoins() {

        //initial setting
        GameManager GM = new GameManager();

        GM.login("Cole", 2, true);
        GM.getPlayerComplexLobby("Cole").deckRequest(Mage.MAGE1, "Cole");
        GM.login("Leo", 2, true);
        GM.getPlayerComplexLobby("Cole").deckRequest(Mage.MAGE2, "Leo");

        Game newGame = GM.getComplexLobbies().get(0).getGame();
        GameComponents gameComponents = newGame.getGameComponents();
        ComplexLobby lobby = GM.getComplexLobbies().get(0);

        newGame.startGameWithRandomPlayer();
        System.out.println("");
        System.out.println("");

        //COINS IN JSON
        CoinMessage coinMessage = new CoinMessage();
        coinMessage.setCoinOwned(lobby.getActivePlayer().getCoinOwned());
        //serialize
        Gson gson2 = new Gson();
        String json2 = gson2.toJson(coinMessage);
        //deserialize
        Gson gson3 = new Gson();
        CoinMessage order = gson3.fromJson(json2, CoinMessage.class);
        System.out.println(order.getCoinOwned());

    }

    @Test
    public void characterListTest() {
        //initial setting
        GameManager GM = new GameManager();

        GM.login("Cole", 2, true);
        GM.getPlayerComplexLobby("Cole").deckRequest(Mage.MAGE1, "Cole");
        GM.login("Leo", 2, true);
        GM.getPlayerComplexLobby("Leo").deckRequest(Mage.MAGE2, "Leo");

        Game newGame = GM.getComplexLobbies().get(0).getGame();
        GameComponents gameComponents = newGame.getGameComponents();
        ComplexLobby lobby = GM.getComplexLobbies().get(0);

        newGame.startGameWithRandomPlayer();
        newGame.getComplexLobby().checkIfPlayable(newGame.getComplexLobby().getActivePlayer().playCard(1));
       // System.out.println(lobby.getChosenCards().get(0));
        System.out.println("");
        System.out.println("");

        //CharacterCards IN JSON
        CharacterCardsMessage characterCardsMessage = new CharacterCardsMessage();

        characterCardsMessage.setCharacterCards( newGame.getGameComponents().getSpecialDeck().getCards());
        //serialize
        Gson gson2 = new Gson();
        String json2 = gson2.toJson(characterCardsMessage);
        //deserialize
        Gson gson3 = new Gson();
        CharacterCardsMessage order = gson3.fromJson(json2, CharacterCardsMessage.class);
        System.out.println("CHARACTER CARDS");
        for(CharacterCard characterCard : order.getCharacterCards()) {
            System.out.println(characterCard.getNum());
        }

    }



    @Test
    public void testCards() {

        //initial setting
        GameManager GM = new GameManager();

        GM.login("Cole", 2, true);
        GM.getPlayerComplexLobby("Cole").deckRequest(Mage.MAGE1, "Cole");
        GM.login("Leo", 2, true);
        GM.getPlayerComplexLobby("Leo").deckRequest(Mage.MAGE2, "Leo");

        Game newGame = GM.getComplexLobbies().get(0).getGame();
        GameComponents gameComponents = newGame.getGameComponents();
        ComplexLobby lobby = GM.getComplexLobbies().get(0);

        newGame.startGameWithRandomPlayer();
        System.out.println("");
        System.out.println("");

        //CARDS IN JSON
        AssistantCardsMessage assistantCardsMessage = new AssistantCardsMessage();

        assistantCardsMessage.setDeck((List<Card>) lobby.getPlayers().get(0).getDeck().getCards());
        //serialize
        Gson gson2 = new Gson();
        String json2 = gson2.toJson(assistantCardsMessage);
        //deserialize
        Gson gson3 = new Gson();
        AssistantCardsMessage order = gson3.fromJson(json2, AssistantCardsMessage.class);
        int i = 1;
        for(Card card : order.getDeck()) {
            System.out.println("card " + i + ":" );
            System.out.println(card.getName());
            i++;
        }

    }

    @Test
    public void playedCardOnTableTest() {
        //initial setting
        GameManager GM = new GameManager();

        GM.login("Cole", 2, true);
        GM.getPlayerComplexLobby("Cole").deckRequest(Mage.MAGE1, "Cole");
        GM.login("Leo", 2, true);
        GM.getPlayerComplexLobby("Leo").deckRequest(Mage.MAGE2, "Leo");

        Game newGame = GM.getComplexLobbies().get(0).getGame();
        GameComponents gameComponents = newGame.getGameComponents();
        ComplexLobby lobby = GM.getComplexLobbies().get(0);

        newGame.startGameWithRandomPlayer();
        newGame.getComplexLobby().checkIfPlayable(newGame.getComplexLobby().getActivePlayer().playCard(1));
       // System.out.println(lobby.getChosenCards().get(0));
        System.out.println("");
        System.out.println("");

        //PLAYED CARDS IN JSON
        AssistantCardsMessage assistantCardsMessage = new AssistantCardsMessage();

        assistantCardsMessage.setDeck((List<Card>) lobby.getChosenCards());
        //serialize
        Gson gson2 = new Gson();
        String json2 = gson2.toJson(assistantCardsMessage);
        //deserialize
        Gson gson3 = new Gson();
        AssistantCardsMessage order = gson3.fromJson(json2, AssistantCardsMessage.class);
        int i = 1;
        for(Card card : order.getDeck()) {
            System.out.println("card " + i + ":" );
            System.out.println(card.getName());
            i++;
        }

    }

    @Test
    public void moveMotherNatureTryPrintTest() {
        int selectedCard = 7;
        System.out.println("How many steps you want MOTHERNATURE do?");
        switch (selectedCard){
            case 1,2:
                System.out.println("(You can Select only 1 step!)");
                break;
            case 3,4:
                System.out.println("(You can Select between 1 or 2 steps!)");
                break;
            case 5,6:
                System.out.println("(You can Select from 1 to 3 steps!)");
                break;
            case 7,8:
                System.out.println("(You can Select from 1 to 4 steps!)");
                break;
            case 9,10:
                System.out.println("(You can Select from 1 to 5 steps!)");
                break;
            default:
                System.out.println("(ERROR: you have to play an Assistant Card first!)");
                break;
        }
    }

    @Test
    public void sendSchoolBoardTest() {
        //initial setting
        GameManager GM = new GameManager();

        GM.login("Cole", 2, true);
        GM.getPlayerComplexLobby("Cole").deckRequest(Mage.MAGE1, "Cole");
        GM.login("Leo", 2, true);
        GM.getPlayerComplexLobby("Leo").deckRequest(Mage.MAGE2, "Leo");

        Game newGame = GM.getComplexLobbies().get(0).getGame();
        GameComponents gameComponents = newGame.getGameComponents();
        ComplexLobby lobby = GM.getComplexLobbies().get(0);

        newGame.startGameWithRandomPlayer();
        newGame.getComplexLobby().checkIfPlayable(newGame.getComplexLobby().getActivePlayer().playCard(1));
        //System.out.println(lobby.getChosenCards().get(0));
        System.out.println("");
        System.out.println("");

        //SCHOOLBOARD IN JSON
        SchoolBoardMessage schoolBoardMessage = new SchoolBoardMessage();

        schoolBoardMessage.setSchoolBoard(lobby.getActivePlayer().getSchoolBoard());
        //serialize
        Gson gson2 = new Gson();
        String json2 = gson2.toJson(schoolBoardMessage);
        //deserialize
        Gson gson3 = new Gson();
        SchoolBoardMessage order = gson3.fromJson(json2, SchoolBoardMessage.class);
        System.out.println("MY SCHOOLBOARD:");
        System.out.println("");
        System.out.println("ENTRANCE:");
        int i = 0;
        for(Student student : order.getSchoolBoard().getEntrance().getStudents()) {
            System.out.println("student " + i + ":" );
            System.out.println(student.getColor());
            i++;
        }
        System.out.println("");
        System.out.println("DINING ROOMS:");
        System.out.println("");
        i = 0;
        for (DiningRoom diningRoom : order.getSchoolBoard().getDiningRooms()){
            System.out.println("Color: " + order.getSchoolBoard().getDiningRooms().get(i).getColor());
            System.out.println("Number of Students: " + order.getSchoolBoard().getDiningRooms().get(i).getStudents().size());
            System.out.println("Professor: " + order.getSchoolBoard().getDiningRooms().get(i).IsProfessor());
            i++;
            System.out.println("");
        }
        System.out.println("");
        if (order.getSchoolBoard().getTowers().get(0).getColor()!=null)
            System.out.println("TOWER color: " + order.getSchoolBoard().getTowers().get(0).getColor());
        System.out.println("Remaining Towers: " + order.getSchoolBoard().getTowers().size());

    }

    @Test
    public void sendArchipelagoTest() {
        //initial setting
        GameManager GM = new GameManager();

        GM.login("Cole", 2, true);
        GM.getPlayerComplexLobby("Cole").deckRequest(Mage.MAGE1, "Cole");
        GM.login("Leo", 2, true);
        GM.getPlayerComplexLobby("Leo").deckRequest(Mage.MAGE2, "Leo");


        Game newGame = GM.getComplexLobbies().get(0).getGame();
        GameComponents gameComponents = newGame.getGameComponents();
        ComplexLobby lobby = GM.getComplexLobbies().get(0);

        newGame.startGameWithRandomPlayer();

        //TO AVOID MOTHERNATURE BUG!! (The first time (initial setting) mother nature isn't shown)
        newGame.moveMotherNature(0, newGame.getGameComponents().getMotherNature(), newGame.getGameComponents().getArchipelago());

        System.out.println(gameComponents.getArchipelago().get(0).getMotherNature());
        System.out.println("");
        System.out.println("");

        //ARCHIPELAGO IN JSON
        ArchipelagoMessage archipelagoMessage = new ArchipelagoMessage();

        archipelagoMessage.setArchipelago(gameComponents.getArchipelago());
        //serialize
        Gson gson2 = new Gson();
        String json2 = gson2.toJson(archipelagoMessage);
        //deserialize
        Gson gson3 = new Gson();
        ArchipelagoMessage order = gson3.fromJson(json2, ArchipelagoMessage.class);
        int i = 0;
        System.out.println("ARCHIPELAGO: ");
        System.out.println("");
        for(IslandCard islandCard : order.getArchipelago()){
            if(islandCard.getMotherNature()){
                System.out.println("MOTHERNATURE");
            }
            if(islandCard.getTower() != null){
                System.out.println("ISLAND: " + i + "\tTower : " + islandCard.getTower().getColor().toString());
            } else {
                System.out.println("ISLAND: " + i + "\tTower : no tower");
            }
            System.out.println("Students:");
            for(int j = 0; j < islandCard.getStudents().size(); j++){
                System.out.println("Student " + j + " color : " + islandCard.getStudents().get(j));
            }
            System.out.println("Merged with: ");
            for(int j = 0; j < islandCard.getMergedWith().size(); j++){
                IslandCard tempIslandCard = islandCard.getMergedWith().get(j);
                for(int k = 0; k < tempIslandCard.getStudents().size(); k++){
                    System.out.println("Student " + k + " color : " + tempIslandCard.getStudents().get(k));
                }
            }
            i++;
            System.out.println("");
        }
    }

    @Test
    public void sendModelTest() {
        //initial setting
        GameManager GM = new GameManager();

        GM.login("Cole", 2, true);
        GM.getPlayerComplexLobby("Cole").deckRequest(Mage.MAGE1, "Cole");
        GM.login("Leo", 2, true);
        GM.getPlayerComplexLobby("Leo").deckRequest(Mage.MAGE2, "Leo");


        Game newGame = GM.getComplexLobbies().get(0).getGame();
        GameComponents gameComponents = newGame.getGameComponents();
        ComplexLobby lobby = GM.getComplexLobbies().get(0);

        newGame.startGameWithRandomPlayer();

        //TO AVOID MOTHERNATURE BUG!! (The first time (initial setting) mother nature isn't shown)
        newGame.moveMotherNature(0, newGame.getGameComponents().getMotherNature(), newGame.getGameComponents().getArchipelago());

        System.out.println("");
        System.out.println("");

        //ARCHIPELAGO IN JSON

        ModelMessage modelMessage = new ModelMessage();


        modelMessage.setArchipelago(gameComponents.getArchipelago());
        modelMessage.setSchoolBoard(lobby.getActivePlayer().getSchoolBoard());
        modelMessage.setCloudCardList(gameComponents.getCloudCards());
        modelMessage.setCharacterCards(newGame.getGameComponents().getSpecialDeck().getCards());
        modelMessage.setCoinOwned(lobby.getActivePlayer().getCoinOwned());

        //serialize
        Gson gson2 = new Gson();
        String json2 = gson2.toJson(modelMessage);
        //deserialize
        Gson gson3 = new Gson();
        ModelMessage order = gson3.fromJson(json2, ModelMessage.class);


        //MODEL

        //if pro
        if(order.getCoinOwned() >= 0){
            System.out.println("CHARACTER CARDS:");
            for(CharacterCard characterCard : order.getCharacterCards()) {
                System.out.println(characterCard.getNum());
            }
            System.out.println("");
            System.out.println("YOUR COINS:");
            System.out.println(order.getCoinOwned());
            System.out.println("");
        }
        int i = 0;
        System.out.println("ARCHIPELAGO: ");
        System.out.println("");
        for(IslandCard islandCard : order.getArchipelago()){
            if(islandCard.getMotherNature()){
                System.out.println("MOTHERNATURE");
            }
            if(islandCard.getTower() != null){
                System.out.println("ISLAND: " + i + "\tTower : " + islandCard.getTower().getColor().toString());
            } else {
                System.out.println("ISLAND: " + i + "\tTower : no tower");
            }
            System.out.println("Students:");
            for(int j = 0; j < islandCard.getStudents().size(); j++){
                System.out.println("Student " + j + " color : " + islandCard.getStudents().get(j));
            }
            System.out.println("Merged with: ");
            for(int j = 0; j < islandCard.getMergedWith().size(); j++){
                IslandCard tempIslandCard = islandCard.getMergedWith().get(j);
                for(int k = 0; k < tempIslandCard.getStudents().size(); k++){
                    System.out.println("Student " + k + " color : " + tempIslandCard.getStudents().get(k));
                }
            }
            i++;
            System.out.println("");
        }
        System.out.println("MY SCHOOLBOARD:");
        System.out.println("");
        System.out.println("ENTRANCE:");
        i = 0;
        for(Student student : order.getSchoolBoard().getEntrance().getStudents()) {
            System.out.println("student " + i + ":" );
            System.out.println(student.getColor());
            i++;
        }
        System.out.println("");
        System.out.println("DINING ROOMS:");
        System.out.println("");
        i = 0;
        for (DiningRoom diningRoom : order.getSchoolBoard().getDiningRooms()){
            System.out.println("Color: " + order.getSchoolBoard().getDiningRooms().get(i).getColor());
            System.out.println("Number of Students: " + order.getSchoolBoard().getDiningRooms().get(i).getStudents().size());
            System.out.println("Professor: " + order.getSchoolBoard().getDiningRooms().get(i).IsProfessor());
            i++;
            System.out.println("");
        }
        if (order.getSchoolBoard().getTowers().get(0).getColor()!=null)
            System.out.println("TOWER color: " + order.getSchoolBoard().getTowers().get(0).getColor());
        System.out.println("Remaining Towers: " + order.getSchoolBoard().getTowers().size());
        i = 0;
        System.out.println("");
        System.out.println("CLOUDS: ");
        for(CloudCard card : order.getCloudCardList()) {
            System.out.println("cloud " + i + ":" );
            System.out.println(card.getStudents());
            i++;
        }

    }


}


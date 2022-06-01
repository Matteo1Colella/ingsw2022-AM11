package it.polimi.ingsw.view;

import it.polimi.ingsw.communication.client.ClientMain;
import it.polimi.ingsw.communication.common.MessageInterface;
import it.polimi.ingsw.communication.common.MessageType;
import it.polimi.ingsw.communication.common.PingPongThread;
import it.polimi.ingsw.communication.common.messages.*;
import it.polimi.ingsw.model.board.CloudCard;
import it.polimi.ingsw.model.board.IslandCard;
import it.polimi.ingsw.model.cards.Card;
import it.polimi.ingsw.model.cards.CharacterCard;
import it.polimi.ingsw.model.colors.ColorStudent;
import it.polimi.ingsw.model.pieces.Student;
import it.polimi.ingsw.view.stages.ActionStage;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class ActionController {
    private ArrayList<Boolean> sized;
    private int turn;
    private boolean start;
    private boolean endgame;
    private volatile boolean chosen;
    private int movesLeft;
    private int moves;
    private ArrayList<CloudCard> clouds;
    private IslandCard selectedIsland;
    private ClientMain client;
    private ArrayList<IslandCard> archipelago;
    private CloudCard selectedCloud;
    private Card selectedCard;
    private ArrayList<Card> deck;
    private volatile boolean students;
    private volatile boolean mn;
    private boolean cloudcard;
    private boolean gametype;
    private boolean choice;
    private Object student;
    private ModelMessage model;
    private volatile boolean cc;
    int cloudchoice;
    int numIslands;


    private int student1Entrance;
    private int student1WhereToPut;
    private int indexIslandIf1ToIsland;
    private int student2Entrance;
    private int student2WhereToPut;
    private int indexIslandIf2ToIsland;
    private int student3Entrance;
    private int student3WhereToPut;
    private int indexIslandIf3ToIsland;
    private int student4Entrance;

    @FXML
    private Label towerColor;

    @FXML
    private Label resetLabel;

    @FXML
    private AnchorPane apane;
    @FXML
    private Label To;
    @FXML
    private Label SelectedCharacter;
    @FXML
    private Label SelectedStudent;
    @FXML
    private Label SelectedCloud;

    @FXML
    private ImageView cloud1;
    @FXML
    private ImageView cloud2;
    @FXML
    private ImageView cloud3;

    @FXML
    private ImageView assistant1;
    @FXML
    private ImageView assistant2;
    @FXML
    private ImageView assistant3;
    @FXML
    private ImageView assistant4;
    @FXML
    private ImageView assistant5;
    @FXML
    private ImageView assistant6;
    @FXML
    private ImageView assistant7;
    @FXML
    private ImageView assistant8;
    @FXML
    private ImageView assistant9;
    @FXML
    private ImageView assistant10;
    @FXML
    private Button confirmCard;

    @FXML
    private javafx.scene.shape.Polygon grid;


    @FXML
    private ImageView island1;
    @FXML
    private ImageView island2;
    @FXML
    private ImageView island3;
    @FXML
    private ImageView island4;
    @FXML
    private ImageView island5;
    @FXML
    private ImageView island6;
    @FXML
    private ImageView island7;
    @FXML
    private ImageView island8;
    @FXML
    private ImageView island9;
    @FXML
    private ImageView island10;
    @FXML
    private ImageView island11;
    @FXML
    private ImageView island12;
    @FXML
    private ImageView motherNature;
    @FXML
    private Button moveMN;
    @FXML
    private TextField steps;
    @FXML
    private ComboBox<Student> entrance;
    @FXML
    private ComboBox<Student> red;
    @FXML
    private ComboBox<Student> blue;
    @FXML
    private ComboBox<Student> yellow;
    @FXML
    private ComboBox<Student> green;
    @FXML
    private ComboBox<Student> pink;
    @FXML
    private Label assistantText;
    @FXML
    private ArrayList<ImageView> listIslands;

    public void setClient(ClientMain client) {
        this.client = client;
    }

    public void bindIslands() {

        System.out.println("listislands: " + listIslands.size());

        grid.getPoints().clear();

        final double angleStep = Math.PI * 2 / (numIslands);

        double angle = Math.PI;
        for (int i = 0; i < numIslands; i++, angle -= angleStep) {

            double x = Math.sin(angle) * 245 + 329 - 237.26 - 90;
            double y = Math.cos(angle) * 245 + 303 - 236 - 70;
            grid.getPoints().addAll(
                    x,
                    y
            );
            grid.setDisable(true);
            grid.setOpacity(0);

            System.out.println(i + "--> x: " + x + " y: " + y);

            listIslands.get(i).setLayoutX(x + 237.26);
            listIslands.get(i).setLayoutY(y + 236);
        }

    }

    public void bind(Stage stage, Scene scene) {


        System.out.println(listIslands.size());

        bindIslands();

        double height = scene.getHeight();
        double width = scene.getWidth();

        System.out.println(height);
        System.out.println(width);

        apane.translateXProperty().bind(scene.widthProperty().subtract(apane.widthProperty()).divide(2));
        apane.translateYProperty().bind(scene.heightProperty().subtract(apane.heightProperty()).divide(2));

        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            double scaleX = newVal.doubleValue() / width;
            apane.setScaleX(scaleX);
            apane.setCenterShape(true);
        });


        stage.heightProperty().addListener((obs, oldVal, newVal) -> {
            double scaleY = newVal.doubleValue() / height;
            apane.setCenterShape(true);
            apane.setScaleY(scaleY);
        });
    }

    public synchronized void clickMoveMN() {


        int maxsteps = selectedCard.getSteps();
        int steps = Integer.parseInt(this.steps.getText());

        if (maxsteps < steps) {
            return;
        } else {

            client.getSendMessage().sendMoveMotherNatureMessage(new MoveMotherNatureMessage(steps));

            client.getSendMessage().sendModelMessage(new ModelMessage());

            System.out.println("middle");

            this.model = (ModelMessage) client.receiveMessage();


            showmodel(client);

            MessageInterface receivedMessage = client.receiveMessage();

            System.out.println("recieved");

            if (receivedMessage.getCode() == MessageType.TURN) {
                System.out.println("Correct selection.\r");
            }


            System.out.println("aftermodel");


            mn = true;

        }

        /*
        Game game = this.lobby.getGame();
        MotherNature mn = game.getGameComponents().getMotherNature();
        ArrayList<IslandCard> archipelago = game.getGameComponents().getArchipelago();

        game.moveMotherNature(steps, mn, archipelago);
        System.out.println(mn.getPosition().getId_island());
        if(mn.getPosition().getId_island() == 0){

            motherNature.setLayoutX(island1.getLayoutX()+42);
            motherNature.setLayoutY(island1.getLayoutY());

        }
        if(mn.getPosition().getId_island() == 1){
            motherNature.setLayoutX(island2.getLayoutX()+42);
            motherNature.setLayoutY(island2.getLayoutY());
        }
        if(mn.getPosition().getId_island() == 2){
            motherNature.setLayoutX(island3.getLayoutX()+42);
            motherNature.setLayoutY(island3.getLayoutY());
        }
        if(mn.getPosition().getId_island() == 3){
            motherNature.setLayoutX(island4.getLayoutX()+42);
            motherNature.setLayoutY(island4.getLayoutY());
        }
        if(mn.getPosition().getId_island() == 4){
            motherNature.setLayoutX(island5.getLayoutX()+42);
            motherNature.setLayoutY(island5.getLayoutY());
        }
        if(mn.getPosition().getId_island() == 5){
            motherNature.setLayoutX(island6.getLayoutX()+42);
            motherNature.setLayoutY(island6.getLayoutY());
        }
        if(mn.getPosition().getId_island() == 6){
            motherNature.setLayoutX(island7.getLayoutX()+42);
            motherNature.setLayoutY(island7.getLayoutY());
        }
        if(mn.getPosition().getId_island() == 7){
            motherNature.setLayoutX(island8.getLayoutX()+42);
            motherNature.setLayoutY(island8.getLayoutY());
        }
        if(mn.getPosition().getId_island() == 8){
            motherNature.setLayoutX(island9.getLayoutX()+42);
            motherNature.setLayoutY(island9.getLayoutY());
        }
        if(mn.getPosition().getId_island() == 9){
            motherNature.setLayoutX(island10.getLayoutX()+42);
            motherNature.setLayoutY(island10.getLayoutY());
        }
        if(mn.getPosition().getId_island() == 10){
            motherNature.setLayoutX(island11.getLayoutX()+42);
            motherNature.setLayoutY(island11.getLayoutY());
        }
        if(mn.getPosition().getId_island() == 11){
            motherNature.setLayoutX(island12.getLayoutX()+42);
            motherNature.setLayoutY(island12.getLayoutY());
        }

         */

    }

    public ClientMain getClient() {
        return this.client;
    }

    public boolean isGametype() {
        return gametype;
    }

    public boolean isStudents() {
        return students;
    }

    public void clickonIsland1() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Island 1 Content:");
        alert.setContentText(this.archipelago.get(0).toString());
        alert.show();
        selectedIsland = this.archipelago.get(0);
        To.setText("To: Island " + selectedIsland.getId_island());
    }

    public void clickonIsland2() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Island 2 Content:");
        alert.setContentText(this.archipelago.get(1).toString());
        alert.show();
        selectedIsland = this.archipelago.get(1);
        To.setText("To: Island " + selectedIsland.getId_island());
    }

    public void clickonIsland3() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Island 3 Content:");
        alert.setContentText(this.archipelago.get(2).toString());
        alert.show();
        selectedIsland = this.archipelago.get(2);
        To.setText("To: Island " + selectedIsland.getId_island());
    }

    public void clickonIsland4() {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Island 4 Content:");
        alert.setContentText(this.archipelago.get(3).toString());
        alert.show();
        selectedIsland = this.archipelago.get(3);
        To.setText("To: Island " + selectedIsland.getId_island());
    }

    public void clickonIsland5() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Island 5 Content:");
        alert.setContentText(this.archipelago.get(4).toString());
        alert.show();
        selectedIsland = this.archipelago.get(4);
        To.setText("To: Island " + selectedIsland.getId_island());
    }

    public void clickonIsland6() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Island 6 Content:");
        alert.setContentText(this.archipelago.get(5).toString());
        alert.show();
        selectedIsland = this.archipelago.get(5);
        To.setText("To: Island " + selectedIsland.getId_island());
    }

    public void clickonIsland7() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Island 7 Content:");
        alert.setContentText(this.archipelago.get(6).toString());
        alert.show();
        selectedIsland = this.archipelago.get(6);
        To.setText("To: Island " + selectedIsland.getId_island());
    }

    public void clickonIsland8() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Island 8 Content:");
        alert.setContentText(this.archipelago.get(7).toString());
        alert.show();
        selectedIsland = this.archipelago.get(7);
        To.setText("To: Island " + selectedIsland.getId_island());
    }

    public void clickonIsland9() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Island 9 Content:");
        alert.setContentText(this.archipelago.get(8).toString());
        alert.show();
        selectedIsland = this.archipelago.get(8);
        To.setText("To: Island " + selectedIsland.getId_island());
    }

    public void clickonIsland10() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Island 10 Content:");
        alert.setContentText(this.archipelago.get(9).toString());
        alert.show();
        selectedIsland = this.archipelago.get(9);
        To.setText("To: Island " + selectedIsland.getId_island());
    }

    public void clickonIsland11() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Island 11 Content:");
        alert.setContentText(this.archipelago.get(10).toString());
        alert.show();
        selectedIsland = this.archipelago.get(10);
        To.setText("To: Island " + selectedIsland.getId_island());
    }

    public void clickonIsland12() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Island 12 Content:");
        alert.setContentText(this.archipelago.get(11).toString());
        alert.show();
        selectedIsland = this.archipelago.get(11);
        To.setText("To: Island " + selectedIsland.getId_island());
    }

    public void clickonCloud1() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Cloud Content:");
        alert.setContentText(this.clouds.get(0).toString());
        alert.show();
        this.cloudchoice = 0;
        this.selectedCloud = this.clouds.get(0);
        this.SelectedCloud.setText("Selected cloud: Cloud 1");
    }

    public void clickonCloud2() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Cloud Content:");
        alert.setContentText(this.clouds.get(1).toString());
        alert.show();
        cloudchoice = 1;
        this.selectedCloud = this.clouds.get(1);
        this.SelectedCloud.setText("Selected cloud: Cloud 2");
    }

    public void clickonCloud3() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Cloud Content:");
        alert.setContentText(this.clouds.get(2).toString());
        alert.show();
        cloudchoice = 2;
        this.selectedCloud = this.clouds.get(2);
        this.SelectedCloud.setText("Selected cloud: Cloud 3");
    }

    public void clickonAssistant1() {

        for (Card card : this.deck) {
            if (card.getInfluence() == 1) {
                selectedCard = card;
                assistantText.setText(card.getName());
            }
        }

    }

    public void clickonAssistant2() {

        for (Card card : this.deck) {
            if (card.getInfluence() == 2) {
                selectedCard = card;
                assistantText.setText(card.getName());
            }
        }

    }

    public void clickonAssistant3() {

        for (Card card : this.deck) {
            if (card.getInfluence() == 3) {
                selectedCard = card;
                assistantText.setText(card.getName());
            }
        }

    }

    public void clickonAssistant4() {

        for (Card card : this.deck) {
            if (card.getInfluence() == 4) {
                selectedCard = card;
                assistantText.setText(card.getName());
            }
        }

    }

    public void clickonAssistant5() {

        for (Card card : this.deck) {
            if (card.getInfluence() == 5) {
                selectedCard = card;
                assistantText.setText(card.getName());
            }
        }

    }

    public void clickonAssistant6() {

        for (Card card : this.deck) {
            if (card.getInfluence() == 6) {
                selectedCard = card;
                assistantText.setText(card.getName());
            }
        }

    }

    public void clickonAssistant7() {

        for (Card card : this.deck) {
            if (card.getInfluence() == 7) {
                selectedCard = card;
                assistantText.setText(card.getName());
            }
        }

    }

    public void clickonAssistant8() {

        for (Card card : this.deck) {
            if (card.getInfluence() == 8) {
                selectedCard = card;
                assistantText.setText(card.getName());
            }
        }

    }

    public void clickonAssistant9() {

        for (Card card : this.deck) {
            if (card.getInfluence() == 9) {
                selectedCard = card;
                assistantText.setText(card.getName());
            }
        }

    }

    public void clickonAssistant10() {

        for (Card card : this.deck) {
            if (card.getInfluence() == 10) {
                selectedCard = card;
                assistantText.setText(card.getName());
            }
        }

    }

    public void initializeStudents() {
        student1Entrance = -1;
        student1WhereToPut = -1;
        indexIslandIf1ToIsland = -1;
        student2Entrance = -1;
        student2WhereToPut = -1;
        indexIslandIf2ToIsland = -1;
        student3Entrance = -1;
        student3WhereToPut = -1;
        indexIslandIf3ToIsland = -1;
        student4Entrance = -1;
    }

    public void comboBoxAction() {

        if (entrance.getSelectionModel().getSelectedItem() == null) {
            return;
        }

        switch (entrance.getSelectionModel().getSelectedItem().getColor()) {
            case YELLOW:
                SelectedStudent.setText("Selected student: yellow");
                break;
            case PINK:
                SelectedStudent.setText("Selected student: pink");
                break;
            case BLUE:
                SelectedStudent.setText("Selected student: blue");
                break;
            case GREEN:
                SelectedStudent.setText("Selected student: green");
                break;
            case RED:
                SelectedStudent.setText("Selected student: red");
                break;
        }
    }

    public synchronized void confirmStudent() {

        if (student1Entrance == -1) {
            student1Entrance = entrance.getSelectionModel().getSelectedIndex();
            if (selectedIsland == null) {
                student1WhereToPut = 0;
                indexIslandIf1ToIsland = -1;
            } else {
                student1WhereToPut = 1;
                indexIslandIf1ToIsland = selectedIsland.getId_island();
            }
            movesLeft--;
        } else if (student2Entrance == -1) {
            student2Entrance = entrance.getSelectionModel().getSelectedIndex();
            if (selectedIsland == null) {
                student2WhereToPut = 0;
                indexIslandIf2ToIsland = -1;
            } else {
                student2WhereToPut = 1;
                indexIslandIf2ToIsland = selectedIsland.getId_island();
            }
            movesLeft--;
        } else if (student3Entrance == -1) {
            student3Entrance = entrance.getSelectionModel().getSelectedIndex();
            if (selectedIsland == null) {
                student3WhereToPut = 0;
                indexIslandIf3ToIsland = -1;
            } else {
                student3WhereToPut = 1;
                indexIslandIf3ToIsland = selectedIsland.getId_island();
            }
            movesLeft--;
        } else if (this.moves == 4) {
            if (student4Entrance == -1) {
                student4Entrance = entrance.getSelectionModel().getSelectedIndex();
                if (selectedIsland == null) {
                    //student4WhereToPut = -1;
                    //indexIslandIf4ToIsland = -1;
                } else {
                    // student4WhereToPut = 1;
                    // indexIslandIf4ToIsland = selectedIsland.getId_island();
                }
                movesLeft--;
            }
        }
        if (movesLeft == 0) {
            System.out.println("sending students");

            System.out.println(student1Entrance + " " + student1WhereToPut + " " + indexIslandIf1ToIsland);
            System.out.println(student2Entrance + " " + student2WhereToPut + " " + indexIslandIf2ToIsland);
            System.out.println(student3Entrance + " " + student3WhereToPut + " " + indexIslandIf3ToIsland);

            client.getSendMessage().sendMoveStudentsMessage(new MoveStudentMessage(student1Entrance + 1, student1WhereToPut, indexIslandIf1ToIsland, student2Entrance + 1, student2WhereToPut, indexIslandIf2ToIsland, student3Entrance + 1, student3WhereToPut, indexIslandIf3ToIsland, -1, -1, -1));
            System.out.println("sent");


            initializeStudents();

            movesLeft = moves;

            students = true;

            client.getSendMessage().sendModelMessage(new ModelMessage());
            model = (ModelMessage) client.receiveMessage();

            Platform.runLater(() -> {
                showmodel(client);
            });

            MessageInterface receivedMessage = client.receiveMessage();
            if (receivedMessage.getCode() == MessageType.TURN) {
                System.out.println("Correct selection.\r");
            }


            //moveMotherNature();
        }


    }

    public synchronized void reset() {
        selectedIsland = null;
        To.setText("null");
        SelectedStudent.setText("null");
    }

    public synchronized void disableIslands() {
        island1.setOpacity(0);
        island1.setDisable(true);
        island2.setOpacity(0);
        island2.setDisable(true);
        island3.setOpacity(0);
        island3.setDisable(true);
        island4.setOpacity(0);
        island4.setDisable(true);
        island5.setOpacity(0);
        island5.setDisable(true);
        island6.setOpacity(0);
        island6.setDisable(true);
        island7.setOpacity(0);
        island7.setDisable(true);
        island8.setOpacity(0);
        island8.setDisable(true);
        island9.setOpacity(0);
        island9.setDisable(true);
        island10.setOpacity(0);
        island10.setDisable(true);
        island11.setOpacity(0);
        island11.setDisable(true);
        island12.setOpacity(0);
        island12.setDisable(true);
    }

    public synchronized void showmodel(ClientMain client) {


        //if pro{
        if (model.getCoinOwned() >= 0) {
            this.gametype = true;
            System.out.println("CHARACTER CARDS:");
            for (CharacterCard characterCard : model.getCharacterCards()) {
                System.out.println(characterCard.getNum());
            }
            System.out.println("");
            System.out.println("YOUR COINS:");
            System.out.println(model.getCoinOwned());
            System.out.println("");
        }
        //}
        this.archipelago.clear();
        this.archipelago.addAll(model.getArchipelago());

        disableIslands();

        // oldArchipelago.removeAll(this.archipelago);
        for (IslandCard temp : archipelago) {
            if (temp.getMergedWith().size() > 0) {
                for (IslandCard islandCard : temp.getMergedWith()) {
                    switch (islandCard.getId_island()) {
                        case 0:
                            this.listIslands.remove(island1);
                            break;
                        case 1:
                            this.listIslands.remove(island2);
                            break;
                        case 2:
                            this.listIslands.remove(island3);
                            break;
                        case 3:
                            this.listIslands.remove(island4);
                            break;
                        case 4:
                            this.listIslands.remove(island5);
                            break;
                        case 5:
                            this.listIslands.remove(island6);
                            break;
                        case 6:
                            this.listIslands.remove(island7);
                            break;
                        case 7:
                            this.listIslands.remove(island8);
                            break;
                        case 8:
                            this.listIslands.remove(island9);
                            break;
                        case 9:
                            this.listIslands.remove(island10);
                            break;
                        case 10:
                            this.listIslands.remove(island11);
                            break;
                        case 11:
                            this.listIslands.remove(island12);
                            break;

                    }
                }
            }
        }

        int index = 0;
        for (IslandCard temp : archipelago) {
            if (temp.getMergedWith().size() > 0) {
                switch (temp.getMergedWith().size()) {
                    case 1:

                        Image image = new javafx.scene.image.Image("Assets/merged2.png");
                        listIslands.get(index).setImage(image);
                        if(!sized.get(index)){
                            listIslands.get(index).setScaleX(listIslands.get(index).getScaleX() * 1.4);
                            listIslands.get(index).setScaleY(listIslands.get(index).getScaleY() * 1.4);
                            sized.set(index, true);
                        }


                        break;
                    case 2:
                        Image image2 = new javafx.scene.image.Image("Assets/merged3.png");
                        listIslands.get(index).setImage(image2);
                        if(!sized.get(index)){
                            listIslands.get(index).setScaleX(listIslands.get(index).getScaleX() * 1.4);
                            listIslands.get(index).setScaleY(listIslands.get(index).getScaleY() * 1.4);
                            sized.set(index, true);
                        }
                        break;
                    case 3:
                        Image image3 = new javafx.scene.image.Image("Assets/merged4.png");
                        listIslands.get(index).setImage(image3);
                        if(!sized.get(index)){
                            listIslands.get(index).setScaleX(listIslands.get(index).getScaleX() * 1.4);
                            listIslands.get(index).setScaleY(listIslands.get(index).getScaleY() * 1.4);
                            sized.set(index, true);
                        }
                        break;
                    case 4:
                        Image image4 = new javafx.scene.image.Image("Assets/merged5.png");
                        listIslands.get(index).setImage(image4);
                        if(!sized.get(index)){
                            listIslands.get(index).setScaleX(listIslands.get(index).getScaleX() * 1.4);
                            listIslands.get(index).setScaleY(listIslands.get(index).getScaleY() * 1.4);
                            sized.set(index, true);
                        }
                        break;

                }
            }
            index++;
        }

        this.numIslands = this.archipelago.size();

        bindIslands();

        for (ImageView island : listIslands) {
            island.setDisable(false);
            island.setOpacity(1);
        }

        int i = 0;
        for (IslandCard islandCard : model.getArchipelago()) {
            if (islandCard.getMotherNature()) {
                if (islandCard.getId_island() == 0) {
                    motherNature.setLayoutX(island1.getLayoutX() + 42);
                    motherNature.setLayoutY(island1.getLayoutY());
                }
                if (islandCard.getId_island() == 1) {
                    motherNature.setLayoutX(island2.getLayoutX() + 42);
                    motherNature.setLayoutY(island2.getLayoutY());
                }
                if (islandCard.getId_island() == 2) {
                    motherNature.setLayoutX(island3.getLayoutX() + 42);
                    motherNature.setLayoutY(island3.getLayoutY());
                }
                if (islandCard.getId_island() == 3) {
                    motherNature.setLayoutX(island4.getLayoutX() + 42);
                    motherNature.setLayoutY(island4.getLayoutY());
                }
                if (islandCard.getId_island() == 4) {
                    motherNature.setLayoutX(island5.getLayoutX() + 42);
                    motherNature.setLayoutY(island5.getLayoutY());
                }
                if (islandCard.getId_island() == 5) {
                    motherNature.setLayoutX(island6.getLayoutX() + 42);
                    motherNature.setLayoutY(island6.getLayoutY());
                }
                if (islandCard.getId_island() == 6) {
                    motherNature.setLayoutX(island7.getLayoutX() + 42);
                    motherNature.setLayoutY(island7.getLayoutY());
                }
                if (islandCard.getId_island() == 7) {
                    motherNature.setLayoutX(island8.getLayoutX() + 42);
                    motherNature.setLayoutY(island8.getLayoutY());
                }
                if (islandCard.getId_island() == 8) {
                    motherNature.setLayoutX(island9.getLayoutX() + 42);
                    motherNature.setLayoutY(island9.getLayoutY());
                }
                if (islandCard.getId_island() == 9) {
                    motherNature.setLayoutX(island10.getLayoutX() + 42);
                    motherNature.setLayoutY(island10.getLayoutY());
                }
                if (islandCard.getId_island() == 10) {
                    motherNature.setLayoutX(island11.getLayoutX() + 42);
                    motherNature.setLayoutY(island11.getLayoutY());
                }
                if (islandCard.getId_island() == 11) {
                    motherNature.setLayoutX(island12.getLayoutX() + 42);
                    motherNature.setLayoutY(island12.getLayoutY());
                }
            }
        }

        ObservableList<Student> entranceStudents = FXCollections.observableArrayList();
        entranceStudents.addAll(model.getSchoolBoard().getEntrance().getStudents());
        entrance.setItems(entranceStudents);

        ObservableList<Student> reds = FXCollections.observableArrayList();
        reds.addAll(model.getSchoolBoard().getDiningRoom(ColorStudent.RED).getStudents());
        red.setItems(reds);

        ObservableList<Student> blues = FXCollections.observableArrayList();
        blues.addAll(model.getSchoolBoard().getDiningRoom(ColorStudent.BLUE).getStudents());
        blue.setItems(blues);

        ObservableList<Student> yellows = FXCollections.observableArrayList();
        yellows.addAll(model.getSchoolBoard().getDiningRoom(ColorStudent.YELLOW).getStudents());
        yellow.setItems(yellows);

        ObservableList<Student> greens = FXCollections.observableArrayList();
        greens.addAll(model.getSchoolBoard().getDiningRoom(ColorStudent.GREEN).getStudents());
        green.setItems(greens);

        ObservableList<Student> pinks = FXCollections.observableArrayList();
        pinks.addAll(model.getSchoolBoard().getDiningRoom(ColorStudent.PINK).getStudents());
        pink.setItems(pinks);

        if (!model.getSchoolBoard().getTowers().isEmpty() && model.getSchoolBoard().getTowers().get(0).getColor() != null)
            towerColor.setText("Tower color: " + model.getSchoolBoard().getTowers().get(0).getColor() + ", remaining: " + model.getSchoolBoard().getTowers().size());


        this.clouds.clear();
        this.clouds.addAll(model.getCloudCardList());
        if (model.getCloudCardList().size() != 3) {
            cloud3.setDisable(true);
            cloud3.setOpacity(0.5);
            this.movesLeft = 3;
            this.moves = 3;
        }


    }

    public synchronized void moveStudents() {
        //inhibits others

        client.getSendMessage().sendMoveStudentsMessage(new MoveStudentMessage());

    }

    public synchronized void moveMotherNature() {
        //inhibits others

        client.getSendMessage().sendMoveMotherNatureMessage(new MoveMotherNatureMessage());

    }

    public synchronized void selectCloudCard() {
        //inhibits others

        client.getSendMessage().sendCloudCardMessage(new CloudCardChoiceMessage());
    }

    public synchronized void confirmCC() {


        client.getSendMessage().sendCloudCardMessage(new CloudCardChoiceMessage(cloudchoice));

        cc = true;
    }

    public synchronized void mainClient(ClientMain client) {

        new PingPongThread(client.getClientSocket(), "client");

        Task<Void> task = new Task<>() {
            @Override
            public Void call() {
                while (!endgame) {

                    cc = false;
                    students = false;
                    mn = false;
                    MessageType message;
                    MessageInterface receivedMessage;


                    receivedMessage = client.receiveMessage();
                    message = receivedMessage.getCode();
                    System.out.println("start turn " + message);


                    if (!client.isGameType()) {
                        switch (message) {
                            case TURN:

                                client.getSendMessage().sendModelMessage(new ModelMessage());
                                model = (ModelMessage) client.receiveMessage();


                                Platform.runLater(() -> {
                                    showmodel(client);
                                });

                                MessageInterface receivedMessage1 = client.receiveMessage();

                                if (receivedMessage1.getCode() == MessageType.TURN) {
                                    System.out.println("lesgo");
                                }

                                System.out.println(students + " " + mn + " " + cc);

                                moveStudents();

                                while (!students) {
                                    Thread.onSpinWait();
                                }
                                students = false;

                                moveMotherNature();
                                while (!mn) {
                                    Thread.onSpinWait();
                                }

                                mn = false;
                                selectCloudCard();
                                while (!cc) {
                                    Thread.onSpinWait();
                                }
                                cc = false;

                                MessageType messageType = client.receiveMessage().getCode();
                                System.out.println(messageType);

                                client.getSendMessage().sendModelMessage(new ModelMessage());
                                model = (ModelMessage) client.receiveMessage();


                                Platform.runLater(() -> {
                                    showmodel(client);
                                });


                                MessageType messageType1 = client.receiveMessage().getCode();

                                System.out.println(messageType1);

                                client.getSendMessage().sendAssistantCardsMessage(new AssistantCardsMessage());

                                ReceiveCards(client);

                                synchronized (student) {
                                    try {
                                        student.wait();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }

                                }

                                break;
                            case WIN:
                                System.out.println("");
                                System.out.println("----GAME-OVER----");
                                System.out.println("-----------------");
                                endgame = true;
                                break;
                            case MODEL:
                                client.setModel((ModelMessage) receivedMessage);
                                model = client.getModel();
                                Platform.runLater(() -> {
                                    showmodel(client);
                                });
                                break;
                        }
                    } else {
                        switch (message) {
                            case TURN:
                                turn++;
                                client.askCharacter();
                                client.moveStudents();
                                client.askCharacter();
                                client.moveMotherNature();
                                client.askCharacter();
                                client.selectCloudCard();
                                choice = false;
                                message = client.receiveMessage().getCode();
                                client.showModel();
                                message = client.receiveMessage().getCode();
                                while (!choice) {
                                    choice = client.playAssistantCard();
                                }
                                break;
                            case WIN:
                                System.out.println("");
                                System.out.println("----GAME-OVER----");
                                System.out.println("-----------------");
                                break;
                            case MODEL:
                                client.setModel((ModelMessage) receivedMessage);
                                break;
                        }

                    }
                }
                return null;
            }
        };
        new Thread(task).start();


    }

    public synchronized void ReceiveCards(ClientMain client) {

        //ask the list of cards already played on the table
        this.deck.clear();

        System.out.println("sent cards");
        AssistantCardsMessage assistantCardsMessage = (AssistantCardsMessage) client.receiveMessage();

        System.out.println(assistantCardsMessage.getCode());

        this.deck.addAll(assistantCardsMessage.getDeck());
        for (int j = 0; j < assistantCardsMessage.getChosenCard().size(); j++) {
            System.out.println("ok");
            switch (assistantCardsMessage.getChosenCard().get(j).getInfluence()) {
                case 1:
                    assistant1.setDisable(true);
                    assistant1.setOpacity(0.5);
                    break;
                case 2:
                    assistant2.setDisable(true);
                    assistant2.setOpacity(0.5);
                    break;
                case 3:
                    assistant3.setDisable(true);
                    assistant3.setOpacity(0.5);
                    break;
                case 4:
                    assistant4.setDisable(true);
                    assistant4.setOpacity(0.5);
                    break;
                case 5:
                    assistant5.setDisable(true);
                    assistant5.setOpacity(0.5);
                    break;
                case 6:
                    assistant6.setDisable(true);
                    assistant6.setOpacity(0.5);
                    break;
                case 7:
                    assistant7.setDisable(true);
                    assistant7.setOpacity(0.5);
                    break;
                case 8:
                    assistant8.setDisable(true);
                    assistant8.setOpacity(0.5);
                    break;
                case 9:
                    assistant9.setDisable(true);
                    assistant9.setOpacity(0.5);
                    break;
                case 10:
                    assistant10.setDisable(true);
                    assistant10.setOpacity(0.5);
                    break;
            }

        }

        //list of my cards:

        for (int i = 0; i < this.deck.size(); i++) {
            if (assistantCardsMessage.getDeck().get(i).isUsed()) {
                switch (this.deck.get(i).getInfluence()) {
                    case 1:
                        assistant1.setDisable(true);
                        assistant1.setOpacity(0.5);
                        break;
                    case 2:
                        assistant2.setDisable(true);
                        assistant2.setOpacity(0.5);
                        break;
                    case 3:
                        assistant3.setDisable(true);
                        assistant3.setOpacity(0.5);
                        break;
                    case 4:
                        assistant4.setDisable(true);
                        assistant4.setOpacity(0.5);
                        break;
                    case 5:
                        assistant5.setDisable(true);
                        assistant5.setOpacity(0.5);
                        break;
                    case 6:
                        assistant6.setDisable(true);
                        assistant6.setOpacity(0.5);
                        break;
                    case 7:
                        assistant7.setDisable(true);
                        assistant7.setOpacity(0.5);
                        break;
                    case 8:
                        assistant8.setDisable(true);
                        assistant8.setOpacity(0.5);
                        break;
                    case 9:
                        assistant9.setDisable(true);
                        assistant9.setOpacity(0.5);
                        break;
                    case 10:
                        assistant10.setDisable(true);
                        assistant10.setOpacity(0.5);
                        break;
                }
            }
        }


    }

    public synchronized void clickConfirmAssistant() {
        int card = -1;
        switch (selectedCard.getInfluence()) {
            case 1:
                card = 1;
                break;
            case 2:
                card = 2;
                break;
            case 3:
                card = 3;
                break;
            case 4:
                card = 4;
                break;
            case 5:
                card = 5;
                break;
            case 6:
                card = 6;
                break;
            case 7:
                card = 7;
                break;
            case 8:
                card = 8;
                break;
            case 9:
                card = 9;
                break;
            case 10:
                card = 10;
                break;
        }
        if (card == -1) return;

        chosen = true;


        int finalCard = card;
        this.client.getSendMessage().sendAssistantCardsMessage(new AssistantCardsMessage(finalCard));
        System.out.println(finalCard);

        MessageInterface receivedMessage = this.client.receiveMessage();

        System.out.println("received message: " + receivedMessage.getCode());


        if (receivedMessage.getCode() == MessageType.NOERROR) {
            synchronized (student) {
                student.notifyAll();
            }
            chosen = true;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Correct Selection");
            alert.setContentText("NAME: " + selectedCard.getName() + "\n" + "Influence: " + selectedCard.getInfluence() + "\n" + "Steps: " + selectedCard.getSteps());
            alert.show();
            switch (selectedCard.getInfluence()) {
                case 1:
                    assistant1.setDisable(true);
                    assistant1.setOpacity(0.5);
                    break;
                case 2:
                    assistant2.setDisable(true);
                    assistant2.setOpacity(0.5);
                    break;
                case 3:
                    assistant3.setDisable(true);
                    assistant3.setOpacity(0.5);
                    break;
                case 4:
                    assistant4.setDisable(true);
                    assistant4.setOpacity(0.5);
                    break;
                case 5:
                    assistant5.setDisable(true);
                    assistant5.setOpacity(0.5);
                    break;
                case 6:
                    assistant6.setDisable(true);
                    assistant6.setOpacity(0.5);
                    break;
                case 7:
                    assistant7.setDisable(true);
                    assistant7.setOpacity(0.5);
                    break;
                case 8:
                    assistant8.setDisable(true);
                    assistant8.setOpacity(0.5);
                    break;
                case 9:
                    assistant9.setDisable(true);
                    assistant9.setOpacity(0.5);
                    break;
                case 10:
                    assistant10.setDisable(true);
                    assistant10.setOpacity(0.5);
                    break;
            }

            if (!start) {
                mainClient(client);
                start = true;
            }


        } else if (receivedMessage.getCode() == MessageType.CARDERROR) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("an error occurred, try again");
            alert.show();
        }


    }

    public void initialize(ClientMain client) {
        this.numIslands = 12;
        this.sized = new ArrayList<>();
        for (Boolean temp : sized) {
            temp = false;
        }
        listIslands = new ArrayList<>();
        listIslands.add(island1);
        listIslands.add(island2);
        listIslands.add(island3);
        listIslands.add(island4);
        listIslands.add(island5);
        listIslands.add(island6);
        listIslands.add(island7);
        listIslands.add(island8);
        listIslands.add(island9);
        listIslands.add(island10);
        listIslands.add(island11);
        listIslands.add(island12);
        this.turn = 0;
        this.client = client;
        this.start = false;
        chosen = false;
        this.student = new Object();
        this.endgame = false;
        this.archipelago = new ArrayList<>();
        this.clouds = new ArrayList<>();
        this.deck = new ArrayList<>();
        this.students = false;
        this.cc = false;
        this.mn = false;
        this.movesLeft = 4;
        this.moves = 4;
        initializeStudents();
        this.gametype = false;


        System.out.println("1");
        client.getSendMessage().sendModelMessage(new ModelMessage());
        model = (ModelMessage) client.receiveMessage();
        showmodel(client);
        System.out.println("2");
        client.getSendMessage().sendAssistantCardsMessage(new AssistantCardsMessage());
        ReceiveCards(client);
        System.out.println("3");

    }

}

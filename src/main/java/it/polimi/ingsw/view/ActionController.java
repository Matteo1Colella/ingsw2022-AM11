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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class ActionController {
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

    public void setClient(ClientMain client) {
        this.client = client;
    }

    public void bind(Stage stage, Scene scene) {

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

            this.model  = (ModelMessage) client.receiveMessage();


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
        alert.setHeaderText("Island Content:");
        alert.setContentText(this.archipelago.get(0).toString());
        alert.show();
        selectedIsland = this.archipelago.get(0);
        To.setText("To: Island " + selectedIsland.getId_island());
    }

    public void clickonIsland2() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Island Content:");
        alert.setContentText(this.archipelago.get(1).toString());
        alert.show();
        selectedIsland = this.archipelago.get(1);
        To.setText("To: Island " + selectedIsland.getId_island());
    }

    public void clickonIsland3() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Island Content:");
        alert.setContentText(this.archipelago.get(2).toString());
        alert.show();
        selectedIsland = this.archipelago.get(2);
        To.setText("To: Island " + selectedIsland.getId_island());
    }

    public void clickonIsland4() {
        ;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Island Content:");
        alert.setContentText(this.archipelago.get(3).toString());
        alert.show();
        selectedIsland = this.archipelago.get(3);
        To.setText("To: Island " + selectedIsland.getId_island());
    }

    public void clickonIsland5() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Island Content:");
        alert.setContentText(this.archipelago.get(4).toString());
        alert.show();
        selectedIsland = this.archipelago.get(4);
        To.setText("To: Island " + selectedIsland.getId_island());
    }

    public void clickonIsland6() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Island Content:");
        alert.setContentText(this.archipelago.get(5).toString());
        alert.show();
        selectedIsland = this.archipelago.get(5);
        To.setText("To: Island " + selectedIsland.getId_island());
    }

    public void clickonIsland7() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Island Content:");
        alert.setContentText(this.archipelago.get(6).toString());
        alert.show();
        selectedIsland = this.archipelago.get(6);
        To.setText("To: Island " + selectedIsland.getId_island());
    }

    public void clickonIsland8() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Island Content:");
        alert.setContentText(this.archipelago.get(7).toString());
        alert.show();
        selectedIsland = this.archipelago.get(7);
        To.setText("To: Island " + selectedIsland.getId_island());
    }

    public void clickonIsland9() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Island Content:");
        alert.setContentText(this.archipelago.get(8).toString());
        alert.show();
        selectedIsland = this.archipelago.get(8);
        To.setText("To: Island " + selectedIsland.getId_island());
    }

    public void clickonIsland10() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Island Content:");
        alert.setContentText(this.archipelago.get(9).toString());
        alert.show();
        selectedIsland = this.archipelago.get(9);
        To.setText("To: Island " + selectedIsland.getId_island());
    }

    public void clickonIsland11() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Island Content:");
        alert.setContentText(this.archipelago.get(10).toString());
        alert.show();
        selectedIsland = this.archipelago.get(10);
        To.setText("To: Island " + selectedIsland.getId_island());
    }

    public void clickonIsland12() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Island Content:");
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

    public void wakeup() {
        synchronized (this.student) {
            this.student.notifyAll();
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


            students = true;

            client.getSendMessage().sendModelMessage(new ModelMessage());
            model = (ModelMessage) client.receiveMessage();
            showmodel(client);

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

    public synchronized void showmodel(ClientMain client) {
        //ask the list of GameComponents (all the model)
       /* this.client = client;
        System.out.println("presend");
        this.client.getSendMessage().sendModelMessage(new ModelMessage());
        System.out.println("aftersend");
        ModelMessage modelMessage = (ModelMessage) this.client.receiveMessage();
        System.out.println("afterreceive");
        this.model = modelMessage;
        */
        //printing model..

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

        this.archipelago.addAll(model.getArchipelago());

        int i = 0;
        for (IslandCard islandCard : model.getArchipelago()) {
            if (islandCard.getMotherNature()) {
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

    public synchronized void confirmCC(){



        client.getSendMessage().sendCloudCardMessage(new CloudCardChoiceMessage(cloudchoice));
        cc = true;
    }
    public synchronized void mainClient(ClientMain client) {

        new PingPongThread(client.getClientSocket(), "client");

        Service<Void> service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {


                    @Override
                    protected Void call() throws Exception {
                        //Background work
                        final CountDownLatch latch = new CountDownLatch(1);
                        Platform.runLater(new Runnable() {

                            private final Object lock = student;


                            @Override
                            public void run() {

                                while (!endgame) {
                                    System.out.println("start turn");
                                    MessageInterface receivedMessage = client.receiveMessage();
                                    MessageType message = receivedMessage.getCode();
                                    System.out.println("start turn");
                                    if (!client.isGameType()) {
                                        switch (message) {
                                            case TURN:

                                                moveStudents();

                                                //afterStudent();

                                                Thread thread = new Thread(){
                                                    @Override
                                                    public void run() {
                                                        while (!students) {
                                                            Thread.onSpinWait();
                                                        }
                                                    }

                                                    @Override
                                                    public synchronized void start() {
                                                        super.start();
                                                    }
                                                };
                                                thread.start();

                                                Thread thread1 = new Thread(){
                                                    @Override
                                                    public void run() {
                                                        while (!mn) {
                                                            Thread.onSpinWait();
                                                        }
                                                        selectCloudCard();
                                                    }

                                                    @Override
                                                    public synchronized void start() {
                                                        super.start();
                                                    }
                                                };
                                                thread1.start();


                                                Thread thread2 = new Thread(){
                                                    @Override
                                                    public void run() {
                                                        while (!cc) {
                                                            Thread.onSpinWait();
                                                        }
                                                        lock.notify();
                                                    }

                                                    @Override
                                                    public synchronized void start() {
                                                        super.start();
                                                    }
                                                };
                                                thread2.start();



                                                try {
                                                    System.out.println("wait");
                                                    thread2.join();
                                                    System.out.println("wat2");
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }

/*
                                                System.out.println("after wait");

                                                moveMotherNature();


                                                 */

                                                /*
                                                System.out.println("std");
                                                System.out.println("stdout");
                                                client.moveMotherNature();
                                                client.selectCloudCard();
                                                choice = false;
                                                message = client.receiveMessage().getCode();
                                                client.showModel();
                                                message = client.receiveMessage().getCode();
                                                while (!choice) {
                                                    choice = client.playAssistantCard();
                                                }

                                                 */
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
                                                showmodel(client);
                                                break;
                                        }
                                    } else {
                                        switch (message) {
                                            case TURN:
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
                                                return;
                                            case MODEL:
                                                client.setModel((ModelMessage) receivedMessage);
                                                break;
                                        }

                                    }
                                }
                            }
                        });
                        latch.await();
                        //Keep with the background work
                        return null;
                    }
                };
            }
        };
        //service.start();



        Task<Void> task = new Task<>() {
            @Override public Void call() {
                while (!endgame) {

                    cc = false;
                    students = false;
                    mn = false;

                    MessageInterface receivedMessage = client.receiveMessage();
                    MessageType message = receivedMessage.getCode();
                    System.out.println("start turn " + message);

                    if (!client.isGameType()) {
                        switch (message) {
                            case TURN:

                                client.getSendMessage().sendModelMessage(new ModelMessage());
                                model = (ModelMessage) client.receiveMessage();
                                showmodel(client);

                                MessageInterface receivedMessage1 = client.receiveMessage();

                                if(receivedMessage1.getCode() == MessageType.TURN){
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









/*
                                                System.out.println("after wait");

                                                moveMotherNature();


                                                 */

                                                /*
                                                System.out.println("std");
                                                System.out.println("stdout");
                                                client.moveMotherNature();
                                                client.selectCloudCard();
                                                choice = false;
                                                message = client.receiveMessage().getCode();
                                                client.showModel();
                                                message = client.receiveMessage().getCode();
                                                while (!choice) {
                                                    choice = client.playAssistantCard();
                                                }

                                                 */
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


    public void clickConfirmAssistant() {
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
            MessageInterface receivedMessage = this.client.receiveMessage();

            System.out.println(receivedMessage.getCode());


            if (receivedMessage.getCode() == MessageType.NOERROR) {
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

                if(!start){
                    Platform.runLater(() -> mainClient(client));
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


        Service<Void> service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        //Background work
                        System.out.println("1");
                        client.getSendMessage().sendModelMessage(new ModelMessage());
                        ModelMessage modelMessage = (ModelMessage) client.receiveMessage();
                        final CountDownLatch latch = new CountDownLatch(1);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    model = modelMessage;
                                    showmodel(client);
                                    System.out.println("2");
                                    client.getSendMessage().sendAssistantCardsMessage(new AssistantCardsMessage());
                                    ReceiveCards(client);
                                    System.out.println("3");
                                } finally {
                                    latch.countDown();
                                }
                            }
                        });
                        latch.await();
                        //Keep with the background work
                        return null;
                    }
                };
            }
        };
        service.start();

    }
}

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
import it.polimi.ingsw.view.stages.CharacterStage;
import it.polimi.ingsw.view.stages.LoginStage;
import it.polimi.ingsw.view.stages.ShowSchoolBoardsStage;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.Bloom;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class ActionController {

    private final Image bTower = new Image("Assets/Tower_black.png");
    private final Image wTower = new Image("Assets/Tower.png");
    private final Image gTower = new Image("Assets/Tower_grey.png");

    private boolean character4;

    private final Image redStudent = new Image("Assets/Students/student_red.png");
    private final Image greenStudent = new Image("Assets/Students/student_green.png");
    private final Image blueStudent = new Image("Assets/Students/student_blue.png");
    private final Image yellowStudent = new Image("Assets/Students/student_yellow.png");
    private final Image pinkStudent = new Image("Assets/Students/student_pink.png");

    private final Image redProf = new Image("Assets/Students/prof_red.png");
    private final Image greenProf = new Image("Assets/Students/prof_green.png");
    private final Image blueProf = new Image("Assets/Students/prof_blue.png");
    private final Image yellowProf = new Image("Assets/Students/prof_yellow.png");
    private final Image pinkProf = new Image("Assets/Students/prof_pink.png");

    private Stage stage;
    private boolean mnMessage;
    private MediaPlayer player;
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
    private ArrayList<CharacterView> characters;

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
    private int student4WhereToPut;
    private int indexIslandIf4ToIsland;

    private ArrayList<ImageView> entranceStudents;
    private ArrayList<ImageView> blues;
    private ArrayList<ImageView> pinks;
    private ArrayList<ImageView> yellows;
    private ArrayList<ImageView> reds;
    private ArrayList<ImageView> greens;
    private ArrayList<ImageView> professors;
    private ArrayList<ImageView> towers;


    @FXML
    private Button confirmStudent;
    @FXML
    private Button confirmCloud;

    @FXML
    private AnchorPane schoolPane;

    @FXML
    private ArrayList<BorderPane> islandStudents;

    @FXML
    private BorderPane cloud1Students;
    @FXML
    private BorderPane cloud2Students;
    @FXML
    private BorderPane cloud3Students;
    @FXML
    private BorderPane cloud4Students;

    private ArrayList<Node> entranceView;

    @FXML
    private Button showBoards;
    @FXML
    private BorderPane island1Students;
    @FXML
    private BorderPane island2Students;
    @FXML
    private BorderPane island3Students;
    @FXML
    private BorderPane island4Students;
    @FXML
    private BorderPane island5Students;
    @FXML
    private BorderPane island6Students;
    @FXML
    private BorderPane island7Students;
    @FXML
    private BorderPane island8Students;
    @FXML
    private BorderPane island9Students;
    @FXML
    private BorderPane island10Students;
    @FXML
    private BorderPane island11Students;
    @FXML
    private BorderPane island12Students;

    @FXML
    private ProgressIndicator progress;

    @FXML
    private AnchorPane progressPane;

    @FXML
    private Label coins;
    @FXML
    private Label username;

    @FXML
    private ImageView character1;
    @FXML
    private ImageView character2;
    @FXML
    private ImageView character3;

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
    private ImageView cloud4;

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

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setClient(ClientMain client) {
        this.client = client;
    }

    /**
     * Allows to print the islands on the vertex of a polygon. Every times the
     * number of islands decreases, the number of the polygon's wedges decreases
     * and the islands will be equidistant from the center of the polygon.
     */
    public void bindIslands() {

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

            listIslands.get(i).setLayoutX(x + 237.26);
            listIslands.get(i).setLayoutY(y + 236);
            islandStudents.get(i).setLayoutX(listIslands.get(i).getLayoutX()+30);
            islandStudents.get(i).setLayoutY(listIslands.get(i).getLayoutY()+25);
        }

    }

    /**
     * Launch the stage that allows to see the other players' SchoolBoards
     * @throws IOException
     */
    public void showOtherBoards() throws IOException {
        new ShowSchoolBoardsStage(this.client, model);
    }

    public void setModel(ModelMessage model) {
        this.model = model;
    }

    /**
     * Allows the resize of the stage
     * @param stage
     * @param scene
     */
    public void bind(Stage stage, Scene scene) {

        progress.setStyle("-fx-accent: midnightblue;");


        bindIslands();

        double height = scene.getHeight();
        double width = scene.getWidth();

        apane.translateXProperty().bind(scene.widthProperty().subtract(apane.widthProperty()).divide(2));
        apane.translateYProperty().bind(scene.heightProperty().subtract(apane.heightProperty()).divide(2));

        progressPane.translateXProperty().bind(scene.widthProperty().subtract(progressPane.widthProperty()).divide(2));
        progressPane.translateYProperty().bind(scene.heightProperty().subtract(progressPane.heightProperty()).divide(2));

        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            double scaleX = newVal.doubleValue() / width;
            apane.setScaleX(scaleX);
            apane.setCenterShape(true);
            progressPane.setScaleX(scaleX);
            progressPane.setCenterShape(true);
        });


        stage.heightProperty().addListener((obs, oldVal, newVal) -> {
            double scaleY = newVal.doubleValue() / height;
            apane.setCenterShape(true);
            apane.setScaleY(scaleY);
            progressPane.setScaleY(scaleY);
            progressPane.setCenterShape(true);
        });
    }

    /**
     * Sends to the server the message about the number of movements that MotherNature has to do
     */
    public synchronized void clickMoveMN() {

        if (!mnMessage){
            client.getSendMessage().sendMoveMotherNatureMessage(new MoveMotherNatureMessage());
            mnMessage = true;
            steps.setDisable(false);
            disableCharacters();
            return;
        }


        int maxsteps = selectedCard.getSteps();

        if (character4){
            maxsteps = maxsteps + 2;
            character4 = false;
        }

        int steps = Integer.parseInt(this.steps.getText());

        if (maxsteps < steps) {
            return;
        } else {

            mnMessage = false;
            this.steps.setDisable(true);
            enableCharacters();

            client.getSendMessage().sendMoveMotherNatureMessage(new MoveMotherNatureMessage(steps));

            client.getSendMessage().sendModelMessage(new ModelMessage());

            this.model = (ModelMessage) client.receiveMessage();


            showmodel(client);

            MessageInterface receivedMessage = client.receiveMessage();

            if (receivedMessage.getCode() == MessageType.TURN) {
                System.out.println("Correct selection.\r");
            }

            mn = true;

        }
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

    /**
     * Select the island on which a student will be placed.
     */
    public void clickonIsland1() {
        IslandCard showIsland = new IslandCard(-1);
        for (IslandCard island : this.archipelago) {
            if (island.getOriginal() == 0) {
                showIsland = island;
            }
        }
        if (showIsland.getId_island() == -1) return;
        selectedIsland = showIsland;
        To.setText("To: Island " + (showIsland.getOriginal() + 1));
    }

    /**
     * Select the island on which a student will be placed.
     */
    public void clickonIsland2() {
        IslandCard showIsland = new IslandCard(-1);
        for (IslandCard island : this.archipelago) {
            if (island.getOriginal() == 1) {
                showIsland = island;
            }
        }
        if (showIsland.getId_island() == -1) return;
        selectedIsland = showIsland;
        To.setText("To: Island " + (showIsland.getOriginal() + 1));
    }

    /**
     * Select the island on which a student will be placed.
     */
    public void clickonIsland3() {
        IslandCard showIsland = new IslandCard(-1);
        for (IslandCard island : this.archipelago) {
            if (island.getOriginal() == 2) {
                showIsland = island;
            }
        }
        if (showIsland.getId_island() == -1) return;
        selectedIsland = showIsland;
        To.setText("To: Island " + (showIsland.getOriginal() + 1));
    }

    /**
     * Select the island on which a student will be placed.
     */
    public void clickonIsland4() {

        IslandCard showIsland = new IslandCard(-1);
        for (IslandCard island : this.archipelago) {
            if (island.getOriginal() == 3) {
                showIsland = island;
            }
        }
        if (showIsland.getId_island() == -1) return;
        selectedIsland = showIsland;
        To.setText("To: Island " + (showIsland.getOriginal() + 1));
    }

    /**
     * Select the island on which a student will be placed.
     */
    public void clickonIsland5() {
        IslandCard showIsland = new IslandCard(-1);
        for (IslandCard island : this.archipelago) {
            if (island.getOriginal() == 4) {
                showIsland = island;
            }
        }
        if (showIsland.getId_island() == -1) return;
        selectedIsland = showIsland;
        To.setText("To: Island " + (showIsland.getOriginal() + 1));
    }

    /**
     * Select the island on which a student will be placed.
     */
    public void clickonIsland6() {
        IslandCard showIsland = new IslandCard(-1);
        for (IslandCard island : this.archipelago) {
            if (island.getOriginal() == 5) {
                showIsland = island;
            }
        }
        if (showIsland.getId_island() == -1) return;
        selectedIsland = showIsland;
        To.setText("To: Island " + (showIsland.getOriginal() + 1));
    }

    /**
     * Select the island on which a student will be placed.
     */
    public void clickonIsland7() {
        IslandCard showIsland = new IslandCard(-1);
        for (IslandCard island : this.archipelago) {
            if (island.getOriginal() == 6) {
                showIsland = island;
            }
        }
        if (showIsland.getId_island() == -1) return;
        selectedIsland = showIsland;
        To.setText("To: Island " + (showIsland.getOriginal() + 1));
    }

    /**
     * Select the island on which a student will be placed.
     */
    public void clickonIsland8() {
        IslandCard showIsland = new IslandCard(-1);
        for (IslandCard island : this.archipelago) {
            if (island.getOriginal() == 7) {
                showIsland = island;
            }
        }
        if (showIsland.getId_island() == -1) return;
        selectedIsland = showIsland;
        To.setText("To: Island " + (showIsland.getOriginal() + 1));
    }

    /**
     * Select the island on which a student will be placed.
     */
    public void clickonIsland9() {
        IslandCard showIsland = new IslandCard(-1);
        for (IslandCard island : this.archipelago) {
            if (island.getOriginal() == 8) {
                showIsland = island;
            }
        }
        if (showIsland.getId_island() == -1) return;
        selectedIsland = showIsland;
        To.setText("To: Island " + (showIsland.getOriginal() + 1));
    }

    /**
     * Select the island on which a student will be placed.
     */
    public void clickonIsland10() {
        IslandCard showIsland = new IslandCard(-1);
        for (IslandCard island : this.archipelago) {
            if (island.getOriginal() == 9) {
                showIsland = island;
            }
        }
        if (showIsland.getId_island() == -1) return;
        selectedIsland = showIsland;
        To.setText("To: Island " + (showIsland.getOriginal() + 1));
    }

    /**
     * Select the island on which a student will be placed.
     */
    public void clickonIsland11() {
        IslandCard showIsland = new IslandCard(-1);
        for (IslandCard island : this.archipelago) {
            if (island.getOriginal() == 10) {
                showIsland = island;
            }
        }
        if (showIsland.getId_island() == -1) return;
        selectedIsland = showIsland;
        To.setText("To: Island " + (showIsland.getOriginal() + 1));
    }

    /**
     * Select the island on which a student will be placed.
     */
    public void clickonIsland12() {
        IslandCard showIsland = new IslandCard(-1);
        for (IslandCard island : this.archipelago) {
            if (island.getOriginal() == 11) {
                showIsland = island;
            }
        }
        if (showIsland.getId_island() == -1) return;
        selectedIsland = showIsland;
        To.setText("To: Island " + (showIsland.getOriginal() + 1));
    }

    /**
     * Select the cloud from which select the students that will be placed in the SchoolBoard's Entrance.
     */
    public void clickonCloud1() {
        if(cloudchoice == -1){
            client.getSendMessage().sendCloudCardMessage(new CloudCardChoiceMessage());
            disableCharacters();
        }
        this.cloudchoice = 0;
        this.selectedCloud = this.clouds.get(0);
        this.SelectedCloud.setText("Selected cloud: Cloud 1");


    }

    /**
     * Select the cloud from which select the students that will be placed in the SchoolBoard's Entrance.
     */
    public void clickonCloud2() {
        if(cloudchoice == -1){
            client.getSendMessage().sendCloudCardMessage(new CloudCardChoiceMessage());
            disableCharacters();
        }
        cloudchoice = 1;
        this.selectedCloud = this.clouds.get(1);
        this.SelectedCloud.setText("Selected cloud: Cloud 2");

    }

    /**
     * Select the cloud from which select the students that will be placed in the SchoolBoard's Entrance.
     */
    public void clickonCloud3() {
        if(cloudchoice == -1){
            client.getSendMessage().sendCloudCardMessage(new CloudCardChoiceMessage());
            disableCharacters();
        }
        cloudchoice = 2;
        this.selectedCloud = this.clouds.get(2);
        this.SelectedCloud.setText("Selected cloud: Cloud 3");

    }

    /**
     * Select the cloud from which select the students that will be placed in the SchoolBoard's Entrance.
     */
    public void clickonCloud4() {
        if(cloudchoice == -1){
            client.getSendMessage().sendCloudCardMessage(new CloudCardChoiceMessage());
            disableCharacters();
        }
        this.cloudchoice = 3;
        this.selectedCloud = this.clouds.get(3);
        this.SelectedCloud.setText("Selected cloud: Cloud 4");

    }

    /**
     * Select the card to be played.
     */
    public void clickonAssistant1() {

        for (Card card : this.deck) {
            if (card.getInfluence() == 1) {
                selectedCard = card;
                assistantText.setText(card.getName());
            }
        }

    }

    /**
     * Select the card to be played.
     */
    public void clickonAssistant2() {

        for (Card card : this.deck) {
            if (card.getInfluence() == 2) {
                selectedCard = card;
                assistantText.setText(card.getName());
            }
        }

    }

    /**
     * Select the card to be played.
     */
    public void clickonAssistant3() {

        for (Card card : this.deck) {
            if (card.getInfluence() == 3) {
                selectedCard = card;
                assistantText.setText(card.getName());
            }
        }

    }

    /**
     * Select the card to be played.
     */
    public void clickonAssistant4() {

        for (Card card : this.deck) {
            if (card.getInfluence() == 4) {
                selectedCard = card;
                assistantText.setText(card.getName());
            }
        }

    }

    /**
     * Select the card to be played.
     */
    public void clickonAssistant5() {

        for (Card card : this.deck) {
            if (card.getInfluence() == 5) {
                selectedCard = card;
                assistantText.setText(card.getName());
            }
        }

    }

    /**
     * Select the card to be played.
     */
    public void clickonAssistant6() {

        for (Card card : this.deck) {
            if (card.getInfluence() == 6) {
                selectedCard = card;
                assistantText.setText(card.getName());
            }
        }

    }

    /**
     * Select the card to be played.
     */
    public void clickonAssistant7() {

        for (Card card : this.deck) {
            if (card.getInfluence() == 7) {
                selectedCard = card;
                assistantText.setText(card.getName());
            }
        }

    }

    /**
     * Select the card to be played.
     */
    public void clickonAssistant8() {

        for (Card card : this.deck) {
            if (card.getInfluence() == 8) {
                selectedCard = card;
                assistantText.setText(card.getName());
            }
        }

    }

    /**
     * Select the card to be played.
     */
    public void clickonAssistant9() {

        for (Card card : this.deck) {
            if (card.getInfluence() == 9) {
                selectedCard = card;
                assistantText.setText(card.getName());
            }
        }

    }

    /**
     * Select the card to be played.
     */
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
        student4WhereToPut = -1;
        indexIslandIf4ToIsland = -1;
    }

    /**
     * Select a student from the entrance.
     */
    public void clickOnentranceStudent1(){
        ImageView student = (ImageView) entranceView.get(0);
        if (student1Entrance == -1) {
            client.getSendMessage().sendMoveStudentsMessage(new MoveStudentMessage());
            disableCharacters();
            student1Entrance = 0;

        }else if (student2Entrance == -1) {
            student2Entrance = 0;
        } else if (student3Entrance == -1) {
            student3Entrance = 0;
        } else if (this.moves == 4) {
            if (student4Entrance == -1) {
                student4Entrance = 0;
            }
        }
        student.setEffect(new Bloom());
        student.setDisable(true);
        confirmStudent.setDisable(false);
        for(Node n : entranceView){
            n.setDisable(true);
        }
        switch (model.getSchoolBoard().getEntrance().getStudents().get(0).getColor()) {
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
        confirmStudent.setDisable(false);

    }
    /**
     * Select a student from the entrance.
     */
    public void clickOnentranceStudent2(){
        ImageView student = (ImageView) entranceView.get(1);
        if (student1Entrance == -1) {
            client.getSendMessage().sendMoveStudentsMessage(new MoveStudentMessage());
            disableCharacters();
            student1Entrance = 1;

        }else if (student2Entrance == -1) {
            student2Entrance = 1;
        } else if (student3Entrance == -1) {
            student3Entrance = 1;
        } else if (this.moves == 4) {
            if (student4Entrance == -1) {
                student4Entrance = 1;
            }
        }
        student.setEffect(new Bloom());
        student.setDisable(true);
        confirmStudent.setDisable(false);
        for(Node n : entranceView){
            n.setDisable(true);
        }
        switch (model.getSchoolBoard().getEntrance().getStudents().get(1).getColor()) {
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
        confirmStudent.setDisable(false);
    }

    /**
     * Select a student from the entrance.
     */
    public void clickOnentranceStudent3(){
        ImageView student = (ImageView) entranceView.get(2);
        if (student1Entrance == -1) {
            client.getSendMessage().sendMoveStudentsMessage(new MoveStudentMessage());
            disableCharacters();
            student1Entrance = 2;

        }else if (student2Entrance == -1) {
            student2Entrance = 2;
        } else if (student3Entrance == -1) {
            student3Entrance = 2;
        } else if (this.moves == 4) {
            if (student4Entrance == -1) {
                student4Entrance = 2;
            }
        }
        student.setEffect(new Bloom());
        student.setDisable(true);
        confirmStudent.setDisable(false);
        for(Node n : entranceView){
            n.setDisable(true);
        }
        switch (model.getSchoolBoard().getEntrance().getStudents().get(2).getColor()) {
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
        confirmStudent.setDisable(false);
    }

    /**
     * Select a student from the entrance.
     */
    public void clickOnentranceStudent4(){
        ImageView student = (ImageView) entranceView.get(3);
        if (student1Entrance == -1) {
            client.getSendMessage().sendMoveStudentsMessage(new MoveStudentMessage());
            disableCharacters();
            student1Entrance = 3;

        }else if (student2Entrance == -1) {
            student2Entrance = 3;
        } else if (student3Entrance == -1) {
            student3Entrance = 3;
        } else if (this.moves == 4) {
            if (student4Entrance == -1) {
                student4Entrance = 3;
            }
        }
        student.setEffect(new Bloom());
        student.setDisable(true);
        confirmStudent.setDisable(false);
        for(Node n : entranceView){
            n.setDisable(true);
        }
        switch (model.getSchoolBoard().getEntrance().getStudents().get(3).getColor()) {
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
        confirmStudent.setDisable(false);
    }

    /**
     * Select a student from the entrance.
     */
    public void clickOnentranceStudent5(){
        ImageView student = (ImageView) entranceView.get(4);
        if (student1Entrance == -1) {
            client.getSendMessage().sendMoveStudentsMessage(new MoveStudentMessage());
            disableCharacters();
            student1Entrance = 4;

        }else if (student2Entrance == -1) {
            student2Entrance = 4;
        } else if (student3Entrance == -1) {
            student3Entrance = 4;
        } else if (this.moves == 4) {
            if (student4Entrance == -1) {
                student4Entrance = 4;
            }
        }
        student.setEffect(new Bloom());
        student.setDisable(true);
        confirmStudent.setDisable(false);
        for(Node n : entranceView){
            n.setDisable(true);
        }
        switch (model.getSchoolBoard().getEntrance().getStudents().get(4).getColor()) {
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
        confirmStudent.setDisable(false);
    }

    /**
     * Select a student from the entrance.
     */
    public void clickOnentranceStudent6(){
        ImageView student = (ImageView) entranceView.get(5);
        if (student1Entrance == -1) {
            client.getSendMessage().sendMoveStudentsMessage(new MoveStudentMessage());
            disableCharacters();
            student1Entrance = 5;

        }else if (student2Entrance == -1) {
            student2Entrance = 5;
        } else if (student3Entrance == -1) {
            student3Entrance = 5;
        } else if (this.moves == 4) {
            if (student4Entrance == -1) {
                student4Entrance = 5;
            }
        }
        student.setEffect(new Bloom());
        student.setDisable(true);
        confirmStudent.setDisable(false);
        for(Node n : entranceView){
            n.setDisable(true);
        }
        switch (model.getSchoolBoard().getEntrance().getStudents().get(5).getColor()) {
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
        confirmStudent.setDisable(false);

    }

    /**
     * Select a student from the entrance.
     */
    public void clickOnentranceStudent7(){
        ImageView student = (ImageView) entranceView.get(6);
        if (student1Entrance == -1) {
            client.getSendMessage().sendMoveStudentsMessage(new MoveStudentMessage());
            disableCharacters();
            student1Entrance = 6;

        }else if (student2Entrance == -1) {
            student2Entrance = 6;
        } else if (student3Entrance == -1) {
            student3Entrance = 6;
        } else if (this.moves == 4) {
            if (student4Entrance == -1) {
                student4Entrance = 6;
            }
        }
        student.setEffect(new Bloom());
        student.setDisable(true);
        confirmStudent.setDisable(false);
        for(Node n : entranceView){
            n.setDisable(true);
        }

        switch (model.getSchoolBoard().getEntrance().getStudents().get(6).getColor()) {
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
        confirmStudent.setDisable(false);
    }

    /**
     * Select a student from the entrance.
     */
    public void clickOnentranceStudent8(){
        ImageView student = (ImageView) entranceView.get(7);
        if (student1Entrance == -1) {
            client.getSendMessage().sendMoveStudentsMessage(new MoveStudentMessage());
            disableCharacters();
            student1Entrance = 7;

        }else if (student2Entrance == -1) {
            student2Entrance = 7;
        } else if (student3Entrance == -1) {
            student3Entrance = 7;
        } else if (this.moves == 4) {
            if (student4Entrance == -1) {
                student4Entrance = 7;
            }
        }
        student.setEffect(new Bloom());
        student.setDisable(true);
        confirmStudent.setDisable(false);
        for(Node n : entranceView){
            n.setDisable(true);
        }
        switch (model.getSchoolBoard().getEntrance().getStudents().get(7).getColor()) {
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
        confirmStudent.setDisable(false);
    }

    /**
     * Select a student from the entrance.
     */
    public void clickOnentranceStudent9(){
        ImageView student = (ImageView) entranceView.get(8);
        if (student1Entrance == -1) {
            client.getSendMessage().sendMoveStudentsMessage(new MoveStudentMessage());
            disableCharacters();
            student1Entrance = 8;

        }else if (student2Entrance == -1) {
            student2Entrance = 8;
        } else if (student3Entrance == -1) {
            student3Entrance = 8;
        } else if (this.moves == 4) {
            if (student4Entrance == -1) {
                student4Entrance = 8;
            }
        }
        student.setEffect(new Bloom());
        student.setDisable(true);
        confirmStudent.setDisable(false);
        for(Node n : entranceView){
            n.setDisable(true);
        }
        switch (model.getSchoolBoard().getEntrance().getStudents().get(8).getColor()) {
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
        confirmStudent.setDisable(false);

    }

    /**
     * Confirm the student selection.
     */
    public synchronized void confirmStudent() {

        if (movesLeft==3) {
            for(int s = 0; s < model.getSchoolBoard().getEntrance().getStudents().size(); s++){
                if (s != student1Entrance && s != student4Entrance){
                    entranceView.get(s).setDisable(false);
                } else{
                    entranceView.get(s).setOpacity(0.5);
                }
            }
            if (selectedIsland == null) {
                student1WhereToPut = 0;
                indexIslandIf1ToIsland = -1;
            } else {
                student1WhereToPut = 1;
                indexIslandIf1ToIsland = selectedIsland.getId_island();
            }
            movesLeft--;
        } else if (movesLeft == 2) {
            for(int s = 0; s < model.getSchoolBoard().getEntrance().getStudents().size(); s++){
                if (s != student2Entrance && s != student1Entrance && s != student4Entrance){
                    entranceView.get(s).setDisable(false);
                } else{
                    entranceView.get(s).setOpacity(0.5);
                }
            }
            if (selectedIsland == null) {
                student2WhereToPut = 0;
                indexIslandIf2ToIsland = -1;
            } else {
                student2WhereToPut = 1;
                indexIslandIf2ToIsland = selectedIsland.getId_island();
            }
            movesLeft--;
        } else if (movesLeft == 1) {
            for(int s = 0; s < model.getSchoolBoard().getEntrance().getStudents().size(); s++){
                if (s != student3Entrance && s != student2Entrance && s != student1Entrance && s != student4Entrance){
                    entranceView.get(s).setDisable(false);
                } else{
                    entranceView.get(s).setOpacity(0.5);
                }
            }
            if (selectedIsland == null) {
                student3WhereToPut = 0;
                indexIslandIf3ToIsland = -1;
            } else {
                student3WhereToPut = 1;
                indexIslandIf3ToIsland = selectedIsland.getId_island();
            }
            movesLeft--;
        } else if (movesLeft == 4) {
            if (student4Entrance == -1) {
                for(int s = 0; s < model.getSchoolBoard().getEntrance().getStudents().size(); s++){
                    if (s != student4Entrance){
                        entranceView.get(s).setDisable(false);
                    } else{
                        entranceView.get(s).setOpacity(0.5);
                    }
                }
                if (selectedIsland == null) {
                    student4WhereToPut = 0;
                    indexIslandIf4ToIsland = -1;
                } else {
                    student4WhereToPut = 1;
                    indexIslandIf4ToIsland = selectedIsland.getId_island();
                }
                movesLeft--;
            }
        }
        if (movesLeft == 0) {
            for(int s = 0; s < model.getSchoolBoard().getEntrance().getStudents().size(); s++){
                entranceView.get(s).setDisable(true);
                entranceView.get(s).setOpacity(1);
                entranceView.get(s).setEffect(null);
            }
            client.getSendMessage().sendMoveStudentsMessage(new MoveStudentMessage(student1Entrance + 1, student1WhereToPut, indexIslandIf1ToIsland, student2Entrance + 1, student2WhereToPut, indexIslandIf2ToIsland, student3Entrance + 1, student3WhereToPut, indexIslandIf3ToIsland, student4Entrance, student4WhereToPut, indexIslandIf4ToIsland));

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
            enableCharacters();

        }
        confirmStudent.setDisable(true);


    }

    /**
     * Reset the Island and Student selection.
     */
    public synchronized void reset() {
        selectedIsland = null;
        To.setText("To: Dining Room");
        SelectedStudent.setText("Selected Student:");
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

        island1Students.setOpacity(0);
        island1Students.setDisable(true);
        island2Students.setOpacity(0);
        island2Students.setDisable(true);
        island3Students.setOpacity(0);
        island3Students.setDisable(true);
        island4Students.setOpacity(0);
        island4Students.setDisable(true);
        island5Students.setOpacity(0);
        island5Students.setDisable(true);
        island6Students.setOpacity(0);
        island6Students.setDisable(true);
        island7Students.setOpacity(0);
        island7Students.setDisable(true);
        island8Students.setOpacity(0);
        island8Students.setDisable(true);
        island9Students.setOpacity(0);
        island9Students.setDisable(true);
        island10Students.setOpacity(0);
        island10Students.setDisable(true);
        island11Students.setOpacity(0);
        island11Students.setDisable(true);
        island12Students.setOpacity(0);
        island12Students.setDisable(true);
    }

    public CharacterCard getCharacterByNum(int num){
        int i = 0;
        for(CharacterCard card : model.getCharacterCards()){
            if(card.getNum()==num){
                return card;
            }
        }
        return null;

    }

    /**
     * Allows to play a CharacterCard
     * @param num
     * @throws IOException
     * @throws InterruptedException
     */
    public synchronized void characterAction(int num) throws IOException, InterruptedException {
        UseCharacterMessage characterMessage = new UseCharacterMessage();
        switch (num){
            case 1:
            case 12:
            case 11:
            case 10:
            case 9:
            case 7:
            case 5:
            case 3:
                new CharacterStage(this.client, num, model.getCharacterCards().indexOf(getCharacterByNum(num)), model, this);
                break;
            case 2:
                client.getSendMessage().sendCharacterMessage(characterMessage.useCharacter2Message(model.getCharacterCards().indexOf(getCharacterByNum(num))));
                client.receiveMessage();
                break;
            case 4:
                client.getSendMessage().sendCharacterMessage(characterMessage.useCharacter4Message(model.getCharacterCards().indexOf(getCharacterByNum(num))));
                character4 = true;
                client.receiveMessage();
                break;
            case 6:
                client.getSendMessage().sendCharacterMessage(characterMessage.useCharacter6Message(model.getCharacterCards().indexOf(getCharacterByNum(num))));
                client.receiveMessage();
                break;
            case 8:
                client.getSendMessage().sendCharacterMessage(characterMessage.useCharacter8Message(model.getCharacterCards().indexOf(getCharacterByNum(num))));
                client.receiveMessage();
                break;

        }


        client.getSendMessage().sendModelMessage(new ModelMessage());
        model = (ModelMessage) client.receiveMessage();

        Platform.runLater(() -> {
            showmodel(client);
        });

        MessageInterface receivedMessage1 = client.receiveMessage();

    }

    /**
     * Play a specific CharacterCard
     * @throws IOException
     * @throws InterruptedException
     */
    public void clickOnCharacter1() throws IOException, InterruptedException {
        characterAction(characters.get(0).getNum());
    }

    /**
     * Play a specific CharacterCard
     * @throws IOException
     * @throws InterruptedException
     */
    public void clickOnCharacter2() throws IOException, InterruptedException {
        characterAction(characters.get(1).getNum());
    }

    /**
     * Play a specific CharacterCard
     * @throws IOException
     * @throws InterruptedException
     */
    public void clickOnCharacter3() throws IOException, InterruptedException {
        characterAction(characters.get(2).getNum());
    }

    /**
     * Sets the students on the clouds.
     * @param nodeMatrix
     * @param nb
     * @param ng
     * @param nr
     * @param ny
     * @param np
     */
    private void setStudentCloud(ArrayList<Node> nodeMatrix, int nb, int ng, int nr, int ny, int np){
        int[] array = new int[] {
                nb, ng, nr, ny, np };
        for(int i = 0; i < 5; i++) {
            StackPane pane = (StackPane) nodeMatrix.get(i);
            ArrayList<Node> nodes = new ArrayList<>(pane.getChildren());
            Label number = (Label) nodes.get(1);
            number.setText("" + array[i]);
            if(array[i]==0){
                nodes.get(1).setDisable(true);
                nodes.get(0).setDisable(true);
                nodes.get(1).setOpacity(0);
                nodes.get(0).setOpacity(0);
            }else{
                nodes.get(1).setDisable(false);
                nodes.get(0).setDisable(false);
                nodes.get(1).setOpacity(1);
                nodes.get(0).setOpacity(1);
            }
        }
    }

    /**
     * Set the students in the SchoolBoard.
     * @param nodeMatrix
     * @param island
     * @param nb
     * @param ng
     * @param nr
     * @param ny
     * @param np
     */
    private void setStudentImage(ArrayList<Node> nodeMatrix, IslandCard island, int nb, int ng, int nr, int ny, int np) {
        ImageView tower = new ImageView();
        int[] array = new int[] {
                nb, ng, nr, ny, np };
        for(int i = 0; i < 5; i++){
            StackPane pane = (StackPane) nodeMatrix.get(i);
            ArrayList<Node> nodes = new ArrayList<>(pane.getChildren());
            if (i == 0){
                tower = (ImageView) nodes.get(2);
                tower.setDisable(true);
                tower.setOpacity(0);
            }
            Label number = (Label) nodes.get(1);
            number.setText("" + array[i]);
            if(array[i]==0){
                nodes.get(1).setDisable(true);
                nodes.get(0).setDisable(true);
                nodes.get(1).setOpacity(0);
                nodes.get(0).setOpacity(0);
            }else{
                nodes.get(1).setDisable(false);
                nodes.get(0).setDisable(false);
                nodes.get(1).setOpacity(1);
                nodes.get(0).setOpacity(1);
            }
        }

        if(island.getTower()!= null){
            switch (island.getTower().getColor()){
                case GREY ->{
                    tower.setDisable(false);
                    tower.setOpacity(1);
                    tower.setImage(gTower);
                }
                case BLACK -> {
                    tower.setDisable(false);
                    tower.setOpacity(1);
                    tower.setImage(bTower);
                }
                case WHITE -> {
                    tower.setDisable(false);
                    tower.setOpacity(1);
                    tower.setImage(wTower);
                }
            }
        }

    }

    /**
     * Show the students on the isands.
     */
    public void showStudents() {
        int numred = 0;
        int numblue= 0;
        int numpink= 0;
        int numyellow= 0;
        int numgreen= 0;
        for (IslandCard island : model.getArchipelago()) {
            for (Student student : island.getStudents()) {
                switch(student.getColor()){
                    case YELLOW -> numyellow++;
                    case PINK -> numpink++;
                    case BLUE -> numblue++;
                    case GREEN -> numgreen++;
                    case RED -> numred++;
                }
            }
            for (IslandCard islandCard : island.getMergedWith()) {
                for (Student student : islandCard.getStudents()) {
                    switch (student.getColor()) {
                        case YELLOW -> numyellow++;
                        case PINK -> numpink++;
                        case BLUE -> numblue++;
                        case GREEN -> numgreen++;
                        case RED -> numred++;
                    }
                }
            }

            ArrayList<Node> nodeMatrix = new ArrayList<>();

            // 0 azzurro
            // 1 verde
            // 2 rosso
            // 3 giallo
            // 4 rosa


            switch (island.getOriginal()) {
                case 0:
                    nodeMatrix.addAll(island1Students.getChildren());
                    setStudentImage(nodeMatrix, island, numblue, numgreen, numred, numyellow, numpink);
                    break;
                case 1:
                    nodeMatrix.addAll(island2Students.getChildren());
                    setStudentImage(nodeMatrix, island, numblue, numgreen, numred, numyellow, numpink);
                    break;
                case 2:
                    nodeMatrix.addAll(island3Students.getChildren());
                    setStudentImage(nodeMatrix, island, numblue, numgreen, numred, numyellow, numpink);
                    break;
                case 3:
                    nodeMatrix.addAll(island4Students.getChildren());
                    setStudentImage(nodeMatrix, island, numblue, numgreen, numred, numyellow, numpink);
                    break;
                case 4:
                    nodeMatrix.addAll(island5Students.getChildren());
                    setStudentImage(nodeMatrix, island, numblue, numgreen, numred, numyellow, numpink);
                    break;
                case 5:
                    nodeMatrix.addAll(island6Students.getChildren());
                    setStudentImage(nodeMatrix, island, numblue, numgreen, numred, numyellow, numpink);
                    break;
                case 6:
                    nodeMatrix.addAll(island7Students.getChildren());
                    setStudentImage(nodeMatrix, island, numblue, numgreen, numred, numyellow, numpink);
                    break;
                case 7:
                    nodeMatrix.addAll(island8Students.getChildren());
                    setStudentImage(nodeMatrix, island, numblue, numgreen, numred, numyellow, numpink);
                    break;
                case 8:
                    nodeMatrix.addAll(island9Students.getChildren());
                    setStudentImage(nodeMatrix, island, numblue, numgreen, numred, numyellow, numpink);
                    break;
                case 9:
                    nodeMatrix.addAll(island10Students.getChildren());
                    setStudentImage(nodeMatrix, island, numblue, numgreen, numred, numyellow, numpink);
                    break;
                case 10:
                    nodeMatrix.addAll(island11Students.getChildren());
                    setStudentImage(nodeMatrix, island, numblue, numgreen, numred, numyellow, numpink);
                    break;
                case 11:
                    nodeMatrix.addAll(island12Students.getChildren());
                    setStudentImage(nodeMatrix, island, numblue, numgreen, numred, numyellow, numpink);
                    break;

            }
            numred = 0;
            numblue= 0;
            numpink= 0;
            numyellow= 0;
            numgreen= 0;
        }
        int i = 0;
        for(CloudCard card : model.getCloudCardList()){
            for (Student student : card.getStudents()) {
                switch (student.getColor()) {
                    case YELLOW -> numyellow++;
                    case PINK -> numpink++;
                    case BLUE -> numblue++;
                    case GREEN -> numgreen++;
                    case RED -> numred++;
                }
            }
            ArrayList<Node> nodeMatrix = new ArrayList<>();
            switch (i) {
                case 0:
                    nodeMatrix.addAll(cloud1Students.getChildren());
                    setStudentCloud(nodeMatrix, numblue, numgreen, numred, numyellow, numpink);
                    break;
                case 1:
                    nodeMatrix.addAll(cloud2Students.getChildren());
                    setStudentCloud(nodeMatrix, numblue, numgreen, numred, numyellow, numpink);
                    break;
                case 2:
                    nodeMatrix.addAll(cloud3Students.getChildren());
                    setStudentCloud(nodeMatrix, numblue, numgreen, numred, numyellow, numpink);
                    break;
                case 3:
                    nodeMatrix.addAll(cloud4Students.getChildren());
                    setStudentCloud(nodeMatrix, numblue, numgreen, numred, numyellow, numpink);
                    break;
            }

            i++;
            numred = 0;
            numblue= 0;
            numpink= 0;
            numyellow= 0;
            numgreen= 0;
        }


    }

    /**
     * Initializes the ArrayLists representing the DiningRooms, Entrance, Professors and Towers with
     * the correspondent ImageView.
     */
    private void initLists(){
        ArrayList<Node> school = new ArrayList<>(schoolPane.getChildren());
        for(int k = 0; k<9; k++){
            entranceStudents.add((ImageView) school.get(k));
        }
        for(int k = 9; k<19; k++){
            blues.add((ImageView) school.get(k));
        }
        for(int k = 19; k<29; k++){
            pinks.add((ImageView) school.get(k));
        }
        for(int k = 29; k<39; k++){
            yellows.add((ImageView) school.get(k));
        }
        for(int k = 39; k<49; k++){
            reds.add((ImageView) school.get(k));
        }
        for(int k = 49; k<59; k++){
            greens.add((ImageView) school.get(k));
        }
        for(int k = 59; k<64; k++){
            professors.add((ImageView) school.get(k));
        }
        for(int k = 64; k<=71; k++){
            towers.add((ImageView) school.get(k));
        }

        professors.get(0).setImage(greenProf);
        professors.get(1).setImage(redProf);
        professors.get(2).setImage(yellowProf);
        professors.get(3).setImage(pinkProf);
        professors.get(4).setImage(blueProf);

        for(ImageView professor : professors){
            professor.setScaleX(1.3);
            professor.setScaleX(1.3);
        }

    }

    /**
     * Shows the entire model
     * @param client
     */
    public synchronized void showmodel(ClientMain client) {

        initLists();

        this.cloudchoice = -1;

        //if pro{
        if (model.getCharacterCards() != null) {
            characters = new ArrayList<>();
            this.gametype = true;
            for (CharacterCard characterCard : model.getCharacterCards()) {
                CharacterView characterView = new CharacterView(characterCard.getNum());
                characters.add(characterView);
            }
            character1.setImage(characters.get(0).getImage());
            character2.setImage(characters.get(1).getImage());
            character3.setImage(characters.get(2).getImage());

            System.out.println("necessary coin : " + model.getCharacterCards().get(0).getNecessaryCoin());
            System.out.println("necessary coin : " + model.getCharacterCards().get(1).getNecessaryCoin());
            System.out.println("necessary coin : " + model.getCharacterCards().get(2).getNecessaryCoin());

            if(model.getCharacterCards().get(0).getNecessaryCoin() > model.getCoinOwned()){
                character1.setOpacity(0.5);
                character1.setDisable(true);
            } else {
                character1.setOpacity(1);
                character1.setDisable(false);
            }
            if(model.getCharacterCards().get(1).getNecessaryCoin() > model.getCoinOwned()){
                character2.setOpacity(0.5);
                character2.setDisable(true);
            } else {
                character2.setOpacity(1);
                character2.setDisable(false);
            }
            if(model.getCharacterCards().get(2).getNecessaryCoin() > model.getCoinOwned()){
                character3.setOpacity(0.5);
                character3.setDisable(true);
            } else {
                character3.setOpacity(1);
                character3.setDisable(false);
            }

            coins.setText("Coins: " + model.getCoinOwned());
        }
        //}
        this.archipelago.clear();
        this.archipelago.addAll(model.getArchipelago());


        this.username.setText("Player: " + client.getUsername());


        disableIslands();


        // oldArchipelago.removeAll(this.archipelago);
        for (IslandCard temp : archipelago) {
            if (temp.getMergedWith().size() > 0) {
                for (IslandCard islandCard : temp.getMergedWith()) {
                    switch (islandCard.getOriginal()) {
                        case 0:
                            this.listIslands.remove(island1);
                            this.islandStudents.remove(island1Students);
                            break;
                        case 1:
                            this.listIslands.remove(island2);
                            this.islandStudents.remove(island2Students);
                            break;
                        case 2:
                            this.listIslands.remove(island3);
                            this.islandStudents.remove(island3Students);
                            break;
                        case 3:
                            this.listIslands.remove(island4);
                            this.islandStudents.remove(island4Students);
                            break;
                        case 4:
                            this.listIslands.remove(island5);
                            this.islandStudents.remove(island5Students);
                            break;
                        case 5:
                            this.listIslands.remove(island6);
                            this.islandStudents.remove(island6Students);
                            break;
                        case 6:
                            this.listIslands.remove(island7);
                            this.islandStudents.remove(island7Students);
                            break;
                        case 7:
                            this.listIslands.remove(island8);
                            this.islandStudents.remove(island8Students);
                            break;
                        case 8:
                            this.listIslands.remove(island9);
                            this.islandStudents.remove(island9Students);
                            break;
                        case 9:
                            this.listIslands.remove(island10);
                            this.islandStudents.remove(island10Students);
                            break;
                        case 10:
                            this.listIslands.remove(island11);
                            this.islandStudents.remove(island11Students);
                            break;
                        case 11:
                            this.listIslands.remove(island12);
                            this.islandStudents.remove(island12Students);
                            break;

                    }
                }
            }
        }

        double scaleX = listIslands.get(0).getScaleX() * 1.4;
        double scaleX2 = scaleX * 1.1;
        int index = 0;
        for (IslandCard temp : archipelago) {
            if (temp.getMergedWith().size() > 0) {
                switch (temp.getMergedWith().size()) {
                    case 1:

                        Image image = new javafx.scene.image.Image("Assets/merged2.png");
                        listIslands.get(index).setImage(image);
                        if (scaleX != listIslands.get(index).getScaleX()) {
                            listIslands.get(index).setScaleX(listIslands.get(index).getScaleX() * 1.4);
                            listIslands.get(index).setScaleY(listIslands.get(index).getScaleY() * 1.4);
                        }

                        break;
                    case 2:
                        Image image2 = new javafx.scene.image.Image("Assets/merged3.png");
                        listIslands.get(index).setImage(image2);
                        if (scaleX != listIslands.get(index).getScaleX()) {
                            listIslands.get(index).setScaleX(listIslands.get(index).getScaleX() * 1.4);
                            listIslands.get(index).setScaleY(listIslands.get(index).getScaleY() * 1.4);
                        }

                        break;
                    case 3:
                        Image image3 = new javafx.scene.image.Image("Assets/merged4.png");
                        listIslands.get(index).setImage(image3);
                        if (scaleX != listIslands.get(index).getScaleX()) {
                            listIslands.get(index).setScaleX(listIslands.get(index).getScaleX() * 1.4);
                            listIslands.get(index).setScaleY(listIslands.get(index).getScaleY() * 1.4);
                        }

                        break;
                    case 4:
                        Image image4 = new javafx.scene.image.Image("Assets/merged5.png");
                        listIslands.get(index).setImage(image4);
                        if (scaleX != listIslands.get(index).getScaleX()) {
                            listIslands.get(index).setScaleX(listIslands.get(index).getScaleX() * 1.4);
                            listIslands.get(index).setScaleY(listIslands.get(index).getScaleY() * 1.4);
                        }
                        break;

                    //TODO 5 6 7;

                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
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

        for (BorderPane studentGrid : islandStudents) {
            studentGrid.setDisable(false);
            studentGrid.setOpacity(1);
        }

        showStudents();

        for (IslandCard islandCard : model.getArchipelago()) {
            if (islandCard.getMotherNature()) {
                if (islandCard.getOriginal() == 0) {
                    motherNature.setLayoutX(island1.getLayoutX() + 42);
                    motherNature.setLayoutY(island1.getLayoutY());
                }
                if (islandCard.getOriginal() == 1) {
                    motherNature.setLayoutX(island2.getLayoutX() + 42);
                    motherNature.setLayoutY(island2.getLayoutY());
                }
                if (islandCard.getOriginal() == 2) {
                    motherNature.setLayoutX(island3.getLayoutX() + 42);
                    motherNature.setLayoutY(island3.getLayoutY());
                }
                if (islandCard.getOriginal() == 3) {
                    motherNature.setLayoutX(island4.getLayoutX() + 42);
                    motherNature.setLayoutY(island4.getLayoutY());
                }
                if (islandCard.getOriginal() == 4) {
                    motherNature.setLayoutX(island5.getLayoutX() + 42);
                    motherNature.setLayoutY(island5.getLayoutY());
                }
                if (islandCard.getOriginal() == 5) {
                    motherNature.setLayoutX(island6.getLayoutX() + 42);
                    motherNature.setLayoutY(island6.getLayoutY());
                }
                if (islandCard.getOriginal() == 6) {
                    motherNature.setLayoutX(island7.getLayoutX() + 42);
                    motherNature.setLayoutY(island7.getLayoutY());
                }
                if (islandCard.getOriginal() == 7) {
                    motherNature.setLayoutX(island8.getLayoutX() + 42);
                    motherNature.setLayoutY(island8.getLayoutY());
                }
                if (islandCard.getOriginal() == 8) {
                    motherNature.setLayoutX(island9.getLayoutX() + 42);
                    motherNature.setLayoutY(island9.getLayoutY());
                }
                if (islandCard.getOriginal() == 9) {
                    motherNature.setLayoutX(island10.getLayoutX() + 42);
                    motherNature.setLayoutY(island10.getLayoutY());
                }
                if (islandCard.getOriginal() == 10) {
                    motherNature.setLayoutX(island11.getLayoutX() + 42);
                    motherNature.setLayoutY(island11.getLayoutY());
                }
                if (islandCard.getOriginal() == 11) {
                    motherNature.setLayoutX(island12.getLayoutX() + 42);
                    motherNature.setLayoutY(island12.getLayoutY());
                }
            }
        }

        ObservableList<Student> entranceStudents = FXCollections.observableArrayList();
        entranceStudents.addAll(model.getSchoolBoard().getEntrance().getStudents());


        int j = 0;
        for(Student student: entranceStudents){
            switch (student.getColor()){
                case RED -> {
                    this.entranceStudents.get(j).setOpacity(1);
                    this.entranceStudents.get(j).setImage(redStudent);
                }
                case GREEN -> {
                    this.entranceStudents.get(j).setOpacity(1);
                    this.entranceStudents.get(j).setImage(greenStudent);
                }
                case BLUE -> {
                    this.entranceStudents.get(j).setOpacity(1);
                    this.entranceStudents.get(j).setImage(blueStudent);
                }
                case PINK -> {
                    this.entranceStudents.get(j).setOpacity(1);
                    this.entranceStudents.get(j).setImage(pinkStudent);
                }
                case YELLOW -> {
                    this.entranceStudents.get(j).setOpacity(1);
                    this.entranceStudents.get(j).setImage(yellowStudent);
                }
            }
            j++;
        }

        for(int l = j; l < 9; l++){
            this.entranceStudents.get(l).setOpacity(0);
        }


        int i = 0;
        for(ColorStudent color: ColorStudent.values()){
            if (model.getSchoolBoard().getProfessor(color)!= null){
                professors.get(i).setOpacity(1);
            } else{
                professors.get(i).setOpacity(0);
            }
            i++;
        }


        ObservableList<Student> reds1 = FXCollections.observableArrayList();
        reds1.addAll(model.getSchoolBoard().getDiningRoom(ColorStudent.RED).getStudents());
        //red.setItems(reds1);

        for(ImageView image: reds){
            image.setOpacity(0);
        }

        for(int size = 0; size < reds1.size(); size++){
            this.reds.get(size).setOpacity(1);
        }

        ObservableList<Student> blues1 = FXCollections.observableArrayList();
        blues1.addAll(model.getSchoolBoard().getDiningRoom(ColorStudent.BLUE).getStudents());
        //blue.setItems(blues1);

        for(ImageView image: blues){
            image.setOpacity(0);
        }

        for(int size = 0; size < blues1.size(); size++){
            this.blues.get(size).setOpacity(1);
        }

        ObservableList<Student> yellows1 = FXCollections.observableArrayList();
        yellows1.addAll(model.getSchoolBoard().getDiningRoom(ColorStudent.YELLOW).getStudents());
        //yellow.setItems(yellows1);

        for(ImageView image: yellows){
            image.setOpacity(0);
        }

        for(int size = 0; size < yellows1.size(); size++){
            this.yellows.get(size).setOpacity(1);
        }

        ObservableList<Student> greens1 = FXCollections.observableArrayList();
        greens1.addAll(model.getSchoolBoard().getDiningRoom(ColorStudent.GREEN).getStudents());
        //green.setItems(greens1);

        for(ImageView image: greens){
            image.setOpacity(0);
        }

        for(int size = 0; size < greens1.size(); size++){
            this.greens.get(size).setOpacity(1);
        }

        ObservableList<Student> pinks1 = FXCollections.observableArrayList();
        pinks1.addAll(model.getSchoolBoard().getDiningRoom(ColorStudent.PINK).getStudents());
        //pink.setItems(pinks1);

        for(ImageView image: pinks){
            image.setOpacity(0);
        }

        for(int size = 0; size < pinks1.size(); size++){
            this.pinks.get(size).setOpacity(1);
        }
        int t = 0;
        if (!model.getSchoolBoard().getTowers().isEmpty() && model.getSchoolBoard().getTowers().get(0).getColor() != null){
            switch(model.getSchoolBoard().getTowers().get(0).getColor()){
                case WHITE -> {
                    for(ImageView tower:towers){
                        tower.setImage(wTower);
                        tower.setOpacity(1);
                        t++;
                    }
                }
                case BLACK -> {
                    for(ImageView tower:towers){
                        tower.setImage(bTower);
                        tower.setOpacity(1);
                        t++;
                    }
                }
                case GREY -> {
                    for(ImageView tower:towers){
                        tower.setImage(gTower);
                        tower.setOpacity(1);
                        t++;
                    }
                }
            }

        }

        for(ImageView tower: this.towers){
            tower.setOpacity(0);
        }
        for(int t1 = 0; t1<this.model.getSchoolBoard().getTowers().size(); t1++){
            towers.get(t1).setOpacity(1);
        }


        towerColor.setText("Tower color: " + model.getSchoolBoard().getTowers().get(0).getColor() + ", remaining: " + model.getSchoolBoard().getTowers().size());


        this.clouds.clear();
        this.clouds.addAll(model.getCloudCardList());
        if (model.getCloudCardList().size() == 2) {
            cloud3.setDisable(true);
            cloud3.setOpacity(0.5);
            cloud3Students.setDisable(true);
            cloud3Students.setOpacity(0);
            this.movesLeft = 3;
            this.moves = 3;
            cloud4.setDisable(true);
            cloud4.setOpacity(0.5);
            cloud4Students.setDisable(true);
            cloud4Students.setOpacity(0);
        }
        if (model.getCloudCardList().size() == 3) {
            this.movesLeft = 4;
            this.moves = 4;
            cloud4.setDisable(true);
            cloud4.setOpacity(0.5);
            cloud4Students.setDisable(true);
            cloud4Students.setOpacity(0);
        }
        if (model.getCloudCardList().size() == 4) {
            this.movesLeft = 3;
            this.moves = 3;
        }


    }

    public synchronized void moveStudents() {
        //inhibits others
        moveMN.setDisable(true);
        confirmCard.setDisable(true);
        confirmStudent.setDisable(true);
        confirmCloud.setDisable(true);
        for(int s = 0; s < model.getSchoolBoard().getEntrance().getStudents().size(); s++){
            entranceView.get(s).setDisable(false);
            entranceView.get(s).setOpacity(1);
        }
        //client.getSendMessage().sendMoveStudentsMessage(new MoveStudentMessage());

    }

    public synchronized void moveMotherNature() {
        //inhibits others
        moveMN.setDisable(false);
        confirmCard.setDisable(true);
        confirmStudent.setDisable(true);
        confirmCloud.setDisable(true);


        //client.getSendMessage().sendMoveMotherNatureMessage(new MoveMotherNatureMessage());

    }

    public synchronized void selectCloudCard() {
        //inhibits others
        moveMN.setDisable(true);
        confirmCard.setDisable(true);
        confirmStudent.setDisable(true);
        confirmCloud.setDisable(false);
        enableclouds();
        //client.getSendMessage().sendCloudCardMessage(new CloudCardChoiceMessage());
    }

    public void disableclouds(){
        cloud1.setDisable(true);
        cloud2.setDisable(true);
        cloud3.setDisable(true);
        cloud4.setDisable(true);
        cloud1Students.setDisable(true);
        cloud2Students.setDisable(true);
        cloud3Students.setDisable(true);
        cloud4Students.setDisable(true);
    }
    public void enableclouds(){
        cloud1.setDisable(false);
        cloud2.setDisable(false);
        cloud3.setDisable(false);
        cloud4.setDisable(false);
        cloud1Students.setDisable(false);
        cloud2Students.setDisable(false);
        cloud3Students.setDisable(false);
        cloud4Students.setDisable(false);
    }

    public synchronized void playAssistant() {
        //inhibits others
        moveMN.setDisable(true);
        confirmCard.setDisable(false);
        confirmStudent.setDisable(true);
        confirmCloud.setDisable(true);
        disableCharacters();
        for(int i = 0; i < 8; i++){
            entranceView.add(schoolPane.getChildren().get(i));
            entranceView.get(i).setEffect(null);
            entranceView.get(i).setDisable(true);
        }
        //client.getSendMessage().sendCloudCardMessage(new CloudCardChoiceMessage());
    }

    public void enableCharacters(){
        character1.setDisable(false);
        character2.setDisable(false);
        character3.setDisable(false);
    }

    public void disableCharacters(){
        character1.setDisable(true);
        character2.setDisable(true);
        character3.setDisable(true);
    }

    public synchronized void confirmCC() {

        if(model.getCloudCardList().get(cloudchoice).getStudents().size() == 0){
            this.SelectedCloud.setText("Empty Cloud!!");
            return;

        }

        client.getSendMessage().sendCloudCardMessage(new CloudCardChoiceMessage(cloudchoice));

        noTurn();
        cc = true;
    }

    /**
     * Control the game by spelling out the action phases.
     * @param client
     */
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

                    isTurn();

                    if (!client.isGameType()) {
                        switch (message) {
                            case TURN:

                                client.getSendMessage().sendModelMessage(new ModelMessage());
                                model = (ModelMessage) client.receiveMessage();

                                disableclouds();

                                Platform.runLater(() -> {
                                    showmodel(client);
                                });

                                MessageInterface receivedMessage1 = client.receiveMessage();

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

                                enableCharacters();

                                MessageType messageType = client.receiveMessage().getCode();

                                client.getSendMessage().sendModelMessage(new ModelMessage());
                                model = (ModelMessage) client.receiveMessage();


                                Platform.runLater(() -> {
                                    showmodel(client);
                                });


                                MessageType messageType1 = client.receiveMessage().getCode();


                                client.getSendMessage().sendAssistantCardsMessage(new AssistantCardsMessage());

                                ReceiveCards(client);

                                playAssistant();

                                isTurn();

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

                                client.getSendMessage().sendModelMessage(new ModelMessage());
                                model = (ModelMessage) client.receiveMessage();

                                Platform.runLater(() -> {
                                    showmodel(client);
                                });

                                MessageInterface receivedMessage1 = client.receiveMessage();

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

                                enableCharacters();

                                MessageType messageType = client.receiveMessage().getCode();

                                client.getSendMessage().sendModelMessage(new ModelMessage());
                                model = (ModelMessage) client.receiveMessage();


                                Platform.runLater(() -> {
                                    showmodel(client);
                                });


                                MessageType messageType1 = client.receiveMessage().getCode();


                                client.getSendMessage().sendAssistantCardsMessage(new AssistantCardsMessage());

                                ReceiveCards(client);

                                playAssistant();

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

    public void enableCards(){
        assistant1.setDisable(false);
        assistant1.setOpacity(1);
        assistant2.setDisable(false);
        assistant2.setOpacity(1);
        assistant3.setDisable(false);
        assistant3.setOpacity(1);
        assistant4.setDisable(false);
        assistant4.setOpacity(1);
        assistant5.setDisable(false);
        assistant5.setOpacity(1);
        assistant6.setDisable(false);
        assistant6.setOpacity(1);
        assistant7.setDisable(false);
        assistant7.setOpacity(1);
        assistant8.setDisable(false);
        assistant8.setOpacity(1);
        assistant9.setDisable(false);
        assistant9.setOpacity(1);
        assistant10.setDisable(false);
        assistant10.setOpacity(1);
    }

    /**
     * Receives the cards from the server, enabling only the ones that can be played.
     * @param client
     */
    public synchronized void ReceiveCards(ClientMain client) {

        //ask the list of cards already played on the table
        this.deck.clear();

        AssistantCardsMessage assistantCardsMessage = (AssistantCardsMessage) client.receiveMessage();
        this.deck.clear();
        this.deck.addAll(assistantCardsMessage.getDeck());

        enableCards();

        for (int j = 0; j < assistantCardsMessage.getChosenCard().size(); j++) {
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

    /**
     * Confirms the AssistantCard selection.
     */
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

        MessageInterface receivedMessage = this.client.receiveMessage();



        if (receivedMessage.getCode() == MessageType.NOERROR) {
            noTurn();
            synchronized (student) {
                student.notifyAll();
            }
            chosen = true;
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

    /**
     * Stops the turn
     */
    public void noTurn() {
        apane.setOpacity(0);
        apane.setDisable(true);
        progressPane.setOpacity(1);
        progressPane.setDisable(true);
    }

    /**
     * Enables the turn.
     */
    public void isTurn() {
        apane.setOpacity(1);
        apane.setDisable(false);
        progressPane.setOpacity(0);
    }

    public void initialize(ClientMain client) {
        this.entranceView = new ArrayList<>();
        this.numIslands = 12;
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

        this.entranceStudents = new ArrayList<>();
        this.reds = new ArrayList<>();
        this.yellows = new ArrayList<>();
        this.blues= new ArrayList<>();
        this.pinks = new ArrayList<>();
        this.greens = new ArrayList<>();
        this.professors = new ArrayList<>();
        this.towers = new ArrayList<>();

        this.islandStudents = new ArrayList<>();
        this.islandStudents.add(island1Students);
        this.islandStudents.add(island2Students);
        this.islandStudents.add(island3Students);
        this.islandStudents.add(island4Students);
        this.islandStudents.add(island5Students);
        this.islandStudents.add(island6Students);
        this.islandStudents.add(island7Students);
        this.islandStudents.add(island8Students);
        this.islandStudents.add(island9Students);
        this.islandStudents.add(island10Students);
        this.islandStudents.add(island11Students);
        this.islandStudents.add(island12Students);

        progressPane.setOpacity(0);

        this.character4 = false;
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
        mnMessage = false;
        steps.setDisable(true);
        disableclouds();
        disableCharacters();


        client.getSendMessage().sendModelMessage(new ModelMessage());
        model = (ModelMessage) receiveMessage();
        showmodel(client);
        client.getSendMessage().sendAssistantCardsMessage(new AssistantCardsMessage());
        ReceiveCards(client);
        playAssistant();
    }

    public synchronized MessageInterface receiveMessage() {
        MessageInterface message = client.getReceiveMessage().receiveMessageClient();
        if(message.getCode() == MessageType.PINGPONG){
            return receiveMessage();
        } else if(message.getCode() == MessageType.WIN){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("End game");
            alert.setContentText(message.getMessage());
            alert.showAndWait();
            System.exit(0);
        } else if(message.getCode() == MessageType.ERROR){
            Platform.runLater(()->{
                Alert alert2 = new Alert(Alert.AlertType.WARNING);
                alert2.setTitle("Connection error.");
                alert2.setHeaderText("Game is ended due to connection problems.");
                alert2.showAndWait();
                System.exit(0);
            });
        } else {
            return message;
        }
        return null;
    }
}
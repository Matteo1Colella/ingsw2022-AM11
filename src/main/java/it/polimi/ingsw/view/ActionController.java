package it.polimi.ingsw.view;

import it.polimi.ingsw.controller.ComplexLobby;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.board.IslandCard;
import it.polimi.ingsw.model.colors.ColorStudent;
import it.polimi.ingsw.model.pieces.MotherNature;
import it.polimi.ingsw.model.pieces.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ActionController{

    private IslandCard selectedIsland;

    @FXML
    private Label To;
    @FXML
    private Label SelectedCharacter;
    @FXML
    private Label SelectedStudent;
    @FXML
    private Label SelectedCloud;



    private ComplexLobby lobby;
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
    private AnchorPane pane;

    public void createExampleLobby(){
        ArrayList<Player> players = new ArrayList<>();
        this.lobby = new ComplexLobby(2, false, 0, players);
        this.lobby.AddPlayer("Cole");
        this.lobby.AddPlayer("Leo");
        this.lobby.createGame(2, 0, false);
        Game game = this.lobby.getGame();

        ObservableList<Student> reds = FXCollections.observableArrayList();
        reds.addAll(this.lobby.getPlayers().get(0).getSchoolBoard().getDiningRoom(ColorStudent.RED).getStudents());
        red.setItems(reds);

        ObservableList<Student> blues = FXCollections.observableArrayList();
        blues.addAll(this.lobby.getPlayers().get(0).getSchoolBoard().getDiningRoom(ColorStudent.BLUE).getStudents());
        blue.setItems(blues);

        ObservableList<Student> yellows = FXCollections.observableArrayList();
        yellows.addAll(this.lobby.getPlayers().get(0).getSchoolBoard().getDiningRoom(ColorStudent.YELLOW).getStudents());
        yellow.setItems(yellows);

        ObservableList<Student> greens = FXCollections.observableArrayList();
        greens.addAll(this.lobby.getPlayers().get(0).getSchoolBoard().getDiningRoom(ColorStudent.GREEN).getStudents());
        green.setItems(greens);

        ObservableList<Student> pinks = FXCollections.observableArrayList();
        pinks.addAll(this.lobby.getPlayers().get(0).getSchoolBoard().getDiningRoom(ColorStudent.PINK).getStudents());
        pink.setItems(pinks);

        ObservableList<Student> entrancelist = FXCollections.observableArrayList();
        entrancelist.addAll(this.lobby.getPlayers().get(0).getSchoolBoard().getEntrance().getStudents());
        entrance.setItems(entrancelist);
    }

    public void bind(Stage stage, Scene scene){
        pane.prefHeightProperty().bind(stage.heightProperty());
        pane.prefWidthProperty().bind(stage.heightProperty());
    }

    public void clickMoveMN(){
        Game game = this.lobby.getGame();
        MotherNature mn = game.getGameComponents().getMotherNature();
        ArrayList<IslandCard> archipelago = game.getGameComponents().getArchipelago();
        int steps = Integer.parseInt(this.steps.getText());
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

    }

    public void clickonIsland1(){
        Game game = this.lobby.getGame();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Island Content:");
        alert.setContentText(game.getGameComponents().getArchipelago().get(0).toString());
        alert.show();
        selectedIsland = game.getGameComponents().getArchipelago().get(0);
        To.setText("To: Island " + selectedIsland.getId_island());
    }
    public void clickonIsland2(){
        Game game = this.lobby.getGame();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Island Content:");
        alert.setContentText(game.getGameComponents().getArchipelago().get(1).toString());
        alert.show();
        selectedIsland = game.getGameComponents().getArchipelago().get(1);
        To.setText("To: Island " + selectedIsland.getId_island());
    }
    public void clickonIsland3(){
        Game game = this.lobby.getGame();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Island Content:");
        alert.setContentText(game.getGameComponents().getArchipelago().get(2).toString());
        alert.show();
        selectedIsland = game.getGameComponents().getArchipelago().get(2);
        To.setText("To: Island " + selectedIsland.getId_island());
    }
    public void clickonIsland4(){
        Game game = this.lobby.getGame();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Island Content:");
        alert.setContentText(game.getGameComponents().getArchipelago().get(3).toString());
        alert.show();
        selectedIsland = game.getGameComponents().getArchipelago().get(3);
        To.setText("To: Island " + selectedIsland.getId_island());
    }
    public void clickonIsland5(){
        Game game = this.lobby.getGame();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Island Content:");
        alert.setContentText(game.getGameComponents().getArchipelago().get(4).toString());
        alert.show();
        selectedIsland = game.getGameComponents().getArchipelago().get(4);
        To.setText("To: Island " + selectedIsland.getId_island());
    }
    public void clickonIsland6(){
        Game game = this.lobby.getGame();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Island Content:");
        alert.setContentText(game.getGameComponents().getArchipelago().get(5).toString());
        alert.show();
        selectedIsland = game.getGameComponents().getArchipelago().get(5);
        To.setText("To: Island " + selectedIsland.getId_island());
    }
    public void clickonIsland7(){
        Game game = this.lobby.getGame();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Island Content:");
        alert.setContentText(game.getGameComponents().getArchipelago().get(6).toString());
        alert.show();
        selectedIsland = game.getGameComponents().getArchipelago().get(6);
        To.setText("To: Island " + selectedIsland.getId_island());
    }
    public void clickonIsland8(){
        Game game = this.lobby.getGame();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Island Content:");
        alert.setContentText(game.getGameComponents().getArchipelago().get(7).toString());
        alert.show();
        selectedIsland = game.getGameComponents().getArchipelago().get(7);
        To.setText("To: Island " + selectedIsland.getId_island());
    }
    public void clickonIsland9(){
        Game game = this.lobby.getGame();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Island Content:");
        alert.setContentText(game.getGameComponents().getArchipelago().get(8).toString());
        alert.show();
        selectedIsland = game.getGameComponents().getArchipelago().get(8);
        To.setText("To: Island " + selectedIsland.getId_island());
    }
    public void clickonIsland10(){
        Game game = this.lobby.getGame();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Island Content:");
        alert.setContentText(game.getGameComponents().getArchipelago().get(9).toString());
        alert.show();
        selectedIsland = game.getGameComponents().getArchipelago().get(9);
        To.setText("To: Island " + selectedIsland.getId_island());
    }
    public void clickonIsland11(){
        Game game = this.lobby.getGame();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Island Content:");
        alert.setContentText(game.getGameComponents().getArchipelago().get(10).toString());
        alert.show();
        selectedIsland = game.getGameComponents().getArchipelago().get(10);
        To.setText("To: Island " + selectedIsland.getId_island());
    }
    public void clickonIsland12(){
        Game game = this.lobby.getGame();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Island Content:");
        alert.setContentText(game.getGameComponents().getArchipelago().get(11).toString());
        alert.show();
        selectedIsland = game.getGameComponents().getArchipelago().get(11);
        To.setText("To: Island " + selectedIsland.getId_island());
    }

}

package it.polimi.ingsw.view;

import it.polimi.ingsw.communication.client.ClientMain;
import it.polimi.ingsw.communication.common.*;
import it.polimi.ingsw.communication.common.messages.LobbiesMessage;
import it.polimi.ingsw.communication.common.messages.LoginMessage;
import it.polimi.ingsw.communication.common.messages.MageMessage;
import it.polimi.ingsw.controller.ComplexLobby;
import it.polimi.ingsw.controller.GameManager;
import it.polimi.ingsw.model.Player;
//import it.polimi.ingsw.view.stages.LoginStage;
import it.polimi.ingsw.view.stages.MageStageSocket;
import it.polimi.ingsw.view.stages.Magestage;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class LoginController implements Initializable{
    private final GameManager gameManager = new GameManager();
    public Label l;
    public Label loading;
    public ImageView background2;
    public Label id;
    public Label gt1;
    public Label nop1;
    public Label lob;
    private int n;
    private String ID;
    private int type;
    private int i = 0;
    private boolean boolType;
    private ToggleGroup toggleGroup;
    private Stage stage;


    private ClientMain client;
    @FXML
    private AnchorPane anchorPane;




    @FXML
    private StackPane stackPane;

    @FXML
    private ProgressBar progressBar;
    @FXML
    private ImageView background;
    @FXML
    private CheckBox pro;
    @FXML
    private TextField nameField;
    @FXML
    private Label welcomeText;
    @FXML
    private ListView<String> Players;
    @FXML
    private ListView<String> Lobbies;
    @FXML
    private Label lobbymembertext;
    @FXML
    private Button login;

    public Button getLogin() {
        return login;
    }

    @FXML
    private RadioButton twoP;
    @FXML
    private RadioButton threeP;
    @FXML
    private RadioButton fourP;


    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setClient(ClientMain client) {
        this.client = client;
    }

    public void setToggleGroup() {
        this.toggleGroup = new ToggleGroup();
        twoP.setToggleGroup(toggleGroup);
        threeP.setToggleGroup(toggleGroup);
        fourP.setToggleGroup(toggleGroup);
    }



    @FXML
    public void loginSocket() throws IOException, InterruptedException {
        boolean set = false;

        if(toggleGroup.getSelectedToggle().equals(twoP)){
            n = 2;
            set = true;
        } else {
            if(toggleGroup.getSelectedToggle().equals(threeP)){
                n = 3;
                set = true;
            }
            if(toggleGroup.getSelectedToggle().equals(fourP)){
                n = 4;
                set = true;
            }
        }
        if (!set){
            n = 0;
        }

        client.setUsername(nameField.getText());

        client.getSendMessage().sendLoginMessage(new LoginMessage(nameField.getText().replaceAll("\\s+",""), n, pro.isSelected()));

        MessageInterface message = client.receiveMessage();
        if(message.getCode() == MessageType.LOGINERROR){
            welcomeText.setText("Something gone wrong, please retry.\r");
        } else if(message.getCode() == MessageType.NOERROR) {
            LobbiesMessage lobbiesMessage = (LobbiesMessage) client.receiveMessage();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Successful Login");
            alert.setContentText("You are in lobby "+ lobbiesMessage.getIdLobby() + ", waiting for players..." );
            alert.show();

            Service<Void> service = new Service<Void>() {
                @Override
                protected Task<Void> createTask() {
                    return new Task<Void>() {
                        @Override
                        protected Void call() throws Exception {

                            client.getSendMessage().sendMageMessage(new MageMessage());
                            MageMessage mageMessage = (MageMessage) client.receiveMessage();
                            final CountDownLatch latch = new CountDownLatch(1);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    try{

                                        try {
                                            new MageStageSocket(client, mageMessage);
                                            stage.close();
                                        } catch (IOException | InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                    }finally{
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

    @FXML
    public void Login() throws IOException {
        ID = nameField.getText();

        boolean set = false;

        if(toggleGroup.getSelectedToggle().equals(twoP)){
            n = 2;
            set = true;
        } else {
            if(toggleGroup.getSelectedToggle().equals(threeP)){
                n = 3;
                set = true;
            }
            if(toggleGroup.getSelectedToggle().equals(fourP)){
                n = 4;
                set = true;
            }
        }
        if (!set){
            n = 0;
        }



        pro.setAllowIndeterminate(false);
        boolType = pro.isSelected();



        ObservableList<String> doList = FXCollections.observableArrayList();
        ObservableList<String> Lobbieslist = FXCollections.observableArrayList();


        boolean login = gameManager.login(ID,n,boolType);
        if(login){
            welcomeText.setText("Successfully logged in lobby " + gameManager.getPlayerComplexLobby(ID).getID());
            lobbymembertext.setText("Players in lobby " + gameManager.getPlayerComplexLobby(ID).getID());

            doList.clear();
            Lobbieslist.clear();
            Players.getItems().clear();
            Lobbies.getItems().clear();

            for(Player p : gameManager.getPlayerComplexLobby(ID).getPlayers()){
                doList.add(p.getID_player());
            }
            for (ComplexLobby c : gameManager.getComplexLobbies()){
                Lobbieslist.add(c.toString());
            }

            Lobbies.getItems().addAll(Lobbieslist);
            Players.getItems().addAll(doList);
            EventType<? extends ListView.EditEvent<String>> type = ListView.editAnyEvent();
            Event event = new ListView.EditEvent<>(Players, type, ID, i);
            Players.fireEvent(event);
            Lobbies.fireEvent(event);
            i++;
            new Magestage();
        } else{
            welcomeText.setText("Login Error, Try Again");
        }


    }
    public void ListClick(){

        /*

        int index = Lobbies.getSelectionModel().getSelectedIndex();
        ObservableList<String> doList = FXCollections.observableArrayList();
        Players.getItems().clear();
        for(Player p : gameManager.getComplexLobbies().get(index).getPlayers()){
            doList.add(p.getID_player());
        }
        Players.getItems().addAll(doList);
        lobbymembertext.setText("Players in lobby " + gameManager.getComplexLobbies().get(index).getID());
        EventType<? extends ListView.EditEvent<String>> type = ListView.editAnyEvent();
        Event event = new ListView.EditEvent<>(Players, type, ID, i);
        Players.fireEvent(event);
        i++;

         */
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.setToggleGroup();
        anchorPane.setOpacity(0);
        stackPane.setOpacity(0);

        stackPane.setOpacity(1);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(progressBar.progressProperty(), 0)),
                new KeyFrame(Duration.seconds(2), e-> {

                    FadeTransition fade1 = new FadeTransition();
                    FadeTransition fade = new FadeTransition();

                    fade1.setDuration(Duration.millis(1000));
                    fade1.setFromValue(1);
                    fade1.setToValue(0);

                    fade.setDuration(Duration.millis(1000));
                    fade.setFromValue(0);
                    fade.setToValue(1);

                    fade.setCycleCount(1);
                    fade1.setCycleCount(1);

                    fade.setNode(anchorPane);
                    fade1.setNode(stackPane);

                    fade.play();
                    fade1.play();
                    loading.setText("Connected to Server");

                }, new KeyValue(progressBar.progressProperty(), 1))
        );
        timeline.setCycleCount(1);
        timeline.play();
    }
}

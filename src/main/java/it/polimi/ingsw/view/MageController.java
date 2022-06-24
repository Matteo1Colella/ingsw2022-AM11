package it.polimi.ingsw.view;

import it.polimi.ingsw.communication.client.ClientMain;
import it.polimi.ingsw.communication.common.MessageInterface;
import it.polimi.ingsw.communication.common.MessageType;
import it.polimi.ingsw.communication.common.messages.MageMessage;
import it.polimi.ingsw.model.Mage;
import it.polimi.ingsw.view.stages.ActionStage;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

public class MageController {
    private ArrayList<Mage> avaiableMages;
    private ClientMain client;
    private Stage stage;
    private Mage selectedmage;
    @FXML
    private ImageView CR7;
    @FXML
    private ImageView mage1;
    @FXML
    private ImageView mage2;
    @FXML
    private ImageView mage3;
    @FXML
    private ImageView mage4;

    private MediaPlayer player;
    private MediaPlayer cr7player;
    private ArrayList<Node> items;

    @FXML
    private AnchorPane loadingPane;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label result;

    public MediaPlayer getPlayer() {
        return player;
    }

    public void setPlayer(MediaPlayer player) {
        this.player = player;
    }

    public void setClient(ClientMain client) {
        this.client = client;
        items = new ArrayList<>(pane.getChildren());


    }

    public void showCR7() {
        if (CR7.getOpacity() == 0){
            CR7.setOpacity(1);
            player.pause();
            cr7player.play();
        } else {
            CR7.setOpacity(0);
            player.play();
            cr7player.pause();
        }

    }
    public void setStage(Stage stage) {
        this.stage = stage;
        Media pick = new Media(Objects.requireNonNull(getClass().getClassLoader()
                .getResource("music/tista.mp3")).toExternalForm());
        this.cr7player = new MediaPlayer(pick);
        this.cr7player.setCycleCount(MediaPlayer.INDEFINITE);
        this.cr7player.setVolume(25);
        this.cr7player.setOnEndOfMedia(() -> {
            this.cr7player.seek(Duration.ZERO);
        });
    }

    @FXML
    private Label textMage;
    @FXML
    public void clickMage1(){
        selectedmage = Mage.MAGE1;
        textMage.setText("You selected SUMMER");
    }
    @FXML
    public void clickMage2(){
        selectedmage = Mage.MAGE2;
        textMage.setText("You selected WINTER");
    }
    @FXML
    public void clickMage3(){
        selectedmage = Mage.MAGE3;
        textMage.setText("You selected SPRING");
    }
    @FXML
    public void clickMage4(){
        selectedmage = Mage.MAGE4;
        textMage.setText("You selected AUTUMN");
    }
    @FXML
    public void setDisabled1(){
        mage1.setDisable(true);
        mage1.setOpacity(0.5);
    }
    @FXML
    public void setDisabled2(){
        mage2.setDisable(true);
        mage2.setOpacity(0.5);
    }
    @FXML
    public void setDisabled3(){
        mage3.setDisable(true);
        mage3.setOpacity(0.5);
    }
    @FXML
    public void setDisabled4(){
        mage4.setDisable(true);
        mage4.setOpacity(0.5);
    }

    @FXML
    public void dragOnMage1(){
        mage1.setOpacity(0);
        items.get(6).setOpacity(1);
    }
    @FXML
    public void dragOnMage2(){
        mage2.setOpacity(0);
        items.get(8).setOpacity(1);
    }
    @FXML
    public void dragOnMage3(){
        mage3.setOpacity(0);
        items.get(5).setOpacity(1);
    }
    @FXML
    public void dragOnMage4(){
        mage4.setOpacity(0);
        items.get(7).setOpacity(1);
    }
    @FXML
    public void dragOnMage1out(){
        mage1.setOpacity(1);
        items.get(6).setOpacity(0);
    }
    @FXML
    public void dragOnMage2out(){
        mage2.setOpacity(1);
        items.get(8).setOpacity(0);
    }
    @FXML
    public void dragOnMage3out(){
        mage3.setOpacity(1);
        items.get(5).setOpacity(0);
    }
    @FXML
    public void dragOnMage4out(){
        mage4.setOpacity(1);
        items.get(7).setOpacity(0);
    }

    @FXML
    public void setEnabled1(){
        mage1.setDisable(false);
        mage1.setOpacity(1);
    }
    @FXML
    public void setEnabled2(){
        mage2.setDisable(false);
        mage2.setOpacity(1);
    }
    @FXML
    public void setEnabled3(){
        mage3.setDisable(false);
        mage3.setOpacity(1);
    }
    @FXML
    public void setEnabled4(){
        mage4.setDisable(false);
        mage4.setOpacity(1);
    }


    @FXML
    public synchronized void confirmMageSocket() throws IOException {

        int mage = 0;

        switch (selectedmage) {
            case MAGE1:
                mage = 1;
                break;
            case MAGE2:
                mage = 2;
                break;
            case MAGE3:
                mage = 3;
                break;
            case MAGE4:
                mage = 4;
                break;
        }

        if (mage == 0) return;
        int finalMage = mage;

        Service<Void> service1 = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {

                        for(int i = 1; i <= 15; i++){
                            items.get(i).setOpacity(0.5);
                            items.get(i).setDisable(true);
                        }

                        loadingPane.setOpacity(1);

                        final CountDownLatch latch = new CountDownLatch(1);
                        Platform.runLater(() -> {


                        });

                        latch.await();
                        //Keep with the background work
                        return null;
                    }
                };
            }
        };
        service1.start();

        CR7.setOpacity(0);

        Service<Void> service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {

                        client.getSendMessage().sendMageMessage(new MageMessage(finalMage));

                        CR7.setOpacity(0);

                        MessageInterface receivedMessage = client.receiveMessage();

                        for(int i = 1; i <= 11; i++){
                            items.get(i).setOpacity(0.2);
                            items.get(i).setDisable(true);
                        }
                        CR7.setOpacity(0);

                        if (receivedMessage.getCode() != MessageType.MAGEERROR){
                            loadingPane.setOpacity(1);
                        }


                        final CountDownLatch latch = new CountDownLatch(1);
                        Platform.runLater(() -> {



                            if (receivedMessage.getCode() == MessageType.MAGEERROR){
                                result.setText("Error, try again");
                                return;
                            }


                            try {
                                new ActionStage(client);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            stage.close();
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
    public void setAvaiableMages(ArrayList<Mage> mages){
        this.avaiableMages = mages;
    }
}
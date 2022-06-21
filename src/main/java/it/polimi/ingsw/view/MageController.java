package it.polimi.ingsw.view;

import it.polimi.ingsw.communication.client.ClientMain;
import it.polimi.ingsw.communication.common.MessageInterface;
import it.polimi.ingsw.communication.common.MessageType;
import it.polimi.ingsw.communication.common.messages.MageMessage;
import it.polimi.ingsw.model.Mage;
import it.polimi.ingsw.view.stages.ActionStage;
import it.polimi.ingsw.view.stages.MageStageSocket;
import it.polimi.ingsw.view.stages.PlanningStage;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class MageController {
    private ArrayList<Mage> avaiableMages;
    private ClientMain client;
    private Stage stage;
    private Mage selectedmage;
    @FXML
    private ImageView mage1;
    @FXML
    private ImageView mage2;
    @FXML
    private ImageView mage3;
    @FXML
    private ImageView mage4;

    private ArrayList<Node> items;

    @FXML
    private AnchorPane loadingPane;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label result;

    public void setClient(ClientMain client) {
        this.client = client;
        items = new ArrayList<>(pane.getChildren());
    }

    public void setStage(Stage stage) {
        this.stage = stage;
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
    public void confirmMage() throws IOException {
        new PlanningStage();
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

        int finalMage = mage;

        Service<Void> service1 = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {

                        for(int i = 1; i <= 11; i++){
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

        Service<Void> service = new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {

                        client.getSendMessage().sendMageMessage(new MageMessage(finalMage));

                        MessageInterface receivedMessage = client.receiveMessage();

                        for(int i = 1; i <= 11; i++){
                            items.get(i).setOpacity(0.2);
                            items.get(i).setDisable(true);
                        }

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

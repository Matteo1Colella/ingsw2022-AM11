package it.polimi.ingsw.view;

import it.polimi.ingsw.communication.client.ClientMain;
import it.polimi.ingsw.communication.common.MessageInterface;
import it.polimi.ingsw.communication.common.MessageType;
import it.polimi.ingsw.communication.common.messages.MageMessage;
import it.polimi.ingsw.model.Mage;
import it.polimi.ingsw.view.stages.PlanningStage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MageController {
    private ArrayList<Mage> avaiableMages;
    private ClientMain client;
    private Mage selectedmage;
    @FXML
    private ImageView mage1;
    @FXML
    private ImageView mage2;
    @FXML
    private ImageView mage3;
    @FXML
    private ImageView mage4;

    public void setClient(ClientMain client) {
        this.client = client;
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
    public void confirmMageSocket() throws IOException {



    }
    public void setAvaiableMages(ArrayList<Mage> mages){
        this.avaiableMages = mages;
    }
}

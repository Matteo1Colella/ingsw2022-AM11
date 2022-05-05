package it.polimi.ingsw.view.stages;

import it.polimi.ingsw.view.ClientApp;
import it.polimi.ingsw.view.MageController;
import it.polimi.ingsw.model.Mage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Magestage extends Stage {
    public Magestage() throws IOException {
        ArrayList<Mage> mages = new ArrayList<>();
        mages.add(Mage.MAGE1);
        mages.add(Mage.MAGE2);
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApp.class.getResource("/MagePhase.fxml"));
        Stage subStage = new Stage () ;
        subStage.setTitle ("Mage Selection") ;
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        subStage.setScene (scene) ;
        MageController controller = fxmlLoader.getController();
        controller.setAvaiableMages(mages);
        if(!mages.contains(Mage.MAGE1)){
            controller.setDisabled1();
        }
        if(!mages.contains(Mage.MAGE2)){
            controller.setDisabled2();
        }
        if(!mages.contains(Mage.MAGE3)){
            controller.setDisabled3();
        }
        if(!mages.contains(Mage.MAGE4)){
            controller.setDisabled4();
        }
        subStage.setResizable(false);
        subStage.setTitle("Choose Mage");
        subStage.setScene(scene);
        subStage.show();
        subStage.show();
    }


}

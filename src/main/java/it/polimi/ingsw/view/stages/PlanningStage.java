package it.polimi.ingsw.view.stages;

import it.polimi.ingsw.view.ClientApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PlanningStage extends Stage {
    public PlanningStage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApp.class.getResource("/PlanningPhase.fxml"));
        Stage subStage = new Stage () ;
        Scene scene = new Scene(fxmlLoader.load(), 750, 500);
        subStage.setResizable(false);
        subStage.setTitle("Planning Phase");
        subStage.setScene (scene) ;
        subStage.show();
    }

}

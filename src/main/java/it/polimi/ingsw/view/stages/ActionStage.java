package it.polimi.ingsw.view.stages;

import it.polimi.ingsw.view.ActionController;
import it.polimi.ingsw.view.ClientApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ActionStage extends Stage {
    public ActionStage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApp.class.getResource("/ActionPhase.fxml"));
        Stage subStage = new Stage () ;
        Scene scene = new Scene(fxmlLoader.load(), 1395, 765);
        subStage.setScene (scene) ;
        ActionController controller = fxmlLoader.getController();
        controller.createExampleLobby();
        //subStage.setResizable(false);
        subStage.setTitle("Action Phase");

        subStage.show();
    }

}

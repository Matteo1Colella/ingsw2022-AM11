package it.polimi.ingsw.view.stages;

import it.polimi.ingsw.view.ActionController;
import it.polimi.ingsw.view.ClientApp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class ActionStage extends Stage {
    public ActionStage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApp.class.getResource("/ActionPhase.fxml"));
        Stage subStage = new Stage () ;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        subStage.setScene (scene) ;
        ActionController controller = fxmlLoader.getController();
        controller.createExampleLobby();
        controller.bind(subStage, scene);
        //subStage.setResizable(false);
        subStage.setTitle("Action Phase");

        subStage.show();
    }

}

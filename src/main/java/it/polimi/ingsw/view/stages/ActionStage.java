package it.polimi.ingsw.view.stages;

import it.polimi.ingsw.communication.client.ClientMain;
import it.polimi.ingsw.view.ActionController;
import it.polimi.ingsw.view.ClientApp;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


import java.io.IOException;

public class ActionStage extends Stage {
    public ActionStage(ClientMain client) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApp.class.getResource("/FXML/ActionPhase.fxml"));
        Stage subStage = new Stage () ;
        Scene scene = new Scene(fxmlLoader.load(), 1280, 750);
        subStage.setScene (scene) ;
        ActionController controller = fxmlLoader.getController();
        controller.initialize(client);
        controller.bind(subStage, scene);
        controller.setStage(subStage);
        subStage.setResizable(true);
        subStage.setMinHeight(560);
        subStage.setMinWidth(960);
        subStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(1);
            }
        });
        scene.setOnKeyPressed(t -> {
            KeyCode key = t.getCode();
            if (key == KeyCode.ESCAPE){
                subStage.setFullScreen(false);
            }
        });
        subStage.getIcons().add(new Image("Assets/MN.png"));
        subStage.setTitle("Action Phase");
        subStage.show();

    }

}
package it.polimi.ingsw.view.stages;

import it.polimi.ingsw.communication.client.ClientMain;
import it.polimi.ingsw.view.ActionController;
import it.polimi.ingsw.view.ClientApp;
import it.polimi.ingsw.view.viewThread;
import it.polimi.ingsw.view.viewThread1;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


import java.awt.*;
import java.io.IOException;

public class ActionStage extends Stage {
    public ActionStage(ClientMain client) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApp.class.getResource("/ActionPhase.fxml"));
        Stage subStage = new Stage () ;
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();
        Scene scene = new Scene(fxmlLoader.load(), width, height);
       // Scene scene = new Scene(fxmlLoader.load(), 1280, 750);
        subStage.setScene (scene) ;
        ActionController controller = fxmlLoader.getController();
        controller.initialize(client);
        controller.bind(subStage, scene);
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
        subStage.setTitle("Action Phase");
        subStage.show();

    }

}

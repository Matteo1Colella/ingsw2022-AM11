package it.polimi.ingsw.view.stages;

import it.polimi.ingsw.communication.client.ClientMain;
import it.polimi.ingsw.view.ClientApp;
import it.polimi.ingsw.view.LoginController;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;


public class LoginStage extends Stage {

    public LoginStage(ClientMain client, MediaPlayer player) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApp.class.getResource("/LoginPhase.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage subStage = new Stage () ;
        subStage.setTitle ("Login") ;
        LoginController controller = fxmlLoader.getController();
        subStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(1);
            }
        });
        controller.setPlayer(player);
        controller.setStage(subStage);
        controller.setToggleGroup();
        controller.setClient(client);
        subStage.setResizable(false);
        subStage.setScene(scene);
        subStage.show();
    }
}

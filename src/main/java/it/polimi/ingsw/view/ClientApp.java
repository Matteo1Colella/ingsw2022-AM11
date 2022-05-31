package it.polimi.ingsw.view;

import it.polimi.ingsw.communication.client.ClientMain;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(ClientApp.class.getResource("/ConnectionPhase.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        ConnectionController controller = fxmlLoader.getController();
        //controller.setToggleGroup();
        controller.setStage(primaryStage);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Create Connection");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

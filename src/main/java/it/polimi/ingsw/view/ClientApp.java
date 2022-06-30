package it.polimi.ingsw.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(ClientApp.class.getResource("/FXML/ConnectionPhase.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        ConnectionController controller = fxmlLoader.getController();
        //controller.setToggleGroup();
        controller.setStage(primaryStage);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Create Connection");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("Assets/MN.png"));
        primaryStage.show();
    }
}
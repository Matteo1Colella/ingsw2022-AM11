package it.polimi.ingsw.view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class ClientApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Stats the ConnectionFase.
     * @param primaryStage
     * @throws IOException
     */
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
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(1);
            }
        });
        primaryStage.show();
    }
}
package it.polimi.ingsw.view.stages;

import it.polimi.ingsw.communication.client.ClientMain;
import it.polimi.ingsw.communication.common.messages.ModelMessage;
import it.polimi.ingsw.view.ClientApp;
import it.polimi.ingsw.view.ShowSchoolBoardsController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;

public class ShowSchoolBoardsStage {
    public ShowSchoolBoardsStage(ClientMain client, ModelMessage model) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApp.class.getResource("/FXML/SchoolBoards.fxml"));
        Stage subStage = new Stage () ;
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        subStage.setScene (scene) ;
        ShowSchoolBoardsController controller = fxmlLoader.getController();
        controller.setClientMain(client);
        controller.setStage(subStage);
        controller.initialize(model);
        subStage.setMinHeight(400);
        subStage.setMinWidth(600);
        scene.setOnKeyPressed(t -> {
            KeyCode key = t.getCode();
            if (key == KeyCode.ESCAPE){
                subStage.setFullScreen(false);
            }
        });
        subStage.getIcons().add(new Image("Assets/MN.png"));
        subStage.setResizable(false);
        subStage.setTitle("SchoolBoards");
        subStage.show();
        subStage.setFullScreen(false);
        subStage.setWidth(600);
        subStage.setHeight(400);
        subStage.setResizable(false);
    }
}
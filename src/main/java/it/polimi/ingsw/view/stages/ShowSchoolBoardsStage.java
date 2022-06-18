package it.polimi.ingsw.view.stages;

import it.polimi.ingsw.communication.client.ClientMain;
import it.polimi.ingsw.communication.common.messages.ModelMessage;
import it.polimi.ingsw.view.ActionController;
import it.polimi.ingsw.view.ClientApp;
import it.polimi.ingsw.view.ShowSchoolBoardsController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;

public class ShowSchoolBoardsStage {
    public ShowSchoolBoardsStage(ClientMain client, ModelMessage model) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApp.class.getResource("/SchoolBoards.fxml"));
        Stage subStage = new Stage () ;
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        subStage.setScene (scene) ;
        ShowSchoolBoardsController controller = fxmlLoader.getController();
        controller.setClientMain(client);
        controller.initialize(model);
        subStage.setResizable(true);
        subStage.setMinHeight(400);
        subStage.setMinWidth(600);
        scene.setOnKeyPressed(t -> {
            KeyCode key = t.getCode();
            if (key == KeyCode.ESCAPE){
                subStage.setFullScreen(false);
            }
        });
        subStage.setTitle("SchoolBoards");
        subStage.show();
    }
}

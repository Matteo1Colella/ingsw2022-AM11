package it.polimi.ingsw.view.stages;

import it.polimi.ingsw.communication.client.ClientMain;
import it.polimi.ingsw.view.ActionController;
import it.polimi.ingsw.view.ClientApp;
import it.polimi.ingsw.view.viewThread;
import it.polimi.ingsw.view.viewThread1;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;


import java.io.IOException;

public class ActionStage extends Stage {
    public ActionStage(ClientMain client) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApp.class.getResource("/ActionPhase.fxml"));
        Stage subStage = new Stage () ;
        Scene scene = new Scene(fxmlLoader.load(), 1280, 750);
        subStage.setScene (scene) ;
        ActionController controller = fxmlLoader.getController();
        controller.initialize(client);
        controller.bind(subStage, scene);
        subStage.setResizable(true);
        subStage.setMinHeight(560);
        subStage.setMinWidth(960);
        scene.setOnKeyPressed(t -> {
            KeyCode key = t.getCode();
            if (key == KeyCode.ESCAPE){
                subStage.setFullScreen(false);
            }
        });
        subStage.setTitle("Action Phase");
        subStage.show();

       //viewThread1 thread = new viewThread1(controller);
     //thread.start();
        /*
        System.out.println("1");
        controller.showmodel(client);
        System.out.println("2");
        controller.ReceiveCards(client);
        System.out.println("3");

         */
        //controller.mainClient(client);
    }

}

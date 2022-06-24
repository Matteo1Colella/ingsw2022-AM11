package it.polimi.ingsw.view.stages;

import it.polimi.ingsw.communication.client.ClientMain;
import it.polimi.ingsw.communication.common.MessageInterface;
import it.polimi.ingsw.communication.common.MessageType;
import it.polimi.ingsw.communication.common.messages.MageMessage;
import it.polimi.ingsw.model.Mage;
import it.polimi.ingsw.view.ClientApp;
import it.polimi.ingsw.view.MageController;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MageStageSocket extends Stage {



    public MageStageSocket(ClientMain client, MageMessage mageMessage, MediaPlayer player) throws IOException, InterruptedException {

        boolean one = false, two = false, three = false, four = false;

        FXMLLoader fxmlLoader = new FXMLLoader(ClientApp.class.getResource("/MagePhase.fxml"));
        Stage subStage = new Stage () ;
        subStage.setTitle ("Mage Selection") ;
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        subStage.setScene (scene) ;
        MageController controller = fxmlLoader.getController();
        subStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(1);
            }
        });
        scene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            final KeyCombination keyComb = new KeyCharacterCombination("7",
                    KeyCombination.CONTROL_DOWN);
            public void handle(KeyEvent ke) {
                if (keyComb.match(ke)) {
                        controller.showCR7();
                    ke.consume(); // <-- stops passing the event to next node
                }
            }
        });
        controller.setPlayer(player);
        controller.setClient(client);
        controller.setStage(subStage);

        /*
        client.getSendMessage().sendMageMessage(new MageMessage());
        MageMessage mageMessage = (MageMessage) client.receiveMessage();

         */

        for (int i = 0; i < mageMessage.getAviableMage().length; i++) {
            switch (mageMessage.getAviableMage()[i]){
                case 1:
                    one = true;
                    break;
                case 2:
                    two = true;
                    break;
                case 3:
                    three = true;
                    break;
                case 4:
                    four = true;
                    break;
            }
        }

        if(!one){
            controller.setDisabled1();
        }
        if(!two){
            controller.setDisabled2();
        }
        if(!three){
            controller.setDisabled3();
        }
        if(!four){
            controller.setDisabled4();
        }



        subStage.show();

    }

}

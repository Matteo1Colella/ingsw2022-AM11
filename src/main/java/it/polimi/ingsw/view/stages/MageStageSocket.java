package it.polimi.ingsw.view.stages;

import it.polimi.ingsw.communication.client.ClientMain;
import it.polimi.ingsw.communication.common.messages.MageMessage;
import it.polimi.ingsw.model.Mage;
import it.polimi.ingsw.view.ClientApp;
import it.polimi.ingsw.view.MageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class MageStageSocket extends Stage {
    public MageStageSocket(ClientMain client) throws IOException {
        ArrayList<Mage> mages = new ArrayList<>();
        mages.add(Mage.MAGE1);
        mages.add(Mage.MAGE2);
        FXMLLoader fxmlLoader = new FXMLLoader(ClientApp.class.getResource("/MagePhase.fxml"));
        Stage subStage = new Stage () ;
        subStage.setTitle ("Mage Selection") ;
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        subStage.setScene (scene) ;
        MageController controller = fxmlLoader.getController();
        controller.setAvaiableMages(mages);
        client.getSendMessage().sendMageMessage(new MageMessage());
        MageMessage mageMessage = (MageMessage)  client.getReceiveMessage().receiveMessage();

        controller.setDisabled1();
        controller.setDisabled2();
        controller.setDisabled3();
        controller.setDisabled4();

        for (int i = 0; i < mageMessage.getAviableMage().length; i++) {

            switch (mageMessage.getAviableMage()[i]){
                case 1:
                    controller.setEnabled1();
                    break;
                case 2:
                    controller.setEnabled2();
                    break;
                case 3:
                    controller.setEnabled3();
                    break;
                case 4:
                    controller.setEnabled4();
                    break;
            }

        }
        controller.setClient(client);


        subStage.setResizable(false);
        subStage.setTitle("Choose Mage");
        subStage.setScene(scene);
        subStage.show();
        subStage.show();
    }

}

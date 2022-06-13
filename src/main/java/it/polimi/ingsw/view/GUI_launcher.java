package it.polimi.ingsw.view;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class GUI_launcher {
    public static void main(String[] args) throws IOException {
        Stage stage = new Stage();
        ClientApp app = new ClientApp();
        app.start(stage);
    }
}

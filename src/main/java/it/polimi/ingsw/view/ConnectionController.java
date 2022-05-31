package it.polimi.ingsw.view;

import it.polimi.ingsw.communication.client.ClientMain;
import it.polimi.ingsw.communication.common.JSONtoObject;
import it.polimi.ingsw.communication.common.ObjectToJSON;
import it.polimi.ingsw.communication.common.PingPongThread;
import it.polimi.ingsw.view.stages.LoginStage;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ConnectionController implements Initializable {

    private ClientMain client;
    private Socket clientSocket;
    private ObjectToJSON sendMessage;
    private JSONtoObject receiveMessage;
    private Stage stage;

    @FXML
    private AnchorPane connectionPane;
    @FXML
    private TextField port;
    @FXML
    private TextField IP;
    @FXML
    private Button confirmConnection;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void connection() throws IOException {
        if(port.getText().equals("") || IP.getText().equals("")){
            try {
                this.client.readParameters();
                this.client.createConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            String ip;
            ip = IP.getText();
            System.out.println("Insert port:");
            String p = IP.getText();
            int pnum = Integer.parseInt(p);
            InetAddress host = InetAddress.getByName(ip);
            clientSocket = new Socket(host, pnum);
            // clientSocket.setSoTimeout(100000);
            client.setReceiveMessage(new JSONtoObject(clientSocket));
            client.setSendMessage(new ObjectToJSON(clientSocket));
        }

        stage.close();
        new LoginStage(this.client);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //this.setToggleGroup();
        ClientMain clientMain = new ClientMain();
        //anchorPane.setOpacity(0);
        //stackPane.setOpacity(0);
        //connectionPane.setOpacity(1);
        new PingPongThread(clientMain.getClientSocket(), "client");
        this.client = clientMain;
    }
}

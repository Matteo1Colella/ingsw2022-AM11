package it.polimi.ingsw.view;

import it.polimi.ingsw.communication.client.ClientMain;
import it.polimi.ingsw.communication.common.MessageInterface;
import it.polimi.ingsw.communication.common.MessageType;
import it.polimi.ingsw.communication.common.messages.ModelMessage;
import javafx.application.Application;
import javafx.stage.Stage;

public class viewThread implements Runnable{

    protected Object lock;

    public viewThread(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {


    }


}

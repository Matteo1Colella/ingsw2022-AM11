package it.polimi.ingsw.view;

import it.polimi.ingsw.communication.client.ClientMain;
import it.polimi.ingsw.communication.common.MessageInterface;
import it.polimi.ingsw.communication.common.MessageType;
import it.polimi.ingsw.communication.common.messages.ModelMessage;

public class viewThread1 extends Thread{
    public ActionController controller;
    private boolean choice;
    private ClientMain client;
    private boolean gametype;
    public viewThread1(ActionController controller){
        this.controller = controller;
        this.choice = false;
        this.client = controller.getClient();
        this.gametype = controller.isGametype();
    }

    public void mainClient(){

        while (true){
            System.out.println("received");
            MessageInterface receivedMessage = client.receiveMessage();
            System.out.println("received");
            MessageType message = receivedMessage.getCode();

            if(!this.gametype){
                System.out.println("ok");
                switch (message){
                    case TURN:
                        moveStudents();
                        moveMotherNature();
                        selectCloudCard();
                        choice = false;
                        message = client.receiveMessage().getCode();
                        client.showModel();
                        message = client.receiveMessage().getCode();
                        while (!choice) {
                            choice = client.playAssistantCard();
                        }
                        break;
                    case WIN:
                        System.out.println(receivedMessage.getMessage());
                        return;
                    case MODEL:
                        client.setModel((ModelMessage) receivedMessage);
                        break;
                }
            } else {
                switch (message){
                    case TURN:
                        client.moveStudents();
                        client.moveMotherNature();
                        client.selectCloudCard();
                        choice = false;
                        message = client.receiveMessage().getCode();
                        client.showModel();
                        message = client.receiveMessage().getCode();
                        while (!choice) {
                            choice = client.playAssistantCard();
                        }
                        break;
                    case WIN:
                        System.out.println(receivedMessage.getMessage());
                        return;
                    case MODEL:
                        client.setModel((ModelMessage) receivedMessage);
                        break;
                }
            }

        }

    }

    public void moveStudents(){
        while(!controller.isStudents()){

        }
    }

    public void moveMotherNature(){
        System.out.println("motherNature");
        while(true){

        }

    }

    public void selectCloudCard(){

    }

    @Override
    public void run() {

        System.out.println("1");
        controller.showmodel(client);
        System.out.println("2");
        controller.ReceiveCards(client);
        System.out.println("3");

       mainClient();
    }
}

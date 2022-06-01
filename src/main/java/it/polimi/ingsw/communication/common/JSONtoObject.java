package it.polimi.ingsw.communication.common;

import com.google.gson.Gson;
import it.polimi.ingsw.communication.common.errors.*;
import it.polimi.ingsw.communication.common.messages.*;

import java.io.*;
import java.net.Socket;

public class JSONtoObject {
    private Socket socket;
    private DataInputStream dataInputStream;
    private BufferedReader input;
    private Gson gson;



    public JSONtoObject(Socket socket){
        this.socket = socket;
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        gson = new Gson();
    }

    /**
     * from json to message
     * @param received
     * @return message interface
     */
    private MessageInterface JSONtoMessage(String received){

        Message message = gson.fromJson(received, Message.class);
        if(message == null){
            return new ErrorMessage();
        }
        MessageType code = message.getCode();

        switch (code){
            case PINGPONG:
                return gson.fromJson(received, PingPongMessage.class);
            case LOGIN:
                return gson.fromJson(received, LoginMessage.class);
            case MAGE:
                return gson.fromJson(received, MageMessage.class);
            case CARD:
                return gson.fromJson(received, AssistantCardsMessage.class);
            case CLOUDCARD:
                return gson.fromJson(received, CloudCardChoiceMessage.class);
            case STUDENT:
                return gson.fromJson(received, MoveStudentMessage.class);
            case MOTHERNATURE:
                return gson.fromJson(received, MoveMotherNatureMessage.class);
            case CHARACTERCHOICE:
                return gson.fromJson(received, UseCharacterMessage.class);
            case LOBBIES:
                return gson.fromJson(received, LobbiesMessage.class);
            case LOGINERROR:
                return gson.fromJson(received, LoginError.class);
            case MAGEERROR:
                return gson.fromJson(received, MageError.class);
            case NOERROR:
                return gson.fromJson(received, NoError.class);
            case MODEL:
                return gson.fromJson(received, ModelMessage.class);
            case CHARACTERLIST:
                return gson.fromJson(received, CharacterCardsMessage.class);
            case CARDERROR:
                return gson.fromJson(received, CardError.class);
            case TURN:
                return gson.fromJson(received, TurnMessage.class);
            case WIN:
                return gson.fromJson(received, WinMessage.class);
        }
        return new ErrorMessage();

    }

    public MessageInterface receiveMessage() {
        try {
            String inputJSON = input.readLine();
            if(JSONtoMessage(inputJSON).getCode() == MessageType.ERROR){
                System.out.println("Connection error in receiving message.\r");
                try{
                    socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else{
                return JSONtoMessage(inputJSON);
            }
        } catch (IOException e) {
            try{
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
       return new ErrorMessage();
    }

    public MessageInterface receiveMessageClient() {
        try {
            String inputJSON = input.readLine();
            if(JSONtoMessage(inputJSON).getCode().equals(MessageType.ERROR)){
                System.out.println("Connection error.\r");
                try{
                    socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                System.exit(1);
            } else if (socket.isClosed()){
                System.out.println("Connection error.\r");
                try{
                    socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                System.exit(1);
            } else{
                return JSONtoMessage(inputJSON);
            }
        } catch (IOException e) {
            System.out.println("Connection error.\r");
            try{
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.exit(1);
        }
        return new ErrorMessage();
    }
}

package it.polimi.ingsw.communication.common;

import com.google.gson.Gson;
import it.polimi.ingsw.communication.common.errors.LoginError;
import it.polimi.ingsw.communication.common.errors.MageError;
import it.polimi.ingsw.communication.common.errors.NoError;
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

    private MessageInterface JSONtoMessage(String received){

        Message message = gson.fromJson(received, Message.class);
        int code = message.getCode();

        switch (code){
            case 0:
                return gson.fromJson(received, PingPongMessage.class);
            case 1:
                return gson.fromJson(received, LoginMessage.class);
            case 2:
                return gson.fromJson(received, MageMessage.class);
            case 3:
                return gson.fromJson(received, PlayCardMessage.class);
            case 4:
                return gson.fromJson(received, CloudCardMessage.class);
            case 5:
                return gson.fromJson(received, MoveStudentMessage.class);
            case 6:
                return gson.fromJson(received, MoveMotherNatureMessage.class);
            case 7:
                return gson.fromJson(received, UseCharacterMessage.class);
            case 8:
                return gson.fromJson(received, LobbiesMessage.class);
            case 100:
                return gson.fromJson(received, LoginError.class);
            case 101:
                return gson.fromJson(received, MageError.class);
            case 1000:
                return gson.fromJson(received, NoError.class);

        }
        return new ErrorMessage();

    }

    public MessageInterface receiveMessage(){
        try {
            String inputJSON = input.readLine();
            return JSONtoMessage(inputJSON);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ErrorMessage();
    }
}

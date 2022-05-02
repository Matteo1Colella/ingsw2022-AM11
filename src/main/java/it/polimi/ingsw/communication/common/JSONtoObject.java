package it.polimi.ingsw.communication.common;

import com.google.gson.Gson;
import it.polimi.ingsw.communication.common.errors.ErrorMessage;
import it.polimi.ingsw.communication.common.errors.LoginError;
import it.polimi.ingsw.communication.common.errors.MageError;
import it.polimi.ingsw.communication.common.errors.NoError;
import it.polimi.ingsw.communication.common.messages.*;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

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
        MessageType code = message.getCode();

        switch (code){
            case PINGPONG:
                return gson.fromJson(received, PingPongMessage.class);
            case LOGIN:
                return gson.fromJson(received, LoginMessage.class);
            case MAGE:
                return gson.fromJson(received, MageMessage.class);
            case CARD:
                return gson.fromJson(received, PlayCardMessage.class);
            case CLOUDCARD:
                return gson.fromJson(received, CloudCardMessage.class);
            case STUDENT:
                return gson.fromJson(received, MoveStudentMessage.class);
            case MOTHERNATURE:
                return gson.fromJson(received, MoveMotherNatureMessage.class);
            case CHARACTER:
                return gson.fromJson(received, UseCharacterMessage.class);
            case LOBBIES:
                return gson.fromJson(received, LobbiesMessage.class);
            case LOGINERROR:
                return gson.fromJson(received, LoginError.class);
            case MAGEERROR:
                return gson.fromJson(received, MageError.class);
            case NOERROR:
                return gson.fromJson(received, NoError.class);

        }
        return new ErrorMessage();

    }

    public MessageInterface receiveMessage() {
        try {
            String inputJSON = input.readLine();
            return JSONtoMessage(inputJSON);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ErrorMessage();
    }
}

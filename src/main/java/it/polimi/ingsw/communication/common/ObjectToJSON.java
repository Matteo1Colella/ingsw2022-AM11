package it.polimi.ingsw.communication.common;

import com.google.gson.Gson;
import it.polimi.ingsw.communication.common.errors.*;
import it.polimi.ingsw.communication.common.messages.*;

import java.io.*;
import java.net.Socket;

public class ObjectToJSON {
    private Socket socket;
    private DataOutputStream dataOutputStream;
    private PrintWriter output;
    private Gson gson;

    /**
     * Serialize from a Java object to a Json stream
     */
    public ObjectToJSON(Socket socket) {
        this.socket = socket;
        try{
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            output = new PrintWriter(dataOutputStream, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        gson = new Gson();
    }

    /**
     * Return a json stream from a PingPong object
     * @param pingPong
     */
    public void sendPingPongMessage(PingPongMessage pingPong){
        output.println(gson.toJson(pingPong));
    }

    /**
     * Return a json stream from a LoginMessage object
     * @param loginMessage
     */
    public void sendLoginMessage(LoginMessage loginMessage){
        output.println(gson.toJson(loginMessage));
    }

    /**
     * Return a json stream from a LoginError object
     */
    public void sendLoginError(){
        output.println(gson.toJson(new LoginError()));
    }

    /**
     * Return a json stream from a NoError object
     */
    public void sendNoError(){
        output.println(gson.toJson(new NoError()));
    }

    /**
     * Return a json stream from a LobbiesMessage
     * @param lobbiesMessage
     */
    public void sendLobbiesMessage(LobbiesMessage lobbiesMessage){
        output.println(gson.toJson(lobbiesMessage));
    }

    /**
     * eturn a json stream from a MageMessage object
     * @param mageMessage
     */
    public void sendMageMessage(MageMessage mageMessage){
        output.println(gson.toJson(mageMessage));
    }

    /**
     * Return a json stream from a MageError object
     */
    public void sendMageError(){
        output.println(gson.toJson(new MageError()));
    }

    /**
     * Return a json stream from an AssistantCardsMessage
     * @param assistantCardsMessage
     */
    public void sendAssistantCardsMessage(AssistantCardsMessage assistantCardsMessage){
        output.println(gson.toJson(assistantCardsMessage));
    }

    /**
     * Return a json stream from a ModelMessage object
     * @param modelMessage
     */
    public void sendModelMessage(ModelMessage modelMessage){output.println(gson.toJson(modelMessage));}

    /**
     * Return a json stream from a MuveStudentMessage
     * @param moveStudentMessage
     */
    public void sendMoveStudentsMessage(MoveStudentMessage moveStudentMessage){output.println(gson.toJson(moveStudentMessage));}

    /**
     * Return a json stream from a TurnMessage object
     */
    public void sendTurnMessage(){
        output.println(gson.toJson(new TurnMessage()));
    }

    /**
     * Return a json stream from a CardError object
     */
    public void sendCardError(){
        output.println(gson.toJson(new CardError()));
    }

    /**
     * Return a json stream from a MoveMotherNatureMessage
     * @param moveMotherNatureMessage
     */
    public void sendMoveMotherNatureMessage(MoveMotherNatureMessage moveMotherNatureMessage){output.println(gson.toJson(moveMotherNatureMessage));}

    /**
     * Return a json stream from a CloudCardChoiceMessage
     * @param cardChoiceMessage
     */
    public void sendCloudCardMessage(CloudCardChoiceMessage cardChoiceMessage){
        output.println(gson.toJson(cardChoiceMessage));
    }

    /**
     * Return a json stream from a UseCharacterMessage
     * @param characterMessage
     */
    public void sendCharacterMessage(UseCharacterMessage characterMessage){
        output.println(gson.toJson(characterMessage));
    }

    /**
     * Return a json stream from a WinMessage object
     * @param winMessage
     */
    public void sendWinMessage(WinMessage winMessage){
        output.println(gson.toJson(winMessage));
    }


}
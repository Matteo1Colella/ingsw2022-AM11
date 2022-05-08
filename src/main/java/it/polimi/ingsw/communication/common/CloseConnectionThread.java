package it.polimi.ingsw.communication.common;

import it.polimi.ingsw.model.Player;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;

public class CloseConnectionThread extends Thread{

    private Map<Player, Socket> playerSocketMap;

    public CloseConnectionThread(Map<Player, Socket> playerSocketMap){
        this.playerSocketMap = playerSocketMap;
    }

    @Override
    public void run() {
        for(Player player : playerSocketMap.keySet()){
            Socket clientSocket = playerSocketMap.get(player);
            if(!clientSocket.isClosed()){
                System.out.println("Closing connection.\r");
                try {
                    clientSocket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}

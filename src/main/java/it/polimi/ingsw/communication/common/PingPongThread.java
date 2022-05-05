package it.polimi.ingsw.communication.common;

import it.polimi.ingsw.controller.ComplexLobby;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class PingPongThread extends Thread{
    private final Socket socket;
    private String hostType;
    private ComplexLobby complexLobby;

    public PingPongThread(Socket socket, ComplexLobby complexLobby) {
        this.socket = socket;

        startThread();
    }

    private void startThread(){
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try{
                    if(socket.getInetAddress().isReachable( 5000)){
                        System.out.println("Is reachable.\r");
                    } else {
                        System.out.println("Is not reachable.\r");
                        complexLobby.closeConnection();
                    }
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        }, 0, 10, TimeUnit.SECONDS);
    }
}


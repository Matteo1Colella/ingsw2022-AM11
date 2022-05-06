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
    private ComplexLobby complexLobby;

    public PingPongThread(Socket socket, ComplexLobby complexLobby, String host) {
        this.socket = socket;
        this.complexLobby = complexLobby;
        startThread(host);
    }

    public PingPongThread(Socket socket, String host){
        this.socket = socket;
        startThread(host);
    }


    private void startThread(String host){
        ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
        exec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if(host.equals("server")){
                    try{
                        if(socket.getInetAddress().isReachable( 5000)){
                            //System.out.println("Is reachable.\r");
                        } else {
                            //System.out.println("Is not reachable.\r");
                            complexLobby.closeConnection();
                        }
                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                } else if (host.equals("client")){
                    try{
                        if(socket.getInetAddress().isReachable( 5000)){
                            //System.out.println("Is reachable.\r");
                        } else {
                            //System.out.println("Is not reachable.\r");
                            System.out.println("Connection lost.\r");
                            socket.close();
                        }
                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                }
            }
        }, 0, 10, TimeUnit.SECONDS);
    }
}



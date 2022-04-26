package it.polimi.ingsw.comunication.client;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Scanner;

public class ClientMain {
    private int port;
    private InetAddress localAddress;

    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    public ClientMain(){
        port = 2063;
        //initializing the socket
        try {
            localAddress = InetAddress.getByName("localhost");
            socket = new Socket(localAddress, port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //creating the objects for input and output
        try {
            OutputStreamWriter outputStreamWriter = null;
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            output = new PrintWriter(new BufferedWriter(outputStreamWriter), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ClientMain clientMain = new ClientMain();
        clientMain.login();
    }

    public int getPort() {
        return port;
    }

    public void readParameters() {
        try {
            Gson gson = new Gson();
            //create a reader
            Reader reader = Files.newBufferedReader(Paths.get("src/main/java/it/polimi/ingsw/comunication/client/config.json"));
            //convert JSON file to map
            Map<?, ?> map = gson.fromJson(reader, Map.class);
            //print map entries
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                System.out.println(entry.getKey() + "=" + entry.getValue());
                if (entry.getKey().equals("port")) {
                    port = (int) Float.parseFloat(entry.getValue().toString());
                }
            }
            // close reader
            try {
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void login() {
        String ID = null;
        int numPlayers = 0;
        int gameType = 0;
        boolean ok = false;
        Gson login = new Gson();

        while (!ok) {
            System.out.println("Enter ID");
            Scanner scanner = new Scanner(System.in);
            ID = scanner.next();
            System.out.println("Enter the number of players:");
            numPlayers = scanner.nextInt();
            System.out.println("Enter the game type:");
            gameType = scanner.nextInt();
            if ((numPlayers > 1 && numPlayers <= 4) || (gameType == 0 || gameType == 1)) {
                ok = true;
            }
        }
    }
}

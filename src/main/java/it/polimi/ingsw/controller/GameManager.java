package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Player;

import java.net.Socket;
import java.util.ArrayList;

public class GameManager {
    private final int maxplayers = 4;
    // list of lobbies
    private ArrayList<ComplexLobby> complexLobbies;
    // list of chosen ids from players
    private ArrayList<String> usednames;
    // constructor
    public GameManager() {
        usednames = new ArrayList<>();
        this.complexLobbies = new ArrayList<ComplexLobby>();
    }

    public ArrayList<ComplexLobby> getComplexLobbies() {
        return complexLobbies;
    }

    // Looks For the last Lobby matching with player preferences and returns, if exists, else it returns null
    public ComplexLobby getComplexlobby(int Numplayers, boolean Gametype){
        ComplexLobby Chosen = null;
        int ID = -1;
        if (complexLobbies.isEmpty()){
            ArrayList<Player> Players = new ArrayList<Player>();
            ComplexLobby firstComplexLobby = new ComplexLobby(Numplayers, Gametype, 0, Players);
            complexLobbies.add(firstComplexLobby);
            return firstComplexLobby;
        }
        for (ComplexLobby Temp : complexLobbies)
        {
            if (Temp.getNumPlayers() == Numplayers && Temp.isGameType() == Gametype && Temp.getID() > ID) {
                ID = Temp.getID();
                Chosen = Temp;
            }
        }
        if (ID == -1) return null;
        return Chosen;
    }

    // returns lobby with ID
    public ComplexLobby ComplexLobbySearch(int ID){

        for(ComplexLobby temp : complexLobbies){
            if (temp.getID()==ID) return temp;
        }
        return null;
    }

    // If there's no Lobbies, it creates one, if there's one matching lobby but full, it creates a new one, if there's one matching Lobby which is not full
    // the player gets added in that Lobby. it checks if UserID is free and if it's not it returns an access failed.
    public boolean login(String ID, int numplayers, boolean Gametype){
        // no more than 4 players
        if (numplayers <= 1 || numplayers > maxplayers){
            System.out.println("Error: Too Many Players");
            return false;
        }
        System.out.println("Creating New user, with ID: " + ID);
        // no name duplicates
        if (usednames.contains(ID.toUpperCase())) {
            System.out.println("Error: Already Existing Name");
            System.out.println("");
            return false;
        }

        // adds id to usednames
        usednames.add(ID.toUpperCase());
        ComplexLobby existingComplexLobby = getComplexlobby(numplayers, Gametype);

        // if no lobby avaiable it creates one
        if (existingComplexLobby == null)
        {
            System.out.println("Creating First Lobby, with ID 0");
            ArrayList<Player> Players = new ArrayList<Player>();
            ComplexLobby newComplexLobby = new ComplexLobby(numplayers, Gametype, this.complexLobbies.size(), Players);
            newComplexLobby.AddPlayer(ID);
            this.complexLobbies.add(newComplexLobby);
            System.out.println("Added Player "+ ID + " to Lobby with ID " + newComplexLobby.getID());
            System.out.println("Players in Lobby:");
            newComplexLobby.getPlayers().stream().map(Player::getID_player).forEach(System.out::println);
            System.out.println("");

            return true;
        }
        // if there's one lobby but full it creates new one
        if (existingComplexLobby.isReady()){
            ArrayList<Player> Players = new ArrayList<Player>();
            ComplexLobby newComplexLobby = new ComplexLobby(numplayers, Gametype, this.complexLobbies.size(), Players);
            newComplexLobby.AddPlayer(ID);
            this.complexLobbies.add(newComplexLobby);
            System.out.println("All lobbies are Full");
            System.out.println("Creating Another Lobby, with ID " + (this.complexLobbies.size() - 1));
            System.out.println("Players in Lobby:");
            newComplexLobby.getPlayers().stream().map(Player::getID_player).forEach(System.out::println);
            System.out.println("");

            return true;
            // if lobby is free adds the player
        } else {
            existingComplexLobby.AddPlayer(ID);
            System.out.println("Added Player "+ ID + " to Lobby with ID " + existingComplexLobby.getID());
            System.out.println("Players in Lobby:");
            existingComplexLobby.getPlayers().stream().map(Player::getID_player).forEach(System.out::println);
            if (existingComplexLobby.isReady()){
                System.out.println("The Lobby is ready");
                System.out.println("");
            } else System.out.println("");

            return true;
        }
    }

    public boolean loginSocket(String ID, int numplayers, boolean Gametype, Socket clientSocket){
        // no more than 4 players
        if (numplayers > maxplayers){
            System.out.println("Error: Too Many Players");
            return false;
        }
        System.out.println("Creating New user, with ID: " + ID);
        // no name duplicates
        if (usednames.contains(ID.toUpperCase())) {
            System.out.println("Error: Already Existing Name");
            System.out.println("");
            return false;
        }

        // adds id to usednames
        usednames.add(ID.toUpperCase());
        ComplexLobby existingComplexLobby = getComplexlobby(numplayers, Gametype);

        // if no lobby avaiable it creates one
        if (existingComplexLobby == null)
        {
            System.out.println("Creating First Lobby, with ID 0");
            ArrayList<Player> Players = new ArrayList<Player>();
            ComplexLobby newComplexLobby = new ComplexLobby(numplayers, Gametype, this.complexLobbies.size(), Players);
            newComplexLobby.AddPlayer(ID);
            newComplexLobby.addClientSocket(clientSocket, ID);
            this.complexLobbies.add(newComplexLobby);
            System.out.println("Added Player "+ ID + " to Lobby with ID " + newComplexLobby.getID());
            System.out.println("Players in Lobby:");
            newComplexLobby.getPlayers().stream().map(Player::getID_player).forEach(System.out::println);
            System.out.println("");

            return true;
        }
        // if there's one lobby but full it creates new one
        if (existingComplexLobby.isReady()){
            ArrayList<Player> Players = new ArrayList<Player>();
            ComplexLobby newComplexLobby = new ComplexLobby(numplayers, Gametype, this.complexLobbies.size(), Players);
            newComplexLobby.AddPlayer(ID);
            newComplexLobby.addClientSocket(clientSocket, ID);
            this.complexLobbies.add(newComplexLobby);
            System.out.println("All lobbies are Full");
            System.out.println("Creating Another Lobby, with ID " + (this.complexLobbies.size() - 1));
            System.out.println("Players in Lobby:");
            newComplexLobby.getPlayers().stream().map(Player::getID_player).forEach(System.out::println);
            System.out.println("");

            // if lobby is free adds the player
        } else {
            existingComplexLobby.AddPlayer(ID);
            existingComplexLobby.addClientSocket(clientSocket, ID);
            System.out.println("Added Player "+ ID + " to Lobby with ID " + existingComplexLobby.getID());
            System.out.println("Players in Lobby:");
            existingComplexLobby.getPlayers().stream().map(Player::getID_player).forEach(System.out::println);
            if (existingComplexLobby.isReady()){
                System.out.println("The Lobby is ready");
                System.out.println("");
            } else System.out.println("");

        }

        if(existingComplexLobby.isReady() && !existingComplexLobby.getClientSocketsMap().isEmpty()){
            existingComplexLobby.setPlayerOrder(existingComplexLobby.getPlayers());
            existingComplexLobby.setActivePlayer(existingComplexLobby.getPlayerOrder().get(0));
            for(Player player : existingComplexLobby.getPlayerOrder()){
                System.out.println(player.getID_player());
            }
        }
        return true;
    }
    // returns the lobby in which plays the player
    public ComplexLobby getPlayerComplexLobby(String player){
        for(ComplexLobby temp : complexLobbies){
            for(Player p : temp.getPlayers()){
                if (p.getID_player().equals(player)) {return temp;}
            }
        }
        return null;
    }


}

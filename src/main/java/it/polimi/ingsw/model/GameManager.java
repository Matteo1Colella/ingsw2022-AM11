package it.polimi.ingsw.model;

import java.util.ArrayList;

public class GameManager {
    private ArrayList<PlayersManager> Lobbies;

    public GameManager(ArrayList<PlayersManager> lobbies) {
        Lobbies = lobbies;
    }


    // Looks For the last Lobby matching with player preferences and returns, if exists, else returns null
    public PlayersManager getLobby(int Numplayers, boolean Gametype){
        PlayersManager Chosen = null;
        int ID = -1;
        if (Lobbies == null){
            ArrayList<Player> Players = new ArrayList<Player>();
            PlayersManager firstPlayersManager = new PlayersManager(Numplayers, Gametype, 0, Players);
            Lobbies.add(firstPlayersManager);
            return firstPlayersManager;
        }
        for (PlayersManager Temp : Lobbies)
        {
            if (Temp.getNumPlayers() == Numplayers && Temp.isGameType() == Gametype && Temp.getID() > ID) {
                ID = Temp.getID();
                Chosen = Temp;
            }
        }
        if (ID == -1) return null;
        return Chosen;
    }

    // If there's no Lobbies, it creates one, if there's one matching lobby but full, it creates a new one, if there's one matching Lobby which is not full
    // the player gets added in that Lobby. it checks if UserID is free and if it's not it returns an access failed.
    public boolean login(String ID, int numplayers, boolean Gametype){
        if (numplayers > 4){
            System.out.println("Error: Too Many Players");
            return false;
        }
        System.out.println("Creating New user, with ID: " + ID);
        PlayersManager existingPlayersManager = getLobby(numplayers, Gametype);
        if (existingPlayersManager == null)
            {
                System.out.println("Creating First Lobby, with ID 0");
                ArrayList<Player> Players = new ArrayList<Player>();
                PlayersManager newPlayersManager = new PlayersManager(numplayers, Gametype, this.Lobbies.size(), Players);
                newPlayersManager.AddPlayer(ID);
                this.Lobbies.add(newPlayersManager);
                System.out.println("Added Player "+ ID + " to Lobby with ID " + newPlayersManager.getID());
                System.out.println("Players in Lobby:");
                newPlayersManager.getPlayers().stream().map(name -> name.getID_player()).forEach(System.out::println);
                System.out.println("");

                return true;
            }
        if (existingPlayersManager.IsIn(ID)) {
            System.out.println("Error: Already Existing Name");
            System.out.println("");
            return false;
        }
            else {
                if (existingPlayersManager.isReady()){
                    ArrayList<Player> Players = new ArrayList<Player>();
                    PlayersManager newPlayersManager = new PlayersManager(numplayers, Gametype, this.Lobbies.size(), Players);
                    newPlayersManager.AddPlayer(ID);
                    this.Lobbies.add(newPlayersManager);
                    System.out.println("All lobbies are Full");
                    System.out.println("Creating Another Lobby, with ID " + (this.Lobbies.size() - 1));
                    System.out.println("Players in Lobby:");
                    newPlayersManager.getPlayers().stream().map(name -> name.getID_player()).forEach(System.out::println);
                    System.out.println("");

                    return true;
                }   else {
                        existingPlayersManager.AddPlayer(ID);
                        System.out.println("Added Player "+ ID + " to Lobby with ID " + existingPlayersManager.getID());
                        System.out.println("Players in Lobby:");
                        existingPlayersManager.getPlayers().stream().map(name -> name.getID_player()).forEach(System.out::println);
                        System.out.println("");
                        return true;
                    }
            }


    }


}

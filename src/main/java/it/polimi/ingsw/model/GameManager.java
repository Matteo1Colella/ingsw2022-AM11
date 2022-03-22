package it.polimi.ingsw.model;

import java.util.ArrayList;

public class GameManager {
    private ArrayList<Lobby> Lobbies;

    public GameManager(ArrayList<Lobby> lobbies) {
        Lobbies = lobbies;
    }


    // Looks For the last Lobby matching with player preferences and returns, if exists, else returns null
    public Lobby getLobby(int Numplayers, boolean Gametype){
        Lobby Chosen = null;
        int ID = -1;
        if (Lobbies == null){
            ArrayList<Player> Players = new ArrayList<Player>();
            Lobby FirstLobby = new Lobby(Numplayers, Gametype, 0, Players);
            Lobbies.add(FirstLobby);
            return FirstLobby;
        }
        for (Lobby Temp : Lobbies)
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
    public boolean Login(String ID, int numplayers, boolean Gametype){
        if (numplayers > 4){
            System.out.println("Error: Too Many Players");
            return false;
        }
        System.out.println("Creating New user, with ID: " + ID);
        Lobby ExistingLobby = getLobby(numplayers, Gametype);
        if (ExistingLobby == null)
            {
                System.out.println("Creating First Lobby, with ID 0");
                ArrayList<Player> Players = new ArrayList<Player>();
                Lobby NewLobby = new Lobby(numplayers, Gametype, this.Lobbies.size(), Players);
                NewLobby.AddPlayer(ID);
                this.Lobbies.add(NewLobby);
                System.out.println("Added Player "+ ID + " to Lobby with ID " + NewLobby.getID());
                System.out.println("Players in Lobby:");
                NewLobby.getPlayers().stream().map(name -> name.getID_player()).forEach(System.out::println);
                System.out.println("");

                return true;
            }
        if (ExistingLobby.IsIn(ID)) {
            System.out.println("Error: Already Existing Name");
            System.out.println("");
            return false;
        }
            else {
                if (ExistingLobby.isReady()){
                    ArrayList<Player> Players = new ArrayList<Player>();
                    Lobby NewLobby = new Lobby(numplayers, Gametype, this.Lobbies.size(), Players);
                    NewLobby.AddPlayer(ID);
                    this.Lobbies.add(NewLobby);
                    System.out.println("All lobbies are Full");
                    System.out.println("Creating Another Lobby, with ID " + (this.Lobbies.size() - 1));
                    System.out.println("Players in Lobby:");
                    NewLobby.getPlayers().stream().map(name -> name.getID_player()).forEach(System.out::println);
                    System.out.println("");

                    return true;
                }   else {
                        ExistingLobby.AddPlayer(ID);
                        System.out.println("Added Player "+ ID + " to Lobby with ID " + ExistingLobby.getID());
                        System.out.println("Players in Lobby:");
                        ExistingLobby.getPlayers().stream().map(name -> name.getID_player()).forEach(System.out::println);
                        System.out.println("");
                        return true;
                    }
            }


    }


}

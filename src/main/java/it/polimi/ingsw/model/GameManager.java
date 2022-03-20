package it.polimi.ingsw.model;

import java.util.ArrayList;

public class GameManager {
    private ArrayList<Lobby> Lobbies;

    // Looks For the last Lobby matching with player preferences and returns, if exists, else returns null
    public Lobby getLobby(int Numplayers, boolean Gametype){
        Lobby Chosen = null;
        int ID = -1;
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

        Lobby ExistingLobby = getLobby(numplayers, Gametype);
        if (ExistingLobby == null)
            {
                Lobby NewLobby = new Lobby(numplayers, Gametype, this.Lobbies.size());
                this.Lobbies.add(NewLobby);
                return true;
            }
        if (ExistingLobby.IsIn(ID) == true) return false;
            else {
                if (ExistingLobby.isReady()==true){
                    Lobby NewLobby = new Lobby(numplayers, Gametype, this.Lobbies.size());
                    this.Lobbies.add(NewLobby);
                    return true;
                }   else {
                        ExistingLobby.AddPlayer(ID);
                        return true;
                    }
            }


    }


}

package it.polimi.ingsw.model;

import java.util.ArrayList;

public class GameManager {
    private final int maxplayers = 4;
    // list of lobbies
    private ArrayList<PlayersManager> Lobbies;
    // list of chosen ids from players
    private ArrayList<String> usednames;
    // constructor
    public GameManager(ArrayList<PlayersManager> lobbies) {
        usednames = new ArrayList<>();
        Lobbies = lobbies;
    }


    // Looks For the last Lobby matching with player preferences and returns, if exists, else it returns null
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
    // A player (IDPlayer) from a lobby (ID) requests a deck with a specified mage (mage). if free, it sets player's deck,
    // if busy, it returns false
    public boolean deckRequest(int ID, Mage mage, String IDplayer){
        //looks for lobby (ID)
        PlayersManager room = GameManagerSearch(ID);


        System.out.println("searching for mage " + mage);
        AssistantDeck d = room.getDM().generateDeck(mage);

        // if mage is not avaible returns false
        if (d == null){
            System.out.println("Mage Already Chosen, here are remaining Mages");
            room.getDM().getAssistantDecks().stream().filter(name -> name.isFree()).map(name -> name.getMage()).forEach(System.out::println);
            System.out.println("");
            return false;
        }
        System.out.println("found deck with mage " + d.getMage());

        // if mage is avaible it returns true and sets the deck
        Player p0 = null;
        for (Player p : room.getPlayers()){
            if (p.getID_player().equals(IDplayer)){
                p0 = p;
            }
        }
        System.out.println("Added deck " + d.getMage() + " to " + IDplayer);
        System.out.println("Here are remaining Mages");
        room.getDM().getAssistantDecks().stream().filter(name -> name.isFree()).map(name -> name.getMage()).forEach(System.out::println);
        System.out.println("");
        p0.setDeck(d);

        if (room.getPlayers().size()==room.getNumPlayers()){
            room.CreateGame(room.getNumPlayers(), room.getID(), room.isGameType());
        }
        return true;
    }
    // returns lobby with ID
    public PlayersManager GameManagerSearch(int ID){

        for(PlayersManager temp : Lobbies){
            if (temp.getID()==ID) return temp;
        }
        return null;
    }

    // If there's no Lobbies, it creates one, if there's one matching lobby but full, it creates a new one, if there's one matching Lobby which is not full
    // the player gets added in that Lobby. it checks if UserID is free and if it's not it returns an access failed.
    public boolean login(String ID, int numplayers, boolean Gametype){
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
        PlayersManager existingPlayersManager = getLobby(numplayers, Gametype);

        // if no lobby avaiable it creates one
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
        // if there's one lobby but full it creates new one
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
            // if lobby is free adds the player
        }   else {
                existingPlayersManager.AddPlayer(ID);
                System.out.println("Added Player "+ ID + " to Lobby with ID " + existingPlayersManager.getID());
                System.out.println("Players in Lobby:");
                existingPlayersManager.getPlayers().stream().map(name -> name.getID_player()).forEach(System.out::println);
                if (existingPlayersManager.isReady()){
                    System.out.println("The Lobby is ready");
                    System.out.println("");
                } else System.out.println("");

                return true;
            }
    }
    // returns the lobby in which plays the player
    public PlayersManager getPlayerLobby(String player){
        for(PlayersManager temp : Lobbies){
            for(Player p : temp.getPlayers()){
                if (p.getID_player().equals(player)) {return temp;}
            }
        }
        return null;
    }


}

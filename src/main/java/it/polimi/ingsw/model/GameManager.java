package it.polimi.ingsw.model;

import java.util.ArrayList;

public class GameManager {
    private final int maxplayers = 4;
    // list of lobbies
    private ArrayList<ComplexLobby> complexLobbies;
    // list of chosen ids from players
    private ArrayList<String> usednames;
    // constructor
    public GameManager(ArrayList<ComplexLobby> complexLobbies) {
        usednames = new ArrayList<>();
        this.complexLobbies = complexLobbies;
    }


    // Looks For the last Lobby matching with player preferences and returns, if exists, else it returns null
    public ComplexLobby getComplexlobby(int Numplayers, boolean Gametype){
        ComplexLobby Chosen = null;
        int ID = -1;
        if (complexLobbies == null){
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
    // A player (IDPlayer) from a lobby (ID) requests a deck with a specified mage (mage). if free, it sets player's deck,
    // if busy, it returns false
    public boolean deckRequest(int ID, Mage mage, String IDplayer){
        //looks for lobby (ID)
        ComplexLobby room = ComplexLobbySearch(ID);


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
                this.complexLobbies.add(newComplexLobby);
                System.out.println("Added Player "+ ID + " to Lobby with ID " + newComplexLobby.getID());
                System.out.println("Players in Lobby:");
                newComplexLobby.getPlayers().stream().map(name -> name.getID_player()).forEach(System.out::println);
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
            newComplexLobby.getPlayers().stream().map(name -> name.getID_player()).forEach(System.out::println);
            System.out.println("");
            return true;
            // if lobby is free adds the player
        }   else {
                existingComplexLobby.AddPlayer(ID);
                System.out.println("Added Player "+ ID + " to Lobby with ID " + existingComplexLobby.getID());
                System.out.println("Players in Lobby:");
                existingComplexLobby.getPlayers().stream().map(name -> name.getID_player()).forEach(System.out::println);
                if (existingComplexLobby.isReady()){
                    System.out.println("The Lobby is ready");
                    System.out.println("");
                } else System.out.println("");

                return true;
            }
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

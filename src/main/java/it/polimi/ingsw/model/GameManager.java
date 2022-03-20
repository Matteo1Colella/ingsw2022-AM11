package it.polimi.ingsw.model;

public class GameManager {
    private int numplayers;
    private String UserId;
    private int Gametype;
    public Game CreateGame(int numplayers, int gametype){
        return null;
    }
    public void JoinGame(){}
    public void LoginManager(){
    }
    public boolean Login(String ID, int numplayers, int Gametype){
            this.numplayers = numplayers;
            this.UserId = ID;
            this.Gametype = Gametype;
            Game ExistingGame = getGame(numplayers, Gametype);
            if (ExistingGame == null)
            {
                Game Newgame = CreateGame(numplayers, Gametype);
                return true;
            }
            if (ExistingGame.IsIn(ID) == true) return false;
            else {
                ExistingGame.AddPlayer(ID);
                return true;
            }


    }
    public Game getGame(int numplayers, int gametype){
        return null;
    }

}

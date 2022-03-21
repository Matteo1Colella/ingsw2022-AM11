package it.polimi.ingsw;

import static org.junit.Assert.assertTrue;

import it.polimi.ingsw.model.GameManager;
import it.polimi.ingsw.model.Lobby;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    @Test
    public void TestSameID(){
        ArrayList<Lobby> Lobbies = new ArrayList<Lobby>();
        GameManager GM = new GameManager(Lobbies);
        GM.Login("Cole", 3, true);
        GM.Login("Leo", 3, true);
        GM.Login("Cole", 3, true);
    }
    @Test
    public void TestFullLobby(){
        ArrayList<Lobby> Lobbies = new ArrayList<Lobby>();
        GameManager GM = new GameManager(Lobbies);
        GM.Login("Cole", 3, true);
        GM.Login("Leo", 3, true);
        GM.Login("Ale", 3, true);
        GM.Login("Gian", 3, true);
    }


}

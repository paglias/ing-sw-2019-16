package messages.client_data;

import java.util.ArrayList;

// Class used to represent the GameBoard on the client
// Contains all the data from the GameBoard model that can be shown to all users
// And optimized for usage on the client
// Every attribute is public to make it easier to create it

public class GameBoard {
    public Boolean gameSetup; // If the game is setup (map and n of skulls chosen)
    public Boolean gameStarted; // If the game has started
    public int nMap; // The number of the map used
    public int skullsN; // The number of skulls
    public ArrayList<String> killers; // A list of the players that killed other players (list of nicknames)
    public ArrayList<PlayerOther> players; // A list of PlayerOther
    public Boolean isFinalFrenzy; // If we're during a final frenzy
    public ArrayList<Square> squares; // List of squares objects (with public info)
}

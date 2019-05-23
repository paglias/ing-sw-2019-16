package messages.client_data;

import java.util.ArrayList;

// Class used to represent the Player model on the client
// Contains all the data from the Player model that can be shown to all users
// And optimized for usage on the client
// Every attribute is public to make it easier to create it

public class PlayerOther {
    public String nickname;
    public Integer position; // The number of the square where the player is positioned (Integer so it can be set to null)
    public Boolean isActive; // The current player?
    public int nDeaths = 0; // The number of times the player died
    public ArrayList<String> marks = new ArrayList<>(); // marks, as a list of nicknames
    public ArrayList<String> damage = new ArrayList<>(); // marks, as a list of nicknames
    public ArrayList<String> cuves = new ArrayList<>(); // cubes of the player, as a list of colors
}

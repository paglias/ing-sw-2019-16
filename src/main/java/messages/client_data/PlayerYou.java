package messages.client_data;

import models.cards.PowerUp;
import models.cards.Weapon;

import java.util.ArrayList;
import java.util.Date;

// Class used to represent the Player model on the client
// Contains all the data from the Player model that can be shown to yourself
// It extends the PlayerOther class to avoid duplication
// And optimized for usage on the client
// Every attribute is public to make it easier to create it

public class PlayerYou extends PlayerOther {
    public Date startTurnDate;                                  // when the turn started
    public ArrayList<PowerUp> powerUps = new ArrayList<>();    // list of available power ups
    public ArrayList<Weapon> weapons = new ArrayList<>();      // list of available weapons
    public int moveCounter;                                    // how many moves can be done TODO needed on client?
    public int actionCounter;                                  // remaining actions that can be done during turn
    public int totalPoints = 0;                                // total points of the current player
}

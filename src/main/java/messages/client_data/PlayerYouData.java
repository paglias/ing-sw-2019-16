package messages.client_data;

import models.Player;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

// Class used to represent the Player model on the client
// Contains all the data from the Player model that can be shown to yourself
// It extends the PlayerOther class to avoid duplication
// And optimized for usage on the client
// Every attribute is public to make it easier to create it

public class PlayerYouData extends PlayerOtherData {
    public Date startTurnDate;                                  // when the turn started
    public ArrayList<PowerUpData> powerUps;    // list of available power ups
    public ArrayList<WeaponData> weapons;      // list of available weapons
    public int actionCounter;                                  // remaining actions that can be done during turn
    public int totalPoints;                                // total points of the current player
    public List<String> possibleActions;

    public PlayerYouData (Player player) {
        super(player);

        startTurnDate = player.getStartTurnDate();

        powerUps = new ArrayList<>(player.getPowerUps().stream()
                .map(p -> new PowerUpData(p)).collect(Collectors.toList()));

        weapons = new ArrayList<>(player.getWeapons().stream()
                .map(w -> new WeaponData(w)).collect(Collectors.toList()));
        actionCounter = player.getActionCounter();
        totalPoints = player.getTotalPoints();
        possibleActions = new ArrayList<>(player.getPossibleActions().stream()
                .map(a -> a.toString()).collect(Collectors.toList()));

    }
}

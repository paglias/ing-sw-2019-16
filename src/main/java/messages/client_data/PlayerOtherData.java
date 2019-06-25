package messages.client_data;

import models.Player;

import java.util.ArrayList;
import java.util.stream.Collectors;

// Class used to represent the Player model on the client
// Contains all the data from the Player model that can be shown to all users
// And optimized for usage on the client
// Every attribute is public to make it easier to create it

public class PlayerOtherData {
    public String nickname;
    public Integer position; // The number of the square where the player is positioned (Integer so it can be set to null)
    public Boolean isActive; // The current player?
    public int nDeaths = 0; // The number of times the player died
    public ArrayList<String> marks; // marks, as a list of nicknames
    public ArrayList<String> damage; // damage, as a list of nicknames
    public ArrayList<String> cubes; // cubes of the player, as a list of colors
    public boolean isConnected;
    public String activeAction;

    public PlayerOtherData (Player player) {
        nickname = player.getNickname();
        position = player.getPosition() != null ? player.getPosition().getNumber() : null;
        isActive = player.isActive();
        isConnected = player.isConnected();
        activeAction = player.getActiveAction() != null ? player.getActiveAction().toString() : null;
        nDeaths = player.getNDeaths();
        marks = new ArrayList<>(player.getMarks().stream()
            .map(p -> p.getNickname()).collect(Collectors.toList()));
        damage = new ArrayList<>(player.getDamage().stream()
                .map(p -> p.getNickname()).collect(Collectors.toList()));
        cubes = new ArrayList<>(player.getCubes().stream()
                .map(c -> c.toString()).collect(Collectors.toList()));
    }
}

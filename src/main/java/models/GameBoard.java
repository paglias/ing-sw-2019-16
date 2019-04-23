package models;

import models.decks.AmmoDeck;
import models.decks.PowerUpsDeck;
import models.decks.WeaponsDeck;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class GameBoard {
    private Skulls skulls;
    private Boolean started;
    private Date gameStartDate;
    private Date gameLaunchDate;
    private int turn;
    private ArrayList<WeaponsSlot> weaponsSlots;
    private PowerUpsDeck powerUpsDeck;
    private WeaponsDeck weaponsDeck;
    private AmmoDeck ammoDeck;
    private ArrayList<Player> players;
    private Boolean isFinalFrenzy;
    private ArrayList<Square> squares;

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setupGame (Integer chosenMap) {
        if (chosenMap == null) chosenMap = 1;

        squares = new ArrayList<>();
        try {
            squares.addAll(MapLoader.loadMap(chosenMap));
        } catch (IOException e) {
            System.out.println(e);
        }

        powerUpsDeck = new PowerUpsDeck();
        weaponsDeck = new WeaponsDeck();
        ammoDeck = new AmmoDeck();
    }
}

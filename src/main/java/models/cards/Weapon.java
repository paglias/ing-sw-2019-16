package models.cards;

import models.Player;
import models.Square;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import com.google.gson.*;

public class Weapon extends Card {
    private enum Effect {
        SHOOT,
        SHOOT_VIEW,
        SHOOT_EVERY,
        SHOOT_CANT_SEE,

        SHOOT_OTHER_VIEW,
        SHOOT_THIRD_VIEW,
        SHOOT_DIRECTION,
        SHOOT_ROOM_CAN_SEE,
        SHOOT_ONE_AWAY_VIEW,
        SHOOT_TWO_AWAY_VIEW,
        SHOOT_TARGET_VIEW,
        SHOOT_SECOND_TARGET_VIEW,
        SHOOT_EVERY_ONE_AWAY_VIEW,

        MOVE,
        MOVE_TARGET,

        MARK,
        MARK_VIEW,
        MARK_ONE_AWAY_VIEW,
        MARK_TWO_AWAY_VIEW,
        MARK_EVERY_ONE_AWAY_VIEW,

        ATTRACT_TARGET
    }

    private String name;
    private ArrayList<Color> rechargeCost;
    private ArrayList<Color> cost;
    private ArrayList<Color> secondaryCost;
    private ArrayList<Color> tertiaryCost;
    private ArrayList<ArrayList<Effect>> primaryEffect;
    private ArrayList<ArrayList<Effect>> secondaryEffect;
    private ArrayList<ArrayList<Effect>> tertiaryEffect;

    // Weapons are loaded when created / picked from a deck
    private boolean loaded = true;

    /**
     * Load weapons from file.
     *
     * @return the weapons
     */
    public static ArrayList<Weapon> loadWeapons () {
        File weaponsFolder = new File(Weapon.class.getResource("/Weapons").getPath());
        File[] listOfWeaponsFiles = weaponsFolder.listFiles();
        ArrayList<Weapon> weapons = new ArrayList<>();
        Gson gson = new Gson();

        try {
            for (File file : listOfWeaponsFiles) {
                if (file.isFile()) {
                    weapons.add(gson.fromJson(new FileReader(file.getAbsolutePath()), Weapon.class));
                }
            }
        } catch (java.io.FileNotFoundException e) {
            // TODO refactor
            System.out.println(e);
        }

        return weapons;
    }

    /**
     * Is the weapon loaded?
     *
     * @return a boolean to indicate the loading status
     */
    public boolean isLoaded() {
        return loaded;
    }

    /**
     * Deal damage to another player.
     *
     * @param playerTarget the target player
     */
    public void dealDamage(Player playerTarget){
        // TODO
        //deal damage based on weapon
    }

    /**
     * Add a mark to another player.
     *
     * @param playerTarget the target player
     */
    public void addMark(Player playerTarget){
        // TODO
        //add mark based on weapon
    }

    /**
     * Move the player.
     *
     * @param playerTarget the target player
     * @param newPosition  where the player will be moved
     */
    public void movePlayer(Player playerTarget, Square newPosition){
        // TODO
        //move players around based on weapon
    }

    /**
     * Reload the weapon.
     */
    public void reload (){
        // TODO
        //load weapon, set loaded to TRUE
    }
}
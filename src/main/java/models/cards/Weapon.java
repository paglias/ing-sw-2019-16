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

    private boolean loaded = false;

    public static ArrayList<Weapon> loadWeapons () throws java.io.FileNotFoundException {
        File weaponsFolder = new File(Weapon.class.getResource("/Weapons").getPath());
        File[] listOfWeaponsFiles = weaponsFolder.listFiles();
        ArrayList<Weapon> weapons = new ArrayList<>();
        Gson gson = new Gson();

        for (File file : listOfWeaponsFiles) {
            if (file.isFile()) {
                weapons.add(gson.fromJson(new FileReader(file.getAbsolutePath()), Weapon.class));
            }
        }

        for (Weapon weapon : weapons) {
            System.out.println(weapon.primaryEffect);
        }

        return weapons;
    }

    /**
     * Is loaded boolean.
     *
     * @return the boolean
     */
    public boolean isLoaded() {
        return loaded;
    }

    /**
     * Instantiates a new Weapon.
     *
     *
     * @param color the color
     */
    public Weapon (Color color) {
        super(color);
    }

    public void dealDamage(Player playerTarget){
        //deal damage based on weapon
    }
    public void addMark(Player playerTarget){
        //add mark based on weapon
    }
    public void movePlayer(Player playerTarget, Square newPosition){
        //move players around based on weapon
    }
    public void reloadWeapon(){
        //load weapon, set is loaded to TRUE
    }
}
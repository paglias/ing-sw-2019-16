package models.cards;

import models.Player;
import models.Square;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import com.google.gson.*;

public class Weapon extends Card {
    private boolean loaded;

    public static ArrayList<Weapon> loadWeapons () throws java.io.FileNotFoundException {
        File weaponsFolder = new File("../../../reources/Weapons");
        File[] listOfWeaponsFiles = weaponsFolder.listFiles();
        ArrayList<Weapon> weapons = new ArrayList<>();
        Gson gson = new Gson();

        for (File file : listOfWeaponsFiles) {
            if (file.isFile()) {
                weapons.add(gson.fromJson(new FileReader(file.getAbsolutePath()), Weapon.class));
            }
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
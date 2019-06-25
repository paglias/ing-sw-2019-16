package models.cards;

import models.Player;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import com.google.gson.*;
import utils.Logger;

public class Weapon extends CardWithAction {
    // Weapons are only loaded from file once
    private static boolean loadedFromFile = false;
    private static ArrayList<Weapon> cachedCards;

    private ArrayList<Color> rechargeCost;
    private ArrayList<Color> cost;

    // Weapons are loaded when created / picked from a deck
    private boolean loaded = true;

    /**
     * Gets recharge cost.
     *
     * @return the recharge cost
     */
    public ArrayList<Color> getRechargeCost() {
        return rechargeCost;
    }

    public ArrayList<Color> getCost() {
        return cost;
    }
    /**
     * Load weapons from file.
     *
     * @return the weapons
     */
    public static ArrayList<Weapon> loadWeapons () {
        Gson gson = new Gson();

        if (!loadedFromFile) {
            String weaponsPath = "." + File.separatorChar + "src" + File.separatorChar + "main" + File.separatorChar + "resources" + File.separatorChar + "Weapons";
            File weaponsFolder = new File(weaponsPath);
            File[] listOfWeaponsFiles = weaponsFolder.listFiles();
            ArrayList<Weapon> weapons = new ArrayList<>();

            try {
                for (File file : listOfWeaponsFiles) {
                    if (file.isFile()) {
                        weapons.add(gson.fromJson(new FileReader(file.getAbsolutePath()), Weapon.class));
                    }
                }
            } catch (Throwable e) {
                Logger.err(e, "Problem loading weapons from file.");
                throw new IllegalArgumentException("Problem loading weapons from file.");
            }

            loadedFromFile = true;
            cachedCards = new ArrayList<>(weapons.size());
            for (Weapon weapon : weapons) {
                cachedCards.add(gson.fromJson(gson.toJson(weapon).toString(), weapon.getClass()));
            }

            return weapons;
        } else {
            ArrayList<Weapon> clonedWeapons = new ArrayList<>(cachedCards.size());
            for (Weapon weapon : cachedCards) {
                Weapon cloneWeapon = gson.fromJson(gson.toJson(weapon), weapon.getClass());
                clonedWeapons.add(cloneWeapon);
            }
            return clonedWeapons;
        }
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
     * Reload the weapon.
     */
    public void reload (Player player){
        for(Color color: getRechargeCost()){
            player.removeCube(color);
        }
        this.loaded = true;
    }

    public void payEffect (Player player, Effect effect) {
        ArrayList<Card.Color> cost = effect.getCost();

        for(Color color: cost){
            player.removeCube(color);
        }
    }

}



















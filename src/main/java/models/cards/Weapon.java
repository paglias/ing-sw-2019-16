package models.cards;

import com.google.gson.stream.JsonReader;
import models.Player;

import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
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

    // Which effects have been used during this turn?
    // {1,2} means primary and secondary for example
    // Used to make sure only one primary/secondary/tertiary can be used
    private ArrayList<Integer> usedEffects = new ArrayList<>();
    public ArrayList<Integer> getUsedEffects() { return usedEffects; }


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
            String[] weaponsNames = {"Cyberblade",   "Hellion",    "RocketLauncher", "VortexCannon",
                    "Electroscythe",  "LockRifle",    "Shockwave",    "Whisper",
                    "Flamethrower", "MachineGun",   "Shotgun",    "Zx2",
                    "Furnace",    "PlasmaGun",    "Sledgehammer",
                    "GrenadeLauncher",  "PowerGlove",   "Thor",
                    "Heatseeker",   "Railgun",    "TractorBeam"};
            ArrayList<Weapon> weapons = new ArrayList<>();

            try {
                for (String weaponName : weaponsNames) {
                    String weaponPath = File.separatorChar + "Weapons" + File.separatorChar + weaponName + ".json";
                    InputStreamReader weaponInput = new InputStreamReader(Weapon.class.getResourceAsStream(weaponPath));
                    JsonReader weapon = new JsonReader(weaponInput);
                    weapons.add(gson.fromJson(weapon, Weapon.class));
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



















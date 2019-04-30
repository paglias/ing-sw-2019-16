package models.cards;

import models.Player;
import models.Square;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

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
    private Boolean isSpawnPoint;

    // Weapons are loaded when created / picked from a deck
    private boolean loaded = true;

    // Weapons are only loaded from file once
    private static boolean weaponsLoadedFromFile = false;
    private static ArrayList<Weapon> cachedWeapons;

    /**
     * Gets recharge cost.
     *
     * @return the recharge cost
     */
    public ArrayList<Color> getRechargeCost() {
        return rechargeCost;
    }

    /**
     * Load weapons from file.
     *
     * @return the weapons
     */
    public static ArrayList<Weapon> loadWeapons () {
        Gson gson = new Gson();

        if (!weaponsLoadedFromFile) {
            File weaponsFolder = new File(Weapon.class.getResource("/Weapons").getPath());
            File[] listOfWeaponsFiles = weaponsFolder.listFiles();
            ArrayList<Weapon> weapons = new ArrayList<>();

            try {
                for (File file : listOfWeaponsFiles) {
                    if (file.isFile()) {
                        weapons.add(gson.fromJson(new FileReader(file.getAbsolutePath()), Weapon.class));
                    }
                }
            } catch (java.io.FileNotFoundException e) {
                e.printStackTrace();
                throw new IllegalArgumentException("Problem loading weapons from file.");
            }

            weaponsLoadedFromFile = true;
            cachedWeapons = new ArrayList<>(weapons.size());
            for (Weapon weapon : weapons) {
                cachedWeapons.add(gson.fromJson(gson.toJson(weapon).toString(), weapon.getClass()));
            }

            return weapons;
        } else {
            ArrayList<Weapon> clonedWeapons = new ArrayList<>(cachedWeapons.size());
            for (Weapon weapon : cachedWeapons) {
                Weapon cloneWeapon = gson.fromJson(gson.toJson(weapon).toString(), weapon.getClass());
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

    }

    /**
     * Reload the weapon.
     */
    public void reload (){
        // TODO
        //load weapon, set loaded to TRUE
        //see Player.reload method
    }

    /**
     * @param damagingPlayer
     * @param playerTarget
     */
    public void shoot(Player damagingPlayer,Player playerTarget){
        if(playerTarget.getPosition()==damagingPlayer.getPosition()) {
            playerTarget.addDamage(damagingPlayer);
        }
        else throw new IllegalArgumentException("Not usable method");
    }

    public void mark(Player damagingPlayer, Player playerTarget){
        if(playerTarget.getPosition().equals(damagingPlayer.getPosition())) {
            playerTarget.addMark(damagingPlayer);
        }
        else throw new IllegalArgumentException("Not usable method");
    }
    public void move(Player damagingPlayer,Square newPosition) {
        List<Square>CanAccessDirectly= damagingPlayer.getPosition().getCanAccessDirectly();
        if(CanAccessDirectly.contains(newPosition)){
        damagingPlayer.move(newPosition);
        }
        else throw new IllegalArgumentException("Not usable method");
    }
    public void shootOneAwayView(Player damagingPlayer, Player playerTarget){
        List<Square> CanAccessDirectly = damagingPlayer.getPosition().getCanAccessDirectly();
        Square position = playerTarget.getPosition();
        if(CanAccessDirectly.contains(position)&& damagingPlayer.getPosition().getCanView().contains(position)){
            playerTarget.addDamage(damagingPlayer);
        }
        else throw new IllegalArgumentException("Not usable method");
    }
    public void markOneAwayView(Player damagingPlayer,Player playerTarget){
        List<Square> CanAccessDirectly = damagingPlayer.getPosition().getCanAccessDirectly();
        Square position = playerTarget.getPosition();
        if(CanAccessDirectly.contains(position)&& damagingPlayer.getPosition().getCanView().contains(position)){
            playerTarget.addMark(damagingPlayer);
        }
        else throw new IllegalArgumentException("Not usable method");
    }
    public void shootTwoAwayView(Player damagingPlayer, Player playerTarget){
        List<Square> CanAccessDirectly = damagingPlayer.getPosition().getCanAccessDirectly();
        Square position = playerTarget.getPosition();
        if(!CanAccessDirectly.contains(position)&& damagingPlayer.getPosition().getCanView().contains(position)){
            playerTarget.addDamage(damagingPlayer);
        }
        else throw new IllegalArgumentException("Not usable method");
    }
    public void markTwoAwayView(Player damagingPlayer, Player playerTarget){
        List<Square> CanAccessDirectly = damagingPlayer.getPosition().getCanAccessDirectly();
        Square position = playerTarget.getPosition();
        if(!CanAccessDirectly.contains(position)&& damagingPlayer.getPosition().getCanView().contains(position)){
            playerTarget.addMark(damagingPlayer);
        }
    }
    public void shootEvery(Player damagingPlayer, ArrayList<Player> PlayerTargets) {
        for (Player Players : PlayerTargets) {
            if (damagingPlayer.getPosition().equals(Players.getPosition())) {
                Players.addDamage(damagingPlayer);
            }
            else throw new IllegalArgumentException("Not usable method");
        }
    }
    public void markEvery(Player damagingPlayer, ArrayList<Player> PlayerTargets) {
        for (Player Players : PlayerTargets) {
            if (damagingPlayer.getPosition().equals(Players.getPosition())) {
                Players.addMark(damagingPlayer);
            }
            else throw new IllegalArgumentException("Not usable method");
        }
    }
    public void shootRoomCanSee(Player damagingPlayer, Square targetSquare, List<Player> playerTargets) {
        if (!damagingPlayer.getPosition().getCanView().contains(targetSquare)) {
            throw new IllegalArgumentException("Invalid target square, cannot see from player.");
        }

        for (Player player : playerTargets) {
            if (player.getPosition() == targetSquare) {
                player.addDamage(damagingPlayer);
            }
        }
    }
    public void shootEveryOneAwayView(Player damagingPlayer, ArrayList<Player> PlayerTargets) {
            for (Player Players : PlayerTargets) {
                List<Square> CanAccessDirectly = damagingPlayer.getPosition().getCanAccessDirectly();
                Square position = Players.getPosition();
                if(CanAccessDirectly.contains(position)&& damagingPlayer.getPosition().getCanView().contains(position)){
                    Players.addDamage(damagingPlayer);
                }
                else throw new IllegalArgumentException("Not usable method");
            }


    }
    public void markEveryOneAwayView(Player damagingPlayer, ArrayList<Player> PlayerTargets) {
        for (Player Players : PlayerTargets) {
            List<Square> CanAccessDirectly = damagingPlayer.getPosition().getCanAccessDirectly();
            Square position = Players.getPosition();
            if(CanAccessDirectly.contains(position)&& damagingPlayer.getPosition().getCanView().contains(position)){
                Players.addMark(damagingPlayer);
            }
            else throw new IllegalArgumentException("Not usable method");
        }


    }
    public void shootView(Player damagingPlayer, Player playerTarget) {
        List<Square> CanView = damagingPlayer.getPosition().getCanView();
        Square position = playerTarget.getPosition();
        if(CanView.contains(position)){
            playerTarget.addDamage(damagingPlayer);

        }
        else throw new IllegalArgumentException("Not usable method");


    }
    public void markView(Player damagingPlayer, Player playerTarget) {
        List<Square> CanView = damagingPlayer.getPosition().getCanView();
        Square position = playerTarget.getPosition();
        if(CanView.contains(position)){
            playerTarget.addMark(damagingPlayer);

        }
        else throw new IllegalArgumentException("Not usable method");

    }

    public void moveTarget(Player playerTarget, Square newPosition) {
        List<Square> CanAccessDirectly = playerTarget.getPosition().getCanAccessDirectly();
        if (CanAccessDirectly.contains(newPosition)) {
            playerTarget.move(newPosition);
        }
        else throw new IllegalArgumentException("Not usable method");

    }
    public void shootDirection(Player damagingPlayer, Player playerTarget) {
        Square position= damagingPlayer.getPosition();
        Square targetPosition= playerTarget.getPosition();
            if ((position.sameDirection(targetPosition) == true) && position.getColor() == targetPosition.getColor() &&
                    position.getCanView() == targetPosition.getCanView()) {
                playerTarget.addDamage(damagingPlayer);
            }
            else throw new IllegalArgumentException("Not usable method");
    }
    public void shootTargetView(Player damagingPlayer,Player playerTarget,Player secondTarget) {
        List<Square> CanView = damagingPlayer.getPosition().getCanView();
        Square position = playerTarget.getPosition();
        Square secondTargetPosition= secondTarget.getPosition();
        if(CanView.contains(position)){
            playerTarget.addDamage(damagingPlayer);
        }
        if(position.getCanView().contains(secondTargetPosition)){
            secondTarget.addDamage(damagingPlayer);
        }
        else throw new IllegalArgumentException("Not usable method");


    }
    public void shootSecondTargetView(Player damagingPlayer, Player playerTarget, Player secondTarget,Player thirdTarget) {
        List<Square> CanView = damagingPlayer.getPosition().getCanView();
        Square position = playerTarget.getPosition();
        Square secondTargetPosition = secondTarget.getPosition();
        Square thirdTargetPosition= thirdTarget.getPosition();
        if (CanView.contains(position)) {
            playerTarget.addDamage(damagingPlayer);
        }
        if (position.getCanView().contains(secondTargetPosition)){
            secondTarget.addDamage(damagingPlayer);
        }
        if(secondTargetPosition.getCanView().contains(thirdTargetPosition)){
            thirdTarget.addDamage(damagingPlayer);
        }
        else throw new IllegalArgumentException("Not usable method");

    }
    public void attractTarget(Player damagingPlayer, Player playerTarget, Square newPosition) {
        Square position= damagingPlayer.getPosition();
        Square targetPosition= playerTarget.getPosition();
        if((position.sameDirection(targetPosition)==true)&& position.getColor()==targetPosition.getColor() &&
                    position.getCanView()==targetPosition.getCanView()){
                playerTarget.move(newPosition);
            }
            else throw new IllegalArgumentException("Not usable method");
    }

    public void ShootCantSee(Player damagingPlayer, Player playerTarget){
        List<Square> CanView = damagingPlayer.getPosition().getCanView();
        Square position = playerTarget.getPosition();
        if(!CanView.contains(position)){
            playerTarget.addDamage(damagingPlayer);
        }
        else throw new IllegalArgumentException("Not usable method");

    }
}





















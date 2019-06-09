package models.cards;

import models.Player;
import models.Square;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.*;

public class Weapon extends Card {
    private String name;
    private ArrayList<Color> rechargeCost;
    private ArrayList<Color> cost;
    private ArrayList<WeaponEffect> primaryEffect;
    private ArrayList<WeaponEffect> secondaryEffect;
    private ArrayList<WeaponEffect> tertiaryEffect;

    private ArrayList<Player> playerTargets;
    private ArrayList<Square> positions;
    private Player damagingPlayer;
    private Square.Direction direction;

    // Weapons are loaded when created / picked from a deck
    private boolean loaded = true;

    // Weapons are only loaded from file once
    private static boolean weaponsLoadedFromFile = false;
    private static ArrayList<Weapon> cachedWeapons;

    public String getName () {return this.name; };

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




    public void setPlayerTargets(ArrayList<Player> playerTargets){this.playerTargets= playerTargets;}
    public void setDirection(Square.Direction direction){this.direction=direction;}
    public void setDamagingPlayer(Player damagingPlayer){this.damagingPlayer=damagingPlayer;}
    public void setPositions(ArrayList<Square> positions){this.positions = positions; };
    /**
     * Reload the weapon.
     */
    public void reload (Player player){
        for(Color color: getRechargeCost()){
            player.removeCube(color);
        }
        this.loaded = true;
    }

    /**
     * @param
     * @param
     */
    public void reset(){
        damagingPlayer=null;
        direction=null;
        playerTargets.clear();
        positions.clear();
        }




    public void shoot(){
        Player playerTarget= playerTargets.get(0);
        if(playerTarget.getPosition()==damagingPlayer.getPosition()) {
            playerTarget.addDamage(damagingPlayer);
            reset();
        }

        else throw new IllegalArgumentException("Not usable method");
    }


    public void mark(){
        Player playerTarget= playerTargets.get(0);
        if(playerTarget.getPosition().equals(damagingPlayer.getPosition())) {
            playerTarget.addMark(damagingPlayer);
            reset();
        }
        else throw new IllegalArgumentException("Not usable method");
    }
    public void move() {
        Square position= positions.get(0);
        List<Square>CanAccessDirectly= damagingPlayer.getPosition().getCanAccessDirectly();
        if(CanAccessDirectly.contains(position)){
        damagingPlayer.move(position);
        reset();
        }
        else throw new IllegalArgumentException("Not usable method");
    }
    public void shootOneAwayView(){
        List<Square> canAccessDirectly = damagingPlayer.getPosition().getCanAccessDirectly();
        Player playerTarget= playerTargets.get(0);
        Square position = playerTarget.getPosition();
        if(canAccessDirectly.contains(position)&& damagingPlayer.getPosition().getCanView().contains(position)){
            playerTarget.addDamage(damagingPlayer);
            reset();
        }
        else throw new IllegalArgumentException("Not usable method");
    }
    public void markOneAwayView(){
        List<Square> canAccessDirectly = damagingPlayer.getPosition().getCanAccessDirectly();
        Player playerTarget= playerTargets.get(0);
        Square position = playerTarget.getPosition();
        if(canAccessDirectly.contains(position)&& damagingPlayer.getPosition().getCanView().contains(position)){
            playerTarget.addMark(damagingPlayer);
            reset();
        }
        else throw new IllegalArgumentException("Not usable method");
    }
    public void shootTwoAwayView(){
        List<Square> canAccessDirectly = damagingPlayer.getPosition().getCanAccessDirectly();
        Player playerTarget= playerTargets.get(0);
        Square position = playerTarget.getPosition();
        if(!canAccessDirectly.contains(position)&& damagingPlayer.getPosition().getCanView().contains(position)){
            playerTarget.addDamage(damagingPlayer);
            reset();
        }
        else throw new IllegalArgumentException("Not usable method");
    }
    public void markTwoAwayView(){
        List<Square> canAccessDirectly = damagingPlayer.getPosition().getCanAccessDirectly();
        Player playerTarget= playerTargets.get(0);
        Square position = playerTarget.getPosition();
        if(!canAccessDirectly.contains(position)&& damagingPlayer.getPosition().getCanView().contains(position)){
            playerTarget.addMark(damagingPlayer);
            reset();
        }
    }
    public void shootEvery() {
        for (Player Players : playerTargets) {
                if (damagingPlayer.getPosition().equals(Players.getPosition())) {
                    Players.addDamage(damagingPlayer);
                }
            else throw new IllegalArgumentException("Not usable method");
        }
        reset(); // TODO make sure it's called even if an error occurs
    }
    public void markEvery() {
        for (Player Players : playerTargets) {
            if (damagingPlayer.getPosition().equals(Players.getPosition())) {
                Players.addMark(damagingPlayer);
            }
            else throw new IllegalArgumentException("Not usable method");
        }
        reset();
    }
    public void shootRoomCanSee() {
        Square targetSquare= positions.get(0);
        if (!damagingPlayer.getPosition().getCanView().contains(targetSquare)) {
            throw new IllegalArgumentException("Invalid target square, cannot see from player.");
        }

        for (Player player : playerTargets) {
            if (player.getPosition() == targetSquare) {
                player.addDamage(damagingPlayer);
            }
        }
        reset();
    }
    public void shootEveryOneAwayView() {
            for (Player Players : playerTargets) {
                List<Square> CanAccessDirectly = damagingPlayer.getPosition().getCanAccessDirectly();
                Square position = Players.getPosition();
                if(CanAccessDirectly.contains(position)&& damagingPlayer.getPosition().getCanView().contains(position)){
                    Players.addDamage(damagingPlayer);

                }
                else throw new IllegalArgumentException("Not usable method");
            }
        reset();


    }
    public void markEveryOneAwayView() {
        for (Player Players : playerTargets) {
            List<Square> CanAccessDirectly = damagingPlayer.getPosition().getCanAccessDirectly();
            Square position = Players.getPosition();
            if(CanAccessDirectly.contains(position)&& damagingPlayer.getPosition().getCanView().contains(position)){
                Players.addMark(damagingPlayer);
            }
            else throw new IllegalArgumentException("Not usable method");
        }
        reset();


    }
    public void shootView() {
        List<Square> CanView = damagingPlayer.getPosition().getCanView();
        Player playerTarget= playerTargets.get(0);
        Square position = playerTarget.getPosition();
        if(CanView.contains(position)){
            playerTarget.addDamage(damagingPlayer);
            reset();

        }
        else throw new IllegalArgumentException("Not usable method");


    }
    public void markView() {
        List<Square> CanView = damagingPlayer.getPosition().getCanView();
        Player playerTarget= playerTargets.get(0);
        Square position = playerTarget.getPosition();
        if(CanView.contains(position)){
            playerTarget.addMark(damagingPlayer);
            reset();

        }
        else throw new IllegalArgumentException("Not usable method");

    }

    public void moveTarget() {
        Player playerTarget= playerTargets.get(0);
        List<Square> CanAccessDirectly = playerTarget.getPosition().getCanAccessDirectly();
        Square newPosition=positions.get(0);
        if (CanAccessDirectly.contains(newPosition)) {
            playerTarget.move(newPosition);
            reset();
        }
        else throw new IllegalArgumentException("Not usable method");

    }
    public void shootDirection() {
        Square position= damagingPlayer.getPosition();

        int playersShot=0;
        for(Square square: position.filterDirectionSquare(positions, direction)){
            for(Player player:square.getPlayersHere(playerTargets)){
                player.addDamage(damagingPlayer);
                playersShot++;
            }
        }
        reset();
        if(playersShot==0){
            throw new IllegalArgumentException("Not usable method");
        }
    }
    public void shootTargetView() {
        List<Square> CanView = damagingPlayer.getPosition().getCanView();
        Player playerTarget= playerTargets.get(0);
        Square position = playerTarget.getPosition();
        Player secondTarget= playerTargets.get(1);
        Square secondTargetPosition= secondTarget.getPosition();
        if(CanView.contains(position)) {
            {
                playerTarget.addDamage(damagingPlayer);
            }
            if (position.getCanView().contains(secondTargetPosition)) {
                secondTarget.addDamage(damagingPlayer);
            }
            reset();
        }
        else throw new IllegalArgumentException("Not usable method");


    }
    public void shootSecondTargetView() {
        List<Square> CanView = damagingPlayer.getPosition().getCanView();
        Player playerTarget= playerTargets.get(0);
        Square position = playerTarget.getPosition();
        Player secondTarget= playerTargets.get(1);
        Square secondTargetPosition = secondTarget.getPosition();
        Player thirdTarget= playerTargets.get(2);
        Square thirdTargetPosition= thirdTarget.getPosition();
        if (CanView.contains(position)) {{
            playerTarget.addDamage(damagingPlayer);
        }
        if (position.getCanView().contains(secondTargetPosition)){
            secondTarget.addDamage(damagingPlayer);
        }
        if(secondTargetPosition.getCanView().contains(thirdTargetPosition)){
            thirdTarget.addDamage(damagingPlayer);
        }
        reset();
        }
        else throw new IllegalArgumentException("Not usable method");

    }
    public void attractTarget() {
        Square position= damagingPlayer.getPosition();
        Player playerTarget= playerTargets.get(0);
        Square targetPosition= playerTarget.getPosition();
        if(position.sameDirection(targetPosition,direction)){
                playerTarget.setPosition(position);
                reset();
            }
            else throw new IllegalArgumentException("Not usable method");
    }

    public void ShootCantSee(){
        List<Square> CanView = damagingPlayer.getPosition().getCanView();
        Player playerTarget= playerTargets.get(0);
        Square position = playerTarget.getPosition();
        if(!CanView.contains(position)){
            playerTarget.addDamage(damagingPlayer);
            reset();
        }
        else throw new IllegalArgumentException("Not usable method");

    }
    public void effect(WeaponAction.Type effect) {
        switch (effect) {
            case MARK_VIEW:
                this.markView();
            case SHOOT:
                this.shoot();
            case SHOOT_CANT_SEE:
                this.ShootCantSee();
            case MARK_TWO_AWAY_VIEW:
                this.markTwoAwayView();
            case MARK:
                this.mark();
            case SHOOT_VIEW:
                this.shootView();
            case SHOOT_ONE_AWAY_VIEW:
                this.shootOneAwayView();
            case MOVE_TARGET:
                this.moveTarget();
            case MOVE:
                this.move();
            case SHOOT_SECOND_TARGET_VIEW:
                this.shootSecondTargetView();
            case SHOOT_TARGET_VIEW:
                this.shootTargetView();
            case SHOOT_EVERY:
                this.shootEvery();
            case MARK_EVERY:
                this.markEvery();
            case SHOOT_EVERY_ONE_AWAY_VIEW:
                this.shootEveryOneAwayView();
            case MARK_EVERY_ONE_AWAY_VIEW:
                this.markEveryOneAwayView();
            case SHOOT_ROOM_CAN_SEE:
                this.shootRoomCanSee();
            case ATTRACT_TARGET:
                this.attractTarget();
            case SHOOT_DIRECTION:
                this.shootDirection();
        }
    }


    /*public ArrayList<Color> getSecondaryCost(){
        return secondaryCost;
    }
    public ArrayList<Color> getTertiaryCost(){
        return tertiaryCost;
    }
    public void payPrimaryCost(){
        if(damagingPlayer.getCubes().containsAll(cost)){
            for(Card.Color color:cost){
                damagingPlayer.removeCube(color);
            }
        }
    }
    public void paySecondaryCost(){
        if(damagingPlayer.getCubes().containsAll(secondaryCost)){
            for(Card.Color color:secondaryCost){
                damagingPlayer.removeCube(color);
            }
        }
    }
    public void payTertiaryCost(){
        if(damagingPlayer.getCubes().containsAll((tertiaryCost))){
            for(Card.Color color:tertiaryCost){
                damagingPlayer.removeCube(color);
            }
        }
    }


    public ArrayList<ArrayList<Effect>> getPrimaryEffect(){ return primaryEffect; }

    public ArrayList<ArrayList<Effect>> getSecondaryEffect(){return secondaryEffect; }

    public ArrayList<ArrayList<Effect>> getTertiaryEffect(){return tertiaryEffect; }*/
}



















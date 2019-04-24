package models;

import models.cards.Card;
import models.cards.PowerUp;
import models.cards.Weapon;
import models.decks.PowerUpsDeck;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

public class Player {
    private String nickname;
    private Color color;
    private Boolean isActive;               //TODO start of turn, set isActive next player. isActive check for every action?
    private Date startTurnDate;             //used for turn timer
    private int nDeaths;                    //number of deaths counted by skulls TODO Top score decreases each death
    private Boolean isFirstPlayer;
    private ArrayList<Card.Color> cubes;    //ammo available
    private ArrayList<String> points;       // TODO what is this? why not integer?
    private ArrayList<Player> marks;        //current marks, depending on player color
    private ArrayList<Player> damage;       //list of damage amount, depending on player color
    private ArrayList<PowerUp> powerUps;    //list of available power ups
    private ArrayList<Weapon> weapons;      //list of available weapons, maximum 3 TODO set limit to 3?
    private Square position;                //current position, updated when move happens
    private int moveCounter;                //TODO restore movecounter at startturn?

    /**
     * Gets cubes of a player. To be used to check necessary ammo.
     *
     * @return the cubes
     */
    public ArrayList<Card.Color> getCubes() {
        return cubes;
    }

    /**
     * Adds cubes of different types to the available cubes of the player.
     *
     * @param cubeColor the cube color
     */
    public void addsCubes(Card.Color cubeColor) {  //TODO MAXCUBES PER COLOR IS 3
        for (Card.Color color: this.cubes) {

            }
        this.cubes.add(cubeColor);
    }

    /**
     * Adds cubes of different types to the available cubes of the player.
     *
     * @param cubeColor the cube color
     */
    public void removesCubes(Card.Color cubeColor) {  //TODO CHECK IF CUBES ARE NOT ZERO
        this.cubes.remove(cubeColor);
    }

    /**
     * Gets move counter.
     *
     * @return the move counter
     */
    public int getMoveCounter() {
        return moveCounter;
    }

    /**
     * Decrease move counter, after a player moved.
     */
    public void decreaseMoveCounter() {
        this.moveCounter--;
    }

    /**
     * Sets move counter. Initial hard-set, in case of adrenaline/finalfrenzy.
     *
     * @param moveCounter the move counter
     */
    public void setMoveCounter(int moveCounter) {
        this.moveCounter = moveCounter;
    }

    /**
     * Set nickname of the player. Setter Used by gameBoard
     *
     * @param nickname the nickname
     */
    public void setNickname(String nickname){
        this.nickname = nickname;
    }

    /**
     * Gets nickname.
     *
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Sets active at the beginning of the turn
     *
     * @param isActive the is active
     */
    public void setActive(Boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * Gets marks.
     *
     * @return the marks
     */
    public ArrayList<Player> getMarks() {
        return marks;
    }

    /**
     * Sets marks.
     *
     * @param marks the marks
     */
    public void addMarks(ArrayList<Player> marks) {
        this.marks = marks;
    }

    /**
     * Adds a powerup to the arraylist of the player
     *
     * @param powerUp the power up
     */
    public void addPowerUps(PowerUp powerUp) {
        this.powerUps.add(powerUp);
    }

    /**
     * Remove power ups when a power up is used.
     *
     * @param powerUp the power up
     */
    public void removePowerUps(PowerUp powerUp) {
        this.powerUps.remove(powerUp);
    }

    /**
     * Sets position after player moves.
     *
     * @param position the position
     */
    public void setPosition(Square position) {
        this.position = position;
    }

    /**
     * Gets position.
     *
     * @return the position
     */
    public Square getPosition() {
        return position;
    }

    /**
     * Sets first player, once at the beginning of the game.
     *
     * @param firstPlayer the first player
     */
    public void setFirstPlayer(Boolean firstPlayer) {
        isFirstPlayer = firstPlayer;
    }

    /**
     *
     * Sets deaths. When called (player death), nDeaths grows by 1. Cannot decrease.
     */
    public void setnDeaths() {
        this.nDeaths = nDeaths++;
    }

    /**
     * Gets weapons.
     *
     * @return the weapons
     */
    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    /**
     * Add weapons to the player's available weapons (max is 3)
     *
     * @param weapon the weapon
     */
    public void addWeapons(Weapon weapon) {
        if (this.weapons.size()<=3) {
            this.weapons.add(weapon);
        }
        else {
            System.out.println("Limit of weapons reached");// TODO What happens when you want to switch one? exception?
        }
    }

    /**
     * Remove weapons. Used when you have 3 weapons and you want a 4th one.
     *
     * @param weapon the weapon
     */
    public void removeWeapons(Weapon weapon) {
        this.weapons.remove(weapon);
    }

    /**
     * Gets first player. Used for finalFrenzy
     *
     * @return the first player
     */
    public Boolean getFirstPlayer() {
        return isFirstPlayer;
    }


    /**
     * Gets damage. Used for scoring purposes and to check if the player is dead.
     *
     * @return the damage
     */
    public ArrayList<Player> getDamage() {
        return damage;
    }

    /**
     * Add damage.
     *
     * @param damagingPlayer the damaging player
     */
    public void addDamage(Player damagingPlayer){
        this.damage.add(damagingPlayer);
    }

    /**
     * Gets power ups. Used when you want to use a powerup.
     *
     * @return the power ups
     */
    public ArrayList<PowerUp> getPowerUps() {
        return powerUps;
    }


    /**
     * Move player.
     *
     * @param newPosition     the new position
     * @param currentPosition the current position
     * @param currentPlayer   the current player
     */
    public void movePlayer(Square newPosition, Square currentPosition, Player currentPlayer) {
        if (currentPlayer.getMoveCounter() <= 0 || currentPlayer.getMoveCounter() > 2) {
            System.out.println("Move is not possible");  //TODO use exceptions?
        }
        else {
            for (Square square : currentPosition.canAccessDirectly) {
                if (currentPosition.getCanAccessDirectly().contains(newPosition)) {
                    currentPlayer.setPosition(newPosition);
                    currentPlayer.decreaseMoveCounter();
                }
                else {
                    System.out.println("Move is not Possible");
                }
            }
        }
    }

    /**
     * Grab item, either weapon, power up or ammo cubes.
     *
     * @param currentPosition     the current position
     * @param currentPlayer       the current player
     * @param currentPowerUpsDeck the current power ups deck
     * @param currentWeaponsSlot  the current weapons slot TODO Associate to the current square?
     */
    public void grabItem(Square currentPosition, Player currentPlayer, PowerUpsDeck currentPowerUpsDeck,
                         WeaponsSlot currentWeaponsSlot)
    {
        //If you are on a spawnpoint, you will grab a weapon of your choice
        if (currentPosition.getSpawnPoint()){
            currentPlayer.addWeapons((currentWeaponsSlot.weaponChoice()));
        }
        else {
            //if the ammo picked has a powerup, add it to your powerups
            if (currentPosition.getAmmo().getHasPowerUp()) {
                currentPlayer.addPowerUps((PowerUp) currentPowerUpsDeck.pick());
            }
            else{
                //if the ammo picked has ammocubes, add them to your cubes
                // and decrease the cubes in the ammo card you grabbed
                while (currentPosition.getAmmo().getnBlueCubes()!=0) {
                    currentPlayer.addsCubes(Card.Color.BLUE);
                    currentPosition.getAmmo().decreasenBlueCubes();
                }
                while ((currentPosition.getAmmo().getnYellowCubes()!=0)){
                    currentPlayer.addsCubes(Card.Color.YELLOW);
                    currentPosition.getAmmo().decreasenYellowCubes();
                }
                while ((currentPosition.getAmmo().getnRedCubes()!=0)){
                    currentPlayer.addsCubes(Card.Color.RED);
                    currentPosition.getAmmo().decreasenRedCubes();
                }
            }
        }
    }

    /**
     * Shoot player. Adds damage, marks, moves players based on weapon effect
     *
     * @param currentPosition  the current position
     * @param currentPlayer    the current player
     * @param currentGameBoard the current game board
     * @param playerTarget     the player target
     * @param newPosition      the new position
     */
    public void shootPlayer(Square currentPosition, Player currentPlayer, GameBoard currentGameBoard,
                            Player playerTarget, Square newPosition){

        //initial check if any player can be shot
        for(Player otherPlayer : currentGameBoard.getPlayers()){
            //TODO REMOVE CURRENT PLAYER FROM TOTAL PLAYERS EVERY TURN?LOOP ON ITSELF

            //if canview of current position DOES NOT contain any position of any player

            if (!(currentPosition.getCanView().contains(otherPlayer.getPosition()))){
                System.out.println("No players can be shot");
            }
        }
        //if weapon is loaded, use weapon effects
        //TODO DECIDE HOW WEAPONS WORK
        for(Weapon availableWeapon : currentPlayer.getWeapons()){
            while (availableWeapon.isLoaded()){
                availableWeapon.dealDamage(playerTarget);
                availableWeapon.addMark(playerTarget);
                availableWeapon.movePlayer(playerTarget, newPosition);
            }
        }
    }
}







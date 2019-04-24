package models;

import models.cards.Card;
import models.cards.PowerUp;
import models.cards.Weapon;
import models.decks.PowerUpsDeck;
import java.awt.*;
import java.util.*;

public class Player {
    private String nickname;
    private Color color;
    private Boolean isActive;               //TODO start of turn, set isActive next player. isActive check for every action?
    private Date startTurnDate;             //used for turn timer
    private int nDeaths;                    //number of deaths counted by skulls TODO Top score decreases each death
    private Boolean isFirstPlayer;
    private ArrayList<Card.Color> cubes;    //ammo available
    private ArrayList<Integer> givenPoints; //points given at next death
    private ArrayList<String> points;       // TODO what is this? why not integer?
    private ArrayList<Player> marks;        //current marks, depending on player color
    private ArrayList<Player> damage;       //list of damage amount, depending on player color
    private ArrayList<PowerUp> powerUps;    //list of available power ups
    private ArrayList<Weapon> weapons;      //list of available weapons, maximum 3 TODO set limit to 3?
    private Square position;                //current position, updated when move happens
    private int moveCounter;                //TODO restore movecounter at startturn?
    private int actionCounter;              //remaining actions per turn
    private int adrenaline;                 //adrenaline counter, max 2
    private int totalPoints;                //total points of the current player
    private boolean isDead;                 //true is the player is currently dead, stays dead until next turn


    /**
     * Adds the value received to the total points of the player.
     * Totalpoints can never be set to a random value
     *
     * @param totalPoints the total points
     */
    public void addToTotalPoints(int totalPoints) {
        this.totalPoints = this.totalPoints + totalPoints;
    }

    /**
     * Gets total points.
     *
     * @return the total points
     */
    public int getTotalPoints() {
        return totalPoints;
    }

    /**
     * Sets given points. Receives an arraylist and replaces the existent.
     * Done at player creation and at finalfrenzy, when the values change.
     *
     * @param givenPoints the given points
     */

    public void setGivenPoints(ArrayList<Integer> givenPoints) {
        this.givenPoints.clear();
        this.givenPoints = givenPoints;
    }

    /**
     * Sets color. USED TO ASSIGN COLOR, GUI
     *
     * @param color the color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Gets action counter.
     *
     * @return the action counter
     */
    public int getActionCounter() {
        return actionCounter;
    }

    /**
     * Sets action counter.
     *
     * @param actionCounter the action counter
     */
    public void setActionCounter(int actionCounter) {
        this.actionCounter = actionCounter;
    }

    /**
     * Decrease action counter.
     */
    public void decreaseActionCounter() {
        this.actionCounter--;
    }

    /**
     * Gets adrenaline.
     *
     * @return the adrenaline
     */
    public int getAdrenaline() {
        return adrenaline;
    }

    /**
     * Sets adrenaline.
     *
     * @param adrenaline the adrenaline
     */
    public void setAdrenaline(int adrenaline) {
        if (adrenaline<0 || adrenaline >2){
            throw new IllegalArgumentException("Adrenaline must be >0 and < 2");
        }
        this.adrenaline = adrenaline;
    }

    /**
     * Increase adrenaline.
     */
    public void increaseAdrenaline() {
        if (adrenaline > 1){
            throw new IllegalArgumentException("Adrenaline must be less than 2");
        }
        this.adrenaline++;
    }

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
        this.cubes.add(cubeColor);
    }

    /**
     * Removes a cube types from the available cubes of the player.
     *
     * @param cubeColor the cube color
     */
    public void removesCubes(Card.Color cubeColor) {
        if (cubes.contains(cubeColor)) {
            this.cubes.remove(cubeColor);
        }
        else {
            System.out.println("There is no ammo of that color available");
        }
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
    public void setNickname(String nickname) {
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
     * Add marks.
     *
     * @param mark the mark
     */
    public void addMarks(Player mark) {
        //TODO SET MAX MARKS FOR EACH PLAYER
        this.marks.add(mark);
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
     * Gets power ups. Used when you want to use a powerup.
     *
     * @return the power ups
     */
    public ArrayList<PowerUp> getPowerUps() {
        return powerUps;
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
        if (this.weapons.size() <= 3) {
            this.weapons.add(weapon);
        } else {
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
    public void addDamage(Player damagingPlayer) {
        if (damage.size()<12) {
            this.damage.add(damagingPlayer);
        }
        else {
            System.out.println("Maximum damage has been reached");
        }
    }

    /**
     * Sets player to dead status for the turn.
     * //TODO Restore the status at the beginning of the next turn
     *
     * @param dead the dead
     */
    public void setDead(boolean dead) {
        isDead = dead;
    }

    /**
     * Move player.
     *
     * @param newPosition     the new position
     * @param currentPosition the current position
     * @param currentPlayer   the current player
     */
    //TODO JSON OF ALL POSSIBLE MOVES FROM ALL SQUARES ON THE MAP?
    public void movePlayer(Square newPosition, Square currentPosition, Player currentPlayer) {
        if (currentPlayer.getMoveCounter() <= 0 || currentPlayer.getMoveCounter() > 2) {
            System.out.println("Move is not possible");
        } else {
            for (Square square : currentPosition.canAccessDirectly) {
                if (currentPosition.getCanAccessDirectly().contains(newPosition)) {
                    currentPlayer.setPosition(newPosition);
                    currentPlayer.decreaseMoveCounter();
                } else {
                    System.out.println("Move is not Possible");
                }
            }
        }
        currentPlayer.decreaseMoveCounter();
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
                         WeaponsSlot currentWeaponsSlot) {
        //If you are on a spawnpoint, you will grab a weapon of your choice
        if (currentPosition.getSpawnPoint()) {
            currentPlayer.addWeapons((currentWeaponsSlot.weaponChoice()));
        } else {
            //if the ammo picked has a powerup, add it to your powerups
            if (currentPosition.getAmmo().getHasPowerUp()) {
                currentPlayer.addPowerUps((PowerUp) currentPowerUpsDeck.pick());
            } else {
                //if the ammo picked has ammocubes, add them to your cubes
                // and decrease the cubes in the ammo card you grabbed
                while (currentPosition.getAmmo().getnBlueCubes() != 0) {
                    currentPlayer.addsCubes(Card.Color.BLUE);
                    currentPosition.getAmmo().decreasenBlueCubes();
                }
                while ((currentPosition.getAmmo().getnYellowCubes() != 0)) {
                    currentPlayer.addsCubes(Card.Color.YELLOW);
                    currentPosition.getAmmo().decreasenYellowCubes();
                }
                while ((currentPosition.getAmmo().getnRedCubes() != 0)) {
                    currentPlayer.addsCubes(Card.Color.RED);
                    currentPosition.getAmmo().decreasenRedCubes();
                }
            }
        }
        decreaseActionCounter();
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
                            Player playerTarget, Square newPosition) {

        //initial check if any player can be shot
        for (Player otherPlayer : currentGameBoard.getPlayers()) {
            //TODO REMOVE CURRENT PLAYER FROM TOTAL PLAYERS EVERY TURN?LOOP ON ITSELF

            //if canview of current position DOES NOT contain any position of any player

            if (!(currentPosition.getCanView().contains(otherPlayer.getPosition()))) {
                System.out.println("No players can be shot");
            }
        }
        //if weapon is loaded, use weapon effects
        //TODO WAITING FOR WEAPONS EFFECT
        if (currentPlayer.getWeapons().isEmpty()) {
            System.out.println("No weapon is available");
        } else {
            for (Weapon availableWeapon : currentPlayer.getWeapons()) {
                if (availableWeapon.isLoaded()) {
                    availableWeapon.dealDamage(playerTarget);
                    availableWeapon.addMark(playerTarget);
                    availableWeapon.movePlayer(playerTarget, newPosition);
                } else {
                    System.out.println("No weapon is loaded");
                }
            }
        }
        //Add adrenaline if damage reaches 2 or 5, only if it is less than 1 and less than 2
        //TODO ADRENALINE CHANGES COUNTER VALUES IN CONTROLLER?
        if (playerTarget.getDamage().size() > 2) {
            if (playerTarget.getAdrenaline() < 1) {
                playerTarget.increaseAdrenaline();
            }
        }
        if (playerTarget.getDamage().size() > 5) {
            if (playerTarget.getAdrenaline() < 2) {
                playerTarget.increaseAdrenaline();
            }
        }
        if (playerTarget.getDamage().size() > 10) {
            playerTarget.setDead(true);
            //returns the last item of the arraylist
            if (givenPoints != null && !givenPoints.isEmpty()) {
                int deathPoints = givenPoints.get(givenPoints.size() - 1);
                 addToTotalPoints(deathPoints);
            }
            else {
                //if givenPoints is empty, the players has been killed more than 6 times, he still awards 1 point
                int deathPoints = 1;
                addToTotalPoints(deathPoints);
            }
        }
        if (playerTarget.getDamage().size() > 11) {
            playerTarget.addMarks(currentPlayer);
        }
    }
}







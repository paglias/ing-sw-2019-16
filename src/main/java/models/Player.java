package models;

import models.cards.Ammo;
import models.cards.Card;
import models.cards.PowerUp;
import models.cards.Weapon;
import models.decks.AmmoDeck;
import models.decks.PowerUpsDeck;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Player {
    private String nickname;
    private Color color; // TODO needed?
    private Boolean isActive;               //TODO start of turn, set isActive next player. isActive check for every action?
    private Date startTurnDate;             //used for turn timer
    private int nDeaths = 0;
    private Boolean isFirstPlayer;
    private ArrayList<Card.Color> cubes = new ArrayList<>();    //ammo available
    private ArrayList<Integer> givenPoints; // Points given at next death
    private ArrayList<String> points;       // TODO what is this? why not integer?
    private ArrayList<Player> marks = new ArrayList<>();        //current marks, depending on player color
    private ArrayList<Player> damage = new ArrayList<>();       //list of damage amount, depending on player color
    private ArrayList<PowerUp> powerUps = new ArrayList<>();    //list of available power ups
    private ArrayList<Weapon> weapons = new ArrayList<>();      //list of available weapons, maximum 3 TODO set limit to 3?
    private Square position;                //current position, updated when move happens
    private int moveCounter;                //TODO restore movecounter at startturn?
    private int actionCounter;              //remaining actions per turn
    private int adrenaline;                 //adrenaline counter, max 2
    private int totalPoints = 0;            //total points of the current player
    private boolean isDead = false;         //true is the player is currently dead, stays dead until next turn
    private int SPosition;


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
    public Color GetColor() {return this.color;}

    /**
     * Sets color. USED TO ASSIGN COLOR, GUI TODO used where?
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
        if (adrenaline < 0 || adrenaline > 2) {
            throw new IllegalArgumentException("Adrenaline must be >0 and < 2");
        }
        this.adrenaline = adrenaline;
    }

    /**
     * Increase adrenaline.
     */
    public void increaseAdrenaline() {
        if (adrenaline > 1) {
            throw new IllegalArgumentException("Adrenaline must be less than 2");
        }
        this.adrenaline++;
    }

    /**
     * Gets cubes of a player. To be used to check necessary ammo.
     *
     * @return the cubes
     */
    public List<Card.Color> getCubes() {
        return cubes;
    }

    /**
     * Adds cubes of different types to the available cubes of the player.
     *
     * @param cubeColor the cube color
     */
    public void addCube(Card.Color cubeColor) {
        // TODO MAXCUBES PER COLOR IS 3
        this.cubes.add(cubeColor);
    }

    /**
     * Removes a cube types from the available cubes of the player.
     *
     * @param cubeColor the cube color
     */
    public void removeCube(Card.Color cubeColor) {
        if (cubes.contains(cubeColor)) {
            this.cubes.remove(cubeColor);
        } else {
            throw new IllegalArgumentException("No cube of given color to remove.");
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
     * Sets move counter. Initial hard-set, in case of adrenaline/finalfrenzy.
     *
     * @param moveCounter the move counter
     */
    public void setMoveCounter(int moveCounter) {
        this.moveCounter = moveCounter;
    }

    /**
     * Decrease move counter, after a player moved.
     */
    public void decreaseMoveCounter() {
        this.moveCounter--;
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
     * Set nickname of the player. Setter Used by gameBoard
     *
     * @param nickname the nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
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
    public void addMark(Player mark) {
        // TODO SET MAX MARKS FOR EACH PLAYER
        this.marks.add(mark);
    }

    /**
     * Adds a powerup to the arraylist of the player
     *
     * @param powerUp the power up
     */
    public void addPowerUp(PowerUp powerUp) {
        this.powerUps.add(powerUp);
    }

    /**
     * Remove power ups when a power up is used.
     *
     * @param powerUp the power up
     */
    public void removePowerUp(PowerUp powerUp) {
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
     * Gets position.
     *
     * @return the position
     */
    public Square getPosition() {
        return position;
    }

    public void setPosition(int SPosition){
        this.SPosition=SPosition;
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
     * Gets first player. Used for finalFrenzy
     *
     * @return the first player
     */
    public Boolean getFirstPlayer() {
        return isFirstPlayer;
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
    public void increaseNDeaths() {
        this.nDeaths++;
    }

    /**
     * Gets deaths.
     *
     * @return the n deaths
     */
    public int getNDeaths() {
        return nDeaths;
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
    public void addWeapon(Weapon weapon) {
        if (this.weapons.size() <= 3) { // TODO allowed 4?
            this.weapons.add(weapon);
        } else {
            throw new IllegalArgumentException("Limit of weapons reached");
        }
    }

    /**
     * Remove weapons. Used when you have 3 weapons and you want a 4th one.
     *
     * @param weapon the weapon
     */
    public void removeWeapon(Weapon weapon) {
        this.weapons.remove(weapon);
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
        if (damage.size() < 12) {
            this.damage.add(damagingPlayer);
        } else {
            throw new IllegalArgumentException("Maximum damage has been reached");
        }
    }

    /**
     * Gets dead status.
     *
     * @return if dead
     */
    public boolean isDead() {
        return isDead;
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
     * Move player, generic move function.
     * Called by particular actions if a move is possible once that action has been chosen.
     *
     * @param newPosition the new position
     */
    //TODO JSON OF ALL POSSIBLE MOVES FROM ALL SQUARES ON THE MAP?
    // TODO multiple moves
    public void move(Square newPosition) {
        Square currentPosition = getPosition();
        List<Square> canAccessSquares = currentPosition.getCanAccessDirectly();

        if (getMoveCounter() <= 0 || canAccessSquares.isEmpty()) {
            throw new IllegalArgumentException("Move is not possible");
        } else {
            for (Square square : canAccessSquares) {
                if (currentPosition.getCanAccessDirectly().contains(newPosition)) {
                    setPosition(newPosition);
                    decreaseMoveCounter();
                    break;
                } else {
                    throw new IllegalArgumentException("Move is not possible");
                }
            }
        }
    }

    /**
     * Grab item, either weapon, power up or ammo cubes.
     *
     * @param powerUpsDeck the current power ups deck
     * @param ammoDeck the current ammo deck
     * @param weaponsSlot  the current weapons slot TODO Associate to the current square?
     * @param weaponToPick the weapon to pick
     */

    //TODO HOW TO REMOVE POWERUPDECK, CURRENTWEAPONSLOT? save gameboard inside player? since it's always the same
    public void grabItem(PowerUpsDeck powerUpsDeck, AmmoDeck ammoDeck, WeaponsSlot weaponsSlot, Weapon weaponToPick) {
        Square currentPosition = getPosition();

        //If you are on a spawnpoint, you will grab a weapon of your choice
        if (currentPosition.isSpawnPoint()) {
            addWeapon(weaponsSlot.weaponChoice(weaponToPick));
        } else {
            Ammo ammo = currentPosition.getAmmo();

            //if the ammo picked has a powerup, add it to your powerups
            if (ammo.getHasPowerUp()) {
                addPowerUp((PowerUp) powerUpsDeck.pick());
            }

            //if the ammo picked has ammocubes, add them to your cubes
            int blueCubes = ammo.getBlueCubes();
            while (blueCubes != 0) {
                addCube(Card.Color.BLUE);
                blueCubes--;
            }

            int yellowCubes = ammo.getYellowCubes();
            while ((yellowCubes != 0)) {
                addCube(Card.Color.YELLOW);
                yellowCubes--;
            }

            int redCubes = ammo.getRedCubes();
            while ((redCubes != 0)) {
                addCube(Card.Color.RED);
                redCubes--;
            }

            ammoDeck.discard(ammo);
        }
    }

    /**
     * Shoot player. Adds damage, marks, moves players based on weapon effect
     *
     * @param currentGameBoard the current game board
     * @param playerTarget     the player target
     * @param newPosition      the new position
     */

    public void shootPlayer(GameBoard currentGameBoard,
                            Player playerTarget, Square newPosition) {

        //initial check if any player can be shot
        //temp value to skip comparing currentplayer to currentplayer in players
        Square currentPosition = getPosition();
        boolean isCurrent = true;
        for (Player otherPlayer : currentGameBoard.getPlayers()) {
            if (!isCurrent) {
                //if canview of current position DOES NOT contain any position of any player
                if (!(currentPosition.getCanView().contains(otherPlayer.getPosition()))) {
                    System.out.println("No players can be shot");
                }
            } else {
                isCurrent = false;  //sets is current to false on the currentplay instance of the loop
            }
        }
        //if weapon is loaded, use weapon effects
        //TODO GENERIC "USE WEAPON"
        if (getWeapons().isEmpty()) {
            System.out.println("No weapon is available");
        } else {
            for (Weapon availableWeapon : getWeapons()) {
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
        //Player death scoring
        if (playerTarget.getDamage().size() > 10) {
            playerTarget.setDead(true);
            playerTarget.increaseNDeaths();

            //assigns values in givenPoints arraylist to players
            //check if givenPoints is empty
            if (givenPoints != null && !givenPoints.isEmpty()) {
                Iterator<Integer> iterator = givenPoints.iterator();
                while (!givenPoints.isEmpty() && iterator.hasNext()) {
                    int deathPoints = givenPoints.get(givenPoints.size() - 1);
                    getDamage().stream();

                    //TODO

                    givenPoints.remove(givenPoints.size()-1);
                }
            } else {
                //if givenPoints is empty, the players has been killed more than 6 times,
                // he still awards 1 point to the killer
                int deathPoints = 1;
                addToTotalPoints(deathPoints);
            }
            //if there are no more skulls, activate finalfrenzy
            //TODO SHOULD THE CONTROLLER DO THIS?
            // TODO currentGameBoard.skulls.decreaseSkullsRemaining() decreasenSkulls();
            /*if (currentGameBoard.getSkulls() == 0) {
                currentGameBoard.finalFrenzy();
            }*/
        }
        if (playerTarget.getDamage().size() > 11) {
            playerTarget.addMark(this);  //TODO THIS HERE MAKES SENSE?
        }
    }

    /**
     * Reload. Calls reload method on the weapon the user wants to reload.
     *
     * @param weaponToReload the weapon to reload
     */
    public void reload (Weapon weaponToReload) {
        if (getCubes().containsAll(weaponToReload.getRechargeCost())) {
            weaponToReload.reload();
        } else {
            System.out.println("Weapon cannot be reloaded");
        }
    }

    //Specific action possibilities

    /**
     * Move action. Specific move action, non finalFrenzy
     *
     * @param newPosition     the new position
     * @param currentPosition the current position
     */
    public void moveAction(Square newPosition, Square currentPosition) {
        setMoveCounter(3);
        while (getMoveCounter()>0) {
            move(newPosition);
        }
        decreaseActionCounter();
    }

    /**
     * Grab action. Specific move action, non finalFrenzy
     *
     * @param currentPowerUpsDeck the current power ups deck
     * @param currentWeaponsSlot  the current weapons slot
     */
    public void grabAction(PowerUpsDeck currentPowerUpsDeck, WeaponsSlot currentWeaponsSlot,
                           AmmoDeck ammoDeck,
                           Weapon newWeapon, Square newPosition) {
        if (getAdrenaline() == 0) {
            setMoveCounter(1);
            while (getMoveCounter()>0) {
                move(newPosition);
            }
            grabItem(currentPowerUpsDeck, ammoDeck, currentWeaponsSlot, newWeapon);
        }
        if (getAdrenaline() == 1) {
            setMoveCounter(2);
            grabItem(currentPowerUpsDeck, ammoDeck, currentWeaponsSlot, newWeapon);
        }
        decreaseActionCounter();
    }

    /**
     * Shoot action. Specific shoot action, non finalFrenzy.
     */
    public void shootAction(GameBoard currentGameBoard, Player playerTarget, Square newShootPosition,
                            Square newPosition){
        if (getAdrenaline()<=1){
            setMoveCounter(0);
            shootPlayer(currentGameBoard, playerTarget, newShootPosition);
        }
        else{
            setMoveCounter(1);
            while (getMoveCounter()>0) {
                move(newPosition);
            }
            shootPlayer(currentGameBoard, playerTarget, newShootPosition);
        }
        decreaseActionCounter();
    }

    /**
     * Move action for players whose turn is before the first player.
     *
     * @param currentPowerUpsDeck the current power ups deck
     * @param currentWeaponsSlot  the current weapons slot
     * @param newWeapon           the new weapon
     */
    public void finalFrenzyBeforeGrab(PowerUpsDeck currentPowerUpsDeck, WeaponsSlot currentWeaponsSlot,
                                      Weapon newWeapon, Square newPosition, AmmoDeck ammoDeck){
        setMoveCounter(3);
        while (getMoveCounter()>0) {
            move(newPosition);
        }
        grabItem (currentPowerUpsDeck, ammoDeck, currentWeaponsSlot, newWeapon);
        decreaseActionCounter();
    }

    /**
     * Shoot action for players whose turn is before the first player.
     *
     * @param weaponToReload the weapon to reload
     */
    public void finalFrenzyBeforeShoot(Weapon weaponToReload, GameBoard currentGameBoard,
                                       Player playerTarget, Square newShootPosition, Square newPosition){
        setMoveCounter(2);
        while (getMoveCounter()>0) {
            move(newPosition);
        }
        reload(weaponToReload);
        shootPlayer(currentGameBoard, playerTarget, newShootPosition);
        decreaseActionCounter();
    }

    /**
     * Move action for players whose turn is after the first player.
     *
     * @param newPosition the new position
     */
    public void finalFrenzyAfterMove(Square newPosition){
        setMoveCounter(4);
        while (getMoveCounter()>0) {
            move(newPosition);
        }
        decreaseActionCounter();
    }

    /**
     * Grab action for players whose turn is after the first player.
     *
     * @param currentPowerUpsDeck the current power ups deck
     * @param currentWeaponsSlot  the current weapons slot
     * @param newWeapon           the new weapon
     */
    public void finalFrenzyAfterGrab(PowerUpsDeck currentPowerUpsDeck, WeaponsSlot currentWeaponsSlot,
                                     AmmoDeck ammoDeck,
                                     Weapon newWeapon, Square newPosition){
        setMoveCounter(2);
        while (getMoveCounter()>0) {
            move(newPosition);
        }
        grabItem (currentPowerUpsDeck, ammoDeck, currentWeaponsSlot, newWeapon);
        decreaseActionCounter();
    }

    /**
     * Shoot action for players whose turn is after the first player.
     *
     * @param weaponToReload the weapon to reload
     */
    public void finalFrenzyAfterShoot (Weapon weaponToReload, GameBoard currentGameBoard,
                                      Player playerTarget, Square newShootPosition, Square newPosition){
        setMoveCounter(1);
        while (getMoveCounter()>0) {
            move(newPosition);
        }
        reload(weaponToReload);
        shootPlayer(currentGameBoard, playerTarget, newShootPosition);
    }
}
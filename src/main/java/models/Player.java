package models;

import models.cards.Ammo;
import models.cards.Card;
import models.cards.PowerUp;
import models.cards.Weapon;
import models.decks.AmmoDeck;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.*;


public class Player {
    private String nickname;
    private Color color;
    private Boolean isActive;                                   //TODO start of turn, set isActive next player. isActive check for every action?
    private Date startTurnDate;                                 //used for turn timer
    private int nDeaths = 0;
    private Boolean isFirstPlayer;
    private ArrayList<Card.Color> cubes = new ArrayList<>();    //ammo available
    private ArrayList<Integer> givenPoints = new ArrayList<>(); // Points given at next death
    private ArrayList<Player> marks = new ArrayList<>();        //current marks, depending on player color
    private ArrayList<Player> damage = new ArrayList<>();       //list of damage amount, depending on player color
    private ArrayList<PowerUp> powerUps = new ArrayList<>();    //list of available power ups
    private ArrayList<Weapon> weapons = new ArrayList<>();      //list of available weapons, maximum 3 TODO set limit to 3?
    private Square position;                                    //current position, updated when move happens
    private int moveCounter;                                    //TODO restore movecounter at startturn?
    private int actionCounter;                                  //remaining actions per turn
    private int adrenaline;                                     //adrenaline counter, max 2
    private int totalPoints = 0;                                //total points of the current player
    private boolean isDead = false;                             //true is the player is currently dead, stays dead until next turn


    /**
     * Player constructor. Instantiates a new Player.
     * Adds one cube of each color, sets points the player can give.
     * Sets initial counter values for adrenaline, actionCounter.
     */
    public Player() {
        //Initial points given for each Player, order is inverted so the last element
        //of the arraylist can be used as first
        setGivenPoints(new ArrayList<>(Arrays.asList(1, 1, 2, 4, 6, 8)));

        addCube(Card.Color.YELLOW);
        addCube(Card.Color.BLUE);
        addCube(Card.Color.RED);

        //Assign initial values, 1 ammo for each color, set counters, add pointsGiven (order is inverted, see above)
        //Set movecounter to 3
        setMoveCounter(3); //TODO can we remove this?
        setActionCounter(2);
        setAdrenaline(0);
    }

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
     * Sets color for the current player at the beginning of the game.
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
            throw new IllegalArgumentException("Adrenaline must be greater than 0 and less than 2");
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
     * Checks if there are less than 3 cubes
     *
     * @param cubeColor the cube color
     */
    public void addCube(Card.Color cubeColor) {
        int nYellow = 0;
        int nRed = 0;
        int nBlue = 0;
        for (Card.Color color : this.getCubes()){
           if (color == Card.Color.YELLOW){
               nYellow++;
           }
           if (color == Card.Color.RED){
               nRed++;
           }
           if (color == Card.Color.BLUE){
               nBlue++;
           }
       }
        //Checks there are already 3 cubes of a color. If there are less, the cube is added.
        if ((cubeColor == Card.Color.BLUE && nBlue >= 3) ||
                (cubeColor == Card.Color.YELLOW && nYellow >= 3) ||
                (cubeColor == Card.Color.RED && nRed >= 3)){

            throw new IllegalArgumentException("Maximum ammo reached");
        }
        else {
            this.cubes.add(cubeColor);
        }
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
     * Sets move counter. Initial hard-set, in case of adrenaline/finalfrenzy/actions.
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
     * Add marks. Checks if there are already 3 marks of a player in getMarks.
     *
     * @param mark the mark
     */
    public void addMark(Player mark) {
        long i = getMarks()
                .stream()
                .filter(p -> p.getNickname().equals(mark.getNickname()))
                .count();
            if (i >= 3) {
                throw new IllegalArgumentException("Maximum marks for that player has been reached");
            }
            else {
                this.marks.add(mark);
            }
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
     * @param newWeapon the weapon
     */
    public void addWeapon(Weapon newWeapon) {
        if (this.weapons.size() < 3) {
            this.weapons.add(newWeapon);
        }
        else if (this.weapons.size()==3) {
            throw new IllegalArgumentException("Weapon limit reached. Remove a weapon first");
        }
        else{
            throw new IllegalArgumentException("Adding that weapon is not possible");
            }
        }

    /**
     * Remove weapons. Used when you have 3 weapons and you want a 4th one.
     * Discards the weapon removed
     *
     * @param weapon the weapon
     */
    public void removeWeapon(Weapon weapon) {
        if (this.getWeapons().contains(weapon)){
            this.weapons.remove(weapon);
            GameBoard gameBoard = new GameBoard();  //TODO CHECK THIS.
            gameBoard.getWeaponsDeck().discard(weapon);
        }
        else{
            throw new IllegalArgumentException("You cannot remove that weapon");
        }

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
    public void move(Square newPosition) {
        Square currentPosition = getPosition();
        List<Square> canAccessSquares = currentPosition.getCanAccessDirectly();

        if (canAccessSquares.isEmpty()) {
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
     * Move random. //TODO Why is it different from normal move?
     *
     * @param newPosition the new position
     */
    public void moveRandom(Square newPosition) {
        this.setPosition(newPosition);

    }

    /**
     * Grab item, either weapon, power up or ammo cubes.
     *
     * @param weaponsSlot  the current weapons slot TODO Associate to the current square?
     * @param weaponToPick the weapon to pick
     */

    public void grabItem(GameBoard currentGameBoard, WeaponsSlot weaponsSlot, Weapon weaponToPick) {
        Square currentPosition = getPosition();

        //If you are on a spawnpoint, you will grab a weapon of your choice
        if (currentPosition.isSpawnPoint()) {
            addWeapon(weaponsSlot.weaponChoice(weaponToPick));
        } else {
            Ammo ammo = currentPosition.getAmmo();

            //if the ammo picked has a powerup, add it to your powerups
            if (ammo.getHasPowerUp()) {
                addPowerUp((PowerUp) currentGameBoard.getPowerUpsDeck().pick());
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

            currentGameBoard.getAmmoDeck().discard(ammo);
        }
    }

    /**
     * Shoot player. Adds damage, marks, moves players based on weapon effect
     *
     * @param currentGameBoard the current game board
     * @param playerTarget     the player target
     * @param newPosition      the new position
     */

    public void shootPlayer(GameBoard currentGameBoard, Player playerTarget,
                            Square newPosition, Weapon weapon) {

        //initial check if any player can be shot
        //temp value to skip comparing currentplayer to currentplayer in players
        Square currentPosition = getPosition();
        boolean isCurrent = true;
        for (Player otherPlayer : currentGameBoard.getPlayers()) {
            if (!isCurrent) {
                //if canview of current position DOES NOT contain any position of any player
                if (!(currentPosition.getCanView().contains(otherPlayer.getPosition()))) {
                    throw new IllegalArgumentException ("No players can be shot");
                }
            } else {
                isCurrent = false;  //sets is current to false on the currentplay instance of the loop
            }
        }
        //if weapon is loaded, use weapon effects
        if (getWeapons().isEmpty()) {
            throw new IllegalArgumentException("No weapon is available");
        } else {
            for (Weapon availableWeapon : getWeapons()) {
                if (availableWeapon.isLoaded()) {
                    availableWeapon.dealDamage(playerTarget);
                    availableWeapon.addMark(playerTarget);
                    availableWeapon.movePlayer(playerTarget, newPosition);
                } else {
                    throw new IllegalArgumentException("No weapon is loaded");
                }
            }
        }
        //Add adrenaline if damage reaches 2 or 5, only if it is less than 1 and less than 2
        if (playerTarget.getDamage().size() > 2 && playerTarget.getAdrenaline() < 1) {
            playerTarget.increaseAdrenaline();
        }
        if (playerTarget.getDamage().size() > 5 && playerTarget.getAdrenaline() < 2) {
            playerTarget.increaseAdrenaline();
        }
        //Player death. The 11th damage point is the killshot.
        if (playerTarget.getDamage().size() > 10) {
            playerTarget.setDead(true);
            playerTarget.increaseNDeaths();
            calculateDeathPoints(currentGameBoard);

            //if there are no more skulls, activate finalfrenzy
            // TODO currentGameBoard.skulls.decreaseSkullsRemaining() decreasenSkulls();
            /*if (currentGameBoard.getSkulls() == 0) {
                currentGameBoard.finalFrenzy();
            }*/
        }

        //Player overkill
        if (playerTarget.getDamage().size() > 11) {
            playerTarget.addMark(this);
        }
    }

    /**
     * Reload. Calls reload method on the weapon the user wants to reload.
     *
     * @param weaponToReload the weapon to reload
     */
    public void reload(Weapon weaponToReload) {
        if (getCubes().containsAll(weaponToReload.getRechargeCost())) {
            weaponToReload.reload(this);
        } else {
            throw new IllegalArgumentException("Weapon cannot be reloaded");
        }
    }

    /**
     * Move action. Specific move action, non finalFrenzy
     *
     * @param newPosition     the new position
     */
    public void moveAction(Square newPosition) {
        setMoveCounter(3);
        while (getMoveCounter() > 0) {
            move(newPosition);
        }
        decreaseActionCounter();
    }

    /**
     * Grab action. Specific move action, non finalFrenzy
     *
     * @param currentGameBoard   the current game board
     * @param currentWeaponsSlot the current weapons slot
     * @param ammoDeck           the ammo deck
     * @param newWeapon          the new weapon
     * @param newPosition        the new position
     */
    public void grabAction(GameBoard currentGameBoard, WeaponsSlot currentWeaponsSlot,
                           AmmoDeck ammoDeck,
                           Weapon newWeapon, Square newPosition) {
        if (getAdrenaline() == 0) {
            setMoveCounter(1);
            while (getMoveCounter() > 0) {
                move(newPosition);
            }
            grabItem(currentGameBoard, currentWeaponsSlot, newWeapon);
        }
        if (getAdrenaline() == 1) {
            setMoveCounter(2);
            grabItem(currentGameBoard, currentWeaponsSlot, newWeapon);
        }
        decreaseActionCounter();
    }

    /**
     * Shoot action. Specific shoot action, non finalFrenzy.
     *
     * @param currentGameBoard the current game board
     * @param playerTarget     the player target
     * @param newShootPosition the new shoot position
     * @param newPosition      the new position
     * @param weapon           the weapon
     */
    public void shootAction(GameBoard currentGameBoard, Player playerTarget, Square newShootPosition,
                            Square newPosition, Weapon weapon) {
        if (getAdrenaline() <= 1) {
            setMoveCounter(0);
            shootPlayer(currentGameBoard, playerTarget, newShootPosition, weapon);
        } else {
            setMoveCounter(1);
            while (getMoveCounter() > 0) {
                move(newPosition);
            }
            shootPlayer(currentGameBoard, playerTarget, newShootPosition, weapon);
        }
        decreaseActionCounter();
    }

    /**
     * Move action for players whose turn is before the first player.
     *
     * @param currentGameBoard   the current game board
     * @param currentWeaponsSlot the current weapons slot
     * @param newWeapon          the new weapon
     * @param newPosition        the new position
     */
    public void finalFrenzyBeforeGrab(GameBoard currentGameBoard, WeaponsSlot currentWeaponsSlot,
                                      Weapon newWeapon, Square newPosition) {
        setMoveCounter(3);
        while (getMoveCounter() > 0) {
            move(newPosition);
        }
        grabItem(currentGameBoard, currentWeaponsSlot, newWeapon);
        decreaseActionCounter();
    }

    /**
     * Shoot action for players whose turn is before the first player.
     *
     * @param weaponToReload   the weapon to reload
     * @param currentGameBoard the current game board
     * @param playerTarget     the player target
     * @param newShootPosition the new shoot position
     * @param newPosition      the new position
     * @param weapon           the weapon
     */
    public void finalFrenzyBeforeShoot(Weapon weaponToReload, GameBoard currentGameBoard,
                                       Player playerTarget, Square newShootPosition, Square newPosition,
                                       Weapon weapon) {
        setMoveCounter(2);
        while (getMoveCounter() > 0) {
            move(newPosition);
        }
        reload(weaponToReload);
        shootPlayer(currentGameBoard, playerTarget, newShootPosition, weapon);
        decreaseActionCounter();
    }

    /**
     * Move action for players whose turn is after the first player.
     *
     * @param newPosition the new position
     */
    public void finalFrenzyAfterMove(Square newPosition) {
        setMoveCounter(4);
        while (getMoveCounter() > 0) {
            move(newPosition);
        }
        decreaseActionCounter();
    }

    /**
     * Grab action for players whose turn is after the first player.
     *
     * @param currentGameBoard   the current game board
     * @param currentWeaponsSlot the current weapons slot
     * @param newWeapon          the new weapon
     * @param newPosition        the new position
     */
    public void finalFrenzyAfterGrab(GameBoard currentGameBoard, WeaponsSlot currentWeaponsSlot,
                                     Weapon newWeapon, Square newPosition) {
        setMoveCounter(2);
        while (getMoveCounter() > 0) {
            move(newPosition);
        }
        grabItem(currentGameBoard, currentWeaponsSlot, newWeapon);
        decreaseActionCounter();
    }

    /**
     * Shoot action for players whose turn is after the first player.
     *
     * @param weaponToReload   the weapon to reload
     * @param currentGameBoard the current game board
     * @param playerTarget     the player target
     * @param newShootPosition the new shoot position
     * @param newPosition      the new position
     * @param weapon           the weapon
     */
    public void finalFrenzyAfterShoot(Weapon weaponToReload, GameBoard currentGameBoard,
                                      Player playerTarget, Square newShootPosition, Square newPosition,
                                      Weapon weapon) {
        setMoveCounter(1);
        while (getMoveCounter() > 0) {
            move(newPosition);
        }
        reload(weaponToReload);
        shootPlayer(currentGameBoard, playerTarget, newShootPosition, weapon);
    }

    /**
     * Calculate death points. Creates an arraylist of players,
     * ordered from least points inflicted to most points inflicted.
     *
     * @param currentGameBoard the current game board
     */
    public void calculateDeathPoints(GameBoard currentGameBoard) {

        //returns a list of players in order of damage, by ordering the getDamage array by damage inflicted
        //Order is from lowest to highest
        Map<String, Long> damageByPlayer = getDamage()
                .stream()
                .collect(groupingBy(Player::getNickname, counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(
                        toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,
                                LinkedHashMap::new));

        ArrayList<String> nickNamesByDamageMade = new ArrayList<>(damageByPlayer.keySet());
        List<Player> playersByDamage = nickNamesByDamageMade.stream()
                .map(nick -> currentGameBoard.getPlayerByNickname(nick))
                .collect(Collectors.toList());

        //assigns values in givenPoints arraylist to players in playerByDamage
        //checks if givenPoints is empty
        if (givenPoints != null && !givenPoints.isEmpty()) {
            int n = 1;
            while (!givenPoints.isEmpty() && n >= 0 && !playersByDamage.isEmpty()) {
                int deathPoints = givenPoints.get(givenPoints.size() - n);
                Player p = playersByDamage.get(playersByDamage.size() - 1);
                p.addToTotalPoints(deathPoints);
                playersByDamage.remove(playersByDamage.size() - 1);
                n++;
            }
        } else {
            //if givenPoints is empty, the players has been killed more than 6 times,
            // he still awards 1 point to the killer
            int deathPoints = 1;
            addToTotalPoints(deathPoints);
        }
    }
}
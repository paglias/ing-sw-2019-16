package models;

import models.cards.Weapon;
import models.decks.AmmoDeck;
import models.decks.PowerUpsDeck;
import models.decks.WeaponsDeck;

import java.util.*;

import static java.util.stream.Collectors.*;

/**
 * The type Game board.
 */
public class GameBoard {
    private Date gameStartDate;
    private Boolean gameSetup;;
    private Skulls skulls;
    private PowerUpsDeck powerUpsDeck;
    private WeaponsDeck weaponsDeck;
    private AmmoDeck ammoDeck;
    private ArrayList<Player> players;
    private ArrayList<Square> squares;
    private Boolean isFinalFrenzy;
    private ArrayList<Weapon> weapons;
    private int mapN;

    /**
     * Setup the game.
     *
     */
    public GameBoard () {
        weaponsDeck = new WeaponsDeck();
        squares = new ArrayList<>();

        powerUpsDeck = new PowerUpsDeck();
        ammoDeck = new AmmoDeck();

        players = new ArrayList<>();

        skulls = new Skulls();

        isFinalFrenzy = false;
        gameSetup = false;
    }

    /**
     * Is game setup boolean.
     *
     * @return the boolean
     */
    public synchronized Boolean isGameSetup () { return gameSetup; }

    /**
     * Sets game setup.
     *
     * @param gameSetup the game setup
     */
    public synchronized void setGameSetup (Boolean gameSetup) {
        this.gameSetup = gameSetup;
    }

    /**
     * Gets skulls.
     *
     * @return the skulls
     */
    public Skulls getSkulls() {
        return skulls;
    }

    /**
     * Gets players.
     *
     * @return the players
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Gets player by nickname.
     *
     * @param nickname the nickname
     * @return the player by nickname
     */

    public Player getPlayerByNickname(String nickname) {
        return getPlayers().stream()
                .filter(player -> player.getNickname().equals(nickname))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }


    /**
     * Gets active player.
     *
     * @return the active player
     */
    public Player getActivePlayer () {
        return getPlayers().stream()
                .filter(Player::isActive)
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

    /**
     * Add player.
     *
     * @param player the player
     */
    public void addPlayer(Player player) {
        if (players.isEmpty()) {
            player.setFirstPlayer(true);
        }

        players.add(player);
        player.setGameBoard(this);
    }


    /**
     * Sets map.
     *
     * @param chosenMap the chosen map
     */
    public void setMap (Integer chosenMap) {
        if (chosenMap == null) chosenMap = 1;
        squares.addAll(MapLoader.loadMap(chosenMap, weaponsDeck));
        mapN = chosenMap;
    }

    public int getMap () { return mapN; }

    /**
     * Start game.
     */
    public void startGame () {
        gameStartDate = new Date();
    }

    /**
     * Has started boolean.
     *
     * @return the boolean
     */
    public synchronized boolean hasStarted () {
        return gameStartDate != null;
    }

    /**
     * Gets power ups deck.
     *
     * @return the power ups deck
     */
    public PowerUpsDeck getPowerUpsDeck() {
        return powerUpsDeck;
    }

    /**
     * Gets ammo deck.
     *
     * @return the ammo deck
     */
    public AmmoDeck getAmmoDeck() {
        return ammoDeck;
    }

    /**
     * Gets weapons deck.
     *
     * @return the weapons deck
     */
    public WeaponsDeck getWeaponsDeck() {
        return weaponsDeck;
    }

    /**
     * Gets squares.
     *
     * @return the squares
     */
    public List<Square> getSquares() {
        return squares;
    }

    /**
     * Next player. Receives current Player, sets next player to active.
     *
     * @param currentPlayer the current player
     * @return the player
     */
    public Player nextPlayer (Player currentPlayer) {
        int i = players.indexOf(currentPlayer);
        try {
            players.get(i++);
        } catch (IndexOutOfBoundsException e) {
            players.get(0).setActive(true);
            i=0;
        }
        players.get(i).setActive(true);
        players.get(i-1).setActive(false);
        return players.get(i);
    }

    /**
     * Is final frenzy boolean.
     *
     * @return the boolean
     */
    public boolean isFinalFrenzy () {
        return isFinalFrenzy;
    }

    /**
     * Final frenzy. TO BE CALLED when nSkulls = X by controller
     * Sets isFinalFrenzy to true.
     * Compares players before or after first player and decides action counter.
     * Changes given points for undamaged players.
     * Sets adrenaline to zero for undamaged players
     */
    public void finalFrenzy() {
        isFinalFrenzy = true;

        // decides action counter based on firstPlayer
        // sets first player
        Player firstPlayer = players.get(0);  //temporary set first player as the actual first

        for (Player player : players) {
            if (player.isFirstPlayer()) {
                firstPlayer = player;
                break;
            }
        }

        for (Player player : players) {
            if (players.indexOf(player) < players.indexOf(firstPlayer)) {
                player.setActionCounter(2);
                player.setIsBeforeFirstPlayer(true);
            } else {
                player.setActionCounter(1);
                player.setIsBeforeFirstPlayer(false);
            }

            if (player.getDamage().isEmpty()) {
                //creates new points awarded for killshot
                player.setGivenPoints(new ArrayList<>(Arrays.asList(1, 1, 1, 5)));
                player.setAdrenaline(0);
            }
        }
    }

    /**
     * Calculate game points. Called by controller at the end of the game.
     * Conditions for call are checked by the callee.
     */
    public void calculateGamePoints() {
        //Orders killer players in Skulls.killer by occurences, from lowest to highest
        Skulls currentSkulls = getSkulls();
        Map<String, Long> killshotByPlayer = currentSkulls.getKillers()
                .stream()
                .collect(groupingBy(Player::getNickname, counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(
                        toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,
                                LinkedHashMap::new));

        ArrayList<String> nickNamesByKillShotsMade = new ArrayList<>(killshotByPlayer.keySet());
        List<Player> playersByKills = nickNamesByKillShotsMade.stream()
                .map(nick -> getPlayerByNickname(nick))
                .collect(toList());

        //assigns values in finalPlayerPoints arraylist to players Skull.killers
        List<Integer> finalPlayerPoints = new ArrayList<>();
        finalPlayerPoints.add(1);
        finalPlayerPoints.add(1);
        finalPlayerPoints.add(2);
        finalPlayerPoints.add(4);
        finalPlayerPoints.add(6);
        finalPlayerPoints.add(8);
        assignPoints(finalPlayerPoints, playersByKills);
    }

    //TODO check if creating only this function is ok to avoid duplicating code.
    /**
     * Assign points. Used in Player and in calculateGamePoints to assign points to players,
     *
     * @param points  the points
     * @param players the players
     */
    public void assignPoints(List<Integer> points, List<Player> players) {
        int n = 1;
        while (!points.isEmpty() && n >= 1 && !players.isEmpty()) {
            int addedPoints = points.get(points.size() - n);
            Player player = players.get(players.size() - 1);
            player.addToTotalPoints(addedPoints);
            players.remove(players.size() - 1);
            n++;
        }
    }
}

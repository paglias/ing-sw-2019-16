package models;

import models.cards.Card;
import models.decks.AmmoDeck;
import models.decks.PowerUpsDeck;
import models.decks.WeaponsDeck;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class GameBoard {
    private Skulls skulls;
    private Boolean started;
    private Date gameStartDate;
    private Date gameLaunchDate;
    private int turn;
    private ArrayList<WeaponsSlot> weaponsSlots;
    private PowerUpsDeck powerUpsDeck;
    private WeaponsDeck weaponsDeck;
    private AmmoDeck ammoDeck;
    private ArrayList<Player> players;
    private Boolean isFinalFrenzy;
    private ArrayList<Square> squares;

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
     * @param nicknanem the nicknanem
     * @return the player by nickname
     */
    public Player getPlayerByNickname (String nicknanem) {
        return getPlayers().stream()
                .filter(player -> player.getNickname().equals(nicknanem))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

    /**
     * Add player.
     *
     * @param player the player
     */
    public void addPlayer(Player player){
        players.add(player);
    }

    /**
     * Sets game.
     *
     * @param chosenMap the chosen map
     */
    public void setupGame (Integer chosenMap) {
        if (chosenMap == null) chosenMap = 1;

        weaponsDeck = new WeaponsDeck();
        squares = new ArrayList<>();
        squares.addAll(MapLoader.loadMap(chosenMap, weaponsDeck));

        powerUpsDeck = new PowerUpsDeck();
        ammoDeck = new AmmoDeck();

        players = new ArrayList<>();
    }

    /**
     * Gets power ups deck.
     *
     * @return the power ups deck
     */
    public PowerUpsDeck getPowerUpsDeck () {
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
     * Create a new player.
     * Initiates values of the new player
     */
    //Creates new player.
    public void createPlayer() { // TODO controller
        //Assign nickname
        Player newPlayer = new Player();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter player nickname");
        String nickName = scanner.nextLine();
        for (Player player : players) {
            if (player.getNickname().compareTo(nickName) != 0) {
                newPlayer.setNickname(nickName);
            } else {
                System.out.println("Player name not available");
                createPlayer();  //TODO BEST SOLUTION, RECURSIVE FUNCTION?
            }
        }

        //TODO COLOR CHOOSER GUI
        //Sets first player

    }

    /**
     * Next player. Receives current Player, sets next player to active.
     *
     * @param currentPlayer the current player
     * @return the player
     */
    public void nextPlayer(Player currentPlayer) {
        int i = players.indexOf(currentPlayer);
        try {
            players.get(i++);
        } catch (IndexOutOfBoundsException e) {
            players.get(0).setActive(true);
        }
        players.get(i).setActive(true);
    }

    /**
     * Final frenzy. TO BE CALLED when nSkulls = X by controller
     * Sets isFinalFrenzy to true.
     * Compares players before or after first player and decides action counter.
     * Changes given points for undamaged players.
     * Sets adrenaline to zero for undamaged players
     */
    public void finalFrenzy () {
        isFinalFrenzy = true;

        //creates new points awarded for killshot
        ArrayList<Integer> points = new ArrayList<>();
        points.add(1);
        points.add(1);
        points.add(1);
        points.add(5);

        // decides action counter based on firstPlayer
        // sets first player
        Player firstPlayer = players.get(0);  //temporary set first player as the actual first

        for (Player player : players) {
            if (player.getFirstPlayer()) {
                firstPlayer = player;
                break;
            }
        }

        for (Player player : players) {
            if (players.indexOf(player) < players.indexOf(firstPlayer)) {
                player.setActionCounter(2);
            } else {
                player.setActionCounter(1);
            }

            if (player.getDamage().isEmpty()) {
                player.setGivenPoints(points);
                player.setAdrenaline(0);
            }
        }
    }
}

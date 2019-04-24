package models;

import models.cards.Card;
import models.decks.AmmoDeck;
import models.decks.PowerUpsDeck;
import models.decks.WeaponsDeck;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
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

    public ArrayList<Player> getPlayers() {
        return players;
    }

    //Creates new player.
    public void createPlayer() {
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
        //Initial points given for each Player, order is inverted so the last element
        //of the arraylist can be used as first, with ArrayList.remove
        ArrayList<Integer> newPlayerPoints = new ArrayList<>();
        newPlayerPoints.add(1);
        newPlayerPoints.add(1);
        newPlayerPoints.add(2);
        newPlayerPoints.add(4);
        newPlayerPoints.add(6);
        newPlayerPoints.add(8);

        //Assign initial values, 1 ammo for each color, set counters, add pointsGiven (order is inverted, see above)
        newPlayer.addsCubes(Card.Color.YELLOW);
        newPlayer.addsCubes(Card.Color.BLUE);
        newPlayer.addsCubes(Card.Color.RED);
        //Set movecounter to 3  //TODO MOVECOUNTER CHANGES IF PLAYER GRABS ITEM/SHOOTS
        newPlayer.setMoveCounter(3);
        newPlayer.setActionCounter(2);
        newPlayer.setAdrenaline(0);
        newPlayer.setGivenPoints(newPlayerPoints);
        //TODO COLOR CHOOSER GUI
        //Sets first player
        if (players.isEmpty()) {
            newPlayer.setFirstPlayer(true);
        }

    }

    /**
     * Next player. Receives current Player, sets next player to active.
     *
     * @param currentPlayer the current player
     * @return the player
     */
    public void nextPlayer(Player currentPlayer) {
        int i;
        i = players.indexOf(currentPlayer);
        i++;
        try {
            players.get(i);
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
     * Sets adrenaline to zero for everyone.
     */
    public void finalFrenzy() {
        isFinalFrenzy = true;
        //creates new points awarded for killshot
        ArrayList<Integer> points = new ArrayList<>();
        points.add(1);
        points.add(1);
        points.add(1);
        points.add(5);
        //decides action counter based on firstPlayer
        //sets first player
        Player firstPlayer = new Player();  //temporary player object, assignment of firstPlayer reference
        for (Player player : players) {
            if (player.getFirstPlayer())
                firstPlayer = player;
            //TODO IS IT POSSIBLE WITHOUT CREATING ANOTHER NEW PLAYER?
        }
        for (Player player : players) {
            if (players.indexOf(player) < players.indexOf(firstPlayer)) {
                player.setActionCounter(2);

            } else {
                player.setActionCounter(1);
            }
        }
        for (Player player : players)
            if (player.getDamage().isEmpty()) {
                player.setGivenPoints(points);
            }
    }
}
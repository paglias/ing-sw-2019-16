package models;

import models.cards.Card;
import models.decks.AmmoDeck;
import models.decks.PowerUpsDeck;
import models.decks.WeaponsDeck;

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
        //Assign initial values
        newPlayer.addsCubes(Card.Color.YELLOW);
        newPlayer.addsCubes(Card.Color.BLUE);
        newPlayer.addsCubes(Card.Color.RED);
        //Set movecounter to 3  //TODO MOVECOUNTER CHANGES IF PLAYER GRABS ITEM/SHOOTS
        newPlayer.setMoveCounter(3);
        newPlayer.setActionCounter(2);
        newPlayer.setAdrenaline(0);
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

    public void finalFrenzy(){
        isFinalFrenzy=true;
        Player firstPlayer = new Player();
        for (Player player : players) {
            if (player.getFirstPlayer())
                firstPlayer = player;  //TODO IS IT POSSIBLE WITHOUT CREATING another NEW PLAYER?
        }
        for (Player player : players) {
            if (players.indexOf(player) < players.indexOf(firstPlayer)){
                player.setActionCounter(2);
            }
            else{
                player.setActionCounter(1);
            }
        }

    }
}

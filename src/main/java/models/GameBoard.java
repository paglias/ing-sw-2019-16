package models;

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
    //Creates new player and assigns a nickname after checking for existing nicknames.
    public void createPlayer(){
        Player newPlayer = new Player();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter player nickname");
        String nickName = scanner.nextLine();
        for (Player player: players) {
            if (player.getNickname().compareTo(nickName)!=0){
                newPlayer.setNickname(nickName);
            }
            else {
                System.out.println("Player name not available");
            }

        }
    }
}

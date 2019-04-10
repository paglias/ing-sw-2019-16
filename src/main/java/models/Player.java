package models;

import models.cards.Card;
import models.cards.PowerUp;
import models.cards.Weapon;

import java.util.ArrayList;
import java.util.Date;

public class Player {
    private String nickname;
    private Boolean isActive;
    private Date startTurnDate;
    private int nDeaths;
    private Boolean isFirstPlayer;
    private ArrayList<Card.Color> cubes;
    private ArrayList<String> points; // TODO what is this? why not integer?
    private ArrayList<Player> marks;
    private ArrayList<Player> damage;
    private ArrayList<PowerUp> powerUps;
    private ArrayList<Weapon> weapons;
    private Square position;
}







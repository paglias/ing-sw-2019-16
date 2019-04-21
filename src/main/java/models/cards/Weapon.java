package models.cards;

import models.Player;
import models.Square;

public class Weapon extends Card{
    private boolean loaded;


    /**
     * Is loaded boolean.
     *
     * @return the boolean
     */
    public boolean isLoaded() {
        return loaded;
    }

    /**
     * Instantiates a new Weapon.
     *
     *
     * @param color the color
     */
    public Weapon (Color color) {
        super(color);
    }

    public void dealDamage(Player playerTarget){
        //deal damage based on weapon
    }
    public void addMark(Player playerTarget){
        //add mark based on weapon
    }
    public void movePlayer(Player playerTarget, Square newPosition){
        //move players around based on weapon
    }
    public void loadWeapon(){
        //load weapon, set is loaded to TRUE
    }
}
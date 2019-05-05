package models.cards;

import models.Player;
import models.Square;
import models.decks.Deck;
import models.decks.PowerUpsDeck;

import java.util.ArrayList;
import java.util.List;

public class PowerUp extends Card {
    public enum Name {
        TARGETING_SCOPE,
        NEWTON,
        TAGBACK_GRENADE,
        TELEPORTER
    }
    private Name name;

    /**
     * Instantiates a new Power up.
     *
     * @param name  the name
     * @param color the color
     */
    public PowerUp (Name name, Color color) {
        super(color);
        this.name = name;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public Name getName () {
        return name;
    }

    public void effect(Player player, Card.Color cubeColor, Player playerTarget){
        if(this.name==Name.TARGETING_SCOPE) {
            if (player.getCubes() != null) {
                player.removeCube(cubeColor);
                playerTarget.addDamage(player);
            }
        }
    }

    public void effect(Player player, Player playerTarget){
        if(this.name==Name.TAGBACK_GRENADE){
                Square position= playerTarget.getPosition();
                if(player.getPosition().getCanView().contains(position)){
                    playerTarget.addMark(player);
                }
        }
    }

    public void effect(Player player, Square position){
        if(this.name==Name.TELEPORTER){
                player.setPosition(position);
        }
    }

    public void effect(Player player, Player playerTarget, Square square,Square newSquare, Square.Direction direction,List<Square>squares){
        if(this.name==Name.NEWTON){
               List<Square>squareList= square.filterDirectionSquare(squares, direction);
               if(squareList.contains(newSquare)){
                   playerTarget.setPosition(newSquare);
               }
            }
        }
    }



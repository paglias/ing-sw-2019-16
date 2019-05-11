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
    private Player player;
    private Card.Color cubeColor;
    private Player playerTarget;
    private Square position;
    private Square square;
    private Square newSquare;
    private Square.Direction direction;
    private List<Square> squares;

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setCubeColor(Card.Color cubeColor) {
        this.cubeColor = cubeColor;
    }

    public void setPlayerTarget(Player playerTarget) {
        this.playerTarget = playerTarget;
    }

    public void setPosition(Square position) {
        this.position = position;
    }

    public void setSquare(Square square) {
        this.square = square;
    }

    public void setNewSquare(Square newSquare) {
        this.newSquare = newSquare;
    }

    public void setDirection(Square.Direction direction) {
        this.direction = direction;
    }

    public void setSquares(List<Square> squares) {
        this.squares = squares;
    }

    /**
     * Instantiates a new Power up.
     *
     * @param name  the name
     * @param color the color
     */
    public PowerUp(Name name, Color color) {
        super(color);
        this.name = name;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public Name getName() {
        return name;
    }

    public void reset(){
        position=null;
        playerTarget=null;
        player=null;
        newSquare=null;
        squares=null;
        direction=null;
        square=null;
        cubeColor=null;


    }

    public void effect(PowerUp.Name name) {
        switch (name) {
            case TARGETING_SCOPE:
                if (player.getCubes().size()!= 0) {
                    player.removeCube(cubeColor);
                    playerTarget.addDamage(player);
                    reset();
                }

            case TAGBACK_GRENADE:
                Square playerPosition = playerTarget.getPosition();
                if (player.getPosition().getCanView().contains(playerPosition)) {
                    playerTarget.addMark(player);
                    reset();
                }
            case TELEPORTER:
                player.setPosition(position);
                reset();

            case NEWTON:
                List<Square> squareList = square.filterDirectionSquare(squares, direction);
                if (squareList.contains(newSquare)) {
                    playerTarget.setPosition(newSquare);
                    reset();
                }
            }
        }
    }



package models.cards;

import models.Player;
import models.Square;

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
    private Square newPosition;
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

    public void setNewPosition(Square newPosition) {
        this.newPosition = newPosition;
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

    public void reset() {
        newPosition = null;
        playerTarget = null;
        player = null;
        squares = null;
        direction = null;
        cubeColor = null;
    }

    public void effect(PowerUp.Name name) {
        switch (name) {
            case TARGETING_SCOPE:
                if (player.getCubes().contains(cubeColor)) {
                    player.removeCube(cubeColor);
                    playerTarget.addDamage(player);
                }
                reset();
                break;

            case TAGBACK_GRENADE:
                Square playerPosition = playerTarget.getPosition();
                if (player.getPosition().getCanView().contains(playerPosition)) {
                    playerTarget.addMark(player);
                }
                reset();
                break;
            case TELEPORTER:
                player.setPosition(newPosition);
                reset();
                break;

            case NEWTON:
                List<Square> squareList = playerTarget.getPosition().filterDirectionSquare(squares, direction);
                if (squareList.contains(newPosition)) {
                    playerTarget.setPosition(newPosition);
                }
                reset();
                break;
        }
    }
}




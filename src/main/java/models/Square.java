package models;

import models.cards.Ammo;
import models.decks.AmmoDeck;

import java.util.ArrayList;
import java.util.List;

public class Square {
    public enum Color {
        YELLOW,
        RED,
        BLUE,
        PURPLE,
        WHITE,
        GREEN,
        EMPTY // Used for empty squares
    }

    private Color color;
    private Boolean isSpawnPoint;
    private ArrayList<Square> canView = new ArrayList<>();
    protected ArrayList<Square> canAccessDirectly = new ArrayList<>();
    private Ammo ammo;

    /**
     * Gets can view of a square.
     *
     * @return the can view
     */
    public List<Square> getCanView() {
        return canView;
    }

    /**
     * Gets boolean spawn point.
     *
     * @return the boolean spawn point
     */
    public Boolean getSpawnPoint() {
        return isSpawnPoint;
    }

    /**
     * Instantiates a new Square.
     *
     * @param squareColor  the square color
     * @param isSpawnPoint the is spawn point
     */
    public Square (String squareColor, boolean isSpawnPoint) {
        this.color = Square.Color.valueOf(squareColor);
        this.isSpawnPoint = isSpawnPoint;
    }

    /**
     * Gets squares that can be accessed directly.
     *
     * @return the can access directly squares
     */
    public List<Square> getCanAccessDirectly() {
        return canAccessDirectly;
    }

    /**
     * Add can view square.
     *
     * @param squareCanView the square can view
     */
    public void addCanViewSquare (Square squareCanView) {
        this.canView.add(squareCanView);
    }

    /**
     * Add can access square.
     *
     * @param squareCanAccess the square can access
     */
    public void addCanAccessSquare (Square squareCanAccess) {
        this.canAccessDirectly.add(squareCanAccess);
    }

    /**
     * Sets ammo in a square instance.
     *
     * @param ammoDeck the ammo deck
     */
    public void setAmmo (AmmoDeck ammoDeck) {
        this.ammo = (Ammo) ammoDeck.pick();
    }

    /**
     * Gets ammo from the square instance.
     *
     * @return the ammo
     */
    public Ammo getAmmo () {
        Ammo ammoPicked = this.ammo;
        this.ammo = null;
        return ammoPicked;
    }
}
package models;

import models.cards.Ammo;
import models.decks.AmmoDeck;
import models.decks.WeaponsDeck;

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
    // Add a weapon slot to spawn points
    private WeaponsSlot weaponsSlot;
    private ArrayList<Square> canView = new ArrayList<>();
    private ArrayList<Square> canAccessDirectly = new ArrayList<>();
    private Ammo ammo;

    /**
     * Instantiates a new Square.
     * @param squareColor  the square color
     * @param isSpawnPoint the is spawn point
     */
    public Square (Color squareColor, boolean isSpawnPoint) {
        this.color = squareColor;
        this.isSpawnPoint = isSpawnPoint;
    }

    /**
     * Create a weapons slot for the Square.
     *
     * @param weaponsDeck the weapons deck
     * @throws IllegalStateException if not a spawn point or if it's already been created
     */
    public void createWeaponsSlot (WeaponsDeck weaponsDeck) {
        if (!isSpawnPoint) {
            throw new IllegalStateException("A WeaponSlot can only be attached to a spawn point.");
        }
        if (weaponsSlot != null) {
            throw new IllegalStateException("A WeaponSlot has already been created.");
        }
        weaponsSlot = new WeaponsSlot(color, weaponsDeck);
    }

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
    public Boolean isSpawnPoint () {
        return isSpawnPoint;
    }

    /**
     * Gets the attached weapons slot (if it exists).
     *
     * @return the weapons slot
     * @throws IllegalStateException if not a spawn point
     */
    public WeaponsSlot getWeaponsSlot() {
        if (!isSpawnPoint) {
            throw new IllegalStateException("WeaponSlots only exists on spawn points");
        }
        return weaponsSlot;
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

    /**
     * Gets color.
     *
     * @return the color
     */
    public Color getColor () {
        return color;
    }
}
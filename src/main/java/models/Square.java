package models;

import java.util.ArrayList;

public class Square {
    public enum Color {
        YELLOW,
        RED,
        BLUE,
        PURPLE,
        WHITE,
        GREEN,
        EMPTY; // Used for empty squares
    }

    private Color color;
    private Boolean isSpawnPoint;
    private ArrayList<Square> canView = new ArrayList<>();
    private ArrayList<Square> canAccessDirectly = new ArrayList<>();
    private Ammo ammo;

    public Square (String squareColor, boolean isSpawnPoint) {
        this.color = Square.Color.valueOf(squareColor);
        this.isSpawnPoint = isSpawnPoint;
    }

    public void addCanViewSquare (Square squareCanView) {
        this.canView.add(squareCanView);
    }

    public void addCanAccessSquare (Square squareCanAccess) {
        this.canAccessDirectly.add(squareCanAccess);
    }

    public void setAmmo (AmmoDeck ammoDeck) {
        this.ammo = (Ammo) ammoDeck.pick();
    }

    public Ammo getAmmo () {
        Ammo ammoPicked = this.ammo;
        this.ammo = null;
        return ammoPicked;
    }
}
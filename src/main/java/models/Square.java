package models;

import java.util.ArrayList;

public class Square {
    public enum SquareColor {
        YELLOW,
        RED,
        BLUE,
        PURPLE,
        WHITE,
        GREEN;
    }

    private int position;
    private Boolean IsSpawnPoint;
    protected ArrayList<Square> canView;
    protected ArrayList<Square> canAccessDirectly;
}
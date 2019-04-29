package models;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import com.google.gson.*;
import models.decks.WeaponsDeck;

public class MapLoader {
    private MapLoader() {
        throw new IllegalStateException("MapLoader class cannot be instantiated.");
    }

    // Only used as a temporary store for squares parsed from JSON
    private class ParsedSquare {
        String color;
        Boolean isSpawnPoint;
        int[] canView;
        int[] canAccess;
    }

    static ArrayList<Square> loadMap(int mapNumber, WeaponsDeck weaponsDeck) {
        Gson gson = new Gson();
        ParsedSquare[] parsedSquares;

        try {
             parsedSquares = gson.fromJson(new FileReader(MapLoader.class.getResource("/Maps/map" + mapNumber + ".json").getPath()), ParsedSquare[].class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Error loading map");
        }
        ArrayList<Square> squares = new ArrayList<>();

        for (ParsedSquare parsedSquare : parsedSquares) {
            squares.add(new Square(Square.Color.valueOf(parsedSquare.color), parsedSquare.isSpawnPoint));
        }

        for (int i = 0; i < squares.size(); i++) {
            Square square = squares.get(i);
            ParsedSquare parsedSquare = parsedSquares[i];
            square.setPosition(i);

            if (parsedSquare.isSpawnPoint) {
                square.createWeaponsSlot(weaponsDeck);
            }

            for (int canViewI : parsedSquare.canView) {
                square.addCanViewSquare(squares.get(canViewI));
            }

            for (int canAccessI : parsedSquare.canAccess) {
                square.addCanAccessSquare(squares.get(canAccessI));
            }
        }

        return squares;
    }
}

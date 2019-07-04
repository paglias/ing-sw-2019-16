package models;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import models.decks.AmmoDeck;
import models.decks.WeaponsDeck;
import utils.Logger;

import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * The type Map loader.
 */
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

    /**
     * Load one game map.
     *
     * @param mapNumber   the map number
     * @param weaponsDeck the weapons deck
     * @param ammoDeck the ammo deck
     * @return the array list of squares that are part of the map
     */
    public static ArrayList<Square> loadMap(int mapNumber, WeaponsDeck weaponsDeck, AmmoDeck ammoDeck) {
        Gson gson = new Gson();
        ParsedSquare[] parsedSquares;

        try {
            String mapPath = "/" + "Maps" + "/" + "map" + mapNumber + ".json";
            InputStreamReader mapInput = new InputStreamReader(MapLoader.class.getResourceAsStream(mapPath));
            JsonReader mapReader = new JsonReader(mapInput);
            parsedSquares = gson.fromJson(mapReader, ParsedSquare[].class);
        } catch (Throwable e) {
            Logger.err(e, "Error loading map " + mapNumber);
            throw new IllegalStateException("Error loading maps!");
        }
        ArrayList<Square> squares = new ArrayList<>();

        for (ParsedSquare parsedSquare : parsedSquares) {
            squares.add(new Square(Square.Color.valueOf(parsedSquare.color), parsedSquare.isSpawnPoint));
        }

        for (int i = 0; i < squares.size(); i++) {
            Square square = squares.get(i);
            ParsedSquare parsedSquare = parsedSquares[i];
            square.setNumber(i);

            if (parsedSquare.isSpawnPoint) {
                square.createWeaponsSlot(weaponsDeck);
            } else if (!parsedSquare.color.equals("EMPTY")) {
                square.setAmmo(ammoDeck);
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

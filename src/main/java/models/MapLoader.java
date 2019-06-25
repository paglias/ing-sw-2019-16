package models;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import com.google.gson.*;
import models.decks.WeaponsDeck;
import utils.Logger;

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
            String mapPath = "." + File.separatorChar + "src" + File.separatorChar + "main" + File.separatorChar + "resources" + File.separatorChar + "Maps" + File.separatorChar + "map" + mapNumber + ".json";
            parsedSquares = gson.fromJson(new FileReader(mapPath), ParsedSquare[].class);
        } catch (IOException e) {
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
            } else {
                // TODO ammo on non spawn points?
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

package models;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import com.google.gson.*;

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

    private static void loadMap (String filename) throws IOException {
        Gson gson = new Gson();
        ParsedSquare[] parsedSquares = gson.fromJson(new FileReader(filename), ParsedSquare[].class);
        ArrayList<Square> squares = new ArrayList<>();

        for (ParsedSquare parsedSquare : parsedSquares) {
            squares.add(new Square(parsedSquare.color, parsedSquare.isSpawnPoint));
        }

        for (int i = 0; i < squares.size(); i++) {
            Square square = squares.get(i);
            ParsedSquare parsedSquare = parsedSquares[i];

            for (int canViewI : parsedSquare.canView) {
                square.addCanViewSquare(squares.get(canViewI));
            }

            for (int canAccessI : parsedSquare.canAccess) {
                square.addCanAccessSquare(squares.get(canAccessI));
            }
        }
    }

    public static void loadMaps () throws IOException {
        int[] maps = {1,2,3,4};
        for (int mapI : maps) {
            loadMap("map" + mapI + ".json");
        }
    }
}

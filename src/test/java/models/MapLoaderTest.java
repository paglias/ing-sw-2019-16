package models;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class MapLoaderTest {
    @Test
    public void testloadMap () throws IOException { // TODO how to handle methods with exceptions?
        // Test that loading doesn't fail
        MapLoader.loadMap("map1.json");
        MapLoader.loadMap("map2.json");
        MapLoader.loadMap("map3.json");
        MapLoader.loadMap("map4.json");
    }

}

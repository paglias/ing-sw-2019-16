package models;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class MapLoaderTest {
    @Test
    public void testloadMap () throws IOException { // TODO how to handle methods with exceptions?
        // Test that loading doesn't fail
        MapLoader.loadMap(1);
        MapLoader.loadMap(2);
        MapLoader.loadMap(3);
        MapLoader.loadMap(4);
    }

}

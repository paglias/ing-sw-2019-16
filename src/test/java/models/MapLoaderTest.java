package models;

import models.decks.WeaponsDeck;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MapLoaderTest {
    @Test
    void loadMap () throws IOException {
        // Test that loading doesn't fail
        MapLoader.loadMap(1, new WeaponsDeck());
        MapLoader.loadMap(2, new WeaponsDeck());
        MapLoader.loadMap(3, new WeaponsDeck());
        MapLoader.loadMap(4, new WeaponsDeck());
    }

    @Test
    void mapCreation () throws IOException {
        // More tests in SquareTest
        ArrayList<Square> map = MapLoader.loadMap(1, new WeaponsDeck());
        assertEquals(map.size(), 12);

        assertEquals(map.get(0).isSpawnPoint(), false);
        assertThrows(IllegalStateException.class, () -> {
            map.get(0).getWeaponSlot();
        });

        assertEquals(map.get(11).isSpawnPoint(), true);
        assertTrue(map.get(11).getWeaponSlot() != null);
    }
}

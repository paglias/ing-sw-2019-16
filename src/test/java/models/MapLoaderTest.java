package models;

import models.decks.AmmoDeck;
import models.decks.WeaponsDeck;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MapLoaderTest {
    @Test
    void loadMap () {
        // Test that loading doesn't fail
        MapLoader.loadMap(1, new WeaponsDeck(), new AmmoDeck());
        MapLoader.loadMap(2, new WeaponsDeck(), new AmmoDeck());
        MapLoader.loadMap(3, new WeaponsDeck(), new AmmoDeck());
        MapLoader.loadMap(4, new WeaponsDeck(), new AmmoDeck());
    }

    @Test
    void mapCreation () {
        // More tests in SquareTest
        ArrayList<Square> map = MapLoader.loadMap(1, new WeaponsDeck(), new AmmoDeck());
        assertEquals(map.size(), 12);

        assertEquals(map.get(0).isSpawnPoint(), false);
        assertThrows(IllegalStateException.class, () -> {
            map.get(0).getWeaponsSlot();
        });

        assertEquals(map.get(11).isSpawnPoint(), true);
        assertNotNull(map.get(11).getWeaponsSlot());
    }
}

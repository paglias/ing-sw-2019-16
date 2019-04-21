package models.decks;

import models.cards.Ammo;
import models.decks.AmmoDeck;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AmmoDeckTest {

    @Test
    public void testAmmoDeckConstructor() {
        AmmoDeck AmmoDeck = new AmmoDeck();
        assertEquals(AmmoDeck.getDeckSize(), 36);
        assertEquals(AmmoDeck.getRemainingCards(), 36);
    }

    @Test
    public void TestPickRefill() {
        AmmoDeck AmmoDeck = new AmmoDeck();

        for (int i = 0; i < 35; i++) {
            Ammo card = (Ammo) AmmoDeck.pick();
            assertEquals(AmmoDeck.getRemainingCards(), AmmoDeck.getDeckSize() - i - 1);
            AmmoDeck.discard(card);
        }

        AmmoDeck.pick();
        assertEquals(AmmoDeck.getRemainingCards(), AmmoDeck.getDeckSize() - 1); // -1 because the last one is still being played
    }
}
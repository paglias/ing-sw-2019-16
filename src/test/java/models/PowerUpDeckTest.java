package models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PowerUpDeckTest {

    @Test
    public void testPowerUpDeckConstructor(){
        PowerUpDeck powerUpDeck = new PowerUpDeck();
        assertEquals(powerUpDeck.getDeckSize(), 24);
    }
    @Test
    public void testPick(){
        PowerUpDeck powerUpDeck = new PowerUpDeck();
        int nBlue = 0;
        int nRed =0;
        int nYellow=0;

        for (int i=0; i<24; i++){
            PowerUp card = (PowerUp) powerUpDeck.pick();
            if (card.getColor() == Color.BLUE) {
                nBlue++;
            }

            if (card.getColor() == Color.YELLOW) {
                nYellow++;
            }

            if (card.getColor() == Color.RED) {
                nRed++;
            }
        }

        assertEquals(nBlue, 8);
        assertEquals(nYellow, 8);
        assertEquals(nRed, 8);

    };

}

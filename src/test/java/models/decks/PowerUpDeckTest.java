package models.decks;

import models.cards.Card;
import models.cards.PowerUp;
import models.decks.PowerUpsDeck;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PowerUpDeckTest {

    /*@Test
    public void testPowerUpDeckConstructor(){
        PowerUpsDeck powerUpDeck = new PowerUpsDeck();
        assertEquals(powerUpDeck.getDeckSize(), 24);
        assertEquals(powerUpDeck.getRemainingCards(), 24);
    }

    @Test
    public void testPickColor(){
        PowerUpsDeck powerUpDeck = new PowerUpsDeck();
        int nBlue = 0;
        int nRed =0;
        int nYellow=0;

        for (int i=0; i<24; i++){
            PowerUp card = (PowerUp) powerUpDeck.pick();
            if (card.getColor() == Card.Color.BLUE) {
                nBlue++;
            }

            if (card.getColor() == Card.Color.YELLOW) {
                nYellow++;
            }

            if (card.getColor() == Card.Color.RED) {
                nRed++;
            }
        }

        assertEquals(nBlue, 8);
        assertEquals(nYellow, 8);
        assertEquals(nRed, 8);

    }

   @Test
   public void testPickType(){
        PowerUpsDeck powerUpDeck = new PowerUpsDeck();

        int nTeleporter = 0;
        int nNewton = 0;
        int nTagbackGrenade = 0;
        int nTargetingScope=0;

        for (int i=0; i<24; i++) {
        PowerUp card = (PowerUp) powerUpDeck.pick();
            if (card.getName() == PowerUp.Name.NEWTON){
                nNewton++;
            }
            if (card.getName() == PowerUp.Name.TAGBACK_GRENADE){
                nTagbackGrenade++;
            }
            if (card.getName() == PowerUp.Name.TELEPORTER){
                nTeleporter++;
            }
            if (card.getName() == PowerUp.Name.TARGETING_SCOPE){
                nTargetingScope++;
            }
        }

        assertEquals(nNewton, 6);
        assertEquals((nTagbackGrenade),6);
        assertEquals(nTargetingScope,6);
        assertEquals(nTeleporter,6);
   }

   @Test
    public void TestPickRefill() {
        PowerUpsDeck powerUpDeck = new PowerUpsDeck();

       for (int i=0; i <23; i++) {
            PowerUp card = (PowerUp) powerUpDeck.pick();
            assertEquals(powerUpDeck.getRemainingCards(),  powerUpDeck.getDeckSize() - i - 1);
            powerUpDeck.discard(card);
        }

        powerUpDeck.pick();
        assertEquals(powerUpDeck.getRemainingCards(), powerUpDeck.getDeckSize() - 1); // -1 because one is still being played
   }*/
}

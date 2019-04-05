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
    public void testPickColor(){
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

    }
   @Test
   public void testPickType(){
        PowerUpDeck powerUpDeck = new PowerUpDeck();
        int nTeleporter = 0;
        int nNewton = 0;
        int nTagback_Grenade = 0;
        int nTargeting_Scope=0;

        for (int i=0; i<24; i++){
            PowerUp card = (PowerUp) powerUpDeck.pick();
            if (card.getName() == PowerUp.Name.NEWTON){
                nNewton++;
            }
            if (card.getName() == PowerUp.Name.TAGBACK_GRENADE){
                nTagback_Grenade++;
            }
            if (card.getName() == PowerUp.Name.TELEPORTER){
                nTeleporter++;
            }
            if (card.getName() == PowerUp.Name.TARGETING_SCOPE){
                nTargeting_Scope++;
            }
        }

        assertEquals(nNewton, 6);
        assertEquals((nTagback_Grenade),6);
        assertEquals(nTargeting_Scope,6);
        assertEquals(nTeleporter,6);
   }
}

package models.cards;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PowerUpTest {
    @Test
    void getName () {
        PowerUp powerUp = new PowerUp(PowerUp.Name.NEWTON, Card.Color.BLUE);
        assertEquals(powerUp.getName(), PowerUp.Name.NEWTON);
    }
}

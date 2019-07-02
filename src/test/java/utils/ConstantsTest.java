package utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConstantsTest {
    @Test
    void loadSetting(){
        assertDoesNotThrow(() -> {
            String args[] = {"TIMEOUT=100"};
            Constants.load(args);
            assertTrue(Constants.DEBUG);
            assertEquals(Constants.TURN_TIMEOUT, 120);
            assertEquals(Constants.TIMEOUT, 100);
        });
    }
}

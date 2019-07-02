package utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class ConstantsTest {
    @Test
    void loadSetting(){
        assertDoesNotThrow(() -> {
            Constants.load();
        });
    }
}

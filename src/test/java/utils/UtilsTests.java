package utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UtilsTests {
    @Test
    void getKeyboard(){
        assertNotNull(Utils.getKeyboard());
    }
}

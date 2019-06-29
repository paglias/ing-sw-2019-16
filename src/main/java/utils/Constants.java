package utils;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.InputStreamReader;

public class Constants {
    private Constants () { }  // Cannot be instantiated

    // Only used as a temporary store for settings parsed from JSON
    private class ParsedSettings {
        Boolean DEBUG;
        int TIMEOUT;
        int TURN_TIMEOUT;
    }

    public static boolean DEBUG;
    public static long TIMEOUT;
    public static long TURN_TIMEOUT;

    public static void load () {
        Gson gson = new Gson();
        String settingsPath = "/" + "settings.json";
        InputStreamReader settingsInput = new InputStreamReader(Constants.class.getResourceAsStream(settingsPath));
        JsonReader settingsReader = new JsonReader(settingsInput);
        ParsedSettings parsedSettings = gson.fromJson(settingsReader, ParsedSettings.class);

        DEBUG = parsedSettings.DEBUG;
        TIMEOUT = parsedSettings.TIMEOUT;
        TURN_TIMEOUT = parsedSettings.TURN_TIMEOUT;
    }
}

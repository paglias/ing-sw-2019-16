package utils;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Constants {
    private Constants () { }  // Cannot be instantiated

    // Only used as a temporary store for settings parsed from JSON
    private class ParsedSettings {
        Boolean DEBUG;
        int TIMEOUT;
        int TURN_TIMEOUT;
    }

    private static boolean loaded = false;

    public static boolean DEBUG;
    public static long TIMEOUT;
    public static long TURN_TIMEOUT;

    /**
     * Load game settings from Json.
     * @param args the args from command line
     */
    public static void load (String[] args) {
        if (loaded) return;

        Gson gson = new Gson();
        String settingsPath = "/" + "settings.json";
        InputStreamReader settingsInput = new InputStreamReader(Constants.class.getResourceAsStream(settingsPath));
        JsonReader settingsReader = new JsonReader(settingsInput);
        ParsedSettings parsedSettings = gson.fromJson(settingsReader, ParsedSettings.class);

        DEBUG = parsedSettings.DEBUG;
        TIMEOUT = parsedSettings.TIMEOUT;
        TURN_TIMEOUT = parsedSettings.TURN_TIMEOUT;

        // Allow custom settings on the command line
        if (args != null) {
            for (String arg : args) {
                try {
                    String[] parts = arg.split(Pattern.quote("="));

                    String key = parts[0];
                    String value = parts[1];

                    switch (key) {
                        case "DEBUG":
                            DEBUG = value.equals("true");
                            break;
                        case "TURN_TIMEOUT":
                            TURN_TIMEOUT = Integer.parseInt(value);
                            break;
                        case "TIMEOUT":
                            TIMEOUT = Integer.parseInt(value);
                            break;
                        default:
                            throw new IllegalArgumentException("Invalid params!");
                    }
                } catch (Exception e) {
                    Logger.err(e, "Invalid params");
                    System.exit(1);
                }
            }
        }

        loaded = true;
    }
}

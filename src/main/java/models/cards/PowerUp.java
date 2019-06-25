package models.cards;

import com.google.gson.Gson;
import utils.Logger;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class PowerUp extends CardWithAction {
    // PowerUps are only loaded from file once
    private static boolean powerUpsLoadedFromFile = false;
    private static ArrayList<PowerUp> cachedPowerUps;

    /**
     * Load powerups from file.
     *
     * @return the powerups
     */
    public static ArrayList<PowerUp> loadPowerUps () {
        Gson gson = new Gson();

        if (!powerUpsLoadedFromFile) {
            String powerUpsPath = "." + File.separatorChar + "src" + File.separatorChar + "main" + File.separatorChar + "resources" + File.separatorChar + "Powerups";
            File powerUpsFolder = new File(powerUpsPath);
            File[] listOfPowerUpsFiles = powerUpsFolder.listFiles();
            ArrayList<PowerUp> powerUps = new ArrayList<>();

            try {
                for (File file : listOfPowerUpsFiles) {
                    if (file.isFile()) {
                        powerUps.add(gson.fromJson(new FileReader(file.getAbsolutePath()), PowerUp.class));
                    }
                }
            } catch (Throwable e) {
                Logger.err(e, "Problem loading powerups from file.");
                throw new IllegalArgumentException("Problem loading powerups from file.");
            }

            powerUpsLoadedFromFile = true;
            cachedPowerUps = new ArrayList<>(powerUps.size());
            for (PowerUp powerUp : powerUps) {
                // To string must be called to clone the classes when they're loaded again
                cachedPowerUps.add(gson.fromJson(gson.toJson(powerUp).toString(), powerUp.getClass()));
            }

            return powerUps;
        } else {
            ArrayList<PowerUp> clonedPowerUps = new ArrayList<>(cachedPowerUps.size());
            for (PowerUp powerUp : cachedPowerUps) {
                PowerUp clonePowerUp = gson.fromJson(gson.toJson(powerUp), powerUp.getClass());
                clonedPowerUps.add(clonePowerUp);
            }
            return clonedPowerUps;
        }
    }

}




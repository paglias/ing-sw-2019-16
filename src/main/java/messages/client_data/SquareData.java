package messages.client_data;

import models.Square;

import java.util.ArrayList;
import java.util.stream.Collectors;

// Class used to represent the Square model on the client
// Contains public info only
// And optimized for usage on the client
// Every attribute is public to make it easier to create it
public class SquareData {
    public String color;
    public Boolean isSpawnPoint;
    public Boolean ammo; // If there's an ammo
    public WeaponsSlotData weaponsSlot;
    public ArrayList<Integer> canView;
    public ArrayList<Integer> canAccessDirectly;

    public SquareData (Square square) {
        color = square.getColor().toString();
        isSpawnPoint = square.isSpawnPoint();
        ammo = square.hasAmmo();

        if (isSpawnPoint) {
            weaponsSlot = new WeaponsSlotData(square.getWeaponsSlot());
        } else {
            weaponsSlot = null;
        }

        canView = new ArrayList<>(square.getCanView().stream()
                .map(s -> s.getNumber()).collect(Collectors.toList()));
        canAccessDirectly = new ArrayList<>(square.getCanAccessDirectly().stream()
                .map(s -> s.getNumber()).collect(Collectors.toList()));
    }
}

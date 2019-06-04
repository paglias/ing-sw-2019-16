package messages.client_data;

import models.WeaponsSlot;

import java.util.ArrayList;
import java.util.stream.Collectors;

// Class used to represent the WeaponSlot model on the client
// Contains public info only
// And optimized for usage on the client
// Every attribute is public to make it easier to create it
public class WeaponsSlotData {
    public ArrayList<WeaponData> weapons;

    public WeaponsSlotData (WeaponsSlot weaponsSlot) {
        weapons = new ArrayList<>(weaponsSlot.getWeapons().stream()
        .map(w -> new WeaponData(w)).collect(Collectors.toList()));
    }
}

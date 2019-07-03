package messages.client_data;

import models.WeaponsSlot;

import java.util.ArrayList;
import java.util.stream.Collectors;


public class WeaponsSlotData {
    public ArrayList<WeaponData> weapons;
    public String color;

    /**
     * Class used to represent the WeaponSlot model on the client
     * Contains public info only
     * And optimized for usage on the client
     * Every attribute is public to make it easier to create it
     *
     * @param weaponsSlot the weapons slot
     */
    public WeaponsSlotData (WeaponsSlot weaponsSlot) {
        color = weaponsSlot.getColor().toString();
        weapons = new ArrayList<>(weaponsSlot.getWeapons().stream()
        .map(w -> new WeaponData(w)).collect(Collectors.toList()));
    }
}

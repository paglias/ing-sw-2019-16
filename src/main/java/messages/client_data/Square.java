package messages.client_data;

import java.util.ArrayList;

// Class used to represent the Square model on the client
// Contains public info only
// And optimized for usage on the client
// Every attribute is public to make it easier to create it
public class Square {
    public String color;
    public Boolean isSpawnPoint;
    public Boolean ammo; // If there's an ammo
    public WeaponsSlot weaponsSlot;
    public ArrayList<Square> canView = new ArrayList<>();
    public ArrayList<Square> canAccessDirectly = new ArrayList<>();
}

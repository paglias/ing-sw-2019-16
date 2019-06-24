package messages.client_data;

import models.cards.Weapon;

import java.util.ArrayList;
import java.util.stream.Collectors;


// TODO is this necessary?
public class WeaponData {
    public String color;
    public String name;
    public ArrayList<String> rechargeCost;
    public ArrayList<String> cost;

    public ArrayList<WeaponEffectData> primaryEffect;
    public ArrayList<WeaponEffectData> secondaryEffect;
    public ArrayList<WeaponEffectData> tertiaryEffect;

    public boolean loaded;

    public WeaponData (Weapon weapon) {
        color = weapon.getColor().toString();
        name = weapon.getName();

        primaryEffect = new ArrayList<>(weapon.getEffects(1).stream()
                .map(e -> new WeaponEffectData(e)).collect(Collectors.toList()));
        secondaryEffect = new ArrayList<>(weapon.getEffects(2).stream()
                .map(e -> new WeaponEffectData(e)).collect(Collectors.toList()));
        tertiaryEffect = new ArrayList<>(weapon.getEffects(3).stream()
                .map(e -> new WeaponEffectData(e)).collect(Collectors.toList()));

        rechargeCost = new ArrayList<>(weapon.getRechargeCost().stream()
                .map(c -> c.toString()).collect(Collectors.toList()));
        cost = new ArrayList<>(weapon.getCost().stream()
                .map(c -> c.toString()).collect(Collectors.toList()));
        loaded = weapon.isLoaded();
    }
}

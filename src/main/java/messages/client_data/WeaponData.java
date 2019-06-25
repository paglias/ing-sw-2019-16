package messages.client_data;

import models.cards.Weapon;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class WeaponData {
    public String color;
    public String name;
    public ArrayList<String> rechargeCost;
    public ArrayList<String> cost;

    public ArrayList<EffectData> primaryEffects;
    public ArrayList<EffectData> secondaryEffects;
    public ArrayList<EffectData> tertiaryEffects;

    public boolean loaded;

    public WeaponData (Weapon weapon) {
        color = weapon.getColor().toString();
        name = weapon.getName();

        if (weapon.getEffects(1) != null) {
            primaryEffects = new ArrayList<>(weapon.getEffects(1).stream()
                    .map(e -> new EffectData(e)).collect(Collectors.toList()));
        }

        if (weapon.getEffects(2) != null) {
            secondaryEffects = new ArrayList<>(weapon.getEffects(2).stream()
                    .map(e -> new EffectData(e)).collect(Collectors.toList()));
        }

        if (weapon.getEffects(3) != null) {
            tertiaryEffects = new ArrayList<>(weapon.getEffects(3).stream()
                    .map(e -> new EffectData(e)).collect(Collectors.toList()));
        }

        if (weapon.getRechargeCost() != null) {
            rechargeCost = new ArrayList<>(weapon.getRechargeCost().stream()
                    .map(c -> c.toString()).collect(Collectors.toList()));
        }

        if (weapon.getCost() != null) {
            cost = new ArrayList<>(weapon.getCost().stream()
                    .map(c -> c.toString()).collect(Collectors.toList()));
        }

        loaded = weapon.isLoaded();
    }
}

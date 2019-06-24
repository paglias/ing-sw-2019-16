package messages.client_data;

import models.cards.WeaponEffect;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class WeaponEffectData {
    public ArrayList<String> cost;
    public ArrayList<String> input;

    public WeaponEffectData (WeaponEffect effect) {
        cost = new ArrayList<>(effect.getCost().stream()
                .map(c -> c.toString()).collect(Collectors.toList()));
        input = new ArrayList<>(effect.getInput().stream()
                .map(i -> i.toString()).collect(Collectors.toList()));
    }
}

package messages.client_data;

import models.cards.Effect;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class EffectData {
    public ArrayList<String> cost;
    public ArrayList<String> input;

    public EffectData(Effect effect) {
        cost = effect.getCost() != null ? new ArrayList<>(effect.getCost().stream()
                .map(c -> c.toString()).collect(Collectors.toList())) : new ArrayList<>();
        input = new ArrayList<>(effect.getInput().stream()
                .map(i -> i.toString()).collect(Collectors.toList()));
    }
}
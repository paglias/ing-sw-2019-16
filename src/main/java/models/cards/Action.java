package models.cards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class Action {
    public enum Type {
        SHOOT,
        SHOOT_VIEW,
        SHOOT_EVERY,
        SHOOT_CANT_SEE,

        SHOOT_DIRECTION,
        SHOOT_ROOM_CAN_SEE,
        SHOOT_ONE_AWAY_VIEW,
        SHOOT_TARGET_VIEW,
        SHOOT_SECOND_TARGET_VIEW,
        SHOOT_EVERY_ONE_AWAY_VIEW,

        MOVE,
        MOVE_TARGET,
        MOVE_TARGET_CAN_SEE,
        MOVE_DIRECTION,
        MOVE_ANYWHERE,

        MARK,
        MARK_VIEW,
        MARK_TWO_AWAY_VIEW,
        MARK_EVERY_ONE_AWAY_VIEW,
        MARK_ONE_AWAY_VIEW,
        MARK_EVERY,

        ATTRACT_TARGET
    }

    private ArrayList<String> parameters;
    private Type type;

    /**
     * Gets type.
     *
     * @return the type
     */
    public Type getType () {
        return type;
    }

    /**
     * Create a map with a string key and an integer value.
     * Splits parameter in two, first one is the effect input, second one the index
     *
     * @return the parameters
     */
    public HashMap<Effect.Input, Integer> getParameters () {
        HashMap<Effect.Input, Integer> map = new HashMap<>();
        parameters.forEach(stringParameter -> {
            String[] parts = stringParameter.split(Pattern.quote("."));
            Effect.Input actionType = Effect.Input.valueOf(parts[0]);
            Integer index = Integer.parseInt(parts[1]);
            map.put(actionType, index);
        });

        return map;
    }

}

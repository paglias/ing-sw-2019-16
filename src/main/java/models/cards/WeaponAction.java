package models.cards;

import java.util.ArrayList;
import java.util.HashMap;

public class WeaponAction {
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

        MARK,
        MARK_VIEW,
        MARK_TWO_AWAY_VIEW,
        MARK_EVERY_ONE_AWAY_VIEW,
        MARK_EVERY,

        ATTRACT_TARGET
    }

    private ArrayList<String> parameters;
    private Type type;

    public Type getType () {
        return type;
    }

    public HashMap<WeaponEffect.Input, Integer> getParameters () {
        HashMap<WeaponEffect.Input, Integer> map = new HashMap<>();
        parameters.forEach(stringParameter -> {
            String[] parts = stringParameter.split(".");
            WeaponEffect.Input actionType = WeaponEffect.Input.valueOf(parts[0]);
            Integer index = Integer.parseInt(parts[1]);
            map.put(actionType, index);
        });

        return map;
    }

}

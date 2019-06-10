package models.cards;

import java.util.ArrayList;

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

    private ArrayList<String> parameters; // TODO transform in specific types?
    private Type name; // from the JSON

    public Type getType () {
        return name;
    }

}

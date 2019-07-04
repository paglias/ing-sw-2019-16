package models.cards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        SHOOT_EVERY_TWO_AWAY_VIEW,

        MOVE,
        MOVE_TARGET,
        MOVE_TARGET_TWO,
        MOVE_TARGET_CAN_SEE,
        MOVE_DIRECTION,
        MOVE_ANYWHERE,
        MOVE_TWO,

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
     * Return the parameters for this action.
     *
     * @return the parameters
     */
    public List<String> getParameters () {
        return  parameters;
    }

}

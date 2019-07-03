package messages;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public abstract class AbstractMessage implements MessageInterface {
    static Gson gson = new Gson();
    private static JsonParser jsonParser = new JsonParser();

    /**
     * Contains the method to deserialize messages from Json and to serialize
     * object in Json messages.
     *
     *
     * @param jsonMsg the json msg
     * @return the abstract message
     */
    public static AbstractMessage deserialize (String jsonMsg) {
        JsonObject jsonObj = jsonParser.parse(jsonMsg).getAsJsonObject();

        String msgTopicString = jsonObj.get("topic").getAsString();
        if (msgTopicString == null) throw new IllegalArgumentException("Missing message topic");

        MessageTopic topic = MessageTopic.valueOf(msgTopicString);

        switch (topic) {
            case CHOOSE_NICKNAME:
                return gson.fromJson(jsonObj, ChooseNicknameMessage.class);
            case GAME_SETTINGS:
                return gson.fromJson(jsonObj, GameSettingsMessage.class);
            case ACTION_START:
                return gson.fromJson(jsonObj, ActionStartMessage.class);

            case ACTION:
                return gson.fromJson(jsonObj, ActionMessage.class);

            case ACTION_END:
                return gson.fromJson(jsonObj, ActionEndMessage.class);

            case END_TURN:
                return gson.fromJson(jsonObj, EndTurnMessage.class);

            case GAME_STATE:
                return gson.fromJson(jsonObj, GameStateMessage.class);
            case END_GAME:
                return gson.fromJson(jsonObj, EndGameMessage.class);

            case ERROR:
                return gson.fromJson(jsonObj, ErrorMessage.class);
            default:
                throw new IllegalArgumentException("Invalid topic " + msgTopicString);
        }
    }

    /**
     * Serialize string.
     *
     * @return the string
     */
    public String serialize () {
        return gson.toJson(this);
    }
}

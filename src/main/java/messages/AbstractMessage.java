package messages;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public abstract class AbstractMessage implements MessageInterface {
    static Gson gson = new Gson();
    private static JsonParser jsonParser = new JsonParser();

    public static AbstractMessage deserialize (String jsonMsg) {
        JsonObject jsonObj = jsonParser.parse(jsonMsg).getAsJsonObject();

        String msgTopicString = jsonObj.get("topic").getAsString();
        if (msgTopicString == null) throw new IllegalArgumentException("Missing message topic");

        MessageTopic topic = MessageTopic.valueOf(msgTopicString);

        switch (topic) {
            case CONNECT:
                return gson.fromJson(jsonObj, ConnectMessage.class);
            case DISCONNECT:
                return gson.fromJson(jsonObj, DisconnectMessage.class);

            case CHOOSE_NICKNAME:
                return gson.fromJson(jsonObj, ChooseNicknameMessage.class);
            case CHOOSE_MAP:
                return gson.fromJson(jsonObj, ChooseMapMessage.class);
            case ACTION:
                return gson.fromJson(jsonObj, ActionMessage.class);
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

    public String serialize () {
        return gson.toJson(this);
    }
}

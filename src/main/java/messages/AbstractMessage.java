package messages;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public abstract class AbstractMessage {
    static Gson gson = new Gson();
    private static JsonParser jsonParser = new JsonParser();

    public static AbstractMessage deserialize (String jsonMsg) {
        JsonObject jsonObj = jsonParser.parse(jsonMsg).getAsJsonObject();

        String msgTopicString = jsonObj.get("topic").getAsString();
        if (msgTopicString == null) throw new IllegalArgumentException("Missing message topic");

        MessageTopic topic = MessageTopic.valueOf(msgTopicString);

        switch (topic) {
            case CONNECTION:
                return gson.fromJson(jsonObj, ConnectionMessage.class);
            case GAME_STARTED:
                return gson.fromJson(jsonObj, GameStartedMessage.class);
            default:
                throw new IllegalArgumentException("Invalid topic " + msgTopicString);
        }
    }

    public String serialize () {
        return gson.toJson(this);
    }
}

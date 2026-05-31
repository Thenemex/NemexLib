package nemexlib.api.util.parsers.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

public class PrettyJsonMaker {

    protected static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    protected static final JsonParser jp = new JsonParser();

    private PrettyJsonMaker() {}

    public static String prettify(String uglyJson) {
        return gson.toJson(jp.parse(uglyJson));
    }
}

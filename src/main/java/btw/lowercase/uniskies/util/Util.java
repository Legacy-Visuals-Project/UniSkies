package btw.lowercase.uniskies.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class Util {
    @Nullable
    public static JsonObject parseProperties(String input) {
        if (input.isEmpty()) {
            return null;
        }

        JsonObject object = new JsonObject();
        for (String line : input.split("\n")) {
            String[] parts = line.split("=");
            if (parts.length < 2) {
                continue;
            }

            String value = parts[parts.length - 1];
            String key = Arrays.toString(Arrays.copyOfRange(parts, 0, parts.length - 1));
            object.addProperty(key, value);
        }

        return object;
    }

    @Nullable
    public static JsonArray parseArray(String input) {
        return null;
    }
}

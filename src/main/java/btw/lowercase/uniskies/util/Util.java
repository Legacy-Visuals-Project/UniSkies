/**
 * UniSkies
 * A skybox mod made for Fabric, intended for OptiFine/MCPatcher based skybox packs.
 * <p>
 * Copyright (C) 2024-2025 lowercasebtw
 * Copyright (C) 2024-2025 Contributors to the project retain their copyright
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

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
        input.lines().forEach(line -> {
            String[] parts = line.split("=");
            if (parts.length >= 2) {
                String key = parts[0];
                String value = Arrays.toString(Arrays.copyOfRange(parts, 1, parts.length));
                object.addProperty(key, value.substring(1, value.length() - 1));
            }
        });

        return object;
    }

    @Nullable
    public static JsonArray parseArray(String input) {
        if (input.isEmpty()) {
            return null;
        }

        JsonArray array = new JsonArray();
        for (String part : input.split(" ")) {
            array.add(part);
        }

        return array;
    }

    public static String asString(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte it : bytes) {
            builder.appendCodePoint(it);
        }

        return builder.toString();
    }
}

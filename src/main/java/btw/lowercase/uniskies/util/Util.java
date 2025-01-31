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

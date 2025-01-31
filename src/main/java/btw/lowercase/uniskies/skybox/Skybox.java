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

package btw.lowercase.uniskies.skybox;

import btw.lowercase.uniskies.util.Blend;
import btw.lowercase.uniskies.util.Util;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3d;

public record Skybox(
        int fadeInDuration, int fadeOutDuration,
        Vector3d axis,
        String texture,
        Blend blend,
        boolean rotate
) {
    @Nullable
    public static Skybox parse(String input) {
        JsonObject properties = Util.parseProperties(input);
        if (properties == null) {
            return null;
        }

        int fadeInDuration = -1;
        if (properties.has("fadeInDuration") && properties.get("fadeInDuration") instanceof JsonPrimitive primitive) {
            fadeInDuration = primitive.getAsInt();
        }

        int fadeOutDuration = -1;
        if (properties.has("fadeOutDuration") && properties.get("fadeOutDuration") instanceof JsonPrimitive primitive) {
            fadeOutDuration = primitive.getAsInt();
        }

        Vector3d axis = new Vector3d(0.0, 0.0, 1.0);
        if (properties.has("axis") && properties.get("axis") instanceof JsonPrimitive primitive && primitive.isString()) {
            JsonArray array = Util.parseArray(primitive.getAsString());
            if (array != null && array.size() == 3) {
                // TODO;
            }
        }

        String texture = null;
        if (properties.has("source") && properties.get("source") instanceof JsonPrimitive primitive && primitive.isString()) {
            texture = primitive.getAsString();
        }

        Blend blend = Blend.ADD;
        if (properties.has("blend") && properties.get("blend") instanceof JsonPrimitive primitive && primitive.isString()) {
            blend = Blend.byName(primitive.getAsString());
        }

        boolean rotate = true;
        if (properties.has("rotate") && properties.get("rotate") instanceof JsonPrimitive primitive && primitive.isBoolean()) {
            rotate = primitive.getAsBoolean();
        }

        return new Skybox(fadeInDuration, fadeOutDuration, axis, texture, blend, rotate);
    }
}
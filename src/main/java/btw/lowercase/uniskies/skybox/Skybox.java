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
import org.joml.Vector3d;

public record Skybox(
        int startFadeInDuration, int endFadeInDuration,
        int startFadeOutDuration, int endFadeOutDuration,
        Vector3d axis,
        String texture,
        Blend blend,
        boolean rotate
) {
    public static Skybox load(JsonObject properties) {
        int startFadeInDuration = -1;
        if (properties.has("startFadeIn") && properties.get("startFadeIn") instanceof JsonPrimitive primitive && primitive.isString()) {
            startFadeInDuration = 0; // TODO: primitive.getAsInt();
        }

        int endFadeInDuration = -1;
        if (properties.has("endFadeIn") && properties.get("endFadeIn") instanceof JsonPrimitive primitive && primitive.isString()) {
            endFadeInDuration = 0; // TODO: primitive.getAsInt();
        }

        int startFadeOutDuration = -1;
        if (properties.has("startFadeOut") && properties.get("startFadeOut") instanceof JsonPrimitive primitive && primitive.isString()) {
            startFadeOutDuration = 0; // TODO: primitive.getAsInt();
        }

        int endFadeOutDuration = -1;
        if (properties.has("endFadeOut") && properties.get("endFadeOut") instanceof JsonPrimitive primitive && primitive.isString()) {
            endFadeOutDuration = 0; // TODO: primitive.getAsInt();
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

        return new Skybox(startFadeInDuration, endFadeInDuration, startFadeOutDuration, endFadeOutDuration, axis, texture, blend, rotate);
    }
}
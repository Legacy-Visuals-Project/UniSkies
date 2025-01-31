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

import java.util.Arrays;

public enum Blend {
    ADD("add"),
    SUBTRACT("subtract"),
    MULTIPLY("multiply"),
    DODGE("dodge"),
    BURN("burn"),
    SCREEN("screen"),
    REPLACE("replace"),
    OVERLAY("overlay"),
    ALPHA("alpha");

    private final String name;

    Blend(String name) {
        this.name = name;
    }

    public static Blend byName(String name) {
        return Arrays.stream(Blend.values()).filter(blend -> blend.name.equals(name)).findFirst().orElse(ADD);
    }
}

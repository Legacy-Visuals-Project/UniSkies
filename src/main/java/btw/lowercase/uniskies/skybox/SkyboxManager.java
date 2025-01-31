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

import java.util.ArrayList;
import java.util.List;

public class SkyboxManager {
    private static SkyboxManager instance = null;

    private final List<Skybox> skyboxes;

    public SkyboxManager() {
        if (instance != null) {
            throw new RuntimeException("SkyboxManager must only have one instance!");
        }

        instance = this;
        this.skyboxes = new ArrayList<>();
    }

    public void load() {
        this.skyboxes.clear();
        // TODO
    }

    public static SkyboxManager getInstance() {
        return instance;
    }

    public List<Skybox> getSkyboxes() {
        return this.skyboxes;
    }
}

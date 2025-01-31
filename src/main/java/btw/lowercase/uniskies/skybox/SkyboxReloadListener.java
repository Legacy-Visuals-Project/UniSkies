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

import btw.lowercase.uniskies.UniSkies;
import btw.lowercase.uniskies.util.Util;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class SkyboxReloadListener implements IdentifiableResourceReloadListener {
    @Override
    public @NotNull CompletableFuture<Void> reload(PreparationBarrier preparationBarrier, ResourceManager resourceManager, Executor executor, Executor executor2) {
        return CompletableFuture.runAsync(() -> {
            Map<String, List<Skybox>> skyboxes = SkyboxManager.getInstance().getSkyboxes();
            skyboxes.clear();

            Map<ResourceLocation, Resource> resources = resourceManager.listResources(UniSkies.SKY_PATH, (resourceLocation) -> resourceLocation.getPath().endsWith(".properties"));
            for (var entry : resources.entrySet()) {
                String path = entry.getKey().getPath();
                path = path.substring(UniSkies.SKY_PATH.length() + 1, path.length() - 1);
                String world = path.split("/")[0];
                try (InputStream inputStream = resourceManager.open(entry.getKey())) {
                    String input = Util.asString(inputStream.readAllBytes());

                    JsonObject properties = Util.parseProperties(input);
                    if (properties == null) {
                        throw new Exception("Failed to parse properties.");
                    }

                    Skybox skybox = Skybox.load(properties);
                    if (!skyboxes.containsKey(world)) {
                        skyboxes.put(world, new ArrayList<>());
                    }

                    skyboxes.get(world).add(skybox);
                } catch (Exception exception) {
                    System.err.println("Failed to load skybox " + entry.getKey() + " for world " + world + "!");
                    exception.printStackTrace();
                }
            }

            for (var entry : skyboxes.entrySet()) {
                System.out.println("Loaded skybox in world " + entry.getKey() + " with " + entry.getValue().size() + " skyboxes");
            }
        }).thenCompose(preparationBarrier::wait);
    }

    @Override
    public ResourceLocation getFabricId() {
        return UniSkies.id("reader"); // uh? IDK what this is for, so I'm just going to put "reader" as that's what I'm doing lol
    }
}

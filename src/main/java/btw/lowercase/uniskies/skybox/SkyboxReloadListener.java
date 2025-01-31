package btw.lowercase.uniskies.skybox;

import btw.lowercase.uniskies.UniSkies;
import btw.lowercase.uniskies.util.Util;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class SkyboxReloadListener implements IdentifiableResourceReloadListener {
    @Override
    public @NotNull CompletableFuture<Void> reload(PreparationBarrier preparationBarrier, ResourceManager resourceManager, Executor executor, Executor executor2) {
        return CompletableFuture.runAsync(() -> {
            List<Skybox> skyboxes = SkyboxManager.getInstance().getSkyboxes();
            skyboxes.clear();

            Map<ResourceLocation, Resource> resources = resourceManager.listResources("optifine/sky", (resourceLocation) -> resourceLocation.getPath().endsWith(".properties"));
            for (var entry : resources.entrySet()) {
                try (InputStream inputStream = resourceManager.open(entry.getKey())) {
                    String input = Util.asString(inputStream.readAllBytes());
                    Skybox skybox = Skybox.parse(input);
                    if (skybox != null) {
                        skyboxes.add(skybox);
                    }
                } catch (IOException e) {
                    System.err.println("Failed to load skybox " + entry.getKey() + "!");
                }
            }

            for (var skybox : skyboxes) {
                System.out.println("Loaded skybox with texture: " + skybox.texture());
            }
        }).thenCompose(preparationBarrier::wait);
    }

    @Override
    public ResourceLocation getFabricId() {
        return UniSkies.id("reader"); // uh? IDK what this is for, so I'm just going to put "reader" as that's what I'm doing lol
    }
}

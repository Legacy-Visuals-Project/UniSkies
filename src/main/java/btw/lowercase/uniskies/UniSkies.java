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

package btw.lowercase.uniskies;

import btw.lowercase.uniskies.skybox.Skybox;
import btw.lowercase.uniskies.skybox.SkyboxManager;
import com.mojang.brigadier.Command;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class UniSkies implements ClientModInitializer {
    public static final String SKY_PATH = "optifine/sky";
    public static final String OVERWORLD = "world0";
    public static final String THE_END = "world1";

    @Override
    public void onInitializeClient() {
        SkyboxManager skyboxManager = new SkyboxManager();
        skyboxManager.setup();
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            var command = ClientCommandManager.literal("uniskies");
            command.executes((context) -> {
                var skyboxes = skyboxManager.getSkyboxes();
                if (!skyboxes.isEmpty()) {
                    context.getSource().sendFeedback(Component.literal("Skyboxes loaded:").withStyle(ChatFormatting.GOLD));
                    for (var entry : skyboxes.entrySet()) {
                        context.getSource().sendFeedback(Component.literal("- World: " + entry.getKey()));
                        int i = 0;
                        for (Skybox skybox : entry.getValue()) {
                            context.getSource().sendFeedback(Component.literal(" - " + i).withColor((int) Math.floor(Math.random() * 0xFFFFFF)));
                            context.getSource().sendFeedback(Component.literal("  - startFadeInDuration: " + skybox.startFadeInDuration()));
                            context.getSource().sendFeedback(Component.literal("  - endFadeInDuration: " + skybox.endFadeInDuration()));
                            context.getSource().sendFeedback(Component.literal("  - startFadeOutDuration: " + skybox.startFadeOutDuration()));
                            context.getSource().sendFeedback(Component.literal("  - endFadeOutDuration: " + skybox.endFadeOutDuration()));
                            context.getSource().sendFeedback(Component.literal("  - texture: " + skybox.texture()));
                            context.getSource().sendFeedback(Component.literal("  - blend: " + skybox.blend()));
                            context.getSource().sendFeedback(Component.literal("  - rotate: " + skybox.rotate()));
                            i++;
                        }
                    }
                } else {
                    context.getSource().sendFeedback(Component.literal("No skyboxes loaded.").withStyle(ChatFormatting.RED));
                }
                return Command.SINGLE_SUCCESS;
            });
            dispatcher.register(command);
        });
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath("uniskies", path);
    }
}
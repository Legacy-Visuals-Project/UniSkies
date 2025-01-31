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

package btw.lowercase.uniskies.mixins;

import btw.lowercase.uniskies.UniSkies;
import btw.lowercase.uniskies.skybox.Skybox;
import btw.lowercase.uniskies.skybox.SkyboxManager;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.FogParameters;
import net.minecraft.client.renderer.LevelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;

@Mixin(LevelRenderer.class)
public abstract class MixinLevelRenderer {
    @Inject(method = "method_62215", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/SkyRenderer;renderEndSky()V", shift = At.Shift.AFTER))
    private void uniskies$renderSky$the_end(FogParameters fogParameters, DimensionSpecialEffects.SkyType skyType, float tickDelta, DimensionSpecialEffects dimensionSpecialEffects, CallbackInfo ci) {
        Map<String, List<Skybox>> skyboxes = SkyboxManager.getInstance().getSkyboxes();
        if (!skyboxes.containsKey(UniSkies.THE_END)) return;
        PoseStack poseStack = new PoseStack();
        for (Skybox skybox : skyboxes.get(UniSkies.THE_END)) {
            skybox.render(poseStack, tickDelta);
        }
    }

    @Inject(method = "method_62215", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/SkyRenderer;renderSunMoonAndStars(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource$BufferSource;FIFFLnet/minecraft/client/renderer/FogParameters;)V", shift = At.Shift.BEFORE))
    private void uniskies$renderSky$overworld(FogParameters fogParameters, DimensionSpecialEffects.SkyType skyType, float tickDelta, DimensionSpecialEffects dimensionSpecialEffects, CallbackInfo ci, @Local PoseStack poseStack) {
        Map<String, List<Skybox>> skyboxes = SkyboxManager.getInstance().getSkyboxes();
        if (!skyboxes.containsKey(UniSkies.OVERWORLD)) return;
        for (Skybox skybox : skyboxes.get(UniSkies.OVERWORLD)) {
            skybox.render(poseStack, tickDelta);
        }
    }
}
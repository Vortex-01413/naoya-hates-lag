package com.naoya.lag.mixin.render;

import com.naoya.lag.config.ModConfig;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BackgroundRenderer.class)
public class VoidFogMixin {
    @Inject(method = "applyFog", at = @At("HEAD"), cancellable = true)
    private static void naoya$noVoidFog(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, float tickDelta, CallbackInfo ci) {
        if (ModConfig.noVoidFog && fogType == BackgroundRenderer.FogType.FOG_SKY) ci.cancel();
    }
}

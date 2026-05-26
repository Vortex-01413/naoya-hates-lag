package com.naoya.lag.mixin.render;

import com.naoya.lag.config.ModConfig;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BackgroundRenderer.class)
public class FogMixin {
    @Inject(method = "applyFog", at = @At("HEAD"), cancellable = true)
    private static void naoya$noFog(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, float tickDelta, CallbackInfo ci) {
        if (ModConfig.noFog) ci.cancel();
    }
}

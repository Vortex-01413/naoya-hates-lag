package com.naoya.lag.mixin.render;

import net.minecraft.client.render.Fog;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.BackgroundRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BackgroundRenderer.class)
public class FogTransitionMixin {
    private static float targetFogStart = 0;
    private static float targetFogEnd = 0;
    private static float currentFogStart = 0;
    private static float currentFogEnd = 0;
    private static boolean isTransitioning = false;
    private static int transitionTicks = 0;
    
    @Inject(method = "applyFog", at = @At("HEAD"), cancellable = true)
    private static void smoothFogTransition(Camera camera, Fog.FogType fogType, float viewDistance, boolean thickFog, float tickDelta, CallbackInfo ci) {
        // This smooths out fog changes to eliminate flashing
        if (isTransitioning && transitionTicks > 0) {
            float progress = (float) transitionTicks / 20f; // Smooth over 1 second
            if (progress >= 1f) {
                isTransitioning = false;
            } else {
                float newStart = currentFogStart + (targetFogStart - currentFogStart) * progress;
                float newEnd = currentFogEnd + (targetFogEnd - currentFogEnd) * progress;
                // Apply smoothed fog values
                Fog.setFogStart(newStart);
                Fog.setFogEnd(newEnd);
                ci.cancel();
            }
        } else if (!isTransitioning && targetFogEnd > 0) {
            targetFogStart = 0;
            targetFogEnd = 0;
        }
    }
    
    public static void onRenderDistanceChanged(int oldDist, int newDist) {
        targetFogStart = 0;
        targetFogEnd = newDist * 16f; // Convert chunks to blocks
        currentFogStart = 0;
        currentFogEnd = oldDist * 16f;
        isTransitioning = true;
        transitionTicks = 20; // 1 second at 20 ticks/sec
    }
}

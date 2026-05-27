package com.naoya.lag.core.performance;

import net.minecraft.client.MinecraftClient;
import com.naoya.lag.mixin.render.FogTransitionMixin;

public class FPSAutoAdjust {
    private static int stableFpsCounter = 0;
    private static int memoryTicker = 0;
    private static int lastRenderDist = -1;
    
    public static void tick(MinecraftClient client) {
        if (client == null || client.player == null) return;
        
        int currentFps = client.getCurrentFps();
        int currentDist = client.options.getViewDistance().getValue();
        
        // Detect render distance change
        if (lastRenderDist == -1) {
            lastRenderDist = currentDist;
        }
        
        int newDist = currentDist;
        
        if (currentFps < 25 && currentDist > 5) {
            newDist = currentDist - 2;
        } else if (currentFps > 50 && currentDist < 12 && stableFpsCounter > 100) {
            newDist = currentDist + 1;
        }
        
        // Apply change with smooth fog transition
        if (newDist != currentDist) {
            FogTransitionMixin.onRenderDistanceChanged(currentDist, newDist);
            client.options.getViewDistance().setValue(newDist);
        }
        
        if (currentFps > 45 && currentFps < 55) {
            stableFpsCounter++;
        } else {
            stableFpsCounter = 0;
        }
        
        if (++memoryTicker >= 600) {
            System.gc();
            memoryTicker = 0;
        }
        
        lastRenderDist = client.options.getViewDistance().getValue();
    }
}

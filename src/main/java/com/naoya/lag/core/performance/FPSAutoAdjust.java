package com.naoya.lag.core.performance;

import net.minecraft.client.MinecraftClient;

public class FPSAutoAdjust {
    private static int stableFpsCounter = 0;
    private static int memoryTicker = 0;
    private static int lastRenderDist = -1;
    
    public static void tick(MinecraftClient client) {
        if (client == null || client.player == null) return;
        
        // FPS-based render distance
        int currentFps = client.getCurrentFps();
        int currentDist = client.options.viewDistance;
        
        if (currentFps < 25 && currentDist > 5) {
            client.options.viewDistance = currentDist - 2;
        } else if (currentFps > 50 && currentDist < 12 && stableFpsCounter > 100) {
            client.options.viewDistance = currentDist + 1;
        }
        
        // Stability counter
        if (currentFps > 45 && currentFps < 55) {
            stableFpsCounter++;
        } else {
            stableFpsCounter = 0;
        }
        
        // Memory sweep every 30 seconds
        if (++memoryTicker >= 600) {
            System.gc();
            memoryTicker = 0;
        }
    }
}

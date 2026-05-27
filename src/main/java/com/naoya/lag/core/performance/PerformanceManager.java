package com.naoya.lag.core.performance;

import com.naoya.lag.config.ModConfig;
import net.minecraft.client.MinecraftClient;

public class PerformanceManager {
    private static int previousRenderDistance = -1;
    private static boolean panicMode = false;
    
    public static void panicMode() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null && client.options != null) {
            if (!panicMode) {
                previousRenderDistance = client.options.getViewDistance().getValue();
                client.options.getViewDistance().setValue(2);
                panicMode = true;
            } else {
                client.options.getViewDistance().setValue(previousRenderDistance);
                panicMode = false;
            }
        }
    }
    
    public static void memorySweep() {
        if (ModConfig.isMemorySweep()) {
            long usedMemory = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1048576;
            long maxMemory = Runtime.getRuntime().maxMemory() / 1048576;
            int percentUsed = (int)((usedMemory * 100) / maxMemory);
            if (percentUsed > ModConfig.getGcThreshold()) {
                System.gc();
            }
        }
    }
    
    public static void onProfileChanged() {
        // Cloud settings removed - use game's native options menu instead
        // This prevents the compilation error
    }
}

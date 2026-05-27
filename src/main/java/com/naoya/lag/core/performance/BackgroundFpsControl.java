package com.naoya.lag.core.performance;

import net.minecraft.client.MinecraftClient;

public class BackgroundFpsControl {
    private static boolean wasFocused = true;
    private static int originalFpsLimit = 60;
    
    public static void tick(MinecraftClient client) {
        boolean isFocused = client.isWindowFocused();
        
        if (!isFocused && wasFocused) {
            originalFpsLimit = client.options.maxFps;
            client.options.maxFps = 5;
        } else if (isFocused && !wasFocused) {
            client.options.maxFps = originalFpsLimit;
        }
        
        wasFocused = isFocused;
    }
}

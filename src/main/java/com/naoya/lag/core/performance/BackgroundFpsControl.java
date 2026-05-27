package com.naoya.lag.core.performance;

import net.minecraft.client.MinecraftClient;

public class BackgroundFpsControl {
    private static boolean wasFocused = true;
    private static int originalFpsLimit = 60;
    
    public static void tick(MinecraftClient client) {
        boolean isFocused = client.isWindowFocused();
        
        if (!isFocused && wasFocused) {
            // Tabbed out - save original limit and cap to 5 FPS
            originalFpsLimit = client.options.getMaxFps().getValue();
            client.options.getMaxFps().setValue(5);
        } else if (isFocused && !wasFocused) {
            // Tabbed back in - restore original
            client.options.getMaxFps().setValue(originalFpsLimit);
        }
        
        wasFocused = isFocused;
    }
}

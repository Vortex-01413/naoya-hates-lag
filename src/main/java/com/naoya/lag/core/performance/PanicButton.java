package com.naoya.lag.core.performance;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;

public class PanicButton {
    private static int originalRenderDistance = 8;
    private static boolean isPanicMode = false;
    private static int lastPressTick = 0;
    
    public static void handleKeyPress(MinecraftClient client, int keyCode) {
        // P key = 80
        if (keyCode != 80) return;
        
        long now = System.currentTimeMillis();
        if (now - lastPressTick < 500) return; // Debounce
        lastPressTick = (int) now;
        
        GameOptions options = client.options;
        
        if (!isPanicMode) {
            // Enter panic mode
            originalRenderDistance = options.viewDistance;
            options.viewDistance = 2;
            options.entityShadowsEnabled = false;
            options.particles = net.minecraft.client.option.ParticlesMode.MINIMAL;
            isPanicMode = true;
            System.out.println("[Naoya] PANIC MODE: Render distance = 2, shadows off, particles minimal");
        } else {
            // Exit panic mode
            options.viewDistance = originalRenderDistance;
            isPanicMode = false;
            System.out.println("[Naoya] Exited panic mode, restored to RD " + originalRenderDistance);
        }
    }
    
    public static boolean isPanicModeActive() {
        return isPanicMode;
    }
}

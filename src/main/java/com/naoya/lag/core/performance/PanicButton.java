package com.naoya.lag.core.performance;

import net.minecraft.client.MinecraftClient;

public class PanicButton {
    private static int originalRenderDistance = 8;
    private static boolean isPanicMode = false;
    private static int lastPressTick = 0;
    
    public static void handleKeyPress(MinecraftClient client, int keyCode) {
        if (keyCode != 80) return;
        
        long now = System.currentTimeMillis();
        if (now - lastPressTick < 500) return;
        lastPressTick = (int) now;
        
        if (!isPanicMode) {
            // Enter panic mode - use getter/setter
            originalRenderDistance = client.options.getViewDistance().getValue();
            client.options.getViewDistance().setValue(2);
            client.options.setEntityShadowsEnabled(false);
            client.options.getParticles().setValue(net.minecraft.client.option.ParticlesMode.MINIMAL);
            isPanicMode = true;
            System.out.println("[Naoya] PANIC MODE: Render distance = 2, shadows off, particles minimal");
        } else {
            // Exit panic mode
            client.options.getViewDistance().setValue(originalRenderDistance);
            isPanicMode = false;
            System.out.println("[Naoya] Exited panic mode, restored to RD " + originalRenderDistance);
        }
    }
    
    public static boolean isPanicModeActive() {
        return isPanicMode;
    }
}

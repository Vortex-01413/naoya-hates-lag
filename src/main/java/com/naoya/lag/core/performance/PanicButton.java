package com.naoya.lag.core.performance;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.ParticlesMode;
import net.minecraft.client.option.CloudRenderMode;

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
            originalRenderDistance = client.options.getViewDistance().getValue();
            client.options.getViewDistance().setValue(2);
            client.options.getEntityShadows().setValue(false);
            client.options.getParticles().setValue(ParticlesMode.MINIMAL);
            client.options.getCloudRenderMode().setValue(CloudRenderMode.OFF);
            client.options.getAo().setValue(false);
            client.options.getBiomeBlendRadius().setValue(1);
            isPanicMode = true;
            System.out.println("[Naoya] PANIC MODE: RD=2, shadows off, particles minimal, clouds off, smooth lighting off");
        } else {
            client.options.getViewDistance().setValue(originalRenderDistance);
            isPanicMode = false;
            System.out.println("[Naoya] Exited panic mode, restored to RD " + originalRenderDistance);
        }
    }
    
    public static boolean isPanicModeActive() { return isPanicMode; }
}

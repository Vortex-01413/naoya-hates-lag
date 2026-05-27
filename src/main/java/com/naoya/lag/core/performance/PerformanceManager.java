package com.naoya.lag.core.performance;

import com.naoya.lag.config.ModConfig;
import net.minecraft.client.MinecraftClient;

public class PerformanceManager {
    private int previousRenderDistance = -1;
    private boolean panicMode = false;
    private int panicTimer = 0;
    
    public void onProfileChanged() {
        // Do NOT change render distance - prevents grey flashing
        // Just apply visual settings via mixins
        applyVisualSettings();
    }
    
    public void activatePanicMode() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null && client.options != null) {
            if (!panicMode) {
                previousRenderDistance = client.options.getViewDistance().getValue();
                client.options.getViewDistance().setValue(2);
                panicMode = true;
                panicTimer = 100;
            } else {
                client.options.getViewDistance().setValue(previousRenderDistance);
                panicMode = false;
                panicTimer = 0;
            }
        }
    }
    
    private void applyVisualSettings() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null && client.options != null) {
            // Apply cloud settings
            if (ModConfig.isCloudsOff()) {
                client.options.getClouds().setValue(net.minecraft.client.option.CloudRenderMode.OFF);
            }
            // Apply smooth lighting
            client.options.getSmoothLighting().setValue(ModConfig.isSmoothLightingOff() ? 
                net.minecraft.client.option.SmoothLighting.OFF : net.minecraft.client.option.SmoothLighting.MAX);
        }
    }
    
    public void tick(MinecraftClient client) {
        if (panicMode && panicTimer > 0) {
            panicTimer--;
            if (panicTimer <= 0 && !panicMode) {
                // Recovery handled elsewhere
            }
        }
        
        // Apply background FPS cap
        if (ModConfig.isBackgroundFpsCap() && client != null && !client.isWindowFocused()) {
            // Background FPS limiting would go here
        }
        
        // Memory sweep
        if (ModConfig.isMemorySweep()) {
            long usedMemory = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1048576;
            long maxMemory = Runtime.getRuntime().maxMemory() / 1048576;
            int percentUsed = (int)((usedMemory * 100) / maxMemory);
            
            if (percentUsed > ModConfig.getGcThreshold()) {
                System.gc();
            }
        }
    }
    
    public boolean isPanicMode() { return panicMode; }
}

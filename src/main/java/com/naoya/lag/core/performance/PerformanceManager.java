package com.naoya.lag.core.performance;

import com.naoya.lag.config.ModConfig;
import net.minecraft.client.MinecraftClient;

public class PerformanceManager {
    private int[] fpsHistory = new int[20];
    private int fpsIndex = 0;
    
    public void onProfileChanged() {
        int[] distances = {4, 6, 10, 14, 20};
        if (MinecraftClient.getInstance().options != null) {
            MinecraftClient.getInstance().options.getViewDistance().setValue(distances[ModConfig.currentProfile]);
        }
    }
    
    public void activatePanicMode() {
        if (MinecraftClient.getInstance().options != null) {
            MinecraftClient.getInstance().options.getViewDistance().setValue(2);
        }
    }
    
    public void tick(MinecraftClient client) {
        if (client == null || client.player == null) return;
        
        int fps = client.getCurrentFps();
        fpsHistory[fpsIndex % fpsHistory.length] = fps;
        fpsIndex++;
        
        if (ModConfig.autoProfileSwitcher && fpsIndex > 20) {
            int avg = getAverageFps();
            if (avg < 25 && ModConfig.currentProfile > 0) {
                ModConfig.currentProfile--;
                onProfileChanged();
            } else if (avg > 55 && ModConfig.currentProfile < 4) {
                ModConfig.currentProfile++;
                onProfileChanged();
            }
        }
    }
    
    private int getAverageFps() {
        int sum = 0;
        for (int i = 0; i < fpsHistory.length && i < fpsIndex; i++) {
            sum += fpsHistory[i];
        }
        return sum / Math.min(fpsHistory.length, fpsIndex);
    }
}

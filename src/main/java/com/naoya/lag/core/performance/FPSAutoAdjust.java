package com.naoya.lag.core.performance;
import com.naoya.lag.config.ModConfig;
import net.minecraft.client.MinecraftClient;
public class FPSAutoAdjust {
    private static int stableFpsCounter = 0;
    private static int lastRenderDist = -1;
    public static void tick(MinecraftClient client) {
        int fps = client.getCurrentFps();
        int cur = client.options.getViewDistance().getValue();
        if (lastRenderDist == -1) lastRenderDist = cur;
        int newDist = cur;
        if (fps < ModConfig.lowFpsThreshold && cur > ModConfig.minRenderDistance)
            newDist = Math.max(ModConfig.minRenderDistance, cur - 2);
        else if (fps > ModConfig.highFpsThreshold && cur < ModConfig.maxRenderDistance && stableFpsCounter > 100)
            newDist = Math.min(ModConfig.maxRenderDistance, cur + 1);
        if (newDist != cur) client.options.getViewDistance().setValue(newDist);
        stableFpsCounter = (fps >= ModConfig.lowFpsThreshold-5 && fps <= ModConfig.highFpsThreshold+5) ? stableFpsCounter+1 : 0;
        lastRenderDist = client.options.getViewDistance().getValue();
    }
}

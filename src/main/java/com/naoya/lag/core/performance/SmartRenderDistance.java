package com.naoya.lag.core.performance;

import com.naoya.lag.config.ModConfig;
import net.minecraft.client.MinecraftClient;

public class SmartRenderDistance {

    private static int lowFpsTicks = 0;
    private static int highFpsTicks = 0;

    public static void tick() {
        if (!ModConfig.smartRenderDistance) return;

        int fps = PerformanceManager.getFps();
        MinecraftClient mc = MinecraftClient.getInstance();
        int current = mc.options.getViewDistance().getValue();

        if (fps < 20) {
            lowFpsTicks++;
            highFpsTicks = 0;
            if (lowFpsTicks >= 3 && current > ModConfig.minRenderDistance) {
                mc.options.getViewDistance().setValue(current - 1);
                lowFpsTicks = 0;
            }
        } else if (fps > 50) {
            highFpsTicks++;
            lowFpsTicks = 0;
            if (highFpsTicks >= 5 && current < ModConfig.maxRenderDistance) {
                mc.options.getViewDistance().setValue(current + 1);
                highFpsTicks = 0;
            }
        } else {
            lowFpsTicks = 0;
            highFpsTicks = 0;
        }
    }
}

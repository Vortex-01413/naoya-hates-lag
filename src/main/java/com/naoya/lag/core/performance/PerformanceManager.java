package com.naoya.lag.core.performance;

import com.naoya.lag.config.ModConfig;
import net.minecraft.client.MinecraftClient;

public class PerformanceManager {

    private static int fpsTier = 3;
    private static int tickCounter = 0;
    private static int lowFpsTicks = 0;
    private static final int[] fpsHistory = new int[20];
    private static int fpsHistoryIndex = 0;
    private static int smoothFpsValue = 0;

    public static void tick() {
        tickCounter++;

        MinecraftClient mc = MinecraftClient.getInstance();
        int fps = mc.getCurrentFps();

        fpsHistory[fpsHistoryIndex % 20] = fps;
        fpsHistoryIndex++;
        int sum = 0;
        for (int f : fpsHistory) sum += f;
        smoothFpsValue = sum / 20;

        if (tickCounter % 20 != 0) return;

        if (fps >= 60) {
            fpsTier = 3;
            lowFpsTicks = 0;
        } else if (fps >= 30) {
            fpsTier = 2;
            lowFpsTicks = 0;
        } else if (fps >= 15) {
            fpsTier = 1;
            lowFpsTicks++;
        } else {
            fpsTier = 0;
            lowFpsTicks++;
        }

        if (ModConfig.autoProfileSwitcher && lowFpsTicks >= 3) {
            if (ModConfig.profile != ModConfig.DeviceProfile.ITEL_A70
                    && ModConfig.profile != ModConfig.DeviceProfile.LOW_END) {
                ModConfig.applyProfile(ModConfig.DeviceProfile.LOW_END);
                if (mc.player != null) {
                    mc.player.sendMessage(
                        net.minecraft.text.Text.literal(
                            "[NaoyaHatesLag] Low FPS detected! Switched to LOW_END profile."
                        ), false
                    );
                }
                lowFpsTicks = 0;
            }
        }

        SmartRenderDistance.tick();
    }

    public static int getTier() { return fpsTier; }

    public static int getFps() {
        return MinecraftClient.getInstance().getCurrentFps();
    }

    public static int getSmoothFps() {
        return ModConfig.smoothFps ? smoothFpsValue : getFps();
    }

    public static long getFreeRamMB() {
        Runtime rt = Runtime.getRuntime();
        return (rt.maxMemory() - rt.totalMemory() + rt.freeMemory()) / 1024 / 1024;
    }
}

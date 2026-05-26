package com.naoya.lag.core.performance;

import com.naoya.lag.config.ModConfig;
import net.minecraft.client.MinecraftClient;

public class PerformanceManager {

    private static int fpsTier = 3;
    private static int tickCounter = 0;
    private static int lowFpsTicks = 0;
    private static final int[] fpsHistory = new int[20];
    private static int fpsHistoryIndex = 0;
    private static int smoothFps = 0;

    public static void tick() {
        tickCounter++;

        MinecraftClient mc = MinecraftClient.getInstance();
        int fps = mc.getCurrentFps();

        // Smooth FPS average
        fpsHistory[fpsHistoryIndex 
package com.naoya.lag.debug;

import com.naoya.lag.config.ModConfig;
import com.naoya.lag.core.performance.PerformanceManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

public class DebugHudRenderer {

    public static boolean enabled = true;

    public static void render(DrawContext context) {

        if (!enabled || !ModConfig.debugHud)
            return;

        MinecraftClient mc = MinecraftClient.getInstance();

        String line1 = "Naoya Hates Lag | Tier: " + PerformanceManager.getTier()
                + " | FPS: " + PerformanceManager.getFps();

        String line2 = "RAM Free: " + PerformanceManager.getFreeRamMB() + "MB"
                + " | Profile: " + ModConfig.profile.name();

        context.drawText(mc.textRenderer, Text.literal(line1), 5, 5, 0xFFFFFF, true);
        context.drawText(mc.textRenderer, Text.literal(line2), 5, 16, 0xAAFFAA, true);
    }
}
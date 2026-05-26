
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

        context.drawText(
                mc.textRenderer,
                Text.literal(
                        "Naoya Hates Lag | Tier: " +
                        PerformanceManager.getTier()
                ),
                5,
                5,
                0xFFFFFF,
                true
        );
    }
}

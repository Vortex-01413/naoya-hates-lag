package com.naoya.lag.debug;

import com.naoya.lag.config.ModConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class DebugHudRenderer {
    public void render(DrawContext context, int y) {
        if (!ModConfig.debugHud) return;
        
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null) return;
        
        int fps = client.getCurrentFps();
        int rd = client.options.getViewDistance().getValue();
        String profile = ModConfig.getProfileName();
        
        String text = String.format("§a[Naoya] §f%d fps | RD:%d | %s", fps, rd, profile);
        context.drawText(client.textRenderer, text, 2, y, 0xFFFFFF, true);
    }
}

package com.naoya.lag.debug;

import com.naoya.lag.config.ModConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class DebugHudRenderer {
    public void render(DrawContext context, int y) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null) return;
        
        Runtime runtime = Runtime.getRuntime();
        long usedMemory = (runtime.totalMemory() - runtime.freeMemory()) / 1048576;
        long maxMemory = runtime.maxMemory() / 1048576;
        
        int fps = client.getCurrentFps();
        int rd = client.options.getViewDistance().getValue();
        String profile = ModConfig.getProfileName();
        
        String text = String.format("§a[Naoya] §f%d fps | RD:%d | Mem:%d/%d MB | %s", 
            fps, rd, usedMemory, maxMemory, profile);
        context.drawText(client.textRenderer, text, 2, y, 0xFFFFFF, true);
    }
}

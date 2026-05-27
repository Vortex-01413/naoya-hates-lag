package com.naoya.lag.debug;

import com.naoya.lag.config.ModConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class DebugHudRenderer {
    private static boolean enabled = true;
    
    public static void toggle() { enabled = !enabled; }
    
    public static void render(DrawContext context) {
        if (!enabled) return;
        
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null) return;
        
        Runtime runtime = Runtime.getRuntime();
        long usedMemory = (runtime.totalMemory() - runtime.freeMemory()) / 1048576;
        
        int fps = client.getCurrentFps();
        int rd = client.options.getViewDistance().getValue();
        String profile = ModConfig.getProfileName();
        
        String text = String.format("§a[Naoya] §f%d fps | RD:%d | Mem:%dMB | %s", fps, rd, usedMemory, profile);
        context.drawText(client.textRenderer, text, 2, 10, 0xFFFFFF, true);
    }
}

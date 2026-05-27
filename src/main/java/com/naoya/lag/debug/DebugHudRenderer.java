package com.naoya.lag.debug;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;

public class DebugHudRenderer {
    private static boolean enabled = false;
    
    public static void toggle() {
        enabled = !enabled;
        System.out.println("[Naoya] Debug HUD: " + (enabled ? "ON" : "OFF"));
    }
    
    public static void render(DrawContext context, float tickDelta) {
        if (!enabled) return;
        
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null) return;
        
        TextRenderer textRenderer = client.textRenderer;
        int fps = client.getCurrentFps();
        int renderDist = client.options.getViewDistance().getValue();
        
        Runtime runtime = Runtime.getRuntime();
        long usedMemory = (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024;
        long maxMemory = runtime.maxMemory() / 1024 / 1024;
        
        int y = 2;
        context.drawText(textRenderer, "§a[NaoyaHatesLag]", 2, y, 0xFFFFFF, true);
        y += 10;
        context.drawText(textRenderer, "§7FPS: §f" + fps, 2, y, 0xFFFFFF, true);
        y += 10;
        context.drawText(textRenderer, "§7Render Distance: §f" + renderDist, 2, y, 0xFFFFFF, true);
        y += 10;
        context.drawText(textRenderer, "§7Memory: §f" + usedMemory + "MB / " + maxMemory + "MB", 2, y, 0xFFFFFF, true);
    }
}

package com.naoya.lag.debug;
import com.naoya.lag.config.ModConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
public class DebugHudRenderer {
    private static boolean enabled = false;
    public static void toggle() { enabled = !enabled; }
    public static void render(DrawContext ctx, float tickDelta) {
        if (!enabled) return;
        MinecraftClient c = MinecraftClient.getInstance();
        TextRenderer tr = c.textRenderer;
        int y = 2;
        ctx.drawText(tr, "§a[Naoya] §f" + c.getCurrentFps() + " fps | RD:" + c.options.getViewDistance().getValue() + " | " + ModConfig.getProfile(), 2, y, 0xFFFFFF, true);
        y += 10;
        Runtime r = Runtime.getRuntime();
        long used = (r.totalMemory() - r.freeMemory()) / 1048576;
        ctx.drawText(tr, "§7Mem: §f" + used + "MB / " + (r.maxMemory()/1048576) + "MB", 2, y, 0xFFFFFF, true);
    }
}

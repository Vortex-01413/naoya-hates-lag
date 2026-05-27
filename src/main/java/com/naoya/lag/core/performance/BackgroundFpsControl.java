package com.naoya.lag.core.performance;
import net.minecraft.client.MinecraftClient;
public class BackgroundFpsControl {
    private static boolean wasFocused = true;
    private static int originalFps = 60;
    public static void tick(MinecraftClient client) {
        boolean focused = client.isWindowFocused();
        if (!focused && wasFocused) {
            originalFps = client.options.getMaxFps().getValue();
            client.options.getMaxFps().setValue(5);
        } else if (focused && !wasFocused) {
            client.options.getMaxFps().setValue(originalFps);
        }
        wasFocused = focused;
    }
}

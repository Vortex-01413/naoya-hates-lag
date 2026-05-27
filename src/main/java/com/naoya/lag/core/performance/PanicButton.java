package com.naoya.lag.core.performance;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.ParticlesMode;
import net.minecraft.client.option.CloudRenderMode;
public class PanicButton {
    private static int originalDist = 8;
    private static boolean active = false;
    private static long lastPress = 0;
    public static void handleKeyPress(MinecraftClient client, int key) {
        if (key != 80) return;
        if (System.currentTimeMillis() - lastPress < 500) return;
        lastPress = System.currentTimeMillis();
        if (!active) {
            originalDist = client.options.getViewDistance().getValue();
            client.options.getViewDistance().setValue(2);
            client.options.getEntityShadows().setValue(false);
            client.options.getParticles().setValue(ParticlesMode.MINIMAL);
            client.options.getCloudRenderMode().setValue(CloudRenderMode.OFF);
            client.options.getAo().setValue(false);
            client.options.getBiomeBlendRadius().setValue(0);
            active = true;
        } else {
            client.options.getViewDistance().setValue(originalDist);
            active = false;
        }
    }
}

package com.naoya.lag.core.render;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.ParticlesMode;
import net.minecraft.client.option.CloudRenderMode;
public class VisualOptimizer {
    private static boolean applied = false;
    public static void apply(MinecraftClient client) {
        if (applied) return;
        if (client.options == null) return;
        client.options.getEntityShadows().setValue(true);
        client.options.getParticles().setValue(ParticlesMode.ALL);
        client.options.getCloudRenderMode().setValue(CloudRenderMode.FANCY);
        client.options.getAo().setValue(true);
        client.options.getBiomeBlendRadius().setValue(2);
        applied = true;
    }
}

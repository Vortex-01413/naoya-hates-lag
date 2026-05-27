package com.naoya.lag.core.render;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.ParticlesMode;
import net.minecraft.client.option.CloudRenderMode;

public class VisualOptimizer {
    private static boolean hasOptimized = false;
    
    public static void applyForDevice(MinecraftClient client) {
        if (hasOptimized) return;
        if (client == null || client.options == null) return;
        
        // Entity shadows - use the correct SimpleOption setter
        client.options.getEntityShadows().setValue(false);
        
        // Reduce particles to minimal
        client.options.getParticles().setValue(ParticlesMode.MINIMAL);
        
        // Disable clouds
        client.options.getCloudRenderMode().setValue(CloudRenderMode.OFF);
        
        // Disable smooth lighting - set value to 0 (off)
        client.options.getAo().setValue(0);
        
        // Reduce biome blend radius
        client.options.getBiomeBlendRadius().setValue(1);
        
        hasOptimized = true;
        System.out.println("[Naoya] Visual optimizations applied for Itel A70");
    }
}

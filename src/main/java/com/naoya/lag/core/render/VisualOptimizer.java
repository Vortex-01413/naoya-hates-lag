package com.naoya.lag.core.render;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.ParticlesMode;
import net.minecraft.client.option.CloudRenderMode;

public class VisualOptimizer {
    private static boolean hasOptimized = false;
    
    public static void applyForDevice(MinecraftClient client) {
        if (hasOptimized) return;
        if (client == null || client.options == null) return;
        
        // Entity shadows - direct field access
        client.options.entityShadowsEnabled = false;
        
        // Reduce particles to minimal
        client.options.getParticles().setValue(ParticlesMode.MINIMAL);
        
        // Disable clouds
        client.options.getCloudRenderMode().setValue(CloudRenderMode.OFF);
        
        // Disable smooth lighting (ambient occlusion)
        client.options.ao = false;
        
        // Reduce biome blend radius
        client.options.biomeBlendRadius = 1;
        
        hasOptimized = true;
        System.out.println("[Naoya] Visual optimizations applied for Itel A70");
    }
    
    public static boolean shouldHideFireOverlay() { return true; }
    public static boolean shouldHidePortalOverlay() { return true; }
    public static boolean shouldShowShadows() { return false; }
}

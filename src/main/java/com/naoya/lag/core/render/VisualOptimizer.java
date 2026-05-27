package com.naoya.lag.core.render;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.ParticlesMode;
import net.minecraft.client.option.CloudRenderMode;
import net.minecraft.client.option.SimpleOption;

public class VisualOptimizer {
    private static boolean hasOptimized = false;
    
    public static void applyForDevice(MinecraftClient client) {
        if (hasOptimized) return;
        if (client == null || client.options == null) return;
        
        // Disable shadows (Mali G57 hates shadows)
        client.options.setEntityShadowsEnabled(false);
        
        // Reduce particles to minimal using the correct setter
        client.options.getParticles().setValue(ParticlesMode.MINIMAL);
        
        // Disable clouds
        client.options.getCloudRenderMode().setValue(CloudRenderMode.OFF);
        
        // Disable smooth lighting (massive GPU save)
        // Ambient occlusion is smooth lighting; set to 0 (off)
        client.options.getAmbientOcclusion().setValue(0);
        
        // Reduce biome blend radius (less GPU work)
        client.options.getBiomeBlendRadius().setValue(1);
        
        hasOptimized = true;
        System.out.println("[Naoya] Visual optimizations applied for Itel A70");
    }
    
    public static boolean shouldHideFireOverlay() {
        return true;
    }
    
    public static boolean shouldHidePortalOverlay() {
        return true;
    }
    
    public static boolean shouldShowShadows() {
        return false;
    }
}

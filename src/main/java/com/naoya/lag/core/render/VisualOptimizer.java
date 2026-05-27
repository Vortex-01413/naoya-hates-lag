package com.naoya.lag.core.render;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.ParticlesMode;
import net.minecraft.client.option.CloudRenderMode;

public class VisualOptimizer {
    private static boolean hasOptimized = false;
    
    public static void applyForDevice(MinecraftClient client) {
        if (hasOptimized) return;
        if (client == null || client.options == null) return;
        
        // Disable shadows completely (Mali G57 hates shadows)
        client.options.entityShadowsEnabled = false;
        
        // Reduce particles to minimal
        client.options.particles = ParticlesMode.MINIMAL;
        
        // Disable clouds
        client.options.cloudRenderMode = CloudRenderMode.OFF;
        
        // Disable smooth lighting (massive GPU save)
        client.options.ambientOcclusion = false;
        
        // Reduce biome blend (less GPU work)
        client.options.biomeBlendRadius = 1;
        
        // Disable rain particles when inside
        client.options.particles = ParticlesMode.MINIMAL;
        
        hasOptimized = true;
        System.out.println("[Naoya] Visual optimizations applied for Itel A70");
    }
    
    // Called every tick to check if fire/portal overlay should be hidden
    public static boolean shouldHideFireOverlay() {
        return true; // Always hide fire overlay - it kills FPS
    }
    
    public static boolean shouldHidePortalOverlay() {
        return true; // Always hide portal overlay
    }
    
    public static boolean shouldShowShadows() {
        return false; // Never show shadows
    }
}

package com.naoya.lag.core.particle;

import com.naoya.lag.config.ModConfig;
import net.minecraft.client.render.Camera;
import net.minecraft.particle.ParticleEffect;

public class ParticleDistanceCuller {
    private static final float[] MAX_DISTANCES = {
        8.0f,   // Potato
        12.0f,  // Low
        16.0f,  // Balanced
        24.0f,  // High
        32.0f   // Extreme
    };
    
    public static boolean shouldRenderParticle(ParticleEffect effect, double x, double y, double z, Camera camera) {
        if (!ModConfig.isParticleDistanceCullingEnabled()) {
            return true;
        }
        
        float maxDist = MAX_DISTANCES[ModConfig.getCurrentProfileIndex()];
        double dx = x - camera.getPos().x;
        double dy = y - camera.getPos().y;
        double dz = z - camera.getPos().z;
        
        return (dx * dx + dy * dy + dz * dz) <= (maxDist * maxDist);
    }
}

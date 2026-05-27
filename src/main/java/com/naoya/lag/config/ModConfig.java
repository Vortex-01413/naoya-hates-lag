package com.naoya.lag.config;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.ParticlesMode;
import net.minecraft.client.option.CloudRenderMode;

public class ModConfig {
    public enum Profile {
        POTATO(0), LOW(1), BALANCED(2), HIGH(3), EXTREME(4);
        public final int id;
        Profile(int id) { this.id = id; }
        public static Profile fromId(int id) {
            for (Profile p : values()) if (p.id == id) return p;
            return BALANCED;
        }
    }
    
    private static Profile currentProfile = Profile.BALANCED;
    
    public static boolean enableEntityCulling = true;
    public static boolean enableParticleLimit = true;
    public static boolean enableFpsAutoAdjust = true;
    public static boolean enableBackgroundFpsCap = true;
    public static boolean enableMemorySweep = true;
    public static boolean enablePanicButton = true;
    public static boolean enableOcclusionCulling = true;
    public static boolean enableAiThrottle = true;
    public static boolean enableHopperOptimization = true;
    public static boolean enableRedstoneOptimization = true;
    public static boolean enableOrbMerging = true;
    public static boolean enableChunkThrottle = true;
    public static boolean enableFastMath = true;
    public static boolean enableTextureCompression = true;
    public static boolean enableVisualOptimizations = true;
    
    public static boolean shadowsOff = true;
    public static boolean cloudsOff = true;
    public static boolean smoothLightingOff = true;
    public static boolean minimalParticles = true;
    public static int biomeBlendRadius = 1;
    
    public static int lowFpsThreshold = 25;
    public static int highFpsThreshold = 50;
    public static int maxRenderDistance = 12;
    public static int minRenderDistance = 4;
    
    public static float memoryGcThreshold = 0.80f;
    public static int memorySweepInterval = 600;
    
    public static void setProfile(Profile profile) {
        currentProfile = profile;
        switch (profile) {
            case POTATO:
                enableEntityCulling = true; enableParticleLimit = true; enableFpsAutoAdjust = true;
                enableBackgroundFpsCap = true; enableMemorySweep = true; enablePanicButton = true;
                enableOcclusionCulling = true; enableAiThrottle = true; enableHopperOptimization = true;
                enableRedstoneOptimization = true; enableOrbMerging = true; enableChunkThrottle = true;
                enableFastMath = true; enableTextureCompression = true; enableVisualOptimizations = true;
                shadowsOff = true; cloudsOff = true; smoothLightingOff = true; minimalParticles = true;
                biomeBlendRadius = 0; lowFpsThreshold = 30; highFpsThreshold = 60; maxRenderDistance = 8; minRenderDistance = 2;
                memoryGcThreshold = 0.70f;
                break;
            case LOW:
                shadowsOff = true; cloudsOff = true; smoothLightingOff = true; minimalParticles = true;
                biomeBlendRadius = 1; lowFpsThreshold = 28; highFpsThreshold = 55; maxRenderDistance = 10; minRenderDistance = 4;
                memoryGcThreshold = 0.75f;
                break;
            case BALANCED:
                shadowsOff = true; cloudsOff = true; smoothLightingOff = false; minimalParticles = false;
                biomeBlendRadius = 2; lowFpsThreshold = 25; highFpsThreshold = 50; maxRenderDistance = 12; minRenderDistance = 5;
                memoryGcThreshold = 0.80f;
                break;
            case HIGH:
                shadowsOff = false; cloudsOff = false; smoothLightingOff = false; minimalParticles = false;
                biomeBlendRadius = 3; lowFpsThreshold = 20; highFpsThreshold = 45; maxRenderDistance = 16; minRenderDistance = 6;
                memoryGcThreshold = 0.85f;
                break;
            case EXTREME:
                shadowsOff = false; cloudsOff = false; smoothLightingOff = false; minimalParticles = false;
                biomeBlendRadius = 4; lowFpsThreshold = 15; highFpsThreshold = 40; maxRenderDistance = 20; minRenderDistance = 8;
                memoryGcThreshold = 0.90f;
                break;
        }
        System.out.println("[Naoya] Profile set to: " + profile);
    }
    
    public static Profile getProfile() { return currentProfile; }
    
    public static void applyVisuals(MinecraftClient client) {
        if (!enableVisualOptimizations) return;
        if (client.options == null) return;
        client.options.getEntityShadows().setValue(shadowsOff);
        client.options.getParticles().setValue(minimalParticles ? ParticlesMode.MINIMAL : ParticlesMode.ALL);
        client.options.getCloudRenderMode().setValue(cloudsOff ? CloudRenderMode.OFF : CloudRenderMode.FANCY);
        client.options.getAo().setValue(!smoothLightingOff);
        client.options.getBiomeBlendRadius().setValue(biomeBlendRadius);
    }
}

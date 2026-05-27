package com.naoya.lag.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import java.io.*;
import java.nio.file.Path;

public class ModConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("naoya-hates-lag.json");
    private static ConfigData data = new ConfigData();
    private static int currentProfile = 2; // Balanced default
    
    public static void init() { load(); }
    
    public static void load() {
        if (CONFIG_PATH.toFile().exists()) {
            try (Reader reader = new FileReader(CONFIG_PATH.toFile())) {
                ConfigData loaded = GSON.fromJson(reader, ConfigData.class);
                if (loaded != null) data = loaded;
            } catch (IOException e) { e.printStackTrace(); }
        }
        applyProfileSettings();
        save();
    }
    
    public static void save() {
        try (Writer writer = new FileWriter(CONFIG_PATH.toFile())) {
            GSON.toJson(data, writer);
        } catch (IOException e) { e.printStackTrace(); }
    }
    
    public static int getCurrentProfile() { return currentProfile; }
    public static String getProfileName() {
        String[] names = {"Potato", "Low", "Balanced", "High", "Extreme"};
        return names[currentProfile];
    }
    
    public static void setCurrentProfile(int profile) {
        currentProfile = Math.max(0, Math.min(4, profile));
        applyProfileSettings();
        save();
    }
    
    public static void cycleProfile() { setCurrentProfile((currentProfile + 1) % 5); }
    
    private static void applyProfileSettings() {
        switch(currentProfile) {
            case 0: // Potato
                data.entityCulling = true;
                data.particleLimit = true;
                data.fpsAutoAdjust = false; // OFF - prevents grey flashing!
                data.backgroundFpsCap = true;
                data.memorySweep = true;
                data.occlusionCulling = true;
                data.aiThrottle = true;
                data.hopperOptimization = true;
                data.redstoneOptimization = true;
                data.orbMerging = true;
                data.chunkThrottle = true;
                data.fastMath = true;
                data.textureCompression = true;
                data.visualOptimizations = true;
                data.shadowsOff = true;
                data.cloudsOff = true;
                data.smoothLightingOff = true;
                data.minimalParticles = true;
                data.biomeBlendRadius = 0;
                data.lowFpsThreshold = 30;
                data.highFpsThreshold = 60;
                data.maxRenderDistance = 8;
                data.minRenderDistance = 2;
                data.gcThreshold = 70;
                data.entityDistanceScaling = true;
                data.liquidThrottling = true;
                data.leafCulling = true;
                data.dynamicChunkLoading = true;
                data.transparentOptimization = true;
                data.blockStateDeduplication = true;
                data.paletteCompression = true;
                data.fireOverlayHide = true;
                data.portalOverlayHide = true;
                break;
            case 1: // Low
                data.entityCulling = true;
                data.particleLimit = true;
                data.fpsAutoAdjust = false;
                data.backgroundFpsCap = true;
                data.memorySweep = true;
                data.occlusionCulling = true;
                data.aiThrottle = true;
                data.hopperOptimization = true;
                data.redstoneOptimization = true;
                data.orbMerging = true;
                data.chunkThrottle = true;
                data.fastMath = true;
                data.textureCompression = true;
                data.visualOptimizations = true;
                data.shadowsOff = true;
                data.cloudsOff = true;
                data.smoothLightingOff = true;
                data.minimalParticles = true;
                data.biomeBlendRadius = 1;
                data.lowFpsThreshold = 28;
                data.highFpsThreshold = 55;
                data.maxRenderDistance = 10;
                data.minRenderDistance = 4;
                data.gcThreshold = 75;
                data.entityDistanceScaling = true;
                data.liquidThrottling = true;
                data.leafCulling = true;
                data.dynamicChunkLoading = true;
                data.transparentOptimization = true;
                data.blockStateDeduplication = true;
                data.paletteCompression = true;
                data.fireOverlayHide = true;
                data.portalOverlayHide = true;
                break;
            case 2: // Balanced
                data.entityCulling = true;
                data.particleLimit = true;
                data.fpsAutoAdjust = false;
                data.backgroundFpsCap = true;
                data.memorySweep = true;
                data.occlusionCulling = true;
                data.aiThrottle = true;
                data.hopperOptimization = true;
                data.redstoneOptimization = true;
                data.orbMerging = true;
                data.chunkThrottle = true;
                data.fastMath = true;
                data.textureCompression = true;
                data.visualOptimizations = true;
                data.shadowsOff = true;
                data.cloudsOff = true;
                data.smoothLightingOff = true;
                data.minimalParticles = false;
                data.biomeBlendRadius = 2;
                data.lowFpsThreshold = 25;
                data.highFpsThreshold = 50;
                data.maxRenderDistance = 12;
                data.minRenderDistance = 5;
                data.gcThreshold = 80;
                data.entityDistanceScaling = true;
                data.liquidThrottling = true;
                data.leafCulling = true;
                data.dynamicChunkLoading = true;
                data.transparentOptimization = true;
                data.blockStateDeduplication = true;
                data.paletteCompression = true;
                data.fireOverlayHide = false;
                data.portalOverlayHide = false;
                break;
            case 3: // High
                data.entityCulling = true;
                data.particleLimit = false;
                data.fpsAutoAdjust = false;
                data.backgroundFpsCap = true;
                data.memorySweep = true;
                data.occlusionCulling = true;
                data.aiThrottle = false;
                data.hopperOptimization = false;
                data.redstoneOptimization = false;
                data.orbMerging = false;
                data.chunkThrottle = false;
                data.fastMath = false;
                data.textureCompression = false;
                data.visualOptimizations = false;
                data.shadowsOff = false;
                data.cloudsOff = false;
                data.smoothLightingOff = false;
                data.minimalParticles = false;
                data.biomeBlendRadius = 3;
                data.lowFpsThreshold = 20;
                data.highFpsThreshold = 45;
                data.maxRenderDistance = 16;
                data.minRenderDistance = 6;
                data.gcThreshold = 85;
                data.entityDistanceScaling = false;
                data.liquidThrottling = false;
                data.leafCulling = false;
                data.dynamicChunkLoading = false;
                data.transparentOptimization = false;
                data.blockStateDeduplication = true;
                data.paletteCompression = false;
                data.fireOverlayHide = false;
                data.portalOverlayHide = false;
                break;
            case 4: // Extreme
                data.entityCulling = false;
                data.particleLimit = false;
                data.fpsAutoAdjust = false;
                data.backgroundFpsCap = true;
                data.memorySweep = true;
                data.occlusionCulling = true;
                data.aiThrottle = false;
                data.hopperOptimization = false;
                data.redstoneOptimization = false;
                data.orbMerging = false;
                data.chunkThrottle = false;
                data.fastMath = false;
                data.textureCompression = false;
                data.visualOptimizations = false;
                data.shadowsOff = false;
                data.cloudsOff = false;
                data.smoothLightingOff = false;
                data.minimalParticles = false;
                data.biomeBlendRadius = 4;
                data.lowFpsThreshold = 15;
                data.highFpsThreshold = 40;
                data.maxRenderDistance = 20;
                data.minRenderDistance = 8;
                data.gcThreshold = 90;
                data.entityDistanceScaling = false;
                data.liquidThrottling = false;
                data.leafCulling = false;
                data.dynamicChunkLoading = false;
                data.transparentOptimization = false;
                data.blockStateDeduplication = true;
                data.paletteCompression = false;
                data.fireOverlayHide = false;
                data.portalOverlayHide = false;
                break;
        }
        save();
    }
    
    // Getters for all features
    public static boolean isEntityCulling() { return data.entityCulling; }
    public static boolean isParticleLimit() { return data.particleLimit; }
    public static boolean isFpsAutoAdjust() { return data.fpsAutoAdjust; }
    public static boolean isBackgroundFpsCap() { return data.backgroundFpsCap; }
    public static boolean isMemorySweep() { return data.memorySweep; }
    public static boolean isOcclusionCulling() { return data.occlusionCulling; }
    public static boolean isAiThrottle() { return data.aiThrottle; }
    public static boolean isHopperOptimization() { return data.hopperOptimization; }
    public static boolean isRedstoneOptimization() { return data.redstoneOptimization; }
    public static boolean isOrbMerging() { return data.orbMerging; }
    public static boolean isChunkThrottle() { return data.chunkThrottle; }
    public static boolean isFastMath() { return data.fastMath; }
    public static boolean isTextureCompression() { return data.textureCompression; }
    public static boolean isVisualOptimizations() { return data.visualOptimizations; }
    public static boolean isShadowsOff() { return data.shadowsOff; }
    public static boolean isCloudsOff() { return data.cloudsOff; }
    public static boolean isSmoothLightingOff() { return data.smoothLightingOff; }
    public static boolean isMinimalParticles() { return data.minimalParticles; }
    public static int getBiomeBlendRadius() { return data.biomeBlendRadius; }
    public static int getLowFpsThreshold() { return data.lowFpsThreshold; }
    public static int getHighFpsThreshold() { return data.highFpsThreshold; }
    public static int getMaxRenderDistance() { return data.maxRenderDistance; }
    public static int getMinRenderDistance() { return data.minRenderDistance; }
    public static int getGcThreshold() { return data.gcThreshold; }
    public static boolean isEntityDistanceScaling() { return data.entityDistanceScaling; }
    public static boolean isLiquidThrottling() { return data.liquidThrottling; }
    public static boolean isLeafCulling() { return data.leafCulling; }
    public static boolean isDynamicChunkLoading() { return data.dynamicChunkLoading; }
    public static boolean isTransparentOptimization() { return data.transparentOptimization; }
    public static boolean isBlockStateDeduplication() { return data.blockStateDeduplication; }
    public static boolean isPaletteCompression() { return data.paletteCompression; }
    public static boolean isFireOverlayHide() { return data.fireOverlayHide; }
    public static boolean isPortalOverlayHide() { return data.portalOverlayHide; }
    
    // Setters for config screen
    public static void setEntityCulling(boolean v) { data.entityCulling = v; save(); }
    public static void setParticleLimit(boolean v) { data.particleLimit = v; save(); }
    public static void setFpsAutoAdjust(boolean v) { data.fpsAutoAdjust = v; save(); }
    public static void setBackgroundFpsCap(boolean v) { data.backgroundFpsCap = v; save(); }
    public static void setMemorySweep(boolean v) { data.memorySweep = v; save(); }
    public static void setOcclusionCulling(boolean v) { data.occlusionCulling = v; save(); }
    public static void setAiThrottle(boolean v) { data.aiThrottle = v; save(); }
    public static void setHopperOptimization(boolean v) { data.hopperOptimization = v; save(); }
    public static void setRedstoneOptimization(boolean v) { data.redstoneOptimization = v; save(); }
    public static void setOrbMerging(boolean v) { data.orbMerging = v; save(); }
    public static void setChunkThrottle(boolean v) { data.chunkThrottle = v; save(); }
    public static void setFastMath(boolean v) { data.fastMath = v; save(); }
    public static void setTextureCompression(boolean v) { data.textureCompression = v; save(); }
    public static void setVisualOptimizations(boolean v) { data.visualOptimizations = v; save(); }
    public static void setShadowsOff(boolean v) { data.shadowsOff = v; save(); }
    public static void setCloudsOff(boolean v) { data.cloudsOff = v; save(); }
    public static void setSmoothLightingOff(boolean v) { data.smoothLightingOff = v; save(); }
    public static void setMinimalParticles(boolean v) { data.minimalParticles = v; save(); }
    
    private static class ConfigData {
        boolean entityCulling = true;
        boolean particleLimit = true;
        boolean fpsAutoAdjust = false; // OFF to prevent grey flashing
        boolean backgroundFpsCap = true;
        boolean memorySweep = true;
        boolean occlusionCulling = true;
        boolean aiThrottle = true;
        boolean hopperOptimization = true;
        boolean redstoneOptimization = true;
        boolean orbMerging = true;
        boolean chunkThrottle = true;
        boolean fastMath = true;
        boolean textureCompression = true;
        boolean visualOptimizations = true;
        boolean shadowsOff = true;
        boolean cloudsOff = true;
        boolean smoothLightingOff = true;
        boolean minimalParticles = false;
        int biomeBlendRadius = 2;
        int lowFpsThreshold = 25;
        int highFpsThreshold = 50;
        int maxRenderDistance = 12;
        int minRenderDistance = 5;
        int gcThreshold = 80;
        boolean entityDistanceScaling = true;
        boolean liquidThrottling = true;
        boolean leafCulling = true;
        boolean dynamicChunkLoading = true;
        boolean transparentOptimization = true;
        boolean blockStateDeduplication = true;
        boolean paletteCompression = true;
        boolean fireOverlayHide = false;
        boolean portalOverlayHide = false;
    }
}

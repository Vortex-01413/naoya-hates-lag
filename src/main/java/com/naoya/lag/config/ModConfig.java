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
    private static int currentProfile = 2;
    
    public static void init() { load(); }
    
    public static void load() {
        if (CONFIG_PATH.toFile().exists()) {
            try (Reader reader = new FileReader(CONFIG_PATH.toFile())) {
                ConfigData loaded = GSON.fromJson(reader, ConfigData.class);
                if (loaded != null) data = loaded;
            } catch (IOException e) { e.printStackTrace(); }
        }
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
    public static void cycleProfile() { currentProfile = (currentProfile + 1) % 5; save(); }
    
    // Getters
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
    
    // Setters
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
        boolean fpsAutoAdjust = false;
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

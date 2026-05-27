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
    private static int currentProfile = 2; // Default: Balanced
    
    public static void init() {
        load();
    }
    
    public static void load() {
        if (CONFIG_PATH.toFile().exists()) {
            try (Reader reader = new FileReader(CONFIG_PATH.toFile())) {
                data = GSON.fromJson(reader, ConfigData.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        save();
    }
    
    public static void save() {
        try (Writer writer = new FileWriter(CONFIG_PATH.toFile())) {
            GSON.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static int getCurrentProfileIndex() {
        return currentProfile;
    }
    
    public static void setCurrentProfile(int profile) {
        currentProfile = Math.max(0, Math.min(4, profile));
        applyProfileSettings();
        save();
    }
    
    public static void cycleProfile() {
        setCurrentProfile((currentProfile + 1) % 5);
    }
    
    public static String getCurrentProfileName() {
        String[] names = {"Potato", "Low", "Balanced", "High", "Extreme"};
        return names[currentProfile];
    }
    
    private static void applyProfileSettings() {
        data.textureCompression = currentProfile <= 2;
        data.particleDistanceCulling = currentProfile <= 3;
        data.entityDistanceScaling = currentProfile <= 3;
        data.liquidThrottling = currentProfile <= 3;
        data.leafCulling = currentProfile <= 2;
        data.dynamicChunkLoading = true;
        data.transparentOptimization = true;
        data.occlusionCulling = true;
        data.fastMath = currentProfile != 4;
        data.entityCulling = true;
        data.chunkLoadThrottle = true;
        data.backgroundFpsCap = true;
        data.memoryCompression = currentProfile <= 2;
        
        // Adjust render distance based on profile
        int[] renderDistances = {4, 6, 10, 14, 20};
        int targetDist = renderDistances[currentProfile];
        if (net.minecraft.client.MinecraftClient.getInstance().options != null) {
            net.minecraft.client.MinecraftClient.getInstance().options.getViewDistance().setValue(targetDist);
        }
        
        save();
    }
    
    // Individual feature toggles
    public static boolean isTextureCompressionEnabled() { return data.textureCompression; }
    public static boolean isParticleDistanceCullingEnabled() { return data.particleDistanceCulling; }
    public static boolean isEntityDistanceScalingEnabled() { return data.entityDistanceScaling; }
    public static boolean isLiquidThrottlingEnabled() { return data.liquidThrottling; }
    public static boolean isLeafCullingEnabled() { return data.leafCulling; }
    public static boolean isDynamicChunkLoadingEnabled() { return data.dynamicChunkLoading; }
    public static boolean isTransparentOptimizationEnabled() { return data.transparentOptimization; }
    public static boolean isOcclusionCullingEnabled() { return data.occlusionCulling; }
    public static boolean isFastMathEnabled() { return data.fastMath; }
    public static boolean isEntityCullingEnabled() { return data.entityCulling; }
    public static boolean isChunkLoadThrottleEnabled() { return data.chunkLoadThrottle; }
    public static boolean isBackgroundFpsCapEnabled() { return data.backgroundFpsCap; }
    public static boolean isMemoryCompressionEnabled() { return data.memoryCompression; }
    
    public static void setTextureCompression(boolean enabled) { data.textureCompression = enabled; save(); }
    public static void setParticleDistanceCulling(boolean enabled) { data.particleDistanceCulling = enabled; save(); }
    public static void setEntityDistanceScaling(boolean enabled) { data.entityDistanceScaling = enabled; save(); }
    public static void setLiquidThrottling(boolean enabled) { data.liquidThrottling = enabled; save(); }
    public static void setLeafCulling(boolean enabled) { data.leafCulling = enabled; save(); }
    public static void setDynamicChunkLoading(boolean enabled) { data.dynamicChunkLoading = enabled; save(); }
    public static void setTransparentOptimization(boolean enabled) { data.transparentOptimization = enabled; save(); }
    public static void setOcclusionCulling(boolean enabled) { data.occlusionCulling = enabled; save(); }
    public static void setFastMath(boolean enabled) { data.fastMath = enabled; save(); }
    public static void setEntityCulling(boolean enabled) { data.entityCulling = enabled; save(); }
    public static void setChunkLoadThrottle(boolean enabled) { data.chunkLoadThrottle = enabled; save(); }
    public static void setBackgroundFpsCap(boolean enabled) { data.backgroundFpsCap = enabled; save(); }
    public static void setMemoryCompression(boolean enabled) { data.memoryCompression = enabled; save(); }
    
    private static class ConfigData {
        boolean textureCompression = true;
        boolean particleDistanceCulling = true;
        boolean entityDistanceScaling = true;
        boolean liquidThrottling = true;
        boolean leafCulling = true;
        boolean dynamicChunkLoading = true;
        boolean transparentOptimization = true;
        boolean occlusionCulling = true;
        boolean fastMath = true;
        boolean entityCulling = true;
        boolean chunkLoadThrottle = true;
        boolean backgroundFpsCap = true;
        boolean memoryCompression = true;
    }
}

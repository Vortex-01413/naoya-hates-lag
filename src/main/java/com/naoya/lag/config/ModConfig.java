package com.naoya.lag.config;

public class ModConfig {

    public enum DeviceProfile {
        ITEL_A70, LOW_END, MID_END, HIGH_END, CUSTOM
    }

    // Device Profile
    public static DeviceProfile profile = DeviceProfile.MID_END;

    // Performance
    public static boolean entityCulling = true;
    public static int entityCap = 60;
    public static boolean particleLimiter = true;
    public static boolean chunkThrottle = true;
    public static boolean smartRenderDistance = true;
    public static int minRenderDistance = 2;
    public static int maxRenderDistance = 8;
    public static boolean xpOrbClumping = true;
    public static boolean dynamicBackgroundFps = true;
    public static int backgroundFpsCap = 10;
    public static boolean recipeCache = true;

    // Visual
    public static boolean noWeather = false;
    public static boolean noFog = false;
    public static boolean noVoidFog = true;
    public static boolean cullLeaves = true;
    public static boolean reduceClouds = true;
    public static boolean noFadeAnimations = false;

    // HUD / Auto
    public static boolean debugHud = true;
    public static boolean smoothFps = true;
    public static boolean lowFpsWarning = true;
    public static int lowFpsThreshold = 20;
    public static boolean autoProfileSwitcher = true;
    public static boolean adaptiveCompatibility = true;

    public static void applyProfile(DeviceProfile p) {
        profile = p;
        switch (p) {
            case ITEL_A70:
                entityCulling = true;
                entityCap = 20;
                particleLimiter = true;
                chunkThrottle = true;
                smartRenderDistance = true;
                minRenderDistance = 2;
                maxRenderDistance = 4;
                xpOrbClumping = true;
                dynamicBackgroundFps = true;
                backgroundFpsCap = 5;
                recipeCache = true;
                noWeather = true;
                noFog = true;
                noVoidFog = true;
                cullLeaves = true;
                reduceClouds = true;
                noFadeAnimations = true;
                lowFpsThreshold = 15;
                break;
            case LOW_END:
                entityCulling = true;
                entityCap = 30;
                particleLimiter = true;
                chunkThrottle = true;
                smartRenderDistance = true;
                minRenderDistance = 2;
                maxRenderDistance = 6;
                xpOrbClumping = true;
                dynamicBackgroundFps = true;
                backgroundFpsCap = 10;
                recipeCache = true;
                noWeather = true;
                noFog = true;
                noVoidFog = true;
                cullLeaves = true;
                reduceClouds = true;
                noFadeAnimations = true;
                lowFpsThreshold = 20;
                break;
            case MID_END:
                entityCulling = true;
                entityCap = 60;
                particleLimiter = true;
                chunkThrottle = true;
                smartRenderDistance = true;
                minRenderDistance = 2;
                maxRenderDistance = 10;
                xpOrbClumping = true;
                dynamicBackgroundFps = true;
                backgroundFpsCap = 10;
                recipeCache = true;
                noWeather = false;
                noFog = false;
                noVoidFog = true;
                cullLeaves = true;
                reduceClouds = false;
                noFadeAnimations = false;
                lowFpsThreshold = 20;
                break;
            case HIGH_END:
                entityCulling = true;
                entityCap = 150;
                particleLimiter = false;
                chunkThrottle = false;
                smartRenderDistance = false;
                minRenderDistance = 2;
                maxRenderDistance = 16;
                xpOrbClumping = true;
                dynamicBackgroundFps = true;
                backgroundFpsCap = 15;
                recipeCache = true;
                noWeather = false;
                noFog = false;
                noVoidFog = false;
                cullLeaves = true;
                reduceClouds = false;
                noFadeAnimations = false;
                lowFpsThreshold = 30;
                break;
            case CUSTOM:
                break;
        }
    }

    public static boolean isModLoaded(String modId) {
        return net.fabricmc.loader.api.FabricLoader.getInstance().isModLoaded(modId);
    }
}

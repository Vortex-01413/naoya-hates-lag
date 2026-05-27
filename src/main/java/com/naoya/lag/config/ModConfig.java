package com.naoya.lag.config;

public class ModConfig {
    public static int currentProfile = 2;
    public static boolean debugHud = true;
    public static boolean autoProfileSwitcher = true;
    public static boolean entityCulling = true;
    public static boolean chunkThrottle = true;
    public static boolean smartRenderDistance = true;
    public static boolean xpOrbClumping = true;
    public static boolean dynamicBackgroundFps = true;
    public static boolean particleLimiter = true;
    public static boolean occlusionCulling = true;
    public static boolean fastMath = true;
    
    public static void init() {}
    public static void cycleProfile() { currentProfile = (currentProfile + 1) % 5; }
    public static String getProfileName() {
        String[] names = {"Potato", "Low", "Balanced", "High", "Extreme"};
        return names[currentProfile];
    }
    public static int getCurrentProfile() { return currentProfile; }
    public static boolean isDebugHudEnabled() { return debugHud; }
    public static boolean isOcclusionCullingEnabled() { return occlusionCulling; }
    public static boolean isFastMathEnabled() { return fastMath; }
}

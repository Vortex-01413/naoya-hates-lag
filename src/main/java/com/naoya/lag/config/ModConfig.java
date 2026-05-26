
package com.naoya.lag.config;

public class ModConfig {

    public static boolean entityCulling = true;
    public static boolean particleLimiter = true;
    public static boolean debugHud = true;
    public static boolean adaptiveCompatibility = true;

    public static boolean isModLoaded(String modId) {
        return net.fabricmc.loader.api.FabricLoader.getInstance().isModLoaded(modId);
    }
}

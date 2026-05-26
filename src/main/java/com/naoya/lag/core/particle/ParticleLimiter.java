
package com.naoya.lag.core.particle;

import com.naoya.lag.config.ModConfig;

public class ParticleLimiter {

    private static int counter = 0;

    private static final boolean particleModsPresent =
            ModConfig.isModLoaded("effective") ||
            ModConfig.isModLoaded("particlerain");

    public static boolean shouldSpawnParticle() {

        if (!ModConfig.particleLimiter)
            return true;

        if (ModConfig.adaptiveCompatibility && particleModsPresent)
            return true;

        counter++;
        return counter % 3 != 0;
    }
}

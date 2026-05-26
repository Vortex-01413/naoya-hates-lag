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

        switch (ModConfig.profile) {
            case ITEL_A70:
                return (counter % 8) != 0;
            case LOW_END:
                return (counter % 5) != 0;
            case MID_END:
                return (counter % 3) != 0;
            case HIGH_END:
            case CUSTOM:
            default:
                return (counter % 2) != 0;
        }
    }
}

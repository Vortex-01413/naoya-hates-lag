package com.naoya.lag.core;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.client.MinecraftClient;

public class ParticleDistanceCuller {
    public static boolean shouldRender(Particle p) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return true;
        return mc.player.squaredDistanceTo(p.getX(), p.getY(), p.getZ()) < 256; // 16 blocks
    }
}

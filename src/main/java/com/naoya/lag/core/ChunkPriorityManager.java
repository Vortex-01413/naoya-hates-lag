package com.naoya.lag.core;

import net.minecraft.client.world.ClientChunkManager;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.client.MinecraftClient;

public class ChunkPriorityManager {
    public static boolean shouldLoad(ChunkPos pos) {
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null) return true;
        double dist = mc.player.squaredDistanceTo(pos.getStartX(), mc.player.getY(), pos.getStartZ());
        return dist < 4096; // prioritize chunks within 64 blocks
    }
}

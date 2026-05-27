package com.naoya.lag.core.render;

import com.naoya.lag.config.ModConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;

public class ChunkPriorityManager {
    private static final PriorityBlockingQueue<ChunkPos> CHUNK_QUEUE = new PriorityBlockingQueue<>(
        100, Comparator.comparingDouble(ChunkPriorityManager::getChunkPriority)
    );
    
    private static double getChunkPriority(ChunkPos pos) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return 0;
        
        double dx = pos.getCenterX() - client.player.getX();
        double dz = pos.getCenterZ() - client.player.getZ();
        double distance = Math.sqrt(dx * dx + dz * dz);
        
        // Prioritize chunks within render distance
        int renderDist = client.options.getViewDistance().getValue();
        if (distance > renderDist) {
            return Double.MAX_VALUE; // Don't load beyond render distance
        }
        
        // Priority based on distance (closer = higher priority)
        double priority = distance;
        
        // Add view direction priority
        float yaw = client.player.getYaw();
        float pitch = client.player.getPitch();
        double dirX = -MathHelper.sin(yaw * MathHelper.RADIANS_PER_DEGREE) * MathHelper.cos(pitch * MathHelper.RADIANS_PER_DEGREE);
        double dirZ = MathHelper.cos(yaw * MathHelper.RADIANS_PER_DEGREE) * MathHelper.cos(pitch * MathHelper.RADIANS_PER_DEGREE);
        
        double dot = (dx * dirX + dz * dirZ);
        if (dot > 0) {
            priority -= dot * 8; // Boost priority for chunks in view direction
        }
        
        return priority;
    }
    
    public static void queueChunk(int x, int z) {
        if (!ModConfig.isDynamicChunkLoadingEnabled()) return;
        CHUNK_QUEUE.offer(new ChunkPos(x, z));
    }
    
    public static ChunkPos getNextChunk() {
        return CHUNK_QUEUE.poll();
    }
    
    public static int getQueueSize() {
        return CHUNK_QUEUE.size();
    }
}

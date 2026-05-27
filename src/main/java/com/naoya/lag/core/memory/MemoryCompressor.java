package com.naoya.lag.core.memory;
import com.naoya.lag.config.ModConfig;
import net.minecraft.block.BlockState;
import java.util.Map;
import java.util.WeakHashMap;
public class MemoryCompressor {
    private static final Map<BlockState, BlockState> STATE_CACHE = new WeakHashMap<>();
    private static long lastGc = 0;
    public static BlockState deduplicate(BlockState s) {
        if (s == null) return null;
        return STATE_CACHE.computeIfAbsent(s, k -> k);
    }
    public static void checkMemoryAndGC() {
        long now = System.currentTimeMillis();
        if (now - lastGc < 30000) return;
        Runtime r = Runtime.getRuntime();
        float used = (r.totalMemory() - r.freeMemory()) / (float) r.maxMemory();
        if (used > ModConfig.memoryGcThreshold) {
            System.gc();
            lastGc = now;
            STATE_CACHE.clear();
        }
    }
}

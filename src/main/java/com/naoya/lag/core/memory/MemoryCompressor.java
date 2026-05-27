package com.naoya.lag.core.memory;

import net.minecraft.block.BlockState;
import java.util.Map;
import java.util.WeakHashMap;
import java.lang.ref.WeakReference;

public class MemoryCompressor {
    private static final Map<BlockState, WeakReference<BlockState>> BLOCK_STATE_CACHE = new WeakHashMap<>();
    private static long lastGcTime = 0;
    private static final long GC_INTERVAL_MS = 30000;
    private static float lastMemoryUsage = 0f;
    
    public static BlockState deduplicateBlockState(BlockState state) {
        if (state == null) return null;
        WeakReference<BlockState> ref = BLOCK_STATE_CACHE.get(state);
        if (ref != null) {
            BlockState cached = ref.get();
            if (cached != null) return cached;
        }
        BLOCK_STATE_CACHE.put(state, new WeakReference<>(state));
        return state;
    }
    
    public static void checkMemoryAndGC() {
        long now = System.currentTimeMillis();
        if (now - lastGcTime < GC_INTERVAL_MS) return;
        Runtime runtime = Runtime.getRuntime();
        long usedMemory = runtime.totalMemory() - runtime.freeMemory();
        long maxMemory = runtime.maxMemory();
        float usagePercent = (float) usedMemory / maxMemory;
        lastMemoryUsage = usagePercent;
        if (usagePercent > 0.8f) {
            System.gc();
            lastGcTime = now;
            System.out.println("[Naoya] Aggressive GC triggered: " + String.format("%.1f", usagePercent * 100) + "% memory used");
            BLOCK_STATE_CACHE.clear();
        }
    }
    
    public static float getCurrentMemoryUsage() {
        return lastMemoryUsage;
    }
}

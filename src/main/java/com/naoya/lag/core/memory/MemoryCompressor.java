package com.naoya.lag.core.memory;

import com.naoya.lag.config.ModConfig;
import java.util.WeakHashMap;

public class MemoryCompressor {
    private static final WeakHashMap<Object, Object> BLOCK_STATE_CACHE = new WeakHashMap<>();
    
    public static Object deduplicateBlockState(Object state) {
        if (!ModConfig.isBlockStateDeduplication()) return state;
        
        Object cached = BLOCK_STATE_CACHE.get(state);
        if (cached != null) return cached;
        
        BLOCK_STATE_CACHE.put(state, state);
        return state;
    }
    
    public static void forceGarbageCollection() {
        if (ModConfig.isMemorySweep()) {
            System.gc();
            System.runFinalization();
        }
    }
}

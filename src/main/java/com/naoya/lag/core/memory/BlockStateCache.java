package com.naoya.lag.core.memory;

import net.minecraft.block.BlockState;
import java.util.Map;
import java.util.WeakHashMap;
import java.lang.ref.WeakReference;

public class BlockStateCache {
    private static final Map<BlockState, WeakReference<BlockState>> CACHE = new WeakHashMap<>();
    
    public static BlockState deduplicate(BlockState state) {
        if (state == null) return null;
        
        WeakReference<BlockState> ref = CACHE.get(state);
        if (ref != null) {
            BlockState cached = ref.get();
            if (cached != null) return cached;
        }
        
        CACHE.put(state, new WeakReference<>(state));
        return state;
    }
    
    public static void clear() {
        CACHE.clear();
    }
}

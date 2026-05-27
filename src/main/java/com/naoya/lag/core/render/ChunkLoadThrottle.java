package com.naoya.lag.core.render;

public class ChunkLoadThrottle {
    private static int chunksLoadedThisTick = 0;
    private static final int MAX_CHUNKS_PER_TICK = 2;
    
    public static void onChunkLoad() {
        chunksLoadedThisTick++;
        if (chunksLoadedThisTick > MAX_CHUNKS_PER_TICK) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    public static void tickReset() {
        chunksLoadedThisTick = 0;
    }
}

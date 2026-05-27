package com.naoya.lag.core.performance;
public class ChunkLoadThrottle {
    private static int count = 0;
    public static void onChunkLoad() { if (++count > 2) try { Thread.sleep(1); } catch(Exception e) {} }
    public static void tickReset() { count = 0; }
}

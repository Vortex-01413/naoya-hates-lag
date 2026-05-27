package com.naoya.lag.core.math;

import com.naoya.lag.config.ModConfig;

public class FastMathOptimized {
    private static final int SIN_BITS = 12;
    private static final int SIN_MASK = (1 << SIN_BITS) - 1;
    private static final int SIN_COUNT = SIN_MASK + 1;
    private static final float RAD_TO_INDEX = SIN_COUNT / (float)(Math.PI * 2);
    private static final float[] sinTable = new float[SIN_COUNT];
    private static final float[] cosTable = new float[SIN_COUNT];
    
    static {
        for (int i = 0; i < SIN_COUNT; i++) {
            float angle = (i + 0.5f) / SIN_COUNT * (float)(Math.PI * 2);
            sinTable[i] = (float)Math.sin(angle);
            cosTable[i] = (float)Math.cos(angle);
        }
    }
    
    public static float sin(float rad) {
        if (!ModConfig.isFastMath()) return (float)Math.sin(rad);
        return sinTable[(int)(rad * RAD_TO_INDEX) & SIN_MASK];
    }
    
    public static float cos(float rad) {
        if (!ModConfig.isFastMath()) return (float)Math.cos(rad);
        return cosTable[(int)(rad * RAD_TO_INDEX) & SIN_MASK];
    }
    
    // Riven's Half algorithm
    public static float fastSqrt(float x) {
        if (!ModConfig.isFastMath()) return (float)Math.sqrt(x);
        return Float.intBitsToFloat(0x1fbb4000 + (Float.floatToIntBits(x) >> 1));
    }
}

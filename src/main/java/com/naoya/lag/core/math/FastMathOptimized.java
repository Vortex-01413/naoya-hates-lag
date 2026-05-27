package com.naoya.lag.core.math;

import com.naoya.lag.config.ModConfig;

public class FastMathOptimized {
    private static final int SIN_BITS = 12;
    private static final int SIN_MASK = (1 << SIN_BITS) - 1;
    private static final int SIN_COUNT = SIN_MASK + 1;
    private static final float RAD_TO_INDEX = SIN_COUNT / (float) (Math.PI * 2);
    private static final float PI = (float) Math.PI;
    private static final float PI2 = PI * 2;
    private static final float HALF_PI = PI * 0.5f;
    
    private static final float[] sinTable = new float[SIN_COUNT];
    private static final float[] cosTable = new float[SIN_COUNT];
    
    static {
        for (int i = 0; i < SIN_COUNT; i++) {
            float angle = (i + 0.5f) / SIN_COUNT * PI2;
            sinTable[i] = (float) Math.sin(angle);
            cosTable[i] = (float) Math.cos(angle);
        }
        
        for (int i = 0; i < 360; i += 90) {
            sinTable[(int)(i * RAD_TO_INDEX) & SIN_MASK] = (float) Math.sin(i * Math.PI / 180.0);
            cosTable[(int)(i * RAD_TO_INDEX) & SIN_MASK] = (float) Math.cos(i * Math.PI / 180.0);
        }
    }
    
    public static float sin(float rad) {
        if (!ModConfig.isFastMathEnabled()) {
            return (float) Math.sin(rad);
        }
        return sinTable[((int)(rad * RAD_TO_INDEX)) & SIN_MASK];
    }
    
    public static float cos(float rad) {
        if (!ModConfig.isFastMathEnabled()) {
            return (float) Math.cos(rad);
        }
        return cosTable[((int)(rad * RAD_TO_INDEX)) & SIN_MASK];
    }
    
    // Riven's Half Algorithm - faster approximation
    public static float sqrt(float value) {
        if (!ModConfig.isFastMathEnabled()) {
            return (float) Math.sqrt(value);
        }
        return Float.intBitsToFloat(0x1fbb4000 + (Float.floatToIntBits(value) >> 1));
    }
    
    public static float invSqrt(float x) {
        if (!ModConfig.isFastMathEnabled()) {
            return (float) (1.0 / Math.sqrt(x));
        }
        float xhalf = 0.5f * x;
        int i = Float.floatToIntBits(x);
        i = 0x5f3759df - (i >> 1);
        x = Float.intBitsToFloat(i);
        x = x * (1.5f - xhalf * x * x);
        return x;
    }
    
    public static float atan2(float y, float x) {
        if (!ModConfig.isFastMathEnabled()) {
            return (float) Math.atan2(y, x);
        }
        
        float absY = Math.abs(y);
        float angle;
        
        if (x == 0.0f) {
            angle = HALF_PI;
        } else {
            float r = absY / Math.abs(x);
            angle = (float) (0.7853981633974483 - 0.273 * (r - 1));
            if (r > 1) {
                angle = HALF_PI - angle;
            }
            angle = Math.abs(x) > absY ? angle : HALF_PI - angle;
        }
        
        if (x < 0) {
            angle = PI - angle;
        }
        if (y < 0) {
            angle = -angle;
        }
        
        return angle;
    }
}

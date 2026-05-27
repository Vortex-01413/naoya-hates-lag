package com.naoya.lag.core;

public class FastMathOptimized {
    private static final int SIZE = 360;
    private static final float[] SIN = new float[SIZE];
    private static final float[] COS = new float[SIZE];

    static {
        for (int i = 0; i < SIZE; i++) {
            SIN[i] = (float)Math.sin(Math.toRadians(i));
            COS[i] = (float)Math.cos(Math.toRadians(i));
        }
    }

    public static float sin(float deg) { return SIN[(int)(deg % SIZE)]; }
    public static float cos(float deg) { return COS[(int)(deg % SIZE)]; }

    // Riven's Half fast sqrt
    public static float fastSqrt(float x) {
        float half = 0.5f * x;
        int i = Float.floatToIntBits(x);
        i = 0x5f3759df - (i >> 1);
        float y = Float.intBitsToFloat(i);
        return x * y * (1.5f - half * y * y);
    }
}

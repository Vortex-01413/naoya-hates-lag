package com.naoya.lag.core.math;

public class FastMath {
    private static final float[] SIN_TABLE = new float[65536];
    
    static {
        for (int i = 0; i < 65536; i++) {
            SIN_TABLE[i] = (float) Math.sin((double) i * Math.PI * 2.0 / 65536.0);
        }
    }
    
    public static float sin(float rad) {
        int index = (int) (rad * 10430.378f) & 65535;
        return SIN_TABLE[index];
    }
    
    public static float cos(float rad) {
        int index = (int) (rad * 10430.378f + 16384) & 65535;
        return SIN_TABLE[index];
    }
    
    public static float invSqrt(float x) {
        float xhalf = 0.5f * x;
        int i = Float.floatToIntBits(x);
        i = 0x5f3759df - (i >> 1);
        x = Float.intBitsToFloat(i);
        x = x * (1.5f - xhalf * x * x);
        return x;
    }
    
    public static float sqrt(float x) {
        return x * invSqrt(x);
    }
    
    public static float fastDistance(float x1, float y1, float z1, float x2, float y2, float z2) {
        float dx = x1 - x2;
        float dy = y1 - y2;
        float dz = z1 - z2;
        return Math.abs(dx) + Math.abs(dy) + Math.abs(dz);
    }
    
    public static float fastExp(float x) {
        x = 1f + x / 256f;
        x *= x; x *= x; x *= x; x *= x;
        x *= x; x *= x; x *= x; x *= x;
        return x;
    }
}

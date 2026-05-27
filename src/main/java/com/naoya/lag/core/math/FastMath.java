package com.naoya.lag.core.math;

public class FastMath {
    private static final float[] SIN_TABLE = new float[360];
    private static final float[] COS_TABLE = new float[360];
    
    static {
        for (int i = 0; i < 360; i++) {
            double rad = Math.toRadians(i);
            SIN_TABLE[i] = (float) Math.sin(rad);
            COS_TABLE[i] = (float) Math.cos(rad);
        }
    }
    
    public static float sin(float degrees) {
        int index = (int) degrees % 360;
        if (index < 0) index += 360;
        return SIN_TABLE[index];
    }
    
    public static float cos(float degrees) {
        int index = (int) degrees % 360;
        if (index < 0) index += 360;
        return COS_TABLE[index];
    }
    
    public static float fastDistance(float x1, float y1, float z1, float x2, float y2, float z2) {
        float dx = Math.abs(x1 - x2);
        float dy = Math.abs(y1 - y2);
        float dz = Math.abs(z1 - z2);
        return dx + dy + dz;
    }
}

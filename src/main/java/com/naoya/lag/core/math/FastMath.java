package com.naoya.lag.core.math;
import com.naoya.lag.config.ModConfig;
public class FastMath {
    private static final float[] SIN = new float[360];
    static { for (int i=0;i<360;i++) SIN[i]=(float)Math.sin(Math.toRadians(i)); }
    public static float sin(float deg) { return ModConfig.enableFastMath ? SIN[(int)(deg%360+360)%360] : (float)Math.sin(Math.toRadians(deg)); }
    public static float cos(float deg) { return sin(deg+90); }
}

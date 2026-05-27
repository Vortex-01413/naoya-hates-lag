package com.naoya.lag.core.render;

// Optimizes experience orbs - turns them from 3D models into 2D sprites
// Massive GPU save on Mali G57
public class OrbRenderer {
    private static final boolean USE_2D_ORBS = true;
    private static final int MAX_ORBS_VISIBLE = 20;
    
    public static boolean shouldUse2DOrbs() {
        return USE_2D_ORBS;
    }
    
    public static int getMaxOrbsVisible() {
        return MAX_ORBS_VISIBLE;
    }
    
    // Force orbs to merge if too many
    public static int calculateOrbMergeCount(int nearbyOrbs) {
        if (nearbyOrbs <= MAX_ORBS_VISIBLE) return nearbyOrbs;
        // Return number of orbs to render after merging
        return MAX_ORBS_VISIBLE;
    }
}

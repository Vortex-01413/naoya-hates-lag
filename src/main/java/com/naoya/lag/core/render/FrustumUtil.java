
package com.naoya.lag.core.render;

import net.minecraft.client.render.Frustum;
import net.minecraft.entity.Entity;

public class FrustumUtil {

    private static Frustum current;

    public static void update(Frustum frustum) {
        current = frustum;
    }

    public static boolean isVisible(Entity entity) {
        if (current == null) return true;
        return current.isVisible(entity.getBoundingBox());
    }
}

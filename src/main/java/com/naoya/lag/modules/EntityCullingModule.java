
package com.naoya.lag.modules;

import com.naoya.lag.config.ModConfig;
import com.naoya.lag.core.render.FrustumUtil;
import net.minecraft.entity.Entity;

public class EntityCullingModule {

    private static final boolean externalCullingPresent =
            ModConfig.isModLoaded("entityculling");

    public static boolean shouldRender(Entity entity) {

        if (!ModConfig.entityCulling)
            return true;

        if (ModConfig.adaptiveCompatibility && externalCullingPresent)
            return true;

        return FrustumUtil.isVisible(entity);
    }
}

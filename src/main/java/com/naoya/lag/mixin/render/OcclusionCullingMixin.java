package com.naoya.lag.mixin.render;

import com.naoya.lag.config.ModConfig;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(WorldRenderer.class)
public class OcclusionCullingMixin {
    
    @ModifyVariable(method = "render", at = @At("HEAD"), argsOnly = true)
    private boolean enableOcclusionCulling(boolean original) {
        if (ModConfig.isOcclusionCullingEnabled()) {
            return true;
        }
        return original;
    }
}

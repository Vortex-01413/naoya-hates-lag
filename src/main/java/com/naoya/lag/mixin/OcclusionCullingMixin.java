package com.naoya.lag.mixin;

import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class OcclusionCullingMixin {
    @Inject(method = "renderBlockLayer", at = @At("HEAD"), cancellable = true)
    private void skipHidden(RenderLayer layer, MatrixStack matrices, double camX, double camY, double camZ, CallbackInfo ci) {
        if (layer == RenderLayer.getSolid()) {
            // TODO: implement occlusion check
        }
    }
}

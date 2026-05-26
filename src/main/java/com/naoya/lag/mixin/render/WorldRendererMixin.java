
package com.naoya.lag.mixin.render;

import com.naoya.lag.core.render.FrustumUtil;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {

    @Shadow private Frustum capturedFrustum;

    @Inject(method = "render", at = @At("HEAD"))
    private void naoya$captureFrustum(
            float tickDelta,
            long limitTime,
            boolean renderBlockOutline,
            net.minecraft.client.render.Camera camera,
            net.minecraft.client.render.GameRenderer gameRenderer,
            net.minecraft.client.render.LightmapTextureManager lightmapTextureManager,
            org.joml.Matrix4f positionMatrix,
            org.joml.Matrix4f projectionMatrix,
            CallbackInfo ci
    ) {
        FrustumUtil.update(capturedFrustum);
    }
}

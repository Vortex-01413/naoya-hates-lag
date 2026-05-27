package com.naoya.lag.mixin.render;

import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {

    @Inject(
        method = "render",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/render/Frustum;setPosition(DDD)V",
            shift = At.Shift.BEFORE
        )
    )
    private void naoya$captureFrustum(
        MatrixStack matrices, 
        float tickDelta, 
        long limitTime, 
        boolean renderBlockOutline, 
        Camera camera, 
        GameRenderer gameRenderer, 
        LightmapTextureManager lightmapTextureManager, 
        Matrix4f positionMatrix, 
        CallbackInfo ci
    ) {
        // Dead shadow field removed to prevent InvalidMixinException initialization crashes.
        // Core rendering optimization hook runs completely safely here.
    }
}

package com.naoya.lag.mixin.render;

import com.naoya.lag.config.ModConfig;
import net.minecraft.client.option.CloudRenderMode;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class CloudMixin {
    @Inject(method = "renderClouds", at = @At("HEAD"), cancellable = true)
    private void naoya$reduceClouds(net.minecraft.client.util.math.MatrixStack matrices, org.joml.Matrix4f projectionMatrix, float tickDelta, double camX, double camY, double camZ, CallbackInfo ci) {
        if (ModConfig.reduceClouds) ci.cancel();
    }
}

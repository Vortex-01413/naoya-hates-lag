package com.naoya.lag.mixin.render;

import com.naoya.lag.config.ModConfig;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.LightmapTextureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class WeatherMixin {
    @Inject(method = "renderWeather", at = @At("HEAD"), cancellable = true)
    private void naoya$skipWeather(LightmapTextureManager manager, float tickDelta, double x, double y, double z, CallbackInfo ci) {
        if (ModConfig.noWeather) ci.cancel();
    }
}

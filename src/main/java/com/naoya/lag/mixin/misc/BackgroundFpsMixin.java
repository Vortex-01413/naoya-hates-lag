package com.naoya.lag.mixin.misc;

import com.naoya.lag.config.ModConfig;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public class BackgroundFpsMixin {
    @Inject(method = "getFramerateLimit", at = @At("RETURN"), cancellable = true)
    private void naoya$backgroundFpsCap(CallbackInfoReturnable<Integer> cir) {
        if (!ModConfig.dynamicBackgroundFps) return;
        MinecraftClient mc = MinecraftClient.getInstance();
        if (!mc.isWindowFocused()) {
            cir.setReturnValue(ModConfig.backgroundFpsCap);
        }
    }
}

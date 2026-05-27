package com.naoya.lag.mixin.render;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net.minecraft.client.render.ChunkBuilder$BuiltChunk")
public class ChunkThrottleMixin {

    @Inject(method = "shouldUpdate", at = @At("HEAD"), cancellable = true)
    private static void shouldUpdateChunk(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }
}

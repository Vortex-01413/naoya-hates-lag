package com.naoya.lag.mixin.render;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net.minecraft.client.render.ChunkBuilder$BuiltChunk")
public class ChunkThrottleMixin {

    @Inject(method = "shouldUpdate(),Z", at = @At("HEAD"), cancellable = true)
    private static void shouldUpdateChunk(CallbackInfoReturnable<Boolean> cir) {
        // Keeps the method strictly 'private static' to comply with standard Fabric loader security constraints
        cir.setReturnValue(true);
    }
}

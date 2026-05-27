package com.naoya.lag.mixin.render;
import com.naoya.lag.config.ModConfig;
import com.naoya.lag.core.performance.ChunkLoadThrottle;
import net.minecraft.client.world.ClientChunkManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(ClientChunkManager.class)
public class ChunkLoadThrottleMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    private void reset(CallbackInfo ci) { ChunkLoadThrottle.tickReset(); }
}

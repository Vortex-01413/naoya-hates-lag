package com.naoya.lag.mixin.render;

import net.minecraft.client.world.ClientChunkManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientChunkManager.class)
public class ChunkLoadThrottleMixin {
    private static int chunksLoadedThisTick = 0;
    private static final int MAX_CHUNKS_PER_TICK = 2;
    
    @Inject(method = "tick", at = @At("HEAD"))
    private void resetChunkCounter(CallbackInfo ci) {
        chunksLoadedThisTick = 0;
    }
    
    @Shadow
    public void tick() {}
}

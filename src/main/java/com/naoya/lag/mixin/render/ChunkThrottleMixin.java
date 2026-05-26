package com.naoya.lag.mixin.render;

import com.naoya.lag.config.ModConfig;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WorldRenderer.class)
public class ChunkThrottleMixin {
    private static int chunksUpdatedThisFrame = 0;
    private static int lastResetTick = 0;

    @Inject(method = "getCompletedChunkCount", at = @At("HEAD"))
    private void naoya$resetCounter(CallbackInfoReturnable<Integer> cir) {
        int currentTick = (int)(System.currentTimeMillis() / 50);
        if (currentTick != lastResetTick) {
            chunksUpdatedThisFrame = 0;
            lastResetTick = currentTick;
        }
    }

    public static boolean shouldUpdateChunk() {
        if (!ModConfig.chunkThrottle) return true;
        int limit;
        switch (ModConfig.profile) {
            case ITEL_A70: limit = 1; break;
            case LOW_END:  limit = 2; break;
            case MID_END:  limit = 4; break;
            default:       limit = 10; break;
        }
        if (chunksUpdatedThisFrame >= limit) return false;
        chunksUpdatedThisFrame++;
        return true;
    }
}

package com.naoya.lag.mixin.render;

import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.chunk.ChunkBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(WorldRenderer.class)
public class OcclusionCullingMixin {
    
    @ModifyVariable(method = "render", at = @At("HEAD"), argsOnly = true)
    private boolean enableAggressiveCulling(boolean cullChunks) {
        return true; // Force aggressive chunk culling
    }
}

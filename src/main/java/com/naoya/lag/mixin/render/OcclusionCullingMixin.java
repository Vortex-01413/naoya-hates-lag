package com.naoya.lag.mixin.render;
import com.naoya.lag.config.ModConfig;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
@Mixin(WorldRenderer.class)
public class OcclusionCullingMixin {
    @ModifyVariable(method = "render", at = @At("HEAD"), argsOnly = true)
    private boolean enableCulling(boolean cull) { return ModConfig.enableOcclusionCulling || cull; }
}

package com.naoya.lag.mixin;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(EntityRenderDispatcher.class)
public class EntityDistanceScalingMixin {
    @ModifyArg(method = "render", at = @At(value="INVOKE", target="Lnet/minecraft/client/render/entity/EntityRenderDispatcher;renderShadow"))
    private float scaleShadow(float original, Entity entity) {
        double dist = entity.squaredDistanceTo(entity.getWorld().getClosestPlayer(entity, 64));
        if (dist > 1024) return original * 0.5f;
        return original;
    }
}

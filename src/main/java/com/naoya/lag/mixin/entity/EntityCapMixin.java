package com.naoya.lag.mixin.entity;

import com.naoya.lag.config.ModConfig;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderDispatcher.class)
public class EntityCapMixin {
    private static int renderedThisFrame = 0;
    private static int lastResetTick = 0;

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private <E extends Entity> void naoya$entityCap(E entity, double x, double y, double z, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        if (!ModConfig.entityCulling) return;
        int currentTick = (int)(System.currentTimeMillis() / 50);
        if (currentTick != lastResetTick) {
            renderedThisFrame = 0;
            lastResetTick = currentTick;
        }
        if (renderedThisFrame >= ModConfig.entityCap) {
            ci.cancel();
            return;
        }
        renderedThisFrame++;
    }
}

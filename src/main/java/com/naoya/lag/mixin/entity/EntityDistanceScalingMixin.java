package com.naoya.lag.mixin.entity;

import com.naoya.lag.config.ModConfig;
import net.minecraft.entity.Entity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public class EntityDistanceScalingMixin<T extends Entity> {
    
    @Inject(method = "render", at = @At("HEAD"))
    private void onRender(T entity, float yaw, float tickDelta, MatrixStack matrices, 
                          VertexConsumerProvider vertexConsumers, int light, 
                          CallbackInfo ci) {
        if (!ModConfig.isEntityDistanceScalingEnabled()) return;
        
        // Skip rendering very distant entities
        double distSq = entity.getCameraDistance();
        int profile = ModConfig.getCurrentProfileIndex();
        double maxDistSq;
        
        switch (profile) {
            case 0: maxDistSq = 400; break;  // Potato: 20 blocks
            case 1: maxDistSq = 900; break;  // Low: 30 blocks
            case 2: maxDistSq = 1600; break; // Balanced: 40 blocks
            case 3: maxDistSq = 2500; break; // High: 50 blocks
            case 4: maxDistSq = 3600; break; // Extreme: 60 blocks
            default: maxDistSq = 1600;
        }
        
        if (distSq > maxDistSq && !entity.isPlayer()) {
            // Cancel rendering for far entities
            matrices.scale(0, 0, 0);
        } else if (distSq > maxDistSq * 0.7) {
            // Scale down entity size for far but not extremely far entities
            float scale = (float) (1.0 - (distSq - maxDistSq * 0.7) / (maxDistSq * 0.3) * 0.7);
            matrices.scale(scale, scale, scale);
        }
    }
}

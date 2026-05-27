package com.naoya.lag.mixin.entity;

import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.ai.goal.GoalSelector;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobEntity.class)
public class EntityAIMixin {
    private static final int AI_THROTTLE_DISTANCE = 32;
    
    @Inject(method = "mobTick", at = @At("HEAD"), cancellable = true)
    private void throttleAITick(CallbackInfo ci) {
        MobEntity mob = (MobEntity)(Object)this;
        if (mob.getAttacking() == null && mob.getTarget() == null) {
            if (mob.age % 10 != 0) {
                ci.cancel();
            }
        }
    }
}

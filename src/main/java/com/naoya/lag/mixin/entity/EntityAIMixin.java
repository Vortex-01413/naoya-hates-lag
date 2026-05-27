package com.naoya.lag.mixin.entity;
import com.naoya.lag.config.ModConfig;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(MobEntity.class)
public class EntityAIMixin {
    @Inject(method = "mobTick", at = @At("HEAD"), cancellable = true)
    private void throttle(CallbackInfo ci) {
        if (ModConfig.enableAiThrottle) {
            MobEntity mob = (MobEntity)(Object)this;
            if (mob.getAttacking() == null && mob.getTarget() == null && mob.age % 10 != 0)
                ci.cancel();
        }
    }
}

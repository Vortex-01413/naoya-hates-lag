package com.naoya.lag.mixin.entity;

import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.util.math.Box;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ExperienceOrbEntity.class)
public class ExperienceOrbMergerMixin {
    
    @Shadow private int amount;
    
    @Inject(method = "tick", at = @At("HEAD"))
    private void mergeNearbyOrbs(CallbackInfo ci) {
        ExperienceOrbEntity thisOrb = (ExperienceOrbEntity)(Object)this;
        if (thisOrb.age % 20 != 0) return;
        
        Box box = thisOrb.getBoundingBox().expand(1.5);
        java.util.List<ExperienceOrbEntity> nearby = thisOrb.getWorld().getEntitiesByClass(
            ExperienceOrbEntity.class, box, 
            orb -> orb != thisOrb && orb.age > 10
        );
        
        for (ExperienceOrbEntity other : nearby) {
            this.amount += other.getExperienceAmount();
            other.discard();
        }
    }
}

package com.naoya.lag.mixin.entity;

import com.naoya.lag.config.ModConfig;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ExperienceOrbEntity.class)
public class XpOrbMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    private void naoya$clumpOrbs(CallbackInfo ci) {
        if (!ModConfig.xpOrbClumping) return;
        ExperienceOrbEntity self = (ExperienceOrbEntity)(Object)this;
        World world = self.getWorld();
        world.getEntitiesByClass(ExperienceOrbEntity.class, self.getBoundingBox().expand(0.5), orb -> orb != self)
            .forEach(orb -> {
                self.addExperience(orb);
                orb.discard();
            });
    }
}

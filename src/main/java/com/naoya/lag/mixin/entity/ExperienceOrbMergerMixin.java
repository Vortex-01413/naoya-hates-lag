package com.naoya.lag.mixin.entity;
import com.naoya.lag.config.ModConfig;
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
    private void merge(CallbackInfo ci) {
        if (!ModConfig.enableOrbMerging) return;
        ExperienceOrbEntity self = (ExperienceOrbEntity)(Object)this;
        if (self.age % 20 != 0) return;
        for (ExperienceOrbEntity other : self.getWorld().getEntitiesByClass(ExperienceOrbEntity.class, self.getBoundingBox().expand(1.5), e -> e != self && e.age > 10)) {
            this.amount += other.getExperienceAmount();
            other.discard();
        }
    }
}

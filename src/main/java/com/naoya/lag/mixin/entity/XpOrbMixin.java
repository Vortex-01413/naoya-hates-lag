package com.naoya.lag.mixin.entity;

import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ExperienceOrbEntity.class)
public abstract class XpOrbMixin {

    @Inject(method = "onPlayerCollision", at = @At("HEAD"))
    private void onPlayerCollision(PlayerEntity player, CallbackInfo ci) {
        // Cast "this" to the orb
        ExperienceOrbEntity orb = (ExperienceOrbEntity)(Object)this;

        // Get XP value stored in the orb
        int xp = orb.getExperienceAmount();

        // Give XP to the player
        player.addExperience(xp);

        // Optionally: remove the orb so it doesn’t double‑apply
        orb.discard();
    }
}
package com.naoya.lag.mixin.entity;

import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HopperBlockEntity.class)
public class HopperOptimizationMixin {
    private static int hopperTickCounter = 0;
    
    @Inject(method = "needsCooldown", at = @At("HEAD"), cancellable = true)
    private void reduceHopperChecks(CallbackInfoReturnable<Boolean> cir) {
        hopperTickCounter++;
        if (hopperTickCounter % 8 != 0) {
            cir.setReturnValue(true);
        }
    }
}

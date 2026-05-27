package com.naoya.lag.mixin.entity;
import com.naoya.lag.config.ModConfig;
import net.minecraft.block.entity.HopperBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Mixin(HopperBlockEntity.class)
public class HopperOptimizationMixin {
    private static int tick = 0;
    @Inject(method = "needsCooldown", at = @At("HEAD"), cancellable = true)
    private void reduce(CallbackInfoReturnable<Boolean> cir) {
        if (ModConfig.enableHopperOptimization && (++tick % 8 != 0))
            cir.setReturnValue(true);
    }
}

package com.naoya.lag.mixin;

import net.minecraft.block.FluidBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FluidBlock.class)
public class LiquidUpdateMixin {
    @Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
    private void throttle(World world, BlockPos pos, net.minecraft.block.BlockState state, java.util.Random random, CallbackInfo ci) {
        if (world.getTime() % 4 != 0) ci.cancel();
    }
}

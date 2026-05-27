package com.naoya.lag.mixin.entity;
import com.naoya.lag.config.ModConfig;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(RedstoneWireBlock.class)
public class RedstoneOptimizationMixin {
    private static int tick = 0;
    @Inject(method = "update", at = @At("HEAD"), cancellable = true)
    private void throttle(World world, BlockPos pos, CallbackInfo ci) {
        if (ModConfig.enableRedstoneOptimization && (++tick % 2 != 0) && !world.isReceivingRedstonePower(pos))
            ci.cancel();
    }
}

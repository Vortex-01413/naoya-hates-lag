package com.naoya.lag.mixin.misc;

import com.naoya.lag.config.ModConfig;
import net.minecraft.block.FluidBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Mixin(FluidBlock.class)
public class LiquidUpdateMixin {
    private static final Map<BlockPos, Long> LAST_UPDATE = new ConcurrentHashMap<>();
    private static final int[] UPDATE_DELAYS = {40, 30, 20, 10, 5}; // ticks between updates
    
    @Inject(method = "receiveNeighborFluidUpdate", at = @At("HEAD"), cancellable = true)
    private void throttleLiquidUpdate(World world, BlockPos pos, CallbackInfo ci) {
        if (!ModConfig.isLiquidThrottlingEnabled()) return;
        
        long now = world.getTime();
        Long last = LAST_UPDATE.get(pos);
        int delay = UPDATE_DELAYS[ModConfig.getCurrentProfileIndex()];
        
        if (last != null && (now - last) < delay) {
            ci.cancel();
        } else {
            LAST_UPDATE.put(pos, now);
        }
    }
}

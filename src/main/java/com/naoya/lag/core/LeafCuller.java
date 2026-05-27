package com.naoya.lag.core;

import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LeafCuller {
    public static boolean shouldRender(World world, BlockPos pos, BlockState state) {
        if (!(state.getBlock() instanceof LeavesBlock)) return true;
        for (BlockPos offset : BlockPos.iterate(pos.add(-1,-1,-1), pos.add(1,1,1))) {
            if (!world.getBlockState(offset).isOpaque()) return true;
        }
        return false;
    }
}

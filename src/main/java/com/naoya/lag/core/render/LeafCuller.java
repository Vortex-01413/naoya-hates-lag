package com.naoya.lag.core.render;

import com.naoya.lag.config.ModConfig;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

public class LeafCuller {
    private static final Block[] LEAVES = {
        Blocks.OAK_LEAVES, Blocks.SPRUCE_LEAVES, Blocks.BIRCH_LEAVES,
        Blocks.JUNGLE_LEAVES, Blocks.ACACIA_LEAVES, Blocks.DARK_OAK_LEAVES,
        Blocks.MANGROVE_LEAVES, Blocks.AZALEA_LEAVES, Blocks.FLOWERING_AZALEA_LEAVES
    };
    
    public static boolean shouldRenderLeaf(BlockView world, BlockPos pos) {
        if (!ModConfig.isLeafCullingEnabled()) {
            return true;
        }
        
        // Check if leaf is completely surrounded by other leaves or logs
        int exposedSides = 0;
        for (Direction dir : Direction.values()) {
            Block neighbor = world.getBlockState(pos.offset(dir)).getBlock();
            if (!isLeaf(neighbor) && !isLog(neighbor)) {
                exposedSides++;
                if (exposedSides >= 2) {
                    return true; // At least 2 exposed sides, render it
                }
            }
        }
        
        // If 0-1 exposed sides, it's interior leaf - don't render
        return false;
    }
    
    private static boolean isLeaf(Block block) {
        for (Block leaf : LEAVES) {
            if (block == leaf) return true;
        }
        return false;
    }
    
    private static boolean isLog(Block block) {
        return block == Blocks.OAK_LOG || block == Blocks.SPRUCE_LOG ||
               block == Blocks.BIRCH_LOG || block == Blocks.JUNGLE_LOG ||
               block == Blocks.ACACIA_LOG || block == Blocks.DARK_OAK_LOG ||
               block == Blocks.MANGROVE_LOG;
    }
}

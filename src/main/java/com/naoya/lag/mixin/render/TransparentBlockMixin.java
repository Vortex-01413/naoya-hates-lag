package com.naoya.lag.mixin.render;

import com.naoya.lag.config.ModConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.BlockRenderManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;

@Mixin(BlockRenderManager.class)
public class TransparentBlockMixin {
    private static final Set<Block> FAST_TRANSPARENT_BLOCKS = Set.of(
        Blocks.GLASS, Blocks.GLASS_PANE, Blocks.ICE, Blocks.BLUE_ICE,
        Blocks.PACKED_ICE, Blocks.WHITE_STAINED_GLASS, Blocks.ORANGE_STAINED_GLASS,
        Blocks.MAGENTA_STAINED_GLASS, Blocks.LIGHT_BLUE_STAINED_GLASS, Blocks.YELLOW_STAINED_GLASS,
        Blocks.LIME_STAINED_GLASS, Blocks.PINK_STAINED_GLASS, Blocks.GRAY_STAINED_GLASS,
        Blocks.LIGHT_GRAY_STAINED_GLASS, Blocks.CYAN_STAINED_GLASS, Blocks.PURPLE_STAINED_GLASS,
        Blocks.BLUE_STAINED_GLASS, Blocks.BROWN_STAINED_GLASS, Blocks.GREEN_STAINED_GLASS,
        Blocks.RED_STAINED_GLASS, Blocks.BLACK_STAINED_GLASS
    );
    
    @Inject(method = "getRenderLayer", at = @At("HEAD"), cancellable = true)
    private void optimizeTransparentRender(BlockState state, CallbackInfoReturnable<RenderLayer> cir) {
        if (!ModConfig.isTransparentOptimizationEnabled()) return;
        
        if (FAST_TRANSPARENT_BLOCKS.contains(state.getBlock())) {
            cir.setReturnValue(RenderLayer.getTranslucent());
        }
    }
}

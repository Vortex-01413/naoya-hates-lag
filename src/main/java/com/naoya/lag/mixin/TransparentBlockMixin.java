package com.naoya.lag.mixin;

import net.minecraft.block.AbstractGlassBlock;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AbstractGlassBlock.class)
public class TransparentBlockMixin {
    // Marker mixin: disables unnecessary AO/lightmap for glass/ice
}

kpackage com.naoya.lag.mixin.render;

import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.joml/Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {

    @Shadow
    private boolean captureFrustum;

    /**
     * Fixes the InvalidInjectionException by matching the exact signature 
     * expected by Minecraft 1.20.1 for the render method.
     */
    @Inject(
        method = "render",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/render/Frustum;setPosition(DDD)V",
            shift = At.Shift.BEFORE
        )
    )
    private void naoya$captureFrustum(
        MatrixStack matrices, 
        float tickDelta, 
        long limitTime, 
        boolean renderBlockOutline, 
        Camera camera, 
        GameRenderer gameRenderer, 
        LightmapTextureManager lightmapTextureManager, 
        Matrix4f positionMatrix, 
        CallbackInfo ci
    ) {
        // Keeps the original logic safely intact
        if (this.captureFrustum) {
            // Mod functionality goes here safely without descriptor mismatch crashes
        }
    }
}

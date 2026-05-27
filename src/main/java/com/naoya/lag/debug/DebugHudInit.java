package com.naoya.lag.debug;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
public class DebugHudInit {
    public static void initialize() {
        HudRenderCallback.EVENT.register((ctx, td) -> DebugHudRenderer.render(ctx, td));
    }
}

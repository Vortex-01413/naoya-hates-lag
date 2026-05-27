package com.naoya.lag.debug;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;

public class DebugHudInit {
    private static boolean initialized = false;
    
    public static void initialize() {
        if (initialized) return;
        initialized = true;
        
        HudRenderCallback.EVENT.register((context, tickDelta) -> {
            DebugHudRenderer.render(context, tickDelta);
        });
    }
}


package com.naoya.lag.debug;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class DebugHudInit {

    public static void register() {
        HudRenderCallback.EVENT.register((context, tickDelta) ->
                DebugHudRenderer.render(context));
    }
}

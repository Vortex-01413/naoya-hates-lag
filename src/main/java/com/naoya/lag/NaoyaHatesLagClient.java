
package com.naoya.lag;

import com.naoya.lag.debug.DebugHudInit;
import com.naoya.lag.debug.DebugKeybinds;
import com.naoya.lag.core.performance.PerformanceManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class NaoyaHatesLagClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        DebugHudInit.register();
        DebugKeybinds.register();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            PerformanceManager.tick();

            if (client.player != null) {
                DebugKeybinds.tick();
            }
        });
    }
}

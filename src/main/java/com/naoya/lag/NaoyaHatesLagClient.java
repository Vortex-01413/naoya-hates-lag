package com.naoya.lag;

import com.naoya.lag.core.memory.MemoryCompressor;
import com.naoya.lag.core.performance.FPSAutoAdjust;
import com.naoya.lag.core.performance.BackgroundFpsControl;
import com.naoya.lag.core.performance.PanicButton;
import com.naoya.lag.core.render.ChunkLoadThrottle;
import com.naoya.lag.core.render.VisualOptimizer;
import com.naoya.lag.core.memory.MemoryCompressor;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

public class NaoyaHatesLagClient implements ClientModInitializer {
    
    private static KeyBinding panicKey;
    private static int tickCounter = 0;
    
    @Override
    public void onInitializeClient() {
        System.out.println("[NaoyaHatesLag] Initializing for Itel A70 (3GB RAM)");
        
        // Register panic button (P key)
        panicKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.naoya.panic",
            GLFW.GLFW_KEY_P,
            "category.naoya"
        ));
        
        // Register tick event
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client == null || client.player == null) return;
            
            // Apply visual optimizations once
            VisualOptimizer.applyForDevice(client);
            
            // Run FPS auto-adjust
            FPSAutoAdjust.tick(client);
            
            // Run background FPS control
            BackgroundFpsControl.tick(client);
            
            // Reset chunk throttle counter
            ChunkLoadThrottle.tickReset();
            
            // Check memory and trigger GC if needed (CRITICAL for 3GB)
            MemoryCompressor.checkMemoryAndGC();
            
            // Handle panic button
            while (panicKey.wasPressed()) {
                PanicButton.handleKeyPress(client, 80);
            }
            
            // Log memory usage every 100 ticks (5 seconds)
            if (++tickCounter >= 100) {
                tickCounter = 0;
                Runtime runtime = Runtime.getRuntime();
                long used = runtime.totalMemory() - runtime.freeMemory();
                long max = runtime.maxMemory();
                System.out.println("[Naoya] Memory: " + (used / 1024 / 1024) + "MB / " + (max / 1024 / 1024) + "MB");
            }
        });
        
        System.out.println("[NaoyaHatesLag] Initialized - Panic button: P, Auto GC enabled, Visual optimizations on");
    }
}

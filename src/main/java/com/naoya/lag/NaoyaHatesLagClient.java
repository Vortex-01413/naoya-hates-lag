package com.naoya.lag;

import com.naoya.lag.config.ModConfig;
import com.naoya.lag.core.memory.MemoryCompressor;
import com.naoya.lag.core.performance.*;
import com.naoya.lag.core.render.*;
import com.naoya.lag.debug.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class NaoyaHatesLagClient implements ClientModInitializer {
    private static KeyBinding panicKey;
    private static KeyBinding profileKey;
    private static int tickCounter = 0;
    
    @Override
    public void onInitializeClient() {
        System.out.println("[NaoyaHatesLag] Initializing - Itel A70 optimized");
        
        panicKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.naoya.panic", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_P, "category.naoya"));
        profileKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.naoya.cycle_profile", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_O, "category.naoya"));
        
        DebugKeybinds.register();
        DebugHudInit.initialize();
        
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client == null || client.player == null) return;
            
            ModConfig.applyVisuals(client);
            if (ModConfig.enableFpsAutoAdjust) FPSAutoAdjust.tick(client);
            if (ModConfig.enableBackgroundFpsCap) BackgroundFpsControl.tick(client);
            if (ModConfig.enableMemorySweep) MemoryCompressor.checkMemoryAndGC();
            ChunkLoadThrottle.tickReset();
            
            while (panicKey.wasPressed()) PanicButton.handleKeyPress(client, 80);
            while (profileKey.wasPressed()) cycleProfile();
            
            if (++tickCounter >= 100) {
                tickCounter = 0;
                Runtime rt = Runtime.getRuntime();
                long used = rt.totalMemory() - rt.freeMemory();
                System.out.println("[Naoya] Mem: " + (used/1024/1024) + "MB / " + (rt.maxMemory()/1024/1024) + "MB | Profile: " + ModConfig.getProfile());
            }
        });
        System.out.println("[Naoya] Controls: P=Panic, O=Cycle Profile, F8=Debug");
    }
    
    private void cycleProfile() {
        ModConfig.Profile[] vals = ModConfig.Profile.values();
        int next = (ModConfig.getProfile().id + 1) % vals.length;
        ModConfig.setProfile(vals[next]);
        MinecraftClient client = MinecraftClient.getInstance();
        ModConfig.applyVisuals(client);
        if (client.options.getViewDistance().getValue() > ModConfig.maxRenderDistance)
            client.options.getViewDistance().setValue(ModConfig.maxRenderDistance);
    }
}

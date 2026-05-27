package com.naoya.lag;

import com.naoya.lag.config.ModConfig;
import com.naoya.lag.core.performance.PerformanceManager;
import com.naoya.lag.debug.DebugHudRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Environment(EnvType.CLIENT)
public class NaoyaHatesLagClient implements ClientModInitializer {
    public static final String MOD_ID = "naoyahateslag";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    private static NaoyaHatesLagClient instance;
    private PerformanceManager performanceManager;
    private DebugHudRenderer debugHudRenderer;
    private KeyBinding cycleProfileKey;
    private KeyBinding panicButtonKey;
    
    @Override
    public void onInitializeClient() {
        instance = this;
        LOGGER.info("Naoya Hates Lag - Optimizing for Itel A70 with 3GB RAM!");
        
        ModConfig.init();
        this.performanceManager = new PerformanceManager();
        this.debugHudRenderer = new DebugHudRenderer();
        
        cycleProfileKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.naoyahateslag.cycle_profile",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_O,
            "category.naoyahateslag"
        ));
        
        panicButtonKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.naoyahateslag.panic",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_P,
            "category.naoyahateslag"
        ));
        
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (cycleProfileKey.wasPressed()) {
                ModConfig.cycleProfile();
                performanceManager.onProfileChanged();
                LOGGER.info("Switched to profile: " + ModConfig.getProfileName());
            }
            
            if (panicButtonKey.wasPressed() && client != null) {
                performanceManager.activatePanicMode();
                LOGGER.info("Panic mode activated! Render distance set to 2");
            }
            
            if (performanceManager != null) {
                performanceManager.tick(client);
            }
        });
        
        LOGGER.info("Naoya Hates Lag - Initialized successfully!");
    }
    
    public static NaoyaHatesLagClient getInstance() { return instance; }
    public PerformanceManager getPerformanceManager() { return performanceManager; }
    public DebugHudRenderer getDebugHudRenderer() { return debugHudRenderer; }
}

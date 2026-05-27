package com.naoya.lag;

import com.naoya.lag.config.ModConfig;
import com.naoya.lag.core.performance.PerformanceManager;
import com.naoya.lag.debug.DebugHudRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Environment(EnvType.CLIENT)
public class NaoyaHatesLagClient implements ClientModInitializer {
    public static final String MOD_ID = "naoyahateslag";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    
    private PerformanceManager performanceManager;
    private DebugHudRenderer debugHudRenderer;
    private KeyBinding cycleProfileKey;
    private KeyBinding panicButtonKey;
    private KeyBinding debugHudKey;
    private static boolean debugHudEnabled = true;
    
    @Override
    public void onInitializeClient() {
        LOGGER.info("Naoya Hates Lag - Loading with ALL 20+ optimization features!");
        
        ModConfig.init();
        performanceManager = new PerformanceManager();
        debugHudRenderer = new DebugHudRenderer();
        
        cycleProfileKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "Cycle Performance Profile",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_O,
            "Naoya Hates Lag"
        ));
        
        panicButtonKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "Panic Button (Emergency)",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_P,
            "Naoya Hates Lag"
        ));
        
        debugHudKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "Toggle Debug HUD",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_F8,
            "Naoya Hates Lag"
        ));
        
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (cycleProfileKey.wasPressed()) {
                ModConfig.cycleProfile();
                performanceManager.onProfileChanged();
                LOGGER.info("Profile: " + ModConfig.getProfileName());
            }
            
            while (panicButtonKey.wasPressed()) {
                performanceManager.activatePanicMode();
                LOGGER.info("Panic mode toggled!");
            }
            
            while (debugHudKey.wasPressed()) {
                debugHudEnabled = !debugHudEnabled;
                LOGGER.info("Debug HUD: " + (debugHudEnabled ? "ON" : "OFF"));
            }
            
            performanceManager.tick(client);
        });
        
        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            if (debugHudEnabled) {
                debugHudRenderer.render(drawContext, 10);
            }
        });
        
        LOGGER.info("Naoya Hates Lag - Ready!");
        LOGGER.info("Controls: O=Cycle Profile | P=Panic Mode | F8=Debug HUD");
    }
    
    public static boolean isDebugHudEnabled() { return debugHudEnabled; }
}

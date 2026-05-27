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
    
    private static KeyBinding panicKey;
    private static KeyBinding cycleProfileKey;
    private static KeyBinding debugHudKey;
    
    @Override
    public void onInitializeClient() {
        LOGGER.info("Naoya Hates Lag - Loading!");
        
        ModConfig.init();
        
        panicKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "Panic Mode", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_P, "Naoya Hates Lag"));
        cycleProfileKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "Cycle Profile", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_O, "Naoya Hates Lag"));
        debugHudKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "Toggle Debug HUD", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_F8, "Naoya Hates Lag"));
        
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (panicKey.wasPressed()) {
                PerformanceManager.panicMode();
                LOGGER.info("Panic mode toggled!");
            }
            while (cycleProfileKey.wasPressed()) {
                ModConfig.cycleProfile();
                PerformanceManager.onProfileChanged();
                LOGGER.info("Profile: " + ModConfig.getProfileName());
            }
            while (debugHudKey.wasPressed()) {
                DebugHudRenderer.toggle();
            }
            PerformanceManager.memorySweep();
        });
        
        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
            DebugHudRenderer.render(drawContext);
        });
        
        LOGGER.info("Ready! O=Profile, P=Panic, F8=HUD");
    }
}

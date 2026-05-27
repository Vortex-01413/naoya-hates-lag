package com.naoya.lag.debug;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class DebugKeybinds {
    private static KeyBinding toggleHudKey;
    
    public static void register() {
        toggleHudKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.naoya.debug_hud",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_F8,
            "category.naoya"
        ));
        
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (toggleHudKey.wasPressed()) {
                DebugHudRenderer.toggle();
            }
        });
    }
}

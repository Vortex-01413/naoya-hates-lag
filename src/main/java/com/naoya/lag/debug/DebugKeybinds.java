
package com.naoya.lag.debug;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

public class DebugKeybinds {

    private static KeyBinding toggleHud;

    public static void register() {
        toggleHud = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key.naoyahateslag.togglehud",
                        GLFW.GLFW_KEY_F8,
                        "Naoya Hates Lag"
                )
        );
    }

    public static void tick() {
        while (toggleHud.wasPressed()) {
            DebugHudRenderer.enabled = !DebugHudRenderer.enabled;
        }
    }
}

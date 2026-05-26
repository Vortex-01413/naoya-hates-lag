
package com.naoya.lag.config;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class SimpleConfigScreen extends Screen {

    private final Screen parent;

    protected SimpleConfigScreen(Screen parent) {
        super(Text.literal("Naoya Hates Lag Config"));
        this.parent = parent;
    }

    @Override
    protected void init() {

        addDrawableChild(ButtonWidget.builder(
                Text.literal("Entity Culling: " + ModConfig.entityCulling),
                button -> {
                    ModConfig.entityCulling = !ModConfig.entityCulling;
                    button.setMessage(Text.literal("Entity Culling: " + ModConfig.entityCulling));
                }
        ).dimensions(width / 2 - 100, 50, 200, 20).build());

        addDrawableChild(ButtonWidget.builder(
                Text.literal("Particle Limiter: " + ModConfig.particleLimiter),
                button -> {
                    ModConfig.particleLimiter = !ModConfig.particleLimiter;
                    button.setMessage(Text.literal("Particle Limiter: " + ModConfig.particleLimiter));
                }
        ).dimensions(width / 2 - 100, 80, 200, 20).build());

        addDrawableChild(ButtonWidget.builder(
                Text.literal("Adaptive Compatibility: " + ModConfig.adaptiveCompatibility),
                button -> {
                    ModConfig.adaptiveCompatibility = !ModConfig.adaptiveCompatibility;
                    button.setMessage(Text.literal("Adaptive Compatibility: " + ModConfig.adaptiveCompatibility));
                }
        ).dimensions(width / 2 - 100, 110, 200, 20).build());

        addDrawableChild(ButtonWidget.builder(
                Text.literal("Done"),
                button -> client.setScreen(parent)
        ).dimensions(width / 2 - 100, 160, 200, 20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context);
        context.drawCenteredTextWithShadow(
                textRenderer,
                title,
                width / 2,
                20,
                0xFFFFFF
        );
        super.render(context, mouseX, mouseY, delta);
    }
}

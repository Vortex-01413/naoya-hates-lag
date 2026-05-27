package com.naoya.lag.config;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class SimpleConfigScreen extends Screen {

    private final Screen parent;
    private int tab = 0;

    protected SimpleConfigScreen(Screen parent) {
        super(Text.literal("Naoya Hates Lag"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        clearChildren();

        // Tab buttons
        addDrawableChild(ButtonWidget.builder(
                Text.literal(tab == 0 ? "[Profile]" : "Profile"),
                b -> { tab = 0; init(); }
        ).dimensions(width / 2 - 150, 25, 90, 18).build());

        addDrawableChild(ButtonWidget.builder(
                Text.literal(tab == 1 ? "[Performance]" : "Performance"),
                b -> { tab = 1; init(); }
        ).dimensions(width / 2 - 55, 25, 110, 18).build());

        addDrawableChild(ButtonWidget.builder(
                Text.literal(tab == 2 ? "[Visual]" : "Visual"),
                b -> { tab = 2; init(); }
        ).dimensions(width / 2 + 60, 25, 90, 18).build());

        if (tab == 0) initProfileTab();
        else if (tab == 1) initPerformanceTab();
        else initVisualTab();

        addDrawableChild(ButtonWidget.builder(
                Text.literal("Done"),
                b -> client.setScreen(parent)
        ).dimensions(width / 2 - 75, height - 30, 150, 20).build());
    }

    private void initProfileTab() {
        addDrawableChild(ButtonWidget.builder(
                Text.literal("Device: " + ModConfig.profile.name()),
                b -> {
                    ModConfig.DeviceProfile[] vals = ModConfig.DeviceProfile.values();
                    int next = (ModConfig.profile.ordinal() + 1) % vals.length;
                    ModConfig.applyProfile(vals[next]);
                    b.setMessage(Text.literal("Device: " + ModConfig.profile.name()));
                }
        ).dimensions(width / 2 - 120, 60, 240, 20).build());

        addDrawableChild(ButtonWidget.builder(
                Text.literal("Auto Switch Profile: " + ModConfig.autoProfileSwitcher),
                b -> {
                    ModConfig.autoProfileSwitcher = !ModConfig.autoProfileSwitcher;
                    b.setMessage(Text.literal("Auto Switch Profile: " + ModConfig.autoProfileSwitcher));
                }
        ).dimensions(width / 2 - 120, 90, 240, 20).build());

        addDrawableChild(ButtonWidget.builder(
                Text.literal("Adaptive Compat: " + ModConfig.adaptiveCompatibility),
                b -> {
                    ModConfig.adaptiveCompatibility = !ModConfig.adaptiveCompatibility;
                    b.setMessage(Text.literal("Adaptive Compat: " + ModConfig.adaptiveCompatibility));
                }
        ).dimensions(width / 2 - 120, 120, 240, 20).build());

        addDrawableChild(ButtonWidget.builder(
                Text.literal("Debug HUD (F8): " + ModConfig.debugHud),
                b -> {
                    ModConfig.debugHud = !ModConfig.debugHud;
                    b.setMessage(Text.literal("Debug HUD (F8): " + ModConfig.debugHud));
                }
        ).dimensions(width / 2 - 120, 150, 240, 20).build());
    }

    private void initPerformanceTab() {
        addDrawableChild(ButtonWidget.builder(
                Text.literal("Entity Culling: " + ModConfig.entityCulling),
                b -> {
                    ModConfig.entityCulling = !ModConfig.entityCulling;
                    b.setMessage(Text.literal("Entity Culling: " + ModConfig.entityCulling));
                }
        ).dimensions(width / 2 - 120, 55, 240, 20).build());

        addDrawableChild(ButtonWidget.builder(
                Text.literal("Entity Cap: " + ModConfig.entityCap),
                b -> {
                    int[] caps = {20, 30, 60, 100, 150, 200};
                    int cur = 0;
                    for (int i = 0; i < caps.length; i++) if (caps[i] == ModConfig.entityCap) cur = i;
                    ModConfig.entityCap = caps[(cur + 1) % caps.length];
                    b.setMessage(Text.literal("Entity Cap: " + ModConfig.entityCap));
                }
        ).dimensions(width / 2 - 120, 80, 240, 20).build());

        addDrawableChild(ButtonWidget.builder(
                Text.literal("Particle Limiter: " + ModConfig.particleLimiter),
                b -> {
                    ModConfig.particleLimiter = !ModConfig.particleLimiter;
                    b.setMessage(Text.literal("Particle Limiter: " + ModConfig.particleLimiter));
                }
        ).dimensions(width / 2 - 120, 105, 240, 20).build());

        addDrawableChild(ButtonWidget.builder(
                Text.literal("Chunk Throttle: " + ModConfig.chunkThrottle),
                b -> {
                    ModConfig.chunkThrottle = !ModConfig.chunkThrottle;
                    b.setMessage(Text.literal("Chunk Throttle: " + ModConfig.chunkThrottle));
                }
        ).dimensions(width / 2 - 120, 130, 240, 20).build());

        addDrawableChild(ButtonWidget.builder(
                Text.literal("Smart Render Dist: " + ModConfig.smartRenderDistance),
                b -> {
                    ModConfig.smartRenderDistance = !ModConfig.smartRenderDistance;
                    b.setMessage(Text.literal("Smart Render Dist: " + ModConfig.smartRenderDistance));
                }
        ).dimensions(width / 2 - 120, 155, 240, 20).build());

        addDrawableChild(ButtonWidget.builder(
                Text.literal("XP Orb Clumping: " + ModConfig.xpOrbClumping),
                b -> {
                    ModConfig.xpOrbClumping = !ModConfig.xpOrbClumping;
                    b.setMessage(Text.literal("XP Orb Clumping: " + ModConfig.xpOrbClumping));
                }
        ).dimensions(width / 2 - 120, 180, 240, 20).build());

        addDrawableChild(ButtonWidget.builder(
                Text.literal("Background FPS Cap: " + ModConfig.dynamicBackgroundFps),
                b -> {
                    ModConfig.dynamicBackgroundFps = !ModConfig.dynamicBackgroundFps;
                    b.setMessage(Text.literal("Background FPS Cap: " + ModConfig.dynamicBackgroundFps));
                }
        ).dimensions(width / 2 - 120, 205, 240, 20).build());
    }

    private void initVisualTab() {
        addDrawableChild(ButtonWidget.builder(
                Text.literal("No Weather: " + ModConfig.noWeather),
                b -> {
                    ModConfig.noWeather = !ModConfig.noWeather;
                    b.setMessage(Text.literal("No Weather: " + ModConfig.noWeather));
                }
        ).dimensions(width / 2 - 120, 55, 240, 20).build());

        addDrawableChild(ButtonWidget.builder(
                Text.literal("No Fog: " + ModConfig.noFog),
                b -> {
                    ModConfig.noFog = !ModConfig.noFog;
                    b.setMessage(Text.literal("No Fog: " + ModConfig.noFog));
                }
        ).dimensions(width / 2 - 120, 80, 240, 20).build());

        addDrawableChild(ButtonWidget.builder(
                Text.literal("No Void Fog: " + ModConfig.noVoidFog),
                b -> {
                    ModConfig.noVoidFog = !ModConfig.noVoidFog;
                    b.setMessage(Text.literal("No Void Fog: " + ModConfig.noVoidFog));
                }
        ).dimensions(width / 2 - 120, 105, 240, 20).build());

        addDrawableChild(ButtonWidget.builder(
                Text.literal("Cull Leaves: " + ModConfig.cullLeaves),
                b -> {
                    ModConfig.cullLeaves = !ModConfig.cullLeaves;
                    b.setMessage(Text.literal("Cull Leaves: " + ModConfig.cullLeaves));
                }
        ).dimensions(width / 2 - 120, 130, 240, 20).build());

        addDrawableChild(ButtonWidget.builder(
                Text.literal("Reduce Clouds: " + ModConfig.reduceClouds),
                b -> {
                    ModConfig.reduceClouds = !ModConfig.reduceClouds;
                    b.setMessage(Text.literal("Reduce Clouds: " + ModConfig.reduceClouds));
                }
        ).dimensions(width / 2 - 120, 155, 240, 20).build());

        addDrawableChild(ButtonWidget.builder(
                Text.literal("No Fade Animations: " + ModConfig.noFadeAnimations),
                b -> {
                    ModConfig.noFadeAnimations = !ModConfig.noFadeAnimations;
                    b.setMessage(Text.literal("No Fade Animations: " + ModConfig.noFadeAnimations));
                }
        ).dimensions(width / 2 - 120, 180, 240, 20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        renderBackground(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(textRenderer, title, width / 2, 8, 0xFFFFFF);
        super.render(context, mouseX, mouseY, delta);
    }
}

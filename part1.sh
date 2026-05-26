#!/bin/sh

mkdir -p src/main/java/com/naoya/lag/config
mkdir -p src/main/java/com/naoya/lag/mixin/render
mkdir -p src/main/java/com/naoya/lag/mixin/entity
mkdir -p src/main/java/com/naoya/lag/mixin/particle
mkdir -p src/main/java/com/naoya/lag/mixin/world
mkdir -p src/main/java/com/naoya/lag/mixin/misc

# SimpleConfigScreen.java
cat > src/main/java/com/naoya/lag/config/SimpleConfigScreen.java << 'EOF'
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
        renderBackground(context);
        context.drawCenteredTextWithShadow(textRenderer, title, width / 2, 8, 0xFFFFFF);
        super.render(context, mouseX, mouseY, delta);
    }
}
EOF
echo "SimpleConfigScreen.java done"

# WeatherMixin.java
cat > src/main/java/com/naoya/lag/mixin/render/WeatherMixin.java << 'EOF'
package com.naoya.lag.mixin.render;

import com.naoya.lag.config.ModConfig;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.LightmapTextureManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class WeatherMixin {
    @Inject(method = "renderWeather", at = @At("HEAD"), cancellable = true)
    private void naoya$skipWeather(LightmapTextureManager manager, float tickDelta, double x, double y, double z, CallbackInfo ci) {
        if (ModConfig.noWeather) ci.cancel();
    }
}
EOF
echo "WeatherMixin.java done"

# FogMixin.java
cat > src/main/java/com/naoya/lag/mixin/render/FogMixin.java << 'EOF'
package com.naoya.lag.mixin.render;

import com.naoya.lag.config.ModConfig;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BackgroundRenderer.class)
public class FogMixin {
    @Inject(method = "applyFog", at = @At("HEAD"), cancellable = true)
    private static void naoya$noFog(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, float tickDelta, CallbackInfo ci) {
        if (ModConfig.noFog) ci.cancel();
    }
}
EOF
echo "FogMixin.java done"

# VoidFogMixin.java
cat > src/main/java/com/naoya/lag/mixin/render/VoidFogMixin.java << 'EOF'
package com.naoya.lag.mixin.render;

import com.naoya.lag.config.ModConfig;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BackgroundRenderer.class)
public class VoidFogMixin {
    @Inject(method = "applyFog", at = @At("HEAD"), cancellable = true)
    private static void naoya$noVoidFog(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, float tickDelta, CallbackInfo ci) {
        if (ModConfig.noVoidFog && fogType == BackgroundRenderer.FogType.FOG_SKY) ci.cancel();
    }
}
EOF
echo "VoidFogMixin.java done"

# CloudMixin.java
cat > src/main/java/com/naoya/lag/mixin/render/CloudMixin.java << 'EOF'
package com.naoya.lag.mixin.render;

import com.naoya.lag.config.ModConfig;
import net.minecraft.client.option.CloudRenderMode;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class CloudMixin {
    @Inject(method = "renderClouds", at = @At("HEAD"), cancellable = true)
    private void naoya$reduceClouds(net.minecraft.client.util.math.MatrixStack matrices, org.joml.Matrix4f projectionMatrix, float tickDelta, double camX, double camY, double camZ, CallbackInfo ci) {
        if (ModConfig.reduceClouds) ci.cancel();
    }
}
EOF
echo "CloudMixin.java done"

# XpOrbMixin.java
cat > src/main/java/com/naoya/lag/mixin/entity/XpOrbMixin.java << 'EOF'
package com.naoya.lag.mixin.entity;

import com.naoya.lag.config.ModConfig;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ExperienceOrbEntity.class)
public class XpOrbMixin {
    @Inject(method = "tick", at = @At("HEAD"))
    private void naoya$clumpOrbs(CallbackInfo ci) {
        if (!ModConfig.xpOrbClumping) return;
        ExperienceOrbEntity self = (ExperienceOrbEntity)(Object)this;
        World world = self.getWorld();
        world.getEntitiesByClass(ExperienceOrbEntity.class, self.getBoundingBox().expand(0.5), orb -> orb != self)
            .forEach(orb -> {
                self.addExperience(orb);
                orb.discard();
            });
    }
}
EOF
echo "XpOrbMixin.java done"

# BackgroundFpsMixin.java
cat > src/main/java/com/naoya/lag/mixin/misc/BackgroundFpsMixin.java << 'EOF'
package com.naoya.lag.mixin.misc;

import com.naoya.lag.config.ModConfig;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public class BackgroundFpsMixin {
    @Inject(method = "getFramerateLimit", at = @At("RETURN"), cancellable = true)
    private void naoya$backgroundFpsCap(CallbackInfoReturnable<Integer> cir) {
        if (!ModConfig.dynamicBackgroundFps) return;
        MinecraftClient mc = MinecraftClient.getInstance();
        if (!mc.isWindowFocused()) {
            cir.setReturnValue(ModConfig.backgroundFpsCap);
        }
    }
}
EOF
echo "BackgroundFpsMixin.java done"

# EntityCapMixin.java
cat > src/main/java/com/naoya/lag/mixin/entity/EntityCapMixin.java << 'EOF'
package com.naoya.lag.mixin.entity;

import com.naoya.lag.config.ModConfig;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderDispatcher.class)
public class EntityCapMixin {
    private static int renderedThisFrame = 0;
    private static int lastResetTick = 0;

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private <E extends Entity> void naoya$entityCap(E entity, double x, double y, double z, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        if (!ModConfig.entityCulling) return;
        int currentTick = (int)(System.currentTimeMillis() / 50);
        if (currentTick != lastResetTick) {
            renderedThisFrame = 0;
            lastResetTick = currentTick;
        }
        if (renderedThisFrame >= ModConfig.entityCap) {
            ci.cancel();
            return;
        }
        renderedThisFrame++;
    }
}
EOF
echo "EntityCapMixin.java done"

echo "ALL PART 1 FILES DONE"

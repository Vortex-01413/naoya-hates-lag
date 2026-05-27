package com.naoya.lag.config;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.text.Text;

public class SimpleConfigScreen extends Screen {
    private final Screen parent;
    
    public SimpleConfigScreen(Screen parent) {
        super(Text.literal("Naoya Hates Lag Config"));
        this.parent = parent;
    }
    
    @Override
    protected void init() {
        int y = 30;
        
        // Profile display
        addDrawableChild(new TextWidget(10, y, 300, 20, 
            Text.literal("Profile: " + ModConfig.getProfileName() + " (Press O in game to cycle)"), textRenderer));
        
        // Cycle button
        addDrawableChild(ButtonWidget.builder(Text.literal("Cycle Profile"), button -> {
                ModConfig.cycleProfile();
                this.client.setScreen(new SimpleConfigScreen(parent));
            }).dimensions(10, y + 25, 100, 20).build());
        
        y += 60;
        
        // Rendering toggles (first row)
        addDrawableChild(createToggle("Entity Culling", ModConfig.isEntityCulling(), 
            v -> ModConfig.setEntityCulling(v), 10, y));
        addDrawableChild(createToggle("Particle Limit", ModConfig.isParticleLimit(), 
            v -> ModConfig.setParticleLimit(v), 120, y));
        addDrawableChild(createToggle("Occlusion Culling", ModConfig.isOcclusionCulling(), 
            v -> ModConfig.setOcclusionCulling(v), 230, y));
        
        y += 25;
        addDrawableChild(createToggle("Shadows Off", ModConfig.isShadowsOff(), 
            v -> ModConfig.setShadowsOff(v), 10, y));
        addDrawableChild(createToggle("Clouds Off", ModConfig.isCloudsOff(), 
            v -> ModConfig.setCloudsOff(v), 120, y));
        addDrawableChild(createToggle("Smooth Lighting Off", ModConfig.isSmoothLightingOff(), 
            v -> ModConfig.setSmoothLightingOff(v), 230, y));
        
        y += 25;
        addDrawableChild(createToggle("Fast Math", ModConfig.isFastMath(), 
            v -> ModConfig.setFastMath(v), 10, y));
        addDrawableChild(createToggle("Memory Sweep", ModConfig.isMemorySweep(), 
            v -> ModConfig.setMemorySweep(v), 120, y));
        addDrawableChild(createToggle("Background FPS Cap", ModConfig.isBackgroundFpsCap(), 
            v -> ModConfig.setBackgroundFpsCap(v), 230, y));
        
        y += 25;
        addDrawableChild(createToggle("AI Throttle", ModConfig.isAiThrottle(), 
            v -> ModConfig.setAiThrottle(v), 10, y));
        addDrawableChild(createToggle("Hopper Optim.", ModConfig.isHopperOptimization(), 
            v -> ModConfig.setHopperOptimization(v), 120, y));
        addDrawableChild(createToggle("Redstone Optim.", ModConfig.isRedstoneOptimization(), 
            v -> ModConfig.setRedstoneOptimization(v), 230, y));
        
        y += 25;
        addDrawableChild(createToggle("Orb Merging", ModConfig.isOrbMerging(), 
            v -> ModConfig.setOrbMerging(v), 10, y));
        addDrawableChild(createToggle("Chunk Throttle", ModConfig.isChunkThrottle(), 
            v -> ModConfig.setChunkThrottle(v), 120, y));
        addDrawableChild(createToggle("Texture Compression", ModConfig.isTextureCompression(), 
            v -> ModConfig.setTextureCompression(v), 230, y));
        
        // Back button
        addDrawableChild(ButtonWidget.builder(Text.literal("Back"), button -> {
                this.client.setScreen(parent);
            }).dimensions(width - 100, height - 30, 90, 20).build());
    }
    
    private ButtonWidget createToggle(String name, boolean value, java.util.function.Consumer<Boolean> setter, int x, int y) {
        return ButtonWidget.builder(Text.literal(name + ": " + (value ? "ON" : "OFF")), button -> {
                setter.accept(!value);
                this.client.setScreen(new SimpleConfigScreen(parent));
            }).dimensions(x, y, 110, 20).build();
    }
    
    @Override
    public void render(net.minecraft.client.gui.DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);
        super.render(context, mouseX, mouseY, delta);
    }
}

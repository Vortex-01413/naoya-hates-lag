package com.naoya.lag.config;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
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
        
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Profile: " + ModConfig.getProfileName() + " (O key)"), button -> {
                ModConfig.cycleProfile();
                this.client.setScreen(new SimpleConfigScreen(parent));
            }).dimensions(10, y, 200, 20).build());
        
        y += 30;
        this.addDrawableChild(createToggle("Entity Culling", ModConfig.isEntityCulling(), v -> ModConfig.setEntityCulling(v), 10, y));
        this.addDrawableChild(createToggle("Shadows Off", ModConfig.isShadowsOff(), v -> ModConfig.setShadowsOff(v), 120, y));
        this.addDrawableChild(createToggle("Clouds Off", ModConfig.isCloudsOff(), v -> ModConfig.setCloudsOff(v), 230, y));
        
        y += 25;
        this.addDrawableChild(createToggle("Fast Math", ModConfig.isFastMath(), v -> ModConfig.setFastMath(v), 10, y));
        this.addDrawableChild(createToggle("Memory Sweep", ModConfig.isMemorySweep(), v -> ModConfig.setMemorySweep(v), 120, y));
        this.addDrawableChild(createToggle("Background FPS Cap", ModConfig.isBackgroundFpsCap(), v -> ModConfig.setBackgroundFpsCap(v), 230, y));
        
        this.addDrawableChild(ButtonWidget.builder(Text.literal("Back"), button -> {
                this.client.setScreen(parent);
            }).dimensions(this.width - 100, this.height - 30, 90, 20).build());
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

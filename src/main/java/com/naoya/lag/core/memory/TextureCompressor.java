package com.naoya.lag.core.memory;

import com.naoya.lag.config.ModConfig;
import net.minecraft.client.texture.AbstractTexture;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.WeakHashMap;

public class TextureCompressor {
    private static final Logger LOGGER = LoggerFactory.getLogger(TextureCompressor.class);
    private static final Map<Identifier, Integer> TEXTURE_SCALES = new WeakHashMap<>();
    
    public static NativeImage compressTexture(NativeImage image, Identifier id) {
        if (!ModConfig.isTextureCompressionEnabled()) {
            return image;
        }
        
        int targetScale = getTargetScale();
        if (targetScale <= 0) {
            return image;
        }
        
        int width = image.getWidth();
        int height = image.getHeight();
        int targetWidth = Math.max(16, width >> targetScale);
        int targetHeight = Math.max(16, height >> targetScale);
        
        if (targetWidth >= width && targetHeight >= height) {
            return image;
        }
        
        try {
            NativeImage compressed = new NativeImage(targetWidth, targetHeight, false);
            
            for (int y = 0; y < targetHeight; y++) {
                for (int x = 0; x < targetWidth; x++) {
                    int srcX = x * width / targetWidth;
                    int srcY = y * height / targetHeight;
                    compressed.setColor(x, y, image.getColor(srcX, srcY));
                }
            }
            
            TEXTURE_SCALES.put(id, targetScale);
            LOGGER.debug("Compressed texture {} from {}x{} to {}x{}", 
                id, width, height, targetWidth, targetHeight);
            
            image.close();
            return compressed;
        } catch (Exception e) {
            LOGGER.error("Failed to compress texture: {}", id, e);
            return image;
        }
    }
    
    private static int getTargetScale() {
        int profileIndex = ModConfig.getCurrentProfileIndex();
        switch (profileIndex) {
            case 0: return 3; // Potato: 1/8 scale
            case 1: return 2; // Low: 1/4 scale
            case 2: return 1; // Balanced: 1/2 scale
            case 3: return 0; // High: no compression
            case 4: return 0; // Extreme: no compression
            default: return 1;
        }
    }
}

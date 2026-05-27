package com.naoya.lag.core;

import net.minecraft.client.texture.NativeImage;

public class TextureCompressor {
    public static NativeImage downscale(NativeImage img) {
        int w = img.getWidth() / 2;
        int h = img.getHeight() / 2;
        NativeImage scaled = new NativeImage(w, h, false);
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                scaled.setPixelColor(x, y, img.getPixelColor(x*2, y*2));
            }
        }
        return scaled;
    }
}

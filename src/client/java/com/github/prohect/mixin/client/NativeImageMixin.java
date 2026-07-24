package com.github.prohect.mixin.client;

import com.github.prohect.mcp.ScreenshotCapture;
import net.minecraft.client.texture.NativeImage;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(NativeImage.class)
public class NativeImageMixin {
    // placeholder for future in-memory screenshot capture via writeToChannel hook
}

package com.github.prohect.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;

public final class McScreenHelper {
    public static Screen getCurrentScreen(MinecraftClient client) {
        return client.currentScreen;
    }

    public static void setScreen(MinecraftClient client, Screen screen) {
        client.setScreen(screen);
    }

    private McScreenHelper() {}
}

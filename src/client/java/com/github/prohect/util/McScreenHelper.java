package com.github.prohect.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;

/**
 * Bridges the screen-access API change between MC 26.1.x and 26.2+.
 * <p>
 * In 26.1.x: {@code Minecraft.screen} (field) and {@code Minecraft.setScreen(Screen)} (method).
 * In 26.2+:  both were moved into the {@code Gui} class, accessed via
 * {@code client.gui.screen()} and {@code client.gui.setScreen(Screen)}.
 * <p>
 * Detection is based on whether the {@code Gui} class (the type of
 * {@code Minecraft.gui}) exposes a {@code screen()} method.
 */
public final class McScreenHelper {

    private static final boolean GUI_HAS_SCREEN;
    private static final Field GUI_FIELD;
    private static final Method GUI_SCREEN;
    private static final Method GUI_SET_SCREEN;
    private static final Field MINECRAFT_SCREEN;
    private static final Method MINECRAFT_SET_SCREEN;

    static {
        Field guiField = null;
        Method guiScreen = null;
        Method guiSetScreen = null;
        boolean hasGuiScreen = false;

        try {
            guiField = Minecraft.class.getDeclaredField("gui");
            guiField.setAccessible(true);
            Class<?> guiClass = guiField.getType();
            guiScreen = guiClass.getMethod("screen");
            guiSetScreen = guiClass.getMethod("setScreen", Screen.class);
            hasGuiScreen = true;
        } catch (Exception ignored) {
            // 26.1.x: Gui class doesn't have screen() — use Minecraft directly
        }

        GUI_FIELD = guiField;
        GUI_SCREEN = guiScreen;
        GUI_SET_SCREEN = guiSetScreen;
        GUI_HAS_SCREEN = hasGuiScreen;

        Field screenField = null;
        Method setScreenM = null;
        if (!hasGuiScreen) {
            try {
                screenField = Minecraft.class.getDeclaredField("screen");
                screenField.setAccessible(true);
            } catch (Exception ignored) {}
            try {
                setScreenM = Minecraft.class.getMethod(
                    "setScreen",
                    Screen.class
                );
            } catch (Exception ignored) {}
        }
        MINECRAFT_SCREEN = screenField;
        MINECRAFT_SET_SCREEN = setScreenM;
    }

    public static Screen getCurrentScreen(Minecraft client) {
        try {
            if (GUI_HAS_SCREEN) {
                Object gui = GUI_FIELD.get(client);
                return (Screen) GUI_SCREEN.invoke(gui);
            }
            return (Screen) MINECRAFT_SCREEN.get(client);
        } catch (Exception e) {
            throw new RuntimeException("Failed to get current screen", e);
        }
    }

    public static void setScreen(Minecraft client, Screen screen) {
        try {
            if (GUI_HAS_SCREEN) {
                Object gui = GUI_FIELD.get(client);
                GUI_SET_SCREEN.invoke(gui, screen);
            } else {
                MINECRAFT_SET_SCREEN.invoke(client, screen);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to set screen", e);
        }
    }

    private McScreenHelper() {}
}

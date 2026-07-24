package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.inventory.ContainerInput;
import net.minecraft.world.inventory.Slot;

public class DropAlias extends BuiltinAliasWithBooleanArgs<DropAlias> {

    /** Ticks to wait after press before continuous drops begin. */
    private static final int INITIAL_DELAY_TICKS = 3;
    /** Ticks elapsed since the last press while the key is held. */
    private long ticksHeld;

    public DropAlias() {
        super("builtinDrop");
    }

    @Override
    public DropAlias run(String args) {
        parseArgs(args);
        if (Alias.isUnderTextInputScreen() && flag)
            return this;
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null)
            return this;
        KeyMapping dropKey = mc.options.keyDrop;

        if (flag) {
            Screen screen = Alias.getCurrentScreen();
            if (screen instanceof AbstractContainerScreen<?> containerScreen) {
                // Container: one immediate drop now; tickDrop() handles
                // continuous after the initial delay.
                Slot hoveredSlot = containerScreen.hoveredSlot;
                if (hoveredSlot != null && hoveredSlot.hasItem()) {
                    int button = mc.hasControlDown() ? 1 : 0;
                    containerScreen.slotClicked(hoveredSlot, hoveredSlot.index, button, ContainerInput.THROW);
                }
                return this;
            }

            // 3D game: immediate first drop via clickCount; tickDrop()
            // drives the rest after the delay.
            dropKey.setDown(true);
            dropKey.clickCount++;
            return this;
        }

        // Release
        ticksHeld = 0;
        dropKey.setDown(false);
        return this;
    }

    /**
     * Called every client tick from {@code MinecraftClientMixin} while {@link #flag} is {@code true}. After an initial delay
     * (matching the OS key-repeat gap that vanilla relies on), drives continuous dropping — {@code slotClicked(…, THROW)} in
     * container screens, {@code keyDrop.clickCount++} in the 3D game.
     */
    public void tickDrop() {
        if (!flag)
            return;
        ticksHeld++;
        if (ticksHeld <= INITIAL_DELAY_TICKS)
            return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null)
            return;
        Screen screen = Alias.getCurrentScreen();
        if (screen instanceof AbstractContainerScreen<?> containerScreen) {
            Slot hoveredSlot = containerScreen.hoveredSlot;
            if (hoveredSlot != null && hoveredSlot.hasItem()) {
                int button = mc.hasControlDown() ? 1 : 0;
                containerScreen.slotClicked(hoveredSlot, hoveredSlot.index, button, ContainerInput.THROW);
            }
        } else if (screen == null) {
            mc.options.keyDrop.clickCount++;
        }
    }

    /**
     * On cursor re-lock, maintain the KeyMapping state without incrementing clickCount — otherwise an extra drop would fire.
     */
    @Override
    public void reapplyToGameKeyMapping() {
        if (this.flag) {
            Minecraft.getInstance().options.keyDrop.setDown(true);
        }
    }
}

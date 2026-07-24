package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;

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
        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null)
            return this;
        KeyBinding dropKey = mc.options.dropKey;

        if (flag) {
            Screen screen = Alias.getCurrentScreen();
            if (screen instanceof HandledScreen<?> handledScreen) {
                // Container: one immediate drop now; tickDrop() handles
                // continuous after the initial delay.
                Slot focusedSlot = handledScreen.focusedSlot;
                if (focusedSlot != null && focusedSlot.hasStack()) {
                    int button = mc.isCtrlPressed() ? 1 : 0;
                    handledScreen.onMouseClick(focusedSlot, focusedSlot.id, button, SlotActionType.THROW);
                }
                return this;
            }

            // 3D game: immediate first drop via timesPressed; tickDrop()
            // drives the rest after the delay.
            dropKey.setPressed(true);
            dropKey.timesPressed++;
            return this;
        }

        // Release
        ticksHeld = 0;
        dropKey.setPressed(false);
        return this;
    }

    /**
     * Called every client tick from {@code MinecraftClientMixin} while {@link #flag} is {@code true}. After an initial delay,
     * drives continuous dropping — {@code onMouseClick(…, THROW)} in handled screens, {@code dropKey.timesPressed++} in the 3D
     * game.
     */
    public void tickDrop() {
        if (!flag)
            return;
        ticksHeld++;
        if (ticksHeld <= INITIAL_DELAY_TICKS)
            return;

        MinecraftClient mc = MinecraftClient.getInstance();
        if (mc.player == null)
            return;
        Screen screen = Alias.getCurrentScreen();
        if (screen instanceof HandledScreen<?> handledScreen) {
            Slot focusedSlot = handledScreen.focusedSlot;
            if (focusedSlot != null && focusedSlot.hasStack()) {
                int button = mc.isCtrlPressed() ? 1 : 0;
                handledScreen.onMouseClick(focusedSlot, focusedSlot.id, button, SlotActionType.THROW);
            }
        } else if (screen == null) {
            mc.options.dropKey.timesPressed++;
        }
    }

    /**
     * On cursor re-lock, maintain the KeyBinding state without incrementing timesPressed — otherwise an extra drop would fire.
     */
    @Override
    public void reapplyToGameKeyMapping() {
        if (this.flag) {
            MinecraftClient.getInstance().options.dropKey.setPressed(true);
        }
    }
}

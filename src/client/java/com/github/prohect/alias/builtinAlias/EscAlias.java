package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithIntegerArgs;
import net.minecraft.client.Minecraft;

/**
 * Closes the current screen or toggles the pause menu.
 * <p>
 * Args: {@code \0} = close screen only (no-op if none), {@code \1} = toggle — close screen if open, open pause menu if in a
 * world.
 * <p>
 * User-facing shortcuts: {@code +esc} (toggle), {@code -esc} (close only).
 */
public class EscAlias extends BuiltinAliasWithIntegerArgs<EscAlias> {

    public EscAlias() {
        super("builtinEsc");
    }

    @Override
    public EscAlias run(String args) {
        parseArgs(args);
        Minecraft mc = Minecraft.getInstance();

        if (Alias.isUnderAnyScreen()) {
            Alias.getCurrentScreen().onClose();
            return this;
        }

        if (flag == 1 && mc.player != null) {
            // toggle mode: no screen + in world → open pause
            mc.pauseGame(false);
        }
        // flag == 0: close-only, nothing to do
        return this;
    }
}

package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;
import net.minecraft.client.Minecraft;

/**
 * Toggles a flag that keeps the OS cursor free while the game behaves as if it were grabbed. When active,
 * {@code MouseMixin} skips only the OS-level grab call inside {@code grabMouse()}/{@code releaseMouse()}, so the
 * logical {@code mouseGrabbed} flag still turns on — hold-to-mine ({@code continueAttack}) keeps working while
 * the host cursor stays usable for a better dev/test experience.
 * <p>
 * Usage: {@code +freeCursor} to enable, {@code -freeCursor} to disable.
 */
public class FreeCursorAlias extends BuiltinAliasWithBooleanArgs<FreeCursorAlias> {

    public FreeCursorAlias() {
        super("builtinFreeCursor");
    }

    /** Read by MouseMixin — when true, the OS-level cursor grab is skipped. */
    public static boolean freeCursor;

    @Override
    public FreeCursorAlias run(String args) {
        parseArgs(args);
        if (!flag && freeCursor) {
            // On -> off transition after a faked grab: drop the logical grab while freeCursor is still on, so
            // MouseMixin also skips the OS-level call inside releaseMouse (no physical cursor jump). With
            // mouseGrabbed false again, the next real grabMouse() re-applies the OS-level grab. Guarded on the
            // previous state so a stray -freeCursor never releases a real grab; no-op when not grabbed.
            Minecraft.getInstance().mouseHandler.releaseMouse();
        }
        freeCursor = flag;
        return this;
    }
}

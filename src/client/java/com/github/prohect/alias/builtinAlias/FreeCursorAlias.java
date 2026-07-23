package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;

/**
 * Toggles a flag that prevents Minecraft from grabbing the mouse cursor.
 * When active, {@code MouseMixin} cancels {@code grabMouse()} at HEAD,
 * keeping the cursor free for a better dev/test experience.
 * <p>
 * Usage: {@code +freeCursor} to enable, {@code -freeCursor} to disable.
 */
public class FreeCursorAlias
    extends BuiltinAliasWithBooleanArgs<FreeCursorAlias> {

    public FreeCursorAlias() {
        super("builtinFreeCursor");
    }

    /** Read by MouseMixin — when true, grabMouse() is cancelled. */
    public static boolean freeCursor;

    @Override
    public FreeCursorAlias run(String args) {
        parseArgs(args);
        freeCursor = flag;
        return this;
    }
}

package com.github.prohect.alias;

import com.github.prohect.BindAliasClient;
import com.github.prohect.alias.builtinAlias.VarAlias;

public abstract class BuiltinAliasWithIntegerArgs<T extends BuiltinAliasWithIntegerArgs<T>> extends BuiltinAliasWithArgs<T> {

    protected BuiltinAliasWithIntegerArgs(String builtinAliasName) {
        super(builtinAliasName);
    }

    public int flag;

    /**
     * @param args 0->key up, or false, 1->key down, or true
     */
    public void parseArgs(String args) {
        int flag = 0;
        // Try variable resolution first, fall back to direct parse
        Integer resolved = VarAlias.resolveInt(args);
        if (resolved != null) {
            flag = resolved;
        } else {
            try {
                flag = Integer.parseInt(args);
            } catch (NumberFormatException e) {
                BindAliasClient.LOGGER.error("{}{}", BindAliasClient.tickPrefix(), e.getMessage(), e);
            }
        }
        this.flag = flag;
    }
}

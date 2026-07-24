package com.github.prohect.alias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.builtinAlias.VarAlias;

public abstract class BuiltinAliasWithDoubleArgs<T extends BuiltinAliasWithDoubleArgs<T>> extends BuiltinAliasWithArgs<T> {

    protected BuiltinAliasWithDoubleArgs(String builtinAliasName) {
        super(builtinAliasName);
    }

    public double flag;

    /**
     * @param args 0->key up, or false, 1->key down, or true
     */
    public void parseArgs(String args) {
        double flag = 0;
        // Try variable resolution first, fall back to direct parse
        Double resolved = VarAlias.resolveDouble(args);
        if (resolved != null) {
            flag = resolved;
        } else {
            try {
                flag = Double.parseDouble(args);
            } catch (NumberFormatException e) {
                BindAliasPlusClient.LOGGER.error("{}{}", BindAliasPlusClient.tickPrefix(), e.getMessage(), e);
            }
        }
        this.flag = flag;
    }
}

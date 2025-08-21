package com.github.prohect.alias;

import com.github.prohect.BindAliasPlusClient;

public abstract class BuiltinAliasWithDoubleArgs<T extends BuiltinAliasWithDoubleArgs<T>> extends BuiltinAliasWithArgs<T> {
    public double flag;

    /**
     * @param args 0->key up, or false, 1->key down, or true
     */
    public void parseArgs(String args) {
        double flag = 0;
        try {
            flag = Double.parseDouble(args);
        } catch (NumberFormatException e) {
            BindAliasPlusClient.LOGGER.error(e.getMessage(), e);
        }
        this.flag = flag;
    }
}

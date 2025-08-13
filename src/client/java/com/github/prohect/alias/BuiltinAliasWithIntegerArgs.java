package com.github.prohect.alias;

import com.github.prohect.BindAliasPlusClient;

public abstract class BuiltinAliasWithIntegerArgs extends BuiltinAliasWithArgs {
    public int flag;

    /**
     * @param args 0->key up, or false, 1->key down, or true
     */
    public void parseArgs(String args) {
        int flag = 0;
        try {
            flag = Integer.parseInt(args);
        } catch (NumberFormatException e) {
            BindAliasPlusClient.LOGGER.error(e.getMessage(), e);
        }
        this.flag = flag;
    }
}

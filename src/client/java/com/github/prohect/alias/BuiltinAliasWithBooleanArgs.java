package com.github.prohect.alias;

import com.github.prohect.BindAliasPlusClient;

public abstract class BuiltinAliasWithBooleanArgs<
    T extends BuiltinAliasWithBooleanArgs<T>
> extends BuiltinAliasWithArgs<T> {

    public boolean flag;

    /**
     * @param args 0->key up, or false, 1->key down, or true
     */
    public void parseArgs(String args) {
        boolean flag = false;
        switch (args) {
            case "0":
                break;
            case "1":
                flag = true;
                break;
            default:
                BindAliasPlusClient.LOGGER.warn("[Attack]Invalid arguments");
                break;
        }
        this.flag = flag;
    }
}

package com.github.prohect.alias;

import com.github.prohect.BindAliasPlusClient;

/**
 * consider whether you need to cancel the press event from text input screen
 */
public abstract class BuiltinAliasWithBooleanArgs<T extends BuiltinAliasWithBooleanArgs<T>> extends BuiltinAliasWithArgs<T> {

    protected BuiltinAliasWithBooleanArgs(String builtinAliasName) {
        super(builtinAliasName);
    }

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
                BindAliasPlusClient.LOGGER.warn(
                    "{}[" + this.builtinAliasName + "]Invalid arguments",
                    BindAliasPlusClient.tickPrefix()
                );
                break;
        }
        this.flag = flag;
    }

    public void reapplyToGameKeyMapping() {
        if (this.flag) {
            this.run("1");
        }
    }
}

package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;

/**
 * Builtin alias to control silent mode (suppress feedback messages)
 * Usage: builtinSilent\1 to enable, builtinSilent\0 to disable
 * Shortcuts: +silent to enable, -silent to disable
 */
public class SilentAlias extends BuiltinAliasWithBooleanArgs<SilentAlias> {

    @Override
    public SilentAlias run(String args) {
        parseArgs(args);
        BindAliasPlusClient.silentMode = flag;
        return this;
    }
}

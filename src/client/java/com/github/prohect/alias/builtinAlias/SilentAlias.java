package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.BuiltinAliasWithBooleanArgs;

/**
 * Builtin alias to control silent mode (suppress feedback messages) Usage: builtinSilent\1 to enable, builtinSilent\0 to
 * disable Shortcuts: +silent to enable, -silent to disable
 */
public class SilentAlias extends BuiltinAliasWithBooleanArgs<SilentAlias> {

    public SilentAlias() {
        super("builtinSilent");
    }

    @Override
    public SilentAlias run(String args) {
        parseArgs(args);
        // this is not a game operation, so we don't need to cancel press events from
        // text input screen
        BindAliasPlusClient.silentMode = flag;
        return this;
    }
}

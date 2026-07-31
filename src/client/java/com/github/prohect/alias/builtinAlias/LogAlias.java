package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasClient;
import com.github.prohect.alias.BuiltinAliasWithArgs;

public class LogAlias extends BuiltinAliasWithArgs<LogAlias> {

    public LogAlias() {
        super("log");
    }

    @Override
    public LogAlias run(String args) {
        BindAliasClient.LOGGER.info("{}{}", BindAliasClient.tickPrefix(), args);
        return this;
    }
}

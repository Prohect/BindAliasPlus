package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.BuiltinAliasWithArgs;

public class LogAlias extends BuiltinAliasWithArgs<LogAlias> {

    public LogAlias() {
        super("log");
    }

    @Override
    public LogAlias run(String args) {
        BindAliasPlusClient.LOGGER.info("{}{}", BindAliasPlusClient.tickPrefix(), args);
        return this;
    }
}

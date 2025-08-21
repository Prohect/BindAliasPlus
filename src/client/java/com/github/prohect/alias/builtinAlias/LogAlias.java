package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.BuiltinAliasWithArgs;

public class LogAlias extends BuiltinAliasWithArgs<LogAlias> {

    @Override
    public LogAlias run(String args) {
        BindAliasPlusClient.LOGGER.info(args);
        return this;
    }
}

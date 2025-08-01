package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.BuiltinAliasWithoutArgs;

public class HelloWorldAlias extends BuiltinAliasWithoutArgs {
    @Override
    public void run(String args) {
        BindAliasPlusClient.LOGGER.info("Hello World!");
    }
}

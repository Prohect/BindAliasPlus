package com.github.prohect.alias;

public interface AliasWithoutArgs extends Alias {
    @SuppressWarnings("UnusedReturnValue")
    default AliasWithoutArgs putToAliasesWithoutArgs(String key) {
        Alias.aliasesWithoutArgs.put(key, this);
        return this;
    }
}

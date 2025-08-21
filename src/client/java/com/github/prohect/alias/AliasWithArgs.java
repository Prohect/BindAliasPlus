package com.github.prohect.alias;

/**
 * only builtinAlias should have valid args
 */
public interface AliasWithArgs extends Alias {
    default AliasWithArgs putToAliasesWithArgs(String key) {
        Alias.aliasesWithArgs.put(key, this);
        return this;
    }
}

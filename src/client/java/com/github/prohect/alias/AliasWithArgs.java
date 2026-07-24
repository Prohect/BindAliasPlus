package com.github.prohect.alias;

/**
 * only builtinAlias should have valid args
 */
public interface AliasWithArgs<T extends AliasWithArgs<T>> extends Alias<T> {
    @SuppressWarnings("unchecked")
    default T putToAliasesWithArgs(String key) {
        Alias.aliasesWithArgs.put(key, this);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    default T putToAliasesWithArgs_notSuggested(String key) {
        Alias.aliasesWithArgs_notSuggested.put(key, this);
        return (T) this;
    }
}

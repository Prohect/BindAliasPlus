package com.github.prohect.alias;

import org.jetbrains.annotations.NotNull;

/**
 * @param alias get from Aliases.aliasesWithoutArgs.get(aliasName)
 * @param args  only builtin aliasWithArgs should have valid one, else empty(not null)
 */
public record AliasWithArgsRecord(Alias alias, @NotNull String args) {
}

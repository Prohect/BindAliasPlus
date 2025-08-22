package com.github.prohect.alias;

import org.jetbrains.annotations.NotNull;

/**
 * alias would be got from Aliases.aliasesWithoutArgs.get(aliasName)
 *
 * @param args only builtin aliasWithArgs should have valid one, else empty(not null)
 */
public record AliasRecord(@NotNull String args, @NotNull String aliasName) {
}

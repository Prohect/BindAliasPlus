package com.github.prohect;

/**
 * only aliasWithoutArgs could be triggered by a key event because it contains no args
 */
public record KeyBindingPlus(
    String aliasNameOnKeyPressed,
    String aliasNameOnKeyReleased
) {}

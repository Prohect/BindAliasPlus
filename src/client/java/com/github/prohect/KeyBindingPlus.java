package com.github.prohect;


import com.github.prohect.alias.AliasWithoutArgs;

/**
 * only aliasWithoutArgs could be triggered by a key event because it contains no args
 */
public record KeyBindingPlus(AliasWithoutArgs keyPressed, AliasWithoutArgs keyReleased) {
}

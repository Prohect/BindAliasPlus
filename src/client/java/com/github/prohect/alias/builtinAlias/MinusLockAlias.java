package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.BuiltinAliasWithArgs;

/**
 * User-facing unlock alias — {@code -lock\attack} unlocks the attack key, etc.
 * <p>
 * Registered in the suggested aliases-with-args map so command completion
 * offers action-type suggestions after {@code -lock\}.
 *
 * @see LockAlias
 * @see PlusLockAlias
 */
public class MinusLockAlias extends BuiltinAliasWithArgs<MinusLockAlias> {

    @Override
    public MinusLockAlias run(String actionType) {
        LockAlias.unlockAction(actionType);
        return this;
    }
}

package com.github.prohect.alias.builtinAlias;

import com.github.prohect.alias.BuiltinAliasWithArgs;

/**
 * User-facing lock alias — {@code +lock\attack} locks the attack key, etc.
 * <p>
 * Registered in the suggested aliases-with-args map so command completion
 * offers action-type suggestions after {@code +lock\}.
 *
 * @see LockAlias
 * @see MinusLockAlias
 */
public class PlusLockAlias extends BuiltinAliasWithArgs<PlusLockAlias> {

    @Override
    public PlusLockAlias run(String actionType) {
        LockAlias.lockAction(actionType);
        return this;
    }
}

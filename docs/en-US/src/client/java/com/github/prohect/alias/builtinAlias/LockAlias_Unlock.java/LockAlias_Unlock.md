# LockAlias_Unlock (src/client/java/com/github/prohect/alias/builtinAlias/LockAlias_Unlock.java)

User-facing unlock-key alias. Wraps `LockAlias.unlockAction()` for the `-lockKey` switch form. Inverse of `LockAlias_OnLock`.

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.LockAlias_Unlock extends com.github.prohect.alias.BuiltinAliasWithArgs<com.github.prohect.alias.builtinAlias.LockAlias_Unlock>
```

## Static Initializer

_None._

## Remarks

Registered as `"-lockKey"`. Usage:
- `-lockKey\gameKey:attack` — unlocks the vanilla attack key
- `-lockKey\myAlias` — unlocks physical keys bound to the custom alias `myAlias`

This is a thin wrapper: `run(actionType)` simply calls `LockAlias.unlockAction(actionType)`, which dispatches to either key-binding restoration (for vanilla keys) or `unlockAliasByName()` (for custom aliases).

## See Also

| Item | Description |
|------|-------------|
| [LockAlias_OnLock](../LockAlias_OnLock.java/LockAlias_OnLock.md) | Inverse `+lockKey` wrapper |
| [LockAlias](../LockAlias.java/LockAlias.md) | Core lock implementation |
| [LockAlias.unlockAction()](../LockAlias.java/unlockAction.md) | The method this delegates to |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*

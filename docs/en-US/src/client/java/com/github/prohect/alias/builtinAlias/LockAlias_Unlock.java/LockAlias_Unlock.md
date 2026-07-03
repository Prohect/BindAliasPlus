# LockAlias_Unlock (src/client/java/com/github/prohect/alias/builtinAlias/LockAlias_Unlock.java)

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.LockAlias_Unlock extends com.github.prohect.alias.BuiltinAliasWithArgs<com.github.prohect.alias.builtinAlias.LockAlias_Unlock>
```

## Static Initializer

_None._

## Remarks

User-facing unlock-key alias — inverse of `LockAlias_OnLock`. Registered as `-lockKey`.

**Purpose**: Thin wrapper around `LockAlias.unlockAction()`. Restores a previously locked vanilla game key or alias-bound physical keys.

**Lifecycle**: Singleton — registered once via `Alias.aliasesWithArgs` at startup.

**Thread safety**: Not thread-safe (render-thread only). Delegates all logic to `LockAlias.unlockAction()`.

**Key collaborators**: Delegates to `[LockAlias](LockAlias.java/LockAlias.md)`. Command suggestions mirror those of `LockAlias_OnLock`.

## See Also

| Item                                                                            | Description                             |
| ------------------------------------------------------------------------------- | --------------------------------------- |
| [LockAlias](../LockAlias.java/LockAlias.md)                                     | Core lock implementation with all logic |
| [LockAlias_OnLock](../LockAlias_OnLock.java/LockAlias_OnLock.md)                | Inverse — lock shortcut (`+lockKey`)    |
| [BuiltinAliasWithArgs](../../BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | Parent class                            |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_

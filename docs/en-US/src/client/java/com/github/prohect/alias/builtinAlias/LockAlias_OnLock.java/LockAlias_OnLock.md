# LockAlias_OnLock (src/client/java/com/github/prohect/alias/builtinAlias/LockAlias_OnLock.java)

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.LockAlias_OnLock extends com.github.prohect.alias.BuiltinAliasWithArgs<com.github.prohect.alias.builtinAlias.LockAlias_OnLock>
```

## Static Initializer

_None._

## Remarks

User-facing lock-key alias providing a friendlier syntax than the raw `builtinLock` command. Registered as `+lockKey`.

**Purpose**: Thin wrapper around `LockAlias.lockAction()`. Accepts either a `gameKey:`-prefixed vanilla action (e.g. `gameKey:attack`) or a custom UserAlias name (e.g. `myAlias`).

**Lifecycle**: Singleton — registered once via `Alias.aliasesWithArgs` at startup.

**Thread safety**: Not thread-safe (render-thread only). Delegates all logic to `LockAlias.lockAction()`.

**Key collaborators**: Delegates to `[LockAlias](LockAlias.java/LockAlias.md)`. Command suggestions are provided for both `gameKey:*` actions (`LockAlias.SUPPORTED_ACTIONS`) and UserAlias names.

## See Also

| Item                                                                            | Description                             |
| ------------------------------------------------------------------------------- | --------------------------------------- |
| [LockAlias](../LockAlias.java/LockAlias.md)                                     | Core lock implementation with all logic |
| [LockAlias_Unlock](../LockAlias_Unlock.java/LockAlias_Unlock.md)                | Inverse — unlock shortcut (`-lockKey`)  |
| [BuiltinAliasWithArgs](../../BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | Parent class                            |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_

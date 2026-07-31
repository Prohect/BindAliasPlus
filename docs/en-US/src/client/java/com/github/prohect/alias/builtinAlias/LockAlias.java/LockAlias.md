# LockAlias (src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java)

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.LockAlias extends com.github.prohect.alias.BuiltinAliasWithArgs<com.github.prohect.alias.builtinAlias.LockAlias>
```

## Static Initializer

_See [static-init](static-init.md)._

## Remarks

Builtin alias to temporarily lock a vanilla game key or custom UserAlias, preventing the user's physical keyboard/mouse input from interfering with an alias sequence.

**Purpose**: Provides two locking mechanisms — (1) vanilla game-key locking by replacing the `KeyMapping` with a sentinel key, and (2) alias-name locking by adding bound physical keys to a blocked set checked by mixins. Both mechanisms can be active simultaneously.

**Lifecycle**: Singleton — registered once via `Alias.aliasesWithArgs` at startup. All key lock state is static, surviving across world joins/disconnects until `clearAllLocks()` is called on server disconnect.

**Thread safety**: Not thread-safe (render-thread only). All state is static and mutated from the render thread. No concurrent access expected.

**Key collaborators**: Works with `LockAlias_OnLock` / `LockAlias_Unlock` (user-facing shortcuts), `BindAliasClient.BINDING_PLUS` (for alias-key lookups), and `KeyMapping`/`InputConstants` (for vanilla key manipulation). Mixins check `LOCKED_PHYSICAL_KEYS` to suppress blocked input.

## See Also

| Item                                                                            | Description                              |
| ------------------------------------------------------------------------------- | ---------------------------------------- |
| [LockAlias_OnLock](../LockAlias_OnLock.java/LockAlias_OnLock.md)                | User-facing lock shortcut (`+lockKey`)   |
| [LockAlias_Unlock](../LockAlias_Unlock.java/LockAlias_Unlock.md)                | User-facing unlock shortcut (`-lockKey`) |
| [BuiltinAliasWithArgs](../../BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md) | Parent class                             |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_

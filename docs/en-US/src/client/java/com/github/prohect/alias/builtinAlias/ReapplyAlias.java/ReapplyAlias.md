# ReapplyAlias (src/client/java/com/github/prohect/alias/builtinAlias/ReapplyAlias.java)

## Syntax

```java
public class com.github.prohect.alias.builtinAlias.ReapplyAlias extends com.github.prohect.alias.BuiltinAliasWithArgs<com.github.prohect.alias.builtinAlias.ReapplyAlias>
```

## Static Initializer

_None._

## Remarks

Builtin alias to manually re-assert a single held-down boolean alias after a screen transition or cursor re-lock. Registered as `reapply`.

**Purpose**: When a screen opens and closes, held-down game keys (attack, forward, drop, etc.) may lose their `KeyMapping.setDown(true)` state. This alias calls `reapplyToGameKeyMapping()` on the corresponding builtin alias to restore that state without firing an extra action (e.g., without incrementing `clickCount`).

**Lifecycle**: Singleton — registered once via `Alias.aliasesWithArgs` at startup.

**Thread safety**: Not thread-safe (render-thread only).

**Key collaborators**: Looks up builtin aliases by name in `Alias.aliasesWithArgs` and `Alias.aliasesWithArgs_notSuggested`. Calls `BuiltinAliasWithBooleanArgs.reapplyToGameKeyMapping()`.

## See Also

| Item                                                                                                 | Description                    |
| ---------------------------------------------------------------------------------------------------- | ------------------------------ |
| [BuiltinAliasWithBooleanArgs](../../BuiltinAliasWithBooleanArgs.java/BuiltinAliasWithBooleanArgs.md) | Parent of the target aliases   |
| [DropAlias.reapplyToGameKeyMapping](../DropAlias.java/reapplyToGameKeyMapping.md)                    | Example reapply implementation |
| [BuiltinAliasWithArgs](../../BuiltinAliasWithArgs.java/BuiltinAliasWithArgs.md)                      | Parent class                   |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_

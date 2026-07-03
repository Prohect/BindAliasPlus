# run method (src/client/java/com/github/prohect/alias/builtinAlias/ReapplyAlias.java)

## Syntax

```java
public com.github.prohect.alias.Alias run(java.lang.String)
```

## Parameters

| Name   | Type     | Description                                                                           |
| ------ | -------- | ------------------------------------------------------------------------------------- |
| `args` | `String` | Action name (e.g. `forward`, `attack`, `+forward`, `sneak`, `drop`, `openInventory`). |

## Remarks

Reasserts the held-down state of a boolean alias by calling its `reapplyToGameKeyMapping()` method, but only if the alias is currently held (`flag == true`).

**Algorithm**:

1. If `args` is null/blank, log warning and return.
2. Strip `+`/`-` prefix from the action name.
3. Derive the builtin name: `"builtin" + capitalize(cleanName)` (e.g. `forward` → `builtinForward`).
4. Look up the builtin in `Alias.aliasesWithArgs` and `Alias.aliasesWithArgs_notSuggested`.
5. If found, is a `BuiltinAliasWithBooleanArgs`, and `flag` is true: call `b.reapplyToGameKeyMapping()`.
6. Otherwise log a warning.

**Side effects**: Calls `reapplyToGameKeyMapping()` on the target builtin, which typically restores `KeyMapping.setDown(true)` without incrementing `clickCount`.

**Callers**: Invoked by the alias dispatch system, typically at the end of a UserAlias sequence or after screen transitions.

## See Also

| Item                                                                              | Description    |
| --------------------------------------------------------------------------------- | -------------- |
| [DropAlias.reapplyToGameKeyMapping](../DropAlias.java/reapplyToGameKeyMapping.md) | Example target |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_

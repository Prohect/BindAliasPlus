# run method (src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java)

## Syntax

```java
public com.github.prohect.alias.Alias run(java.lang.String)
```

## Parameters

| Name   | Type     | Description                                                                                                                                     |
| ------ | -------- | ----------------------------------------------------------------------------------------------------------------------------------------------- |
| `args` | `String` | Two tokens separated by `Alias.divider4AliasArgs`: the action type (e.g. `gameKey:attack` or alias name) and a flag (`1` = lock, `0` = unlock). |

## Remarks

Dispatches to `lockAction()` or `unlockAction()` based on the flag value.

**Algorithm**:

1. Split `args` by the alias argument divider.
2. If exactly 2 parts: parse part 0 as the action type, part 1 as the flag.
3. If `"1"` → call `lockAction(actionType)`; if `"0"` → call `unlockAction(actionType)`.
4. Logs a warning if the argument count is not 2.

**Side effects**: Modifies vanilla `KeyMapping` bindings (replacing keys with a sentinel) and/or adds physical keys to `LOCKED_PHYSICAL_KEYS`. Calls `KeyMapping.resetMapping()` to propagate changes.

**Callers**: Called by `LockAlias_OnLock.run()`, `LockAlias_Unlock.run()`, or directly via user alias definitions.

**Error handling**: Logs warning on malformed args; returns `this` without action.

## See Also

| Item                            | Description           |
| ------------------------------- | --------------------- |
| [lockAction](lockAction.md)     | Called when flag == 1 |
| [unlockAction](unlockAction.md) | Called when flag == 0 |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_

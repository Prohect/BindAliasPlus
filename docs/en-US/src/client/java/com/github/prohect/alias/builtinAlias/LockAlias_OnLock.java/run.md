# run method (src/client/java/com/github/prohect/alias/builtinAlias/LockAlias_OnLock.java)

## Syntax

```java
public com.github.prohect.alias.Alias run(java.lang.String)
```

## Parameters

| Name         | Type     | Description                                                                                                                           |
| ------------ | -------- | ------------------------------------------------------------------------------------------------------------------------------------- |
| `actionType` | `String` | The action to lock — either a `gameKey:`-prefixed vanilla action (e.g. `gameKey:attack`) or a custom UserAlias name (e.g. `myAlias`). |

## Remarks

Locks the specified action by delegating to `LockAlias.lockAction(actionType)`. The entire argument string is passed as-is to the shared lock logic.

**Algorithm**: Single delegation call — `LockAlias.lockAction(actionType)`.

**Side effects**: See `[LockAlias.lockAction](../LockAlias.java/lockAction.md)` — may replace vanilla `KeyMapping` keys with a sentinel, block physical keys in `LOCKED_PHYSICAL_KEYS`, and/or lock mod-bound keys.

**Callers**: Invoked by the alias dispatch system when a user types `+lockKey\gameKey:attack` or when a UserAlias definition contains `+lockKey\someAction`.

**Error handling**: All validation and error handling is inside `LockAlias.lockAction()`.

## See Also

| Item                                                    | Description              |
| ------------------------------------------------------- | ------------------------ |
| [LockAlias.lockAction](../LockAlias.java/lockAction.md) | Delegated implementation |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_

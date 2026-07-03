# run method (src/client/java/com/github/prohect/alias/builtinAlias/LockAlias_Unlock.java)

## Syntax

```java
public com.github.prohect.alias.Alias run(java.lang.String)
```

## Parameters

| Name         | Type     | Description                                                        |
| ------------ | -------- | ------------------------------------------------------------------ |
| `actionType` | `String` | The action to unlock — same value previously passed to `+lockKey`. |

## Remarks

Unlocks the specified action by delegating to `LockAlias.unlockAction(actionType)`. Restores vanilla `KeyMapping` bindings and/or removes physical keys from the blocked set.

**Algorithm**: Single delegation call — `LockAlias.unlockAction(actionType)`.

**Side effects**: See `[LockAlias.unlockAction](../LockAlias.java/unlockAction.md)` — restores original `KeyMapping` keys, calls `KeyMapping.resetMapping()`, removes keys from `LOCKED_PHYSICAL_KEYS`.

**Callers**: Invoked by the alias dispatch system when a user types `-lockKey\gameKey:attack` or when a UserAlias definition contains `-lockKey\someAction`.

**Error handling**: All validation and error handling is inside `LockAlias.unlockAction()`. If the action was not locked, the call is a no-op.

## See Also

| Item                                                        | Description              |
| ----------------------------------------------------------- | ------------------------ |
| [LockAlias.unlockAction](../LockAlias.java/unlockAction.md) | Delegated implementation |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_

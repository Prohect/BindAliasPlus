# unlockAction method (src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java)

## Syntax

```java
static void unlockAction(java.lang.String)
```

## Parameters

| Name         | Type     | Description                                                              |
| ------------ | -------- | ------------------------------------------------------------------------ |
| `actionType` | `String` | The action type previously locked (e.g. `gameKey:attack` or alias name). |

## Remarks

Restores a previously locked game action or alias to its original state.

**Algorithm**:

1. Look up the vanilla `KeyMapping` via `getKeyBindingForAction(actionType)`.
2. If found: retrieve and remove the saved key from `savedBoundKeys`, remove it from `LOCKED_PHYSICAL_KEYS`, restore `keyBinding.key` to the saved value, call `KeyMapping.resetMapping()`, then call `unlockModBoundKeys(actionType)`.
3. If not a vanilla action: delegate to `unlockAliasByName(actionType)`.

**Side effects**: Restores vanilla `KeyMapping.key` to its original value. Logs info/warn messages.

**Callers**: `run()` (when flag == 0), `LockAlias_Unlock.run()`, `clearAllLocks()`.

**Error handling**: If no saved key exists for the action type, the method is a no-op (the action was not locked).

## See Also

| Item                                        | Description                             |
| ------------------------------------------- | --------------------------------------- |
| [lockAction](lockAction.md)                 | Inverse operation                       |
| [unlockAliasByName](unlockAliasByName.md)   | Fallback for non-vanilla actions        |
| [unlockModBoundKeys](unlockModBoundKeys.md) | Unblocks mod-bound keys for this action |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_

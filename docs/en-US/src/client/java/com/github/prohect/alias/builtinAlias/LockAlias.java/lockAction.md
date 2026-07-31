# lockAction method (src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java)

## Syntax

```java
static void lockAction(java.lang.String)
```

## Parameters

| Name         | Type     | Description                                                                                 |
| ------------ | -------- | ------------------------------------------------------------------------------------------- |
| `actionType` | `String` | Either a `gameKey:`-prefixed vanilla action (e.g. `gameKey:attack`) or a custom alias name. |

## Remarks

Locks a game action by replacing its vanilla `KeyMapping` key with a sentinel (`InputConstants.UNKNOWN`), or falls back to alias-name locking if the action is not a recognized vanilla key.

**Algorithm**:

1. Look up the vanilla `KeyMapping` via `getKeyBindingForAction(actionType)`.
2. If found and not already locked: save the original key in `savedBoundKeys`, add it to `LOCKED_PHYSICAL_KEYS`, replace `keyBinding.key` with `LOCK_PLACEHOLDER`, call `KeyMapping.resetMapping()`, then call `lockModBoundKeys(actionType)` to also block mod-bound keys targeting this action.
3. If not a vanilla action: delegate to `lockAliasByName(actionType)`.

**Side effects**: Mutates a vanilla `KeyMapping.key` field directly. Logs info/warn messages.

**Callers**: `run()` (when flag == 1), `LockAlias_OnLock.run()`.

**Error handling**: Idempotent — if `savedBoundKeys` already contains the action type, no action is taken (prevents double-locking).

## See Also

| Item                                                | Description                           |
| --------------------------------------------------- | ------------------------------------- |
| [unlockAction](unlockAction.md)                     | Inverse operation                     |
| [lockAliasByName](lockAliasByName.md)               | Fallback for non-vanilla actions      |
| [getKeyBindingForAction](getKeyBindingForAction.md) | Maps action name to KeyMapping        |
| [lockModBoundKeys](lockModBoundKeys.md)             | Blocks mod-bound keys for this action |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_

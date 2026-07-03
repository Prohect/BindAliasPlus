# clearAllLocks method (src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java)

## Syntax

```java
public static void clearAllLocks()
```

## Parameters

| Name     | Type | Description |
| -------- | ---- | ----------- |
| _(none)_ |      |             |

## Remarks

Restores all locked game keys and alias-bound keys to their original state, then clears all internal lock-tracking data structures.

**Algorithm**:

1. Iterate over a snapshot of `savedBoundKeys` keys and call `unlockAction(actionType)` for each.
2. Iterate over a snapshot of `LOCKED_ALIAS_KEYS` keys and call `unlockAliasByName(aliasName)` for each.
3. Clear `LOCKED_PHYSICAL_KEYS`, `savedBoundKeys`, and `LOCKED_ALIAS_KEYS`.

**Side effects**: Restores all vanilla `KeyMapping` instances to their original bound keys, calls `KeyMapping.resetMapping()`, removes all entries from the three static maps/sets.

**Callers**: Called on server disconnect (via `ClientPlayConnectionEvents.DISCONNECT`) to prevent stale key bindings from persisting after leaving a world.

**Error handling**: Uses snapshot copies of key sets to avoid `ConcurrentModificationException` during iteration while removing.

Return value: Returns nothing (`void`).

## See Also

| Item                                      | Description                         |
| ----------------------------------------- | ----------------------------------- |
| [unlockAction](unlockAction.md)           | Called for each saved game-key lock |
| [unlockAliasByName](unlockAliasByName.md) | Called for each alias-name lock     |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_

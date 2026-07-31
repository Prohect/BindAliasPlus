# unlockAction method (src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java)

Unlocks a previously locked vanilla game key or custom user alias. Shared by `LockAlias` and `LockAlias_Unlock`.

## Syntax

```java
static void unlockAction(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `actionType` | `String` | A game-key action (e.g., `"attack"`, `"gameKey:forward"`) or custom alias name |

## Remarks

1. Looks up the `KeyMapping` for the action via `getKeyBindingForAction(actionType)`.
2. **If a vanilla KeyMapping is found:**
   - Removes the saved key from `savedBoundKeys`. If no saved key exists (action wasn't locked), the method is a no-op for the rest.
   - Removes the saved key from `LOCKED_PHYSICAL_KEYS`.
   - Restores the original key: `keyBinding.key = savedKey`.
   - Calls `KeyMapping.resetMapping()` to apply the restoration.
   - Calls `unlockModBoundKeys(actionType)` to remove mod-bound key locks, but only if those keys are not still needed by another locked action.
3. **If no vanilla KeyMapping is found (null):**
   - Falls through to `unlockAliasByName(actionType)` — treats `actionType` as a custom user alias name and unlocks its physical keys.

## See Also

| Item | Description |
|------|-------------|
| [lockAction](lockAction.md) | Inverse: locks a key/alias |
| [unlockAliasByName](unlockAliasByName.md) | Unlocks keys bound to a custom alias name |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*

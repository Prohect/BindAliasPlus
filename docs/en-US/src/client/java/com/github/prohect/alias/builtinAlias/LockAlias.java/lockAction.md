# lockAction method (src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java)

Locks a vanilla game key or custom user alias. Shared by `LockAlias`, `LockAlias_OnLock`, and `LockAlias_Unlock`.

## Syntax

```java
static void lockAction(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `actionType` | `String` | A game-key action (e.g., `"attack"`, `"gameKey:forward"`) or custom alias name |

## Remarks

1. Looks up the `KeyBinding` for the action via `getKeyBindingForAction(actionType)`.
2. **If a vanilla KeyBinding is found:**
   - Checks if the action is already locked (`savedBoundKeys.containsKey(actionType)`). If so, returns (no-op — idempotent).
   - Saves the original key: `savedBoundKeys.put(actionType, keyBinding.key)`.
   - Adds the original key to `LOCKED_PHYSICAL_KEYS`.
   - Replaces the KeyBinding's key with `LOCK_PLACEHOLDER` (`InputUtil.UNKNOWN_KEY`).
   - Calls `KeyBinding.updateKeysByCode()` to apply the change system-wide.
   - Calls `lockModBoundKeys(actionType)` to also block any mod-bound keys whose alias targets this action.
3. **If no vanilla KeyBinding is found (null):**
   - Falls through to `lockAliasByName(actionType)` — treats `actionType` as a custom user alias name and locks all physical keys bound to it.

## See Also

| Item | Description |
|------|-------------|
| [unlockAction](unlockAction.md) | Inverse: unlocks a key/alias |
| [lockAliasByName](lockAliasByName.md) | Locks keys bound to a custom alias name |
| [getKeyBindingForAction](getKeyBindingForAction.md) | Maps action type to KeyBinding |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*

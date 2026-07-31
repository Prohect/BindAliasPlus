# unlockModBoundKeys method (src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java)

Removes physical key blocks from `LOCKED_PHYSICAL_KEYS` that were previously added by `lockModBoundKeys()` — but only if those keys are not still needed by another locked action.

## Syntax

```java
private static void unlockModBoundKeys(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `actionType` | `String` | The bare game action name being unlocked (e.g., `"attack"`, `"forward"`) |

## Remarks

**Algorithm:**

1. Looks up the alias-name patterns for `actionType` from `ACTION_ALIAS_PATTERNS`. If no patterns, returns.
2. Builds a set of keys to potentially remove by iterating `BINDING_PLUS` and checking via `aliasTargetsLockedAction()`.
3. For each candidate key:
   - Checks every **other** still-locked action (`savedBoundKeys.keySet()` excluding `actionType`) to see if that action also needs this key blocked.
   - If any other action still needs the key (its patterns match the binding), the key is **not** removed — it stays in `LOCKED_PHYSICAL_KEYS`.
   - Only if no other locked action needs the key is it removed from `LOCKED_PHYSICAL_KEYS`.

This prevents premature key unblocking when multiple game actions are locked simultaneously and share mod-bound keys.

## See Also

| Item | Description |
|------|-------------|
| [lockModBoundKeys](lockModBoundKeys.md) | Inverse: blocks mod-bound keys |
| [aliasTargetsLockedAction](aliasTargetsLockedAction.md) | Recursive check through UserAlias definitions |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*

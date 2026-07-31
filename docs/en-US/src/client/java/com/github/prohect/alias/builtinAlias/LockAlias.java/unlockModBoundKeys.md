# unlockModBoundKeys method (src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java)

## Syntax

```java
private static void unlockModBoundKeys(java.lang.String)
```

## Parameters

| Name         | Type     | Description                                                                               |
| ------------ | -------- | ----------------------------------------------------------------------------------------- |
| `actionType` | `String` | The bare action name (without `gameKey:` prefix) whose mod-bound keys should be unlocked. |

## Remarks

Unlocks mod-registered keys previously locked for the given action type. Carefully avoids removing keys that are still needed by other active locks.

**Algorithm**:

1. Look up the alias patterns for `actionType` from `ACTION_ALIAS_PATTERNS`.
2. Iterate `BINDING_PLUS`, collecting all keys whose bindings target the action (via `aliasTargetsLockedAction()`).
3. For each collected key, check whether any _other_ still-locked action (from `savedBoundKeys`) also needs this key. If so, skip removal.
4. Remove keys from `LOCKED_PHYSICAL_KEYS` that are not still needed.

**Side effects**: Removes keys from `LOCKED_PHYSICAL_KEYS` only if no other lock requires them.

**Callers**: `unlockAction()`.

**Error handling**: Returns immediately if no patterns exist for the action type. The `stillNeeded` check prevents prematurely unlocking keys that serve multiple locked actions.

## See Also

| Item                                                    | Description                                     |
| ------------------------------------------------------- | ----------------------------------------------- |
| [lockModBoundKeys](lockModBoundKeys.md)                 | Inverse operation                               |
| [aliasTargetsLockedAction](aliasTargetsLockedAction.md) | Determines whether a binding targets the action |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_

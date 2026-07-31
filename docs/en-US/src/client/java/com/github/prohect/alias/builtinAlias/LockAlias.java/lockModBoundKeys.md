# lockModBoundKeys method (src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java)

## Syntax

```java
private static void lockModBoundKeys(java.lang.String)
```

## Parameters

| Name         | Type     | Description                                                                             |
| ------------ | -------- | --------------------------------------------------------------------------------------- |
| `actionType` | `String` | The bare action name (without `gameKey:` prefix) whose mod-bound keys should be locked. |

## Remarks

Locks all mod-registered keys (in `BindAliasClient.BINDING_PLUS`) whose bound alias targets the given action type. This ensures that when a vanilla key like "attack" is locked, any custom keybinding that also triggers attack (directly or through `+attack`/`builtinAttack` aliases) is also blocked.

**Algorithm**:

1. Look up the alias patterns for `actionType` from `ACTION_ALIAS_PATTERNS`.
2. Iterate `BINDING_PLUS`, checking each binding's `aliasNameOnKeyPressed()` and `aliasNameOnKeyReleased()` against the patterns via `aliasTargetsLockedAction()`.
3. Add matching keys to `LOCKED_PHYSICAL_KEYS`.

**Side effects**: Adds keys to `LOCKED_PHYSICAL_KEYS`.

**Callers**: `lockAction()`.

**Error handling**: Returns immediately if no patterns exist for the action type.

## See Also

| Item                                                    | Description                                            |
| ------------------------------------------------------- | ------------------------------------------------------ |
| [unlockModBoundKeys](unlockModBoundKeys.md)             | Inverse operation                                      |
| [aliasTargetsLockedAction](aliasTargetsLockedAction.md) | Determines whether a binding targets the locked action |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_

# lockAliasByName method (src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java)

## Syntax

```java
static void lockAliasByName(java.lang.String)
```

## Parameters

| Name        | Type     | Description                                                       |
| ----------- | -------- | ----------------------------------------------------------------- |
| `aliasName` | `String` | Name of the UserAlias whose bound physical keys should be locked. |

## Remarks

Locks all physical keyboard/mouse keys that are bound to the given alias name in `BindAliasPlusClient.BINDING_PLUS`. The alias can still be triggered programmatically via `runAlias` — only physical input is blocked.

**Algorithm**:

1. If already locked (`LOCKED_ALIAS_KEYS` contains the name), return immediately (idempotent).
2. Iterate `BINDING_PLUS`, collecting every key whose `aliasNameOnKeyPressed()` or `aliasNameOnKeyReleased()` matches `aliasName`.
3. If no keys found: log a warning and return.
4. Add all collected keys to `LOCKED_PHYSICAL_KEYS` and store the mapping in `LOCKED_ALIAS_KEYS`.

**Side effects**: Populates `LOCKED_PHYSICAL_KEYS` and `LOCKED_ALIAS_KEYS`. Logs info/warn messages.

**Callers**: `lockAction()` (when actionType is not a recognized vanilla key).

**Error handling**: Logs warning if no keys are bound to the alias. Idempotent on repeated calls.

## See Also

| Item                                      | Description                                         |
| ----------------------------------------- | --------------------------------------------------- |
| [unlockAliasByName](unlockAliasByName.md) | Inverse operation                                   |
| [lockAction](lockAction.md)               | Caller — falls back to this for non-vanilla actions |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_

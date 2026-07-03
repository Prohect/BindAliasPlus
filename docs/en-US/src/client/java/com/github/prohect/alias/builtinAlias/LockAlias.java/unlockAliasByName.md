# unlockAliasByName method (src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java)

## Syntax

```java
static void unlockAliasByName(java.lang.String)
```

## Parameters

| Name        | Type     | Description                                             |
| ----------- | -------- | ------------------------------------------------------- |
| `aliasName` | `String` | Name of the alias whose locked keys should be restored. |

## Remarks

Restores physical keyboard/mouse keys that were previously locked for the given alias name.

**Algorithm**:

1. Remove the entry from `LOCKED_ALIAS_KEYS`. If null (alias was not locked), log a warning and return.
2. Remove all associated keys from `LOCKED_PHYSICAL_KEYS`.

**Side effects**: Removes entries from `LOCKED_PHYSICAL_KEYS` and `LOCKED_ALIAS_KEYS`. Logs info/warn messages.

**Callers**: `unlockAction()` (when actionType is not a recognized vanilla key), `clearAllLocks()`.

**Error handling**: Logs warning if the alias was not locked (null return from `LOCKED_ALIAS_KEYS.remove()`).

## See Also

| Item                                  | Description                                         |
| ------------------------------------- | --------------------------------------------------- |
| [lockAliasByName](lockAliasByName.md) | Inverse operation                                   |
| [unlockAction](unlockAction.md)       | Caller — falls back to this for non-vanilla actions |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_

# unlockAliasByName method (src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java)

Unlocks physical keys previously locked for a given user alias name.

## Syntax

```java
static void unlockAliasByName(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `aliasName` | `String` | The name of the user alias whose physical key bindings should be unlocked |

## Remarks

1. Removes the alias name from `LOCKED_ALIAS_KEYS`. If the alias wasn't locked (null return), logs a warning and returns.
2. Removes all associated keys from `LOCKED_PHYSICAL_KEYS`.
3. Logs success with the alias name and number of keys restored.

After unlocking, pressing the physical keys bound to this alias will once again trigger the alias normally.

## See Also

| Item | Description |
|------|-------------|
| [lockAliasByName](lockAliasByName.md) | Inverse: locks keys for an alias |
| [unlockAction](unlockAction.md) | Unlocks a vanilla game key |
| [LOCKED_PHYSICAL_KEYS](LOCKED_PHYSICAL_KEYS.md) | Set checked by mixins |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*

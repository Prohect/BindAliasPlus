# lockAliasByName method (src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java)

Locks all physical keys bound to a given user alias name. The alias can still be triggered programmatically via `builtinRunAlias`.

## Syntax

```java
static void lockAliasByName(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `aliasName` | `String` | The name of the user alias whose physical key bindings should be locked |

## Remarks

1. Checks if the alias is already locked (`LOCKED_ALIAS_KEYS.containsKey(aliasName)`). If so, returns immediately.
2. Iterates over `BindAliasClient.BINDING_PLUS` to find all physical keys whose `aliasNameOnKeyPressed()` or `aliasNameOnKeyReleased()` matches `aliasName`.
3. If no keys are found, logs a warning and returns — the alias has no physical bindings.
4. Adds the found keys to `LOCKED_PHYSICAL_KEYS` (the set checked by mixins to block input).
5. Records the mapping in `LOCKED_ALIAS_KEYS` for later unlock.
6. Logs success with the alias name and number of keys blocked.

After locking, pressing the physical keys bound to this alias has no effect, but the alias can still be executed via `builtinRunAlias`.

## See Also

| Item | Description |
|------|-------------|
| [unlockAliasByName](unlockAliasByName.md) | Inverse: unlocks keys for an alias |
| [lockAction](lockAction.md) | Locks a vanilla game key |
| [LOCKED_PHYSICAL_KEYS](LOCKED_PHYSICAL_KEYS.md) | Set checked by mixins |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*

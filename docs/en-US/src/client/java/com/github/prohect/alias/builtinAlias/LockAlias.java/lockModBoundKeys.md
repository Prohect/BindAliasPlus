# lockModBoundKeys method (src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java)

Blocks physical keys in `BINDING_PLUS` whose bound alias targets a locked game action.

## Syntax

```java
private static void lockModBoundKeys(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `actionType` | `String` | The bare game action name (e.g., `"attack"`, `"forward"`) |

## Remarks

When a vanilla game key is locked, the player could still trigger the action indirectly through a custom mod key binding — for example, a `BINDING_PLUS` entry that maps the R key to `+attack`. This method prevents that bypass.

**Algorithm:**

1. Looks up the alias-name patterns for `actionType` from `ACTION_ALIAS_PATTERNS` (e.g., `["+attack", "-attack", "builtinAttack"]`). If no patterns, returns.
2. Iterates `BindAliasClient.BINDING_PLUS` and for each key/binding pair:
   - Checks if `binding.aliasNameOnKeyPressed()` matches any pattern (meaning pressing this key triggers the locked action).
   - Checks if `binding.aliasNameOnKeyReleased()` matches any pattern (meaning releasing this key triggers the locked action).
   - Also checks these names through `aliasTargetsLockedAction()` which recursively examines `UserAlias` definitions — if a user alias's definition contains a locked action, its key is also blocked.
3. Adds any matching physical keys to `LOCKED_PHYSICAL_KEYS`.

## See Also

| Item | Description |
|------|-------------|
| [unlockModBoundKeys](unlockModBoundKeys.md) | Inverse: removes mod-key locks |
| [aliasTargetsLockedAction](aliasTargetsLockedAction.md) | Recursive check through UserAlias definitions |
| [ACTION_ALIAS_PATTERNS](static-init.md) | Pattern map populated by static initializer |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*

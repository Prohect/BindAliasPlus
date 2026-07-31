# SUPPORTED_ACTIONS field (src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java)

Public static list of all supported game-key action types, prefixed with `"gameKey:"` for command suggestions.

## Syntax

```java
public static final java.util.List<java.lang.String> SUPPORTED_ACTIONS
```

## Remarks

Contains: `gameKey:attack`, `gameKey:use`, `gameKey:forward`, `gameKey:back`, `gameKey:left`, `gameKey:right`, `gameKey:jump`, `gameKey:sneak`, `gameKey:sprint`.

This list serves dual purposes:
1. **Command suggestions:** Exposed to the command suggestion system so autocomplete can offer valid lock targets.
2. **Static initializer seed:** Used to populate `ACTION_ALIAS_PATTERNS` which maps each bare action name to its alias-name patterns (`+attack`, `-attack`, `builtinAttack`).

The `gameKey:` prefix is stripped when looking up the actual `KeyMapping` via `getKeyBindingForAction()`.

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*

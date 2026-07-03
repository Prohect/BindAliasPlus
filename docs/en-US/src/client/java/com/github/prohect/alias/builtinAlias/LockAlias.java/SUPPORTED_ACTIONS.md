# SUPPORTED_ACTIONS field (src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java)

## Syntax

```java
public static final java.util.List<java.lang.String> SUPPORTED_ACTIONS
```

## Remarks

An immutable list of supported game-key action type identifiers, each prefixed with `gameKey:` for command suggestion auto-completion. The nine supported values are: `gameKey:attack`, `gameKey:use`, `gameKey:forward`, `gameKey:back`, `gameKey:left`, `gameKey:right`, `gameKey:jump`, `gameKey:sneak`, `gameKey:sprint`.

Used by the static initializer to build `ACTION_ALIAS_PATTERNS` (mapping bare action names to patterns like `+attack`, `-attack`, `builtinAttack`) and by the command suggestion system to offer completions for `+lockKey` / `-lockKey`.

Read-only after class loading. No thread-safety concerns.

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_

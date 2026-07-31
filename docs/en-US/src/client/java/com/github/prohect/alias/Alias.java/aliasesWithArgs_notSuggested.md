# aliasesWithArgs_notSuggested field (src/client/java/com/github/prohect/alias/Alias.java)

## Syntax

```java
public static final HashMap<String, AliasWithArgs<?>> aliasesWithArgs_notSuggested
```

Global map of internal / non-suggested builtin aliases that accept arguments. Same structure as `aliasesWithArgs` but for aliases that should not appear in user-facing command suggestions (e.g. `builtinDrop`, `builtinLock`).

## Remarks

Populated by `BuiltinAliasWithArgs.putToAliasesWithArgs_notSuggested()`. These aliases are still executable — `UserAlias.run()` checks this map during alias-chain execution — but suggestion engines (the `bind` command, autocomplete) skip them.

**Lookup order**: In `UserAlias.run()`, the `_notSuggested` maps are checked **after** the primary maps and **before** the primary with-args map: `aliasesWithoutArgs` → `aliasesWithoutArgs_notSuggested` → `aliasesWithArgs_notSuggested` → `aliasesWithArgs`.

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*

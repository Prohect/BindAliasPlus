# aliasesWithoutArgs_notSuggested field (src/client/java/com/github/prohect/alias/Alias.java)

## Syntax

```java
public static final HashMap<String, AliasWithoutArgs<?>> aliasesWithoutArgs_notSuggested
```

Global map of internal / non-suggested aliases that take no arguments. Same structure as `aliasesWithoutArgs` but for aliases that should not appear in user suggestions (e.g. `LockAlias_OnLock`, `LockAlias_Unlock`, view-switching aliases like `FPS`/`TPS`/`TPS2`).

## Remarks

Populated by `BuiltinAliasWithoutArgs.putToAliasesWithoutArgs_notSuggested()`. Checked second in `UserAlias.run()` lookup order, after `aliasesWithoutArgs`. These aliases execute normally but are hidden from autocomplete.

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*

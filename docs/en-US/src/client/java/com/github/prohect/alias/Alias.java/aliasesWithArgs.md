# aliasesWithArgs field (src/client/java/com/github/prohect/alias/Alias.java)

## Syntax

```java
public static final HashMap<String, AliasWithArgs<?>> aliasesWithArgs
```

Global map of all builtin aliases that accept arguments. Keys are the `builtinAliasName` strings (e.g. `"slot"`, `"yaw"`, `"var"`, `"say"`). Populated by `BuiltinAliasWithArgs.putToAliasesWithArgs()` during client initialization in `BindAliasClient.onInitializeClient()`.

## Remarks

This is the **primary** args-accepting map — from which command suggestions are derived. Aliases here are suggested to the user.

**Readers**: `UserAlias.run()` and `UserAlias.runInternal()` look up aliases from this map when executing alias chains (checked last in the lookup order). The `reapply` alias iterates all boolean-arg aliases across both this and `aliasesWithArgs_notSuggested`. The `KeyBoardMixin` routes key events to aliases found in this and the `withoutArgs` maps.

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*

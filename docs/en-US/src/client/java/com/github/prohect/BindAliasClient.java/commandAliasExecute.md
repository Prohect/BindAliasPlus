# commandAliasExecute method (src/client/java/com/github/prohect/BindAliasClient.java)

## Syntax

```java
private int commandAliasExecute(java.lang.String, java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `aliasName` | `String` | The alias name to create |
| `definition` | `String` | The alias chain definition string (space-separated invocations with `\` arg delimiters) |

## Remarks

Creates or redefines a user alias. Guards ensure builtin aliases cannot be overwritten:
1. If `aliasName` matches an args-accepting alias (either public or `_notSuggested`), returns `2`.
2. If `aliasName` matches a no-args alias that is not a `UserAlias`, returns `3`.
3. If `aliasName` matches a `UserAlias` that is `predefined` (created by `onInitializeClient` for `+`/`-` wrappers), returns `3`.
4. Otherwise, puts a new `UserAlias(definition, fromAutoload)` into `aliasesWithoutArgs` and returns `1`.

The private 3-arg overload adds `fromAutoload` tracking for CFG-origin aliases so `unloadCFGAliases` can clean them.

## See Also

| Item | Description |
|------|-------------|
| [UserAlias](../alias/UserAlias.java/UserAlias.md) | The user-defined alias stored in `aliasesWithoutArgs` |
| [Alias](../alias/Alias.java/Alias.md) | Registration maps checked for existing entries |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*

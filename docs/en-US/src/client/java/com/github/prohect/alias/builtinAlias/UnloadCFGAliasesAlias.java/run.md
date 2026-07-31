# run method (src/client/java/com/github/prohect/alias/builtinAlias/UnloadCFGAliasesAlias.java)

Removes all user aliases that were loaded from the configuration file.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.UnloadCFGAliasesAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| args | String | Unused (one-shot alias, ignored) |

## Remarks

**Algorithm:**

1. Create `toRemove` list.
2. Iterate `Alias.aliasesWithoutArgs`: for each entry where the alias is a `UserAlias` with `isFromCFG() == true`, add its name to `toRemove`.
3. Remove each name from `aliasesWithoutArgs`.
4. If not in silent mode, log the count: `"Removed {count} autoloaded alias(es)"`.

**Return value:** `this` (fluent return).

**Side effects:** Removes CFG-loaded `UserAlias` instances from the global `aliasesWithoutArgs` registry. Runtime-created aliases and builtin aliases are unaffected.

## See Also

| Item | Description |
|------|-------------|
| [UnloadCFGAliasesAlias](UnloadCFGAliasesAlias.md) | Class overview |
| [UnloadUserAliasesAlias](../UnloadUserAliasesAlias.java/run.md) | Remove runtime aliases (opposite) |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*

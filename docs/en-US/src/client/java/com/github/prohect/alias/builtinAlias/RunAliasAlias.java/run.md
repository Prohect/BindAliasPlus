# run method (src/client/java/com/github/prohect/alias/builtinAlias/RunAliasAlias.java)

Splits args into alias name and extra args, then looks up and invokes the alias.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.RunAliasAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| args | String | Alias name optionally followed by `\`-separated extra args (e.g., `"slot\3"`, `"myAlias"`) |

## Remarks

**Algorithm:**

1. If `args` is null or blank, log a warning and return.
2. Split at the first `\` (backslash, `divider4AliasArgs`):
   - Before the split → `aliasName` (trimmed)
   - After the split → `extraArgs` (empty string if no split)
3. Search registries in order: `aliasesWithoutArgs`, `aliasesWithoutArgs_notSuggested`, `aliasesWithArgs`, `aliasesWithArgs_notSuggested`.
4. If an alias is found, call `alias.run(extraArgs)`.
5. If not found, log a warning: `"Unknown alias: {aliasName}"`.

**Return value:** `this` (fluent return — does NOT return the invoked alias's return value).

**Error handling:**
- Null/blank args: logs warning, returns.
- Unknown alias name: logs warning, returns.

**Side effects:** Invokes the resolved alias's `run()` method, which may have any range of side effects depending on the alias.

## See Also

| Item | Description |
|------|-------------|
| [RunAliasAlias](RunAliasAlias.md) | Class overview |
| [UserAlias](../../UserAlias.java/UserAlias.md) | Chain execution (space-separated alias definitions) |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*

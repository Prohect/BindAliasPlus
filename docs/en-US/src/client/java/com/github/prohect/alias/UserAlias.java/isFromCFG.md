# isFromCFG method (src/client/java/com/github/prohect/alias/UserAlias.java)

## Syntax

```java
public boolean isFromCFG()
```

## Return value

`true` if this alias was loaded from the CFG file (auto-load), `false` if created at runtime (via `alias` command) or is builtin.

## Remarks

Used by `unloadCFGAliases` to identify which user aliases to remove — only those with `fromCFG == true` are unloaded. User-created and predefined aliases are preserved.

The flag is set at construction time via the 2-arg or 3-arg constructor, or later via `setFromCFG(boolean)`.

## See Also

| Item | Description |
|------|-------------|
| [setFromCFG](setFromCFG.md) | Setter for this flag |
| [isPredefined](isPredefined.md) | Related protection flag |
| [UnloadCFGAliasesAlias](builtinAlias/UnloadCFGAliasesAlias.java/UnloadCFGAliasesAlias.md) | The builtin that uses this flag |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*

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

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*

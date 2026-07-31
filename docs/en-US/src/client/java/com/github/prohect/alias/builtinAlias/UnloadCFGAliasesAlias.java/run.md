# run method (src/client/java/com/github/prohect/alias/builtinAlias/UnloadCFGAliasesAlias.java)

## Syntax

```java
public com.github.prohect.alias.Alias run(java.lang.String)
```

## Parameters

| Name   | Type     | Description |
| ------ | -------- | ----------- |
| `args` | `String` | Ignored.    |

## Remarks

Removes all user aliases with `fromAutoload=true` from `Alias.aliasesWithoutArgs`.

**Algorithm**:

1. Iterate `Alias.aliasesWithoutArgs`, collecting names of `UserAlias` instances where `userAlias.isFromAutoload()` returns true.
2. Remove each collected name from the map.
3. Log the removal count unless `BindAliasClient.silentMode` is active.

**Side effects**: Removes entries from `Alias.aliasesWithoutArgs`. Does not affect `Alias.aliasesWithArgs` (builtins).

**Callers**: Invoked by the alias dispatch system and by `UnloadCFGAllAlias.run()`.

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_

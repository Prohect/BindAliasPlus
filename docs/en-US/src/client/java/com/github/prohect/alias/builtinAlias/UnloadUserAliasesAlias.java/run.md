# run method (src/client/java/com/github/prohect/alias/builtinAlias/UnloadUserAliasesAlias.java)

Removes all user aliases created at runtime (not from CFG, not predefined).

## Syntax

```java
public com.github.prohect.alias.builtinAlias.UnloadUserAliasesAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| args | String | Unused (one-shot alias, ignored) |

## Remarks

**Algorithm:**

1. Create `toRemove` list.
2. Iterate `Alias.aliasesWithoutArgs`: for each `UserAlias` where `isFromCFG() == false` AND `isPredefined() == false`, add name to `toRemove`.
3. Remove each name from `aliasesWithoutArgs`.
4. If not in silent mode, log count.

**Return value:** `this` (fluent return).

**Side effects:** Removes runtime-created user aliases from the global registry. CFG-loaded and predefined aliases are preserved.

**Filter criteria:** Only aliases that are BOTH not from CFG AND not predefined are removed. This ensures:
- CFG-loaded aliases persist (protected from runtime cleanup).
- Builtin aliases exposed as `UserAlias` with `isPredefined() == true` persist.

## See Also

| Item | Description |
|------|-------------|
| [UnloadUserAliasesAlias](UnloadUserAliasesAlias.md) | Class overview |
| [UnloadCFGAliasesAlias](../UnloadCFGAliasesAlias.java/run.md) | Remove CFG-loaded aliases (opposite) |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*

# run method (src/client/java/com/github/prohect/alias/builtinAlias/UnloadCFGBindsAlias.java)

Removes all keybindings loaded from the configuration file and cleans up associated auto-created aliases.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.UnloadCFGBindsAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| args | String | Unused (one-shot alias, ignored) |

## Remarks

**Algorithm:**

1. Create `toRemove` (keys) and `aliasesToRemove` (alias names) lists.
2. Iterate `BINDING_PLUS`: for each binding with `fromCFG() == true`:
   - Add its key to `toRemove`.
   - If `aliasNameOnKeyPressed()` is non-empty, add to `aliasesToRemove`.
   - If `aliasNameOnKeyReleased()` is non-empty, add to `aliasesToRemove`.
3. Remove each key from `BINDING_PLUS`.
4. Remove each alias name from `aliasesWithoutArgs_fromBindCommand`.
5. If not in silent mode, log the count.

**Return value:** `this` (fluent return).

**Side effects:**
- Removes CFG-loaded keybindings from `BINDING_PLUS`.
- Cleans up associated auto-created aliases from `aliasesWithoutArgs_fromBindCommand`.
- Physical key mappings revert to their default behavior.

## See Also

| Item | Description |
|------|-------------|
| [UnloadCFGBindsAlias](UnloadCFGBindsAlias.md) | Class overview |
| [UnloadUserBindsAlias](../UnloadUserBindsAlias.java/run.md) | Remove runtime bindings (opposite) |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*

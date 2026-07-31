# run method (src/client/java/com/github/prohect/alias/builtinAlias/UnloadUserBindsAlias.java)

Removes all keybindings created at runtime (not from CFG) and cleans up associated aliases.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.UnloadUserBindsAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| args | String | Unused (one-shot alias, ignored) |

## Remarks

**Algorithm:**

1. Create `toRemove` (keys) and `aliasesToRemove` (alias names) lists.
2. Iterate `BINDING_PLUS`: for each binding with `fromCFG() == false`:
   - Add its key to `toRemove`.
   - Collect non-empty `aliasNameOnKeyPressed()` and `aliasNameOnKeyReleased()` names to `aliasesToRemove`.
3. Remove each key from `BINDING_PLUS`.
4. Remove each alias name from `aliasesWithoutArgs_fromBindCommand`.
5. If not in silent mode, log count.

**Return value:** `this` (fluent return).

**Side effects:** Removes runtime-created keybindings and cleans up associated auto-created aliases. CFG-loaded bindings are preserved.

## See Also

| Item | Description |
|------|-------------|
| [UnloadUserBindsAlias](UnloadUserBindsAlias.md) | Class overview |
| [UnloadCFGBindsAlias](../UnloadCFGBindsAlias.java/run.md) | Remove CFG-loaded bindings (opposite) |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*

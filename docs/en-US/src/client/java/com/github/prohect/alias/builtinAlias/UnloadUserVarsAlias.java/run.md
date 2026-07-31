# run method (src/client/java/com/github/prohect/alias/builtinAlias/UnloadUserVarsAlias.java)

Removes all runtime-created variables from both general and container slot variable maps.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.UnloadUserVarsAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| args | String | Unused (one-shot alias, ignored) |

## Remarks

**Algorithm:**

1. Create `toRemoveGeneral` list: all keys in `GENERAL_VARIABLES` not in `CFG_VARIABLES`.
2. Create `toRemoveContainer` list: all keys in `CONTAINER_SLOT_VARIABLES` not in `CFG_CONTAINER_SLOT_VARIABLES`.
3. Remove each `toRemoveGeneral` name from `GENERAL_VARIABLES` — count `generalCount`.
4. Remove each `toRemoveContainer` name from `CONTAINER_SLOT_VARIABLES` — count `containerCount`.
5. If not in silent mode, log: `"Removed {total} runtime variable(s) ({generalCount} general, {containerCount} container_slot)"`.

**Return value:** `this` (fluent return).

**Side effects:** Removes runtime-created general variables and container slot references. CFG-loaded variables in both maps are preserved.

**Key behavior:** Unlike `UnloadCFGVarsAlias`, this also cleans `CONTAINER_SLOT_VARIABLES`, making it the only unload alias that touches container slot references directly.

## See Also

| Item | Description |
|------|-------------|
| [UnloadUserVarsAlias](UnloadUserVarsAlias.md) | Class overview |
| [UnloadCFGVarsAlias](../UnloadCFGVarsAlias.java/run.md) | Remove CFG-loaded vars (opposite, general only) |
| [VarAlias](../VarAlias.java/VarAlias.md) | Variable system |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*

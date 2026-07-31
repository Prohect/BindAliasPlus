# run method (src/client/java/com/github/prohect/alias/builtinAlias/UnloadCFGVarsAlias.java)

Removes all variables that were loaded from the configuration file.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.UnloadCFGVarsAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| args | String | Unused (one-shot alias, ignored) |

## Remarks

**Algorithm:**

1. Create `toRemove` list from all names in `VarAlias.CFG_VARIABLES`.
2. For each name: remove from `GENERAL_VARIABLES`, then remove from `CFG_VARIABLES`.
3. If not in silent mode, log the count.

**Return value:** `this` (fluent return).

**Side effects:** Removes CFG-loaded general variables from the variable storage. Container slot variables are NOT cleaned — they use a separate tracking set (`CFG_CONTAINER_SLOT_VARIABLES`).

**Limitation:** Only cleans `GENERAL_VARIABLES` and `CFG_VARIABLES`. Does NOT clean `CONTAINER_SLOT_VARIABLES` or `CFG_CONTAINER_SLOT_VARIABLES`.

## See Also

| Item | Description |
|------|-------------|
| [UnloadCFGVarsAlias](UnloadCFGVarsAlias.md) | Class overview |
| [UnloadUserVarsAlias](../UnloadUserVarsAlias.java/run.md) | Remove runtime variables (opposite) |
| [VarAlias](../VarAlias.java/VarAlias.md) | Variable system |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*

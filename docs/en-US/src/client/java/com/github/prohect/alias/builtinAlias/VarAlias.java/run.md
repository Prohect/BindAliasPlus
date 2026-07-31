# run method (src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java)

Standard runtime overload — stores a variable without CFG autoload tracking.

## Syntax

```java
public com.github.prohect.alias.builtinAlias.VarAlias run(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| args | String | Format: `"varName\source"` — e.g., `"mySlot\hotbarSlot"`, `"count\itemsOfSlot2"` |

## Remarks

**Algorithm:**

1. Split args by `\` (backslash) via `getDefinitionSplits()`.
2. Validate at least 2 parts (varName and source). If not, log error.
3. Extract and trim `varName` and `source`.
4. Validate `varName` via `isValidVarName()`. Reject if starts with a number.
5. Call `getValueFromSource(source)` to resolve the source to a `Number`.
6. Store in `GENERAL_VARIABLES.put(varName, value)`.
7. Handle container slot source: if source is `cN`, store in `CONTAINER_SLOT_VARIABLES`; otherwise, remove any existing container slot entry for that name.
8. Log info: `"Variable '{name}' set to {value}"`.

**Return value:** `this` (fluent return).

**Side effects:** Stores a variable in `GENERAL_VARIABLES` and potentially `CONTAINER_SLOT_VARIABLES`. Does NOT add to CFG tracking sets.

**No screen suppression:** Works on any screen.

## See Also

| Item | Description |
|------|-------------|
| [VarAlias](VarAlias.md) | Class overview |
| [run (autoload overload)](run.md) | The fromAutoload variant |
| [getValueFromSource](getValueFromSource.md) | Source resolution |
| [isValidVarName](isValidVarName.md) | Name validation |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*

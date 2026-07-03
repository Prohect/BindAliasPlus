# isValidVarName method (src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java)

## Syntax

```java
private boolean isValidVarName(java.lang.String)
```

## Parameters

| Name      | Type     | Description                              |
| --------- | -------- | ---------------------------------------- |
| `varName` | `String` | The candidate variable name to validate. |

## Remarks

Validates that a variable name is acceptable. The only restriction is that the name must not start with a digit (0-9), enforced via the precompiled `Pattern` `STARTS_WITH_NUMBER`. Null or empty names are also rejected.

This restriction exists because `resolveValue()` tries literal number parsing before variable lookup — a variable named `"5"` would be shadowed by the integer 5 and never resolvable as a variable.

**Side effects**: None (pure predicate).

**Callers**: `run()` and `run(String, boolean)` — called before storing a variable.

Return value: `true` if the name is non-null, non-empty, and does not start with a digit.

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_

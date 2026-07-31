# isVariable method (src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java)

## Syntax

```java
public static boolean isVariable(java.lang.String)
```

## Parameters

| Name    | Type     | Description                    |
| ------- | -------- | ------------------------------ |
| `input` | `String` | A string to test. May be null. |

## Remarks

Checks whether a given string names a variable that exists in `VARIABLES`. Returns `false` for null or empty input.

Note that this only checks existence — it does not resolve the value. A string like `"5"` will return `false` even though `resolveValue("5")` would return `5`, because `"5"` is a literal number, not a variable name.

**Side effects**: None (reads `VARIABLES`).

Return value: `true` if `VARIABLES.containsKey(input.trim())`, `false` otherwise.

## See Also

| Item                            | Description                                 |
| ------------------------------- | ------------------------------------------- |
| [VARIABLES](VARIABLES.md)       | Storage map                                 |
| [resolveValue](resolveValue.md) | Value resolution (includes literal parsing) |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_

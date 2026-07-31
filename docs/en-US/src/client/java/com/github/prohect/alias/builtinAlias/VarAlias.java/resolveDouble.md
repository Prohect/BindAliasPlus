# resolveDouble method (src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java)

## Syntax

```java
public static java.lang.Double resolveDouble(java.lang.String)
```

## Parameters

| Name    | Type     | Description                                            |
| ------- | -------- | ------------------------------------------------------ |
| `input` | `String` | A string to resolve — literal number or variable name. |

## Remarks

Convenience method that resolves a string to a `Double` by calling `resolveValue(input)` and returning `n.doubleValue()`. If `resolveValue` returns `null`, this returns `null`.

Useful for aliases that need floating-point values (e.g. pitch/yaw angles, coordinates). Note that even integer variable values (stored as `Integer`) will be widened to `double` by `Number.doubleValue()`.

**Side effects**: None (pure delegation).

**Callers**: Any alias needing double-precision variable resolution.

Return value: The `doubleValue()` of the resolved `Number`, or `null` if unresolvable.

## See Also

| Item                            | Description                 |
| ------------------------------- | --------------------------- |
| [resolveValue](resolveValue.md) | Underlying resolution logic |
| [resolveInt](resolveInt.md)     | Integer variant             |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_

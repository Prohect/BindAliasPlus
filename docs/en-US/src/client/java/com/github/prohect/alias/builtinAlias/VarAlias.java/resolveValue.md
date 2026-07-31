# resolveValue method (src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java)

## Syntax

```java
public static java.lang.Number resolveValue(java.lang.String)
```

## Parameters

| Name    | Type     | Description                                                                                              |
| ------- | -------- | -------------------------------------------------------------------------------------------------------- |
| `input` | `String` | A string that is either a literal number (`"5"`, `"3.14"`) or a variable name (`"mySlot"`). May be null. |

## Remarks

Resolves a string input to a `Number` value. This is the primary lookup method used by other aliases to support variable references in their arguments.

**Algorithm**:

1. If `input` is null or empty, return `null`.
2. Trim whitespace.
3. Try `Integer.parseInt(trimmed)` — if successful, return the `Integer`.
4. Try `Double.parseDouble(trimmed)` — if successful, return the `Double`.
5. If not a literal number, look up `trimmed` in `VARIABLES`. If found, return the stored `Number`.
6. Otherwise return `null`.

**Important precedence**: Literal numbers always take priority over variable names. A variable named `"5"` can never be resolved because `"5"` always parses as the integer 5. This is by design to prevent ambiguity — variable names that are valid numbers are disallowed by `isValidVarName()` which rejects names starting with digits.

**Side effects**: None (reads `VARIABLES` but does not modify it).

**Callers**: Called by [resolveInt](resolveInt.md), [resolveDouble](resolveDouble.md), and externally by [SwapSlotAlias.run](SwapSlotAlias.java/run.md), [SlotAlias.run](SlotAlias.java/run.md), etc.

Return value: The resolved `Number` (Integer or Double), or `null` if unresolvable.

## See Also

| Item                              | Description                        |
| --------------------------------- | ---------------------------------- |
| [resolveInt](resolveInt.md)       | Integer convenience wrapper        |
| [resolveDouble](resolveDouble.md) | Double convenience wrapper         |
| [isVariable](isVariable.md)       | Check if a string names a variable |
| [VARIABLES](VARIABLES.md)         | Storage map                        |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_

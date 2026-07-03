# resolveInt method (src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java)

## Syntax

```java
public static java.lang.Integer resolveInt(java.lang.String)
```

## Parameters

| Name    | Type     | Description                                            |
| ------- | -------- | ------------------------------------------------------ |
| `input` | `String` | A string to resolve — literal number or variable name. |

## Remarks

Convenience method that resolves a string to an `Integer` by calling `resolveValue(input)` and returning `n.intValue()`. If `resolveValue` returns `null`, this returns `null`.

This is the most commonly used resolution method by other aliases ([SwapSlotAlias](SwapSlotAlias.java/SwapSlotAlias.md), [SlotAlias](SlotAlias.java/SlotAlias.md)) since slot indices are always integers.

**Side effects**: None (pure delegation).

**Callers**: [SwapSlotAlias.run](SwapSlotAlias.java/run.md), [SlotAlias.run](SlotAlias.java/run.md), and any alias needing integer variable resolution.

Return value: The `intValue()` of the resolved `Number`, or `null` if unresolvable.

## See Also

| Item                              | Description                 |
| --------------------------------- | --------------------------- |
| [resolveValue](resolveValue.md)   | Underlying resolution logic |
| [resolveDouble](resolveDouble.md) | Double variant              |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_

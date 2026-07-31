# resolveInt method (src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java)

Convenience static resolver that returns the integer value of a resolved input.

## Syntax

```java
public static java.lang.Integer resolveInt(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| input | String | A variable name or numeric string to resolve |

## Remarks

**Algorithm:** Calls `resolveValue(input)`. If the result is non-null, returns `n.intValue()`. Otherwise returns null.

**Return value:** An `Integer` or null.

**Loss of precision:** If the resolved value is a `Double` (e.g., from `var\myAngle\pitch`), `intValue()` truncates the decimal portion. Be aware that floating-point variable values lose precision when used with integer-arg aliases.

**Callers:** `SlotAlias.run()`, `SetPerspectiveAlias.run()` (via `parseArgs()`), `WaitAlias.run()`, `SwapSlotAlias.parseSlotRef()`.

## See Also

| Item | Description |
|------|-------------|
| [resolveValue](resolveValue.md) | The underlying resolver |
| [resolveDouble](resolveDouble.md) | Double variant |
| [SlotAlias](../SlotAlias.java/run.md) | Primary consumer (slot numbers) |
| [WaitAlias](../WaitAlias.java/run.md) | Consumer (tick counts) |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*

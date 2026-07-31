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

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*

# resolveDouble method (src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java)

Convenience static resolver that returns the double value of a resolved input.

## Syntax

```java
public static java.lang.Double resolveDouble(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| input | String | A variable name or numeric string to resolve |

## Remarks

**Algorithm:** Calls `resolveValue(input)`. If the result is non-null, returns `n.doubleValue()`. Otherwise returns null.

**Return value:** A `Double` or null.

**Safe for integers:** Integer values are safely widened to double via `intValue()` → `doubleValue()`. No loss of precision for integer values within the double's exact integer range (±2^53).

**Callers:** `PitchAlias.run()`, `YawAlias.run()`, `SetPitchAlias.run()`, `SetYawAlias.run()` — all via `BuiltinAliasWithDoubleArgs.parseArgs()`.

## See Also

| Item | Description |
|------|-------------|
| [resolveValue](resolveValue.md) | The underlying resolver |
| [resolveInt](resolveInt.md) | Integer variant |
| [PitchAlias](../PitchAlias.java/run.md) | Consumer (pitch rotation) |
| [YawAlias](../YawAlias.java/run.md) | Consumer (yaw rotation) |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*

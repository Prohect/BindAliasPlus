# parseArgs method (src/client/java/com/github/prohect/alias/BuiltinAliasWithDoubleArgs.java)

## Syntax

```java
public void parseArgs(String args)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `args` | `String` | A numeric string (e.g. `"90.5"`) or a variable name resolvable by `VarAlias.resolveDouble()` |

## Remarks

Sets the `flag` field via a two-step resolution:

1. **Variable lookup**: Calls `VarAlias.resolveDouble(args)`. If a variable with that name exists and holds a `Double` value, uses it.
2. **Literal parse**: Falls back to `Double.parseDouble(args)`. On `NumberFormatException`, logs an error via `BindAliasClient.LOGGER` and leaves `flag` at `0.0`.

This allows both literal values (`setYaw\90.5`) and variable-driven values (`var\myYaw\90.5` then `setYaw\myYaw`).

## See Also

| Item | Description |
|------|-------------|
| [flag](flag.md) | The field set by this method |
| [VarAlias.resolveDouble](builtinAlias/VarAlias.java/resolveDouble.md) | Variable-resolution step |
| [BuiltinAliasWithIntegerArgs.parseArgs](BuiltinAliasWithIntegerArgs.java/parseArgs.md) | Integer counterpart |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
